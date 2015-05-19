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
	public static final String VERSION = "Aplha 0.0.2";
	
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
