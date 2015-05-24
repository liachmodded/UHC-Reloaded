package mod.uhcreloaded.rules;

import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnforceNoGhastTear {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onGhastDropsTears(LivingDropsEvent event) {
		if ((!ConfigHandler.allowGhastTear) && (event.entityLiving instanceof EntityGhast)) {
			for (EntityItem item : event.drops) {
				if (item.getEntityItem().getItem() == Items.ghast_tear) {
					event.entity.dropItem(Items.gold_ingot, item.getEntityItem().stackSize);
					item.setDead();
				}
			}
		}
	}
}