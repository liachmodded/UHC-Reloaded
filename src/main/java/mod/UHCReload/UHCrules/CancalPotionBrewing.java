package mod.UHCReload.UHCrules;

import mod.UHCReload.util.configHandler;
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
					if (!configHandler.allowBrewingPotionRegen)
						Evt.setCanceled(true);
				}
				
				if (stack.getItem().equals(Items.gunpowder)){
					if (!configHandler.allowBrewingPotionSplash)
						Evt.setCanceled(true);
				}
				
				if (stack.getItem().equals(Items.glowstone_dust)){
					if (!configHandler.allowBrewingPotionLevelII)
						Evt.setCanceled(true);
				}
			}
		}
	}
}
