package mod.uhcreloaded.rules;

import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnforceNoGhastTear {

	private boolean isGhastTear;
	private int tearAmount;

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onGhastDropsTears(EntityItemPickupEvent Evt) {
		if (!ConfigHandler.allowGhastTear) {
			if (Evt.item.getEntityItem().getItem().equals(Items.ghast_tear)) {
				Evt.setCanceled(true);
				isGhastTear = true;
				tearAmount = Evt.item.getEntityItem().stackSize;
				Evt.item.setDead();
			} else {
				isGhastTear = false;
			}

			if (isGhastTear) {
				for (int a = 0; a < 1; a++) {
					if (tearAmount > 0){
						EntityItem entityItem = Evt.entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.gold_ingot, tearAmount), false);
						entityItem.setNoPickupDelay();
						entityItem.setOwner(Evt.entityPlayer.getName());
					}
				}
			}
		}
	}
}