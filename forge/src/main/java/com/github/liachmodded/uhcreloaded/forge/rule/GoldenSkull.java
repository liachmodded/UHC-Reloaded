/*
 * The MIT License (MIT)
 *
 * Copyright (c) liachmodded <https://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.liachmodded.uhcreloaded.forge.rule;

import static com.github.liachmodded.uhcreloaded.forge.util.Misc.appendToolTip;
import static com.github.liachmodded.uhcreloaded.forge.util.Misc.getOwnerFromSkull;
import static com.github.liachmodded.uhcreloaded.forge.util.Misc.translate;

import com.github.liachmodded.uhcreloaded.forge.util.BasicRecipe;
import com.github.liachmodded.uhcreloaded.forge.util.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Skull.
 *
 * @author liach
 */
public final class GoldenSkull {

    public static final GoldenSkull INSTANCE = new GoldenSkull();

    private GoldenSkull() {
    }

    @SubscribeEvent
    public void eatApple(LivingEntityUseItemEvent.Start event) {
        if (event.item.getItem() != Items.golden_apple || !(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entity;
        if (ConfigHandler.antiCheatMode && event.item.getItemDamage() == 1) {
            player.addChatMessage(new TextComponentString(translate("message.uhcreloaded.apple.enchanted")));
            event.setCanceled(true);
            return;
        }
        if (!event.item.hasTagCompound()) {
            return;
        }
        NBTTagCompound tag = event.item.getTagCompound();
        if (tag.getInteger("golden_skull") == 1) {
            player.addPotionEffect(new PotionEffect(
                    Potion.getPotionById(6), 1, ConfigHandler.healAmountSkull - 4
            ));
        }
    }

    /**
     * The recipe for the golden skull.
     */
    public static class SkullRecipe extends BasicRecipe {

        public static final SkullRecipe INSTANCE = new SkullRecipe();

        private final ItemStack sample;

        private SkullRecipe() {
            this.sample = new ItemStack(Items.golden_apple);
            NBTTagCompound tag = this.sample.getTagCompound();
            tag.setByte("golden_skull", (byte) 1);
        }

        /**
         * The checking method.
         *
         * @param grid The crafting grid
         * @return The result of the recipe, or {@code null} if no matching.
         */
        @Override
        @Nullable
        public ItemStack getCraftingResult(InventoryCrafting grid) {
            ItemStack outputHead = this.sample.copy();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.getStackInRowAndColumn(i, j) == null) {
                        return null;
                    }
                    ItemStack stack = grid.getStackInRowAndColumn(i, j);
                    if (i == 1 && j == 1) {
                        if (stack.getItem() != Items.skull || stack.getItemDamage() != 3) {
                            return null;
                        }
                        outputHead.setTagCompound(stack.getTagCompound());
                    } else {
                        if (!OreDictionary.itemMatches(stack, new ItemStack(Items.gold_ingot), false)) {
                            return null;
                        }
                    }
                }
            }
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(TextFormatting.ITALIC + translate("tooltip.uhcreloaded.skull"));
            outputHead = appendToolTip(outputHead, lore);
            if (!getOwnerFromSkull(outputHead).isEmpty()) {
                outputHead.setStackDisplayName(translate(
                        TextFormatting.GOLD + "item.uhcreloaded.ownedskull.name",
                        getOwnerFromSkull(outputHead)
                ));
            } else {
                outputHead.setStackDisplayName(translate(
                        TextFormatting.GOLD + "item.uhcreloaded.skullapple.name"
                ));
            }
            return outputHead;
        }

        /**
         * For easy recipe disabling.
         *
         * @return The sample head
         */
        @Override
        public ItemStack getRecipeOutput() {
            return this.sample;
        }
    }
}
