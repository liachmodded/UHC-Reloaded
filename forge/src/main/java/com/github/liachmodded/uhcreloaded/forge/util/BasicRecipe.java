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

import static com.github.liachmodded.uhcreloaded.forge.util.Misc.MODID;
import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPED;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
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
        RecipeSorter.register(MODID + ":" + getClass().getName(), getClass(), SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
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
     * Let forge do it.
     * @param grid The crafting grid
     * @return The default process result by forge
     */
    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting grid) {
        return ForgeHooks.defaultRecipeGetRemainingItems(grid);
    }
}
