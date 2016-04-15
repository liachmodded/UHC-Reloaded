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
