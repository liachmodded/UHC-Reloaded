package mod.uhcreloaded.util;

import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class Misc {
	
	public static final String MODID = "UHCReload";
	public static final String NAME = "UltraHardcore-Mode: Reload";
	public static final String VERSION = "Aplha 0.0.2";
	
	public static String translate(String tag){
		return StatCollector.translateToLocal(tag);
	}

	/**
	 * This method is just to get a skull with owner's name
	 * 
	 * @param OwnerName
	 *            the name of owner you want to set
	 * @return the skull with owner name
	 * @author 3tusk
	 */
	public static ItemStack getSkullWithOwner(String OwnerName) {
		ItemStack skull = new ItemStack(Items.skull, 1, 3);

		NBTTagCompound tag = skull.getSubCompound("SkullOwner", true);
		tag.setString("Name", OwnerName);

		ItemStack output = skull.copy();

		return output;
	}

	/**
	 * Remove certain vanilla recipe. Credit to some code from railcraft by
	 * CovertJaguar
	 * 
	 * @param item The item whose recipe you want to remove
	 * @author 3tusk
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

	/**
	 * to check whether certain stacks has target item. So far it's unused.
	 * 
	 * @param stacks range you'd like to check
	 * @param item Target item
	 * @return <code>true</code> if get target item
	 */
	public boolean hasTargetItem(ItemStack[] stacks, Item item) {
		for (int a = 0; a < stacks.length; a++) {
			ItemStack obj = stacks[a];
			if (obj.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}
}
