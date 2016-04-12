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

package com.github.liachmodded.uhcreloaded.sponge.rule;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.recipe.RecipeRegistry;

import java.util.function.Consumer;

/**
 * Created by liach on 4/11/2016.
 *
 * @author liach
 */
public final class GoldenAppleRecipeRemover implements Consumer<RecipeRegistry> {

    public static final GoldenAppleRecipeRemover INSTANCE = new GoldenAppleRecipeRemover();

    private GoldenAppleRecipeRemover() {
    }

    @Override
    public void accept(RecipeRegistry recipeRegistry) {
        recipeRegistry.getRecipes().forEach(recipe -> {
            if (recipe.getResultTypes().contains(ItemTypes.GOLDEN_APPLE)) {
                recipeRegistry.remove(recipe);
            }
        });
    }

}
