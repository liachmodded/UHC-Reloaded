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
