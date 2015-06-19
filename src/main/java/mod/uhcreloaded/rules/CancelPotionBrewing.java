/** This file is part of UltraHardcore: Reloaded (abbr. UHCR in following context)
 *  Copyright (C) 2015- 3TUSK
 *
 *  UHCR is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or 
 *  (at your option) any later version.
 *
 *  UHCR is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UHCR.  If not, see <http://www.gnu.org/licenses/>.
 */
package mod.uhcreloaded.rules;

import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Will be replaced by the brewing registry system.
 */
public class CancelPotionBrewing {

	@SubscribeEvent
	public void CancelCertainPotionBrewing(PotionBrewEvent.Pre evt) {
		for (int i = 0; i < evt.getLength(); i++) {
			ItemStack stack = evt.getItem(i);
			if (stack != null) {
				if (stack.getItem().equals(Items.ghast_tear)) {
					if (!ConfigHandler.allowBrewingPotionRegen)
						evt.setCanceled(true);
				}

				if (stack.getItem().equals(Items.gunpowder)) {
					if (!ConfigHandler.allowBrewingPotionSplash)
						evt.setCanceled(true);
				}

				if (stack.getItem().equals(Items.glowstone_dust)) {
					if (!ConfigHandler.allowBrewingPotionLevelII)
						evt.setCanceled(true);
				}
			}
		}
	}
}
