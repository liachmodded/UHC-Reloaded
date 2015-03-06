package mod.uhcreloaded.rules;

import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CancalPotionBrewing {

	@SubscribeEvent
	public void CancalCertainPotionBrewing(PotionBrewEvent.Pre Evt) {
		for (int a = 0; a <= 5; a++) {
			ItemStack stack = Evt.getItem(a);
			if (stack != null) {
				if (stack.getItem().equals(Items.ghast_tear)) {
					if (!ConfigHandler.allowBrewingPotionRegen)
						Evt.setCanceled(true);
				}

				if (stack.getItem().equals(Items.gunpowder)) {
					if (!ConfigHandler.allowBrewingPotionSplash)
						Evt.setCanceled(true);
				}

				if (stack.getItem().equals(Items.glowstone_dust)) {
					if (!ConfigHandler.allowBrewingPotionLevelII)
						Evt.setCanceled(true);
				}
			}
		}
	}
}
