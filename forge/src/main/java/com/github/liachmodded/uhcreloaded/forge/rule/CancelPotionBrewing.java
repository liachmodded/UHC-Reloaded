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

package com.github.liachmodded.uhcreloaded.forge.rule;

import com.github.liachmodded.uhcreloaded.forge.util.ConfigHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Will be replaced by the brewing registry system.
 */
public class CancelPotionBrewing {

    @SubscribeEvent
    public void cancelCertainPotionBrewing(PotionBrewEvent.Pre evt) {
        for (int i = 0; i < evt.getLength(); i++) {
            ItemStack stack = evt.getItem(i);
            if (stack != null) {
                if (stack.getItem().equals(Items.GHAST_TEAR)) {
                    if (!ConfigHandler.allowBrewingPotionRegen) {
                        evt.setCanceled(true);
                    }
                }

                if (stack.getItem().equals(Items.GUNPOWDER)) {
                    if (!ConfigHandler.allowBrewingPotionSplash) {
                        evt.setCanceled(true);
                    }
                }

                if (stack.getItem().equals(Items.GLOWSTONE_DUST)) {
                    if (!ConfigHandler.allowBrewingPotionLevelII) {
                        evt.setCanceled(true);
                    }
                }
            }
        }
    }
}
