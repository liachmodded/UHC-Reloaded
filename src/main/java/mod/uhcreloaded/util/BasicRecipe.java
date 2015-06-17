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
package mod.uhcreloaded.util;

import static mod.uhcreloaded.util.Misc.MODID;
import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPED;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.RecipeSorter;

/**
 * A utility class for recipes.
 *
 * @author liach
 */
public abstract class BasicRecipe implements IRecipe {
    /**
     * The method for registering the recipe and the sorting.
     */
    public void registerRecipe() {
        CraftingManager.getInstance().addRecipe(this);
        RecipeSorter.register(MODID + ":" + getRecipeName(), getClass(), SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
    }

    /**
     * Check method.
     * @param grid The crafting grid
     * @param worldIn The world in
     * @return Whether this recipe could be used
     */
    @Override
    public boolean matches(InventoryCrafting grid, World worldIn) {
        return getCraftingResult(grid) != null;
    }

    /**
     * 3*3 Recipes.
     * @return Recipe size
     */
    @Override
    public int getRecipeSize() {
        return 9;
    }

    /**
     * Actually useless.
     * @return {@code null}
     */
    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    /**
     * Let forge do it.
     * @param grid The crafting grid
     * @return The default process result by forge
     */
    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting grid) {
        return ForgeHooks.defaultRecipeGetRemainingItems(grid);
    }

    /**
     * The name of the recipe. Used in forge sorting.
     * @return The name of the recipe
     */
    public abstract String getRecipeName();
}
