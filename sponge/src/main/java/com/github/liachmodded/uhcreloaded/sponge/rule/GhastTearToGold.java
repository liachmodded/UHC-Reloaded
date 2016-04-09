package com.github.liachmodded.uhcreloaded.sponge.rule;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.SpawnEntityEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liach on 4/3/2016.
 *
 * @author liach
 */
public final class GhastTearToGold {

    private static final GhastTearToGold INSTANCE = new GhastTearToGold();

    private GhastTearToGold() {
    }

    @Listener
    public void onItemSpawn(DropItemEvent.Pre e) {
        List<ItemStackSnapshot> itemStackSnapshots = new ArrayList<>();
        Iterator<ItemStackSnapshot> itr = e.getDroppedItems().iterator();
        while (itr.hasNext()) {
            ItemStackSnapshot snapshot = itr.next();
            if (snapshot.getType() == ItemTypes.GHAST_TEAR) {
                itr.remove();
                ItemStack stack = ItemStack.of(ItemTypes.GOLD_INGOT, snapshot.getCount());
                itemStackSnapshots.add(stack.createSnapshot());
            }
        }
        e.getDroppedItems().addAll(itemStackSnapshots);
    }

}
