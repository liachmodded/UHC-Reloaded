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

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.tileentity.BrewingEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * An event handler which cancels certain potion brewing.
 */
public final class CancelPotionBrewingListener implements EventListener<BrewingEvent.Start> {

    public static final CancelPotionBrewingListener INSTANCE = new CancelPotionBrewingListener();

    private CancelPotionBrewingListener() {
    }

    @Override
    public void handle(BrewingEvent.Start event) {
        event.filter(stack -> {
            if (stack.getItem() == ItemTypes.SPLASH_POTION) {
                return false;
            }
            if (stack.getItem() == ItemTypes.LINGERING_POTION) {
                return false;
            }
            if (stack.supports(Keys.POTION_EFFECTS)) {
                Optional<List<PotionEffect>> optPotions = stack.get(Keys.POTION_EFFECTS);
                if (!optPotions.isPresent()) {
                    return true;
                }
                for (PotionEffect effect : optPotions.get()) {
                    if (effect.getType() == PotionEffectTypes.REGENERATION) {
                        return false;
                    }
                    if (effect.getAmplifier() > 1) {
                        return false;
                    }
                }
            }
            return true;
        });
    }

}
