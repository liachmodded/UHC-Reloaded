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