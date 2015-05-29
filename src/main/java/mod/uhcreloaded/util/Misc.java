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

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class Misc {
	
	public static final String MODID = "UHCReloaded";
	public static final String NAME = "UltraHardcore-Mode: Reloaded";
	public static final String VERSION = "Alpha 0.0.2";
	
	public static String translate(String tag){
		return StatCollector.translateToLocal(tag);
	}

	public static ItemStack getSkullWithOwner(String OwnerName) {
		ItemStack skull = new ItemStack(Items.skull, 1, 3);

		NBTTagCompound tag = skull.getSubCompound("SkullOwner", true);
		tag.setString("Name", OwnerName);

		return skull.copy();
	}

	/**
	 * Remove certain vanilla recipe. 
	 * Credit to some code from Railcraft by CovertJaguar
	 * 
	 * @param item The item whose recipe you want to remove
	 */
	public static void removeRecipe(ItemStack item) {
		CraftingManager craft = CraftingManager.getInstance();

		@SuppressWarnings("rawtypes")
		Iterator iter = craft.getRecipeList().iterator();

		while (iter.hasNext()) {
			IRecipe recipe = (IRecipe) iter.next();
			ItemStack targetOutput = recipe.getRecipeOutput();

			if (targetOutput != null) {
				if (targetOutput.isItemEqual(item)) {
					iter.remove();
				}
			}
		}
	}
}
