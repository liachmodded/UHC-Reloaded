package mod.UHCReload.UHCrules;

import mod.UHCReload.util.configHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnforceNoGhastTear {
	
	private boolean isGhastTear;
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onGhastDropsTears(EntityItemPickupEvent Evt){
		if (!configHandler.allowGhastTear){
			if (Evt.item.getEntityItem().getItem().equals(Items.ghast_tear)){
				Evt.setCanceled(true);
				isGhastTear = true;
				Evt.item.setDead();
			} else {
				isGhastTear = false;
			}
		
			if (isGhastTear){
				for (int a = 0;a < 1; a++){
					EntityItem entityItem = Evt.entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.gold_ingot, Evt.item.getEntityItem().stackSize), false);
					entityItem.setNoPickupDelay();
					entityItem.setOwner(Evt.entityPlayer.getName());
				}
			}
		}
	}
}