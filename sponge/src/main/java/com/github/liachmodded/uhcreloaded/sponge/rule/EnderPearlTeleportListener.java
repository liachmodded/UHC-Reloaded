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

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.projectile.EnderPearl;
import org.spongepowered.api.event.cause.entity.spawn.SpawnCause;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.event.entity.SpawnEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

import java.util.List;

/**
 * Created by liach on 4/14/2016.
 *
 * @author liach
 */
public final class EnderPearlTeleportListener {

    public static final EnderPearlTeleportListener INSTANCE = new EnderPearlTeleportListener();

    private EnderPearlTeleportListener() {
    }

    public void onPearlThrown(SpawnEntityEvent e, @First SpawnCause spawnCause) {
        if (spawnCause.getType() != SpawnTypes.PROJECTILE) {
            return;
        }
        e.getEntities().stream().filter(ent -> ent instanceof EnderPearl).forEach(ent -> {
            EnderPearl enderPearl = (EnderPearl) ent;
            enderPearl.getDamagingData().damage().set(0.0D);
        });
    }

}
