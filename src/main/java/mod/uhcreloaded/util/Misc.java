/** This file is part of UltraHardcore: Reloaded (abbr. UHCR in following context)
 *  Copyright (C) 2015- 3TUSK
 *
 *  UHCR is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or 
 *  (at your option) any later version.
 *
 *  UHCR is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UHCR.  If not, see <http://www.gnu.org/licenses/>.
 */
package mod.uhcreloaded.util;

import java.util.Iterator;
import java.util.List;

import com.mojang.authlib.GameProfile;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Misc {
	
	public static final String MODID = "UHCReloaded";
	public static final String NAME = "UltraHardcore-Mode: Reloaded";
	public static final String VERSION = "Alpha 0.0.2";
	
	public static String translate(String tag){
		return StatCollector.translateToLocal(tag);
	}

	public static String translate(String tag, Object... format) {
		return StatCollector.translateToLocalFormatted(tag, format);
	}

	/**
	 * The utility method to get the skull item stack from the player's name.
	 * @param owner The skull owner
	 * @return The stack of skull with one item inside
	 */
	public static ItemStack getSkullFromOwner(GameProfile owner) {
		ItemStack skull = new ItemStack(Items.skull, 1, 3);

		NBTTagCompound tag = skull.getSubCompound("SkullOwner", true);
		NBTUtil.writeGameProfile(tag, owner);

		return skull.copy();
	}

	/**
	 * The utility method to get the player's name from the skull item stack.
	 * @param skull The item stack of the skull
	 * @return The name of player, empty if invalid
	 */
	public static String getOwnerFromSkull(ItemStack skull) {
		if (skull.getItem() != Items.skull) return "";
		if (!skull.hasTagCompound()) return "";
		if (skull.getTagCompound().hasKey("SkullOwner", 8)) {
			return skull.getTagCompound().getString("SkullOwner");
		}
		if (skull.getTagCompound().hasKey("SkullOwner", 10)) {
			NBTTagCompound tag = skull.getTagCompound().getCompoundTag("SkullOwner");
			if (tag.hasKey("Name", 8)) {
				return tag.getString("Name");
			}
		}
		return "";
	}

	/**
	 * Add a tooltip for an item stack. It will be auto formatted to reset.
	 * @param stack The original item stack that needs a tool tip
	 * @param tooltip The list of tool tips to be added
	 * @return The new item stack with these tool tip
	 */
	public static ItemStack appendToolTip(ItemStack stack, List<String> tooltip) {
		NBTTagCompound tag = stack.hasTagCompound()? stack.getTagCompound(): new NBTTagCompound();
		boolean needAppend = false;
		NBTTagCompound tag1;
		if (tag.hasKey("display", 10)) {
			tag1 = tag.getCompoundTag("display");
			if (tag.hasKey("Lore", 9))
				needAppend = true;
		} else {
			tag.setTag("display", new NBTTagCompound());
			tag1 = tag.getCompoundTag("display");
		}
		if (!needAppend) {
			tag1.setTag("Lore", new NBTTagList());
		}
		for (String s : tooltip) {
			tag1.getTagList("Lore", 8).appendTag(new NBTTagString(EnumChatFormatting.RESET + s));
		}
		tag.setTag("display", tag1);
		ItemStack output = stack.copy();
		output.setTagCompound(tag);
		return output;
	}

	/**
	 * Remove certain vanilla recipe and smelting.
	 * Has been rewritten.
	 * 
	 * @param item The item whose recipe you want to remove
	 */
	public static void removeRecipe(ItemStack item) {
		for (int i = 0; i < CraftingManager.getInstance().getRecipeList().size(); i++) {
			IRecipe recipe = (IRecipe) CraftingManager.getInstance().getRecipeList().get(i);
			if (recipe.getRecipeOutput()!= null && recipe.getRecipeOutput().copy() == item.copy()) {
				CraftingManager.getInstance().getRecipeList().remove(i--);
			}
		}

		FurnaceRecipes.instance().getSmeltingList().remove(item);
	}

	public static void registerBus(Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
		FMLCommonHandler.instance().bus().register(obj);
	}

	public static void unregisterBus(Object obj) {
		MinecraftForge.EVENT_BUS.unregister(obj);
		FMLCommonHandler.instance().bus().unregister(obj);
	}
}
