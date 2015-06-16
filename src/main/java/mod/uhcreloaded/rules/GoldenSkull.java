/*  This file is part of UltraHardcore: Reloaded (abbr. UHCR in following context)
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
package mod.uhcreloaded.rules;

import static mod.uhcreloaded.util.Misc.*;

import mod.uhcreloaded.util.BasicRecipe;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Skull.
 *
 * @author liach
 */
public class GoldenSkull {
    /**
     * The recipe for the golden skull.
     */
    public static class SkullRecipe extends BasicRecipe {
        /**
         * The name of the recipe.
         * @return The name of the recipe.
         */
        @Override
        public String getRecipeName() {
            return "golden_skull";
        }

        /**
         * The checking method.
         * @param grid The crafting grid
         * @return The result of the recipe. Returns {@code null} if not matching.
         */
        @Override
        public ItemStack getCraftingResult(InventoryCrafting grid) {
            ItemStack outputHead = new ItemStack(Items.skull);
            outputHead.setItemDamage(3);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.getStackInRowAndColumn(i, j) == null)
                        return null;
                    ItemStack stack = grid.getStackInRowAndColumn(i, j);
                    if (i == 1 && j == 1) {
                        if (stack.getItem() != Items.skull || stack.getItemDamage() != 3)
                            return null;
                        outputHead.setTagCompound(stack.getTagCompound());
                    } else {
                        if (!OreDictionary.itemMatches(stack, new ItemStack(Items.gold_ingot), false))
                            return null;
                    }
                }
            }
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(translate("tooltip.uhcreloaded.skull"));
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
