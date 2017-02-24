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

package com.github.liachmodded.uhcreloaded.forge.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants.NBT;

import java.util.Iterator;
import java.util.Map;

public class Misc {

    public static final String MODID = "uhcreloaded";
    public static final String NAME = "UltraHardcore:Reloaded";
    public static final String VERSION = "0.1-SNAPSHOT";

    @SuppressWarnings("deprecation")
    public static String translate(String tag, Object... format) {
        return net.minecraft.util.text.translation.I18n.translateToLocalFormatted(tag, format);
    }

    /**
     * The utility method to get the skull item stack from the player's name.
     *
     * @param owner The skull owner
     * @return The stack of skull with one item inside
     */
    public static ItemStack getSkullFromOwner(GameProfile owner) {
        ItemStack skull = new ItemStack(Items.SKULL, 1, 3);

        NBTTagCompound tag = skull.getOrCreateSubCompound("SkullOwner");
        NBTUtil.writeGameProfile(tag, owner);

        return skull;
    }

    /**
     * The utility method to get the player's name from the skull item stack.
     *
     * @param skull The item stack of the skull
     * @return The name of player, empty if invalid
     */
    public static String getOwnerFromSkull(ItemStack skull) {
        if (skull.getItem() != Items.SKULL) {
            return "";
        }
        if (!skull.hasTagCompound()) {
            return "";
        }
        if (skull.getTagCompound().hasKey("SkullOwner", NBT.TAG_STRING)) {
            return skull.getTagCompound().getString("SkullOwner");
        }
        if (skull.getTagCompound().hasKey("SkullOwner", NBT.TAG_COMPOUND)) {
            NBTTagCompound tag = skull.getTagCompound().getCompoundTag("SkullOwner");
            if (tag.hasKey("Name", NBT.TAG_STRING)) {
                return tag.getString("Name");
            }
        }
        return "";
    }

    /**
     * Add a tooltip for an item stack. It will be auto formatted to reset.
     *
     * @param stack   The original item stack that needs a tool tip
     * @param tooltip The list of tool tips to be added
     * @return The new item stack with these tool tip
     */
    public static ItemStack appendToolTip(ItemStack stack, Iterable<String> tooltip) {
        NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        boolean needAppend = false;
        NBTTagCompound tag1;
        if (tag.hasKey("display", NBT.TAG_COMPOUND)) {
            tag1 = tag.getCompoundTag("display");
            if (tag.hasKey("Lore", NBT.TAG_LIST)) {
                needAppend = true;
            }
        } else {
            tag.setTag("display", new NBTTagCompound());
            tag1 = tag.getCompoundTag("display");
        }
        if (!needAppend) {
            tag1.setTag("Lore", new NBTTagList());
        }
        for (String s : tooltip) {
            tag1.getTagList("Lore", NBT.TAG_LIST).appendTag(new NBTTagString(TextFormatting.RESET + s));
        }
        tag.setTag("display", tag1);
        ItemStack output = stack.copy();
        output.setTagCompound(tag);
        return output;
    }

    /**
     * Remove certain vanilla recipe and smelting. Has been rewritten.
     *
     * @param item The item whose recipe you want to remove
     */
    public static void removeRecipe(ItemStack item) {
        Iterator<IRecipe> recipeIterator = CraftingManager.getInstance().getRecipeList().iterator();
        while (recipeIterator.hasNext()) {
            IRecipe recipe = recipeIterator.next();
            if (recipe.getRecipeOutput().isItemEqual(item)) {
                recipeIterator.remove();
            }
        }

        Iterator<Map.Entry<ItemStack, ItemStack>> smeltingIterator = FurnaceRecipes.instance().getSmeltingList().entrySet().iterator();
        while (smeltingIterator.hasNext()) {
            Map.Entry<ItemStack, ItemStack> entry = smeltingIterator.next();
            if (entry.getKey().isItemEqual(item)) {
                smeltingIterator.remove();
            }
        }
    }

    public static void registerBus(Object obj) {
        MinecraftForge.EVENT_BUS.register(obj);
    }

    @SuppressWarnings("unused")
    public static String itemStackInfo(ItemStack stack) {
        if (stack.isEmpty()) {
            return "ItemStack.EMPTY";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Item: ");
        sb.append(stack.getItem().getRegistryName());
        sb.append("; Amount: ");
        sb.append(stack.getCount());
        sb.append(";");
        return sb.toString();
    }

}
