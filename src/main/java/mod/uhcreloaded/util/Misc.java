package mod.uhcreloaded.util;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class Misc {
	
	/**
	 * Warning: User own their risk!
	 * 
	 * @param itemIn
	 * @param info
	 * @return
	 * @author 3tusk
	 */
	@Deprecated
	public static ItemStack setItemStackWithCustomInfo(ItemStack itemIn,
			String[] info) {
		itemIn.getSubCompound("display", true);
		NBTTagCompound nbtTag1 = itemIn.getSubCompound("display", false);
		NBTTagList nbtTagList1 = nbtTag1.getTagList("Lore", 9);

		ArrayList<String> toolTips = new ArrayList<String>();

		for (int n = 0; n < info.length; n++) {
			String anInfo = info[n];
			toolTips.add(anInfo);
		}

		Iterator<String> iterator = toolTips.iterator();

		while (iterator.hasNext()) {
			nbtTagList1.appendTag(new NBTTagString(iterator.next()));
		}

		ItemStack output = itemIn.copy();
		return output;
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
	 * @param item
	 *            the item whose recipe you want to remove
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
	 * @param stacks
	 *            range you'd like to check
	 * @param item
	 *            target item
	 * @return true if get target item
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
