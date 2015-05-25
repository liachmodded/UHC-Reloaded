/**
 *     Copyright (C) 2015- 3TUSK
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or 
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
