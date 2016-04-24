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

import com.github.liachmodded.uhcreloaded.forge.worldly.ScopeManager;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;

/**
 * Created by liach on 4/11/2016.
 *
 * @author liach
 */
public class EasierSkeleton {

    @SubscribeEvent
    public void onSkeletonUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!ScopeManager.handle(event)) {
            return;
        }
        if (!(event.getEntityLiving() instanceof EntitySkeleton)) {
            return;
        }
        EntitySkeleton skeleton = (EntitySkeleton) event.getEntityLiving();
        EntityAITasks tasks = skeleton.tasks;
        boolean containsOld = false;
        Iterator<EntityAITasks.EntityAITaskEntry> itr = tasks.taskEntries.iterator();
        while (itr.hasNext()) {
            EntityAITasks.EntityAITaskEntry entry = itr.next();
            if (entry.action instanceof EntityAIAttackRangedBow) {
                itr.remove();
            }
            if (entry.action instanceof EntityAIAttackRanged) {
                containsOld = true;
            }
        }
        if (!containsOld) {
            tasks.addTask(4, new EntityAIAttackRanged(skeleton, 1.0D, 20, 15.0F));
        }
    }
}
