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

package mod.uhcreloaded.rules;

import static mod.uhcreloaded.util.Misc.appendToolTip;
import static mod.uhcreloaded.util.Misc.getOwnerFromSkull;
import static mod.uhcreloaded.util.Misc.translate;

import mod.uhcreloaded.util.BasicRecipe;
import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Skull.
 *
 * @author liach
 */
public class GoldenSkull {

    @SubscribeEvent
    public void eatApple(PlayerUseItemEvent.Start event) {
        if (event.item.getItem() != Items.golden_apple) {
            return;
        }
        if (ConfigHandler.antiCheatMode && event.item.getItemDamage() == 1) {
            event.entityPlayer.addChatMessage(new ChatComponentText(translate("message.uhcreloaded.apple.enchanted")));
            event.setCanceled(true);
            return;
        }
        if (!event.item.hasTagCompound()) {
            return;
        }
        NBTTagCompound tag = event.item.getTagCompound();
        if (tag.getInteger("golden_skull") == 1) {
            event.entityPlayer.addPotionEffect(new PotionEffect(
                    6, 1, ConfigHandler.healAmountSkull - 4
            ));
        }
    }

    /**
     * The recipe for the golden skull.
     */
    public static class SkullRecipe extends BasicRecipe {

        /**
         * The checking method.
         * @param grid The crafting grid
         * @return The result of the recipe. Returns {@code null} if not matching.
         */
        @Override
        public ItemStack getCraftingResult(InventoryCrafting grid) {
            ItemStack outputHead = new ItemStack(Items.golden_apple);
            outputHead.setItemDamage(0);
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
            lore.add(EnumChatFormatting.ITALIC + translate("tooltip.uhcreloaded.skull"));
            outputHead = appendToolTip(outputHead, lore);
            if (!getOwnerFromSkull(outputHead).isEmpty()) {
                outputHead.setStackDisplayName(translate(
                        EnumChatFormatting.GOLD + "item.uhcreloaded.ownedskull.name",
                        getOwnerFromSkull(outputHead)
                ));
            } else {
                outputHead.setStackDisplayName(translate(
                        EnumChatFormatting.GOLD + "item.uhcreloaded.skullapple.name"
                ));
            }
            NBTTagCompound tag = outputHead.getTagCompound();
            tag.setInteger("golden_skull", 1);
            outputHead.setTagCompound(tag);
            return outputHead;
        }
    }
}
