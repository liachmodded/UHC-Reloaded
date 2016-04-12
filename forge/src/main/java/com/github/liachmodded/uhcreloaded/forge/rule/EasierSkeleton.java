package com.github.liachmodded.uhcreloaded.forge.rule;

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
