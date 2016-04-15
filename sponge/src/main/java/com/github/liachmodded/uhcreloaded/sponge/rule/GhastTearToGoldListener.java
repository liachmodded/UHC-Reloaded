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

import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * A Ghast tear handler.
 *
 * @author liach
 */
public final class GhastTearToGoldListener implements EventListener<DropItemEvent.Pre> {

    public static final GhastTearToGoldListener INSTANCE = new GhastTearToGoldListener();

    private GhastTearToGoldListener() {
    }

    @Override
    public void handle(@Nonnull DropItemEvent.Pre e) throws Exception {
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
