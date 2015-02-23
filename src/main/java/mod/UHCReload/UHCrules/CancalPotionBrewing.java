package mod.UHCReload.UHCrules;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CancalPotionBrewing {

	@SubscribeEvent
	public void CancalCertainPotionBrewing(PotionBrewEvent.Pre Evt){
		for (int a = 0; a <= 5; a++){
			ItemStack stack = Evt.getItem(a);
			if (stack != null){
				if (stack.getItem().equals(Items.ghast_tear)){
					Evt.setCanceled(true);
				}
				
				if (stack.getItem().equals(Items.gunpowder)){
					Evt.setCanceled(true);
				}
				
				if (stack.getItem().equals(Items.glowstone_dust)){
					Evt.setCanceled(true);
				}
			}
		}
	}
	
	/*
	@SuppressWarnings("unused")
	private boolean hasTargetItem(ItemStack[] stacks,Item item){
		for (int a = 0; a < stacks.length; a++){
			ItemStack obj = stacks[a];
			if (obj.getItem().equals(item)){
				return true;
			}
		}
		
		return false;
	}
	*/
}
