package mod.UHCReload.UHCrules;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnforceNoGhastTear {
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerPickUpGhastTear(EntityItemPickupEvent Event){
		if (Event.item.getEntityItem().getItem().equals(Items.ghast_tear)){
			InventoryPlayer inventory = Event.entityPlayer.inventory;
			for(int i = 0; i <= 36; i++){
				if (inventory.getStackInSlot(i).getItem().equals(Items.ghast_tear)){
					inventory.setInventorySlotContents(i, 
							new ItemStack(Items.gold_ingot, Event.item.getEntityItem().stackSize));
				}
			}
		}
	}
}
