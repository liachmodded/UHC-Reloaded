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

package com.github.liachmodded.uhcreloaded.sponge;

import com.github.liachmodded.uhcreloaded.sponge.rule.CancelPotionBrewingListener;
import com.github.liachmodded.uhcreloaded.sponge.rule.GhastTearToGoldListener;
import com.github.liachmodded.uhcreloaded.sponge.rule.GoldenAppleRecipeRemover;
import com.google.inject.Inject;
import net.minecrell.mcstats.SpongeStatsLite;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.tileentity.BrewingEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = References.PLUGIN_ID,
        name = References.NAME,
        authors = {"liach", "3TUSK"},
        description = References.DESCRIPTION,
        version = References.VERSION
)
public final class SpongeUhcReloaded {

    private static SpongeUhcReloaded instance;

    @Inject
    private SpongeStatsLite statsLite;
    @Inject
    private Game game;

    @Inject
    private SpongeUhcReloaded() {
        instance = this;
    }

    public static SpongeUhcReloaded getInstance() {
        return instance;
    }

    @Listener
    public void onPreInit(GamePreInitializationEvent e) {
        this.statsLite.start();
    }

    @Listener
    public void onInit(GameInitializationEvent evt) {
        this.game.getEventManager().registerListener(this, DropItemEvent.Pre.class, Order.FIRST, GhastTearToGoldListener.INSTANCE);
        this.game.getEventManager().registerListener(this, BrewingEvent.Start.class, Order.FIRST, CancelPotionBrewingListener.INSTANCE);
        try {
            GoldenAppleRecipeRemover.INSTANCE.accept(this.game.getRegistry().getRecipeRegistry());
        } catch (UnsupportedOperationException e) {
            // Default...
        }
    }

}
