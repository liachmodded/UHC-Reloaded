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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.uhcreloaded.util.Misc;
import mod.uhcreloaded.util.ConfigHandler;

public class ModdedGoldenStuff {

	public static void regGoldenSkull() {
		if (ConfigHandler.allowCraftingGoldenSkull) {
			GameRegistry.addShapedRecipe(
					new ItemStack(Items.golden_apple, 1, 0).setStackDisplayName("§6Golden Skull"), 
					new Object[] {
						"GGG", 
						"GSG", 
						"GGG", 
						'G', Items.gold_ingot, 
						'S', new ItemStack(Items.skull, 1, 3) });
		}
	}

	public static void removeEnhancedGoldenApple() {
		if (!ConfigHandler.allowEnchantedGoldenApple)
			Misc.removeRecipe(new ItemStack(Items.golden_apple, 1, 1));
	}

	public static void harderGoldenCarrot() {
		if (ConfigHandler.harderGoldenCarrot) {
			Misc.removeRecipe(new ItemStack(Items.golden_carrot));
			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_carrot),
					new Object[] { "GGG", "GCG", "GGG", 'G', Items.gold_ingot, 'C', Items.carrot });
		}
	}

	public static void harderGlisteringMelon() {
		if (ConfigHandler.harderGlisteringMelon) {
			Misc.removeRecipe(new ItemStack(Items.speckled_melon));
			GameRegistry.addShapelessRecipe(
					new ItemStack(Items.speckled_melon), new Object[] {
							Items.melon, Blocks.gold_block });
		}
	}

	// allow player drops their skull, to crafting golden skull
	@SubscribeEvent
	public void onPlayerDeath(PlayerDropsEvent Event) {
		Event.entityPlayer.dropItem(
				Misc.getSkullWithOwner(Event.entityPlayer.getName()), true,
				true);
	}

	@SubscribeEvent
	public void onPlayerEatGoldenApple(PlayerUseItemEvent.Finish Event) {
		EntityPlayer currentPlayer = (EntityPlayer) Event.entityPlayer;
		if (!ConfigHandler.allowGoldenAppleRegen) {
			if (Event.entityLiving instanceof EntityPlayerMP) {
				if (Event.item.getItem().equals(Items.golden_apple)
						&& Event.item.getMetadata() == 0) {
					if (Event.item.getDisplayName() == "§6Golden Skull") {
						currentPlayer.clearActivePotions();
						currentPlayer.heal(8.0F);
					} else {
						currentPlayer.clearActivePotions();
						currentPlayer.heal(4.0F);
					}
				}
			} else {
				if (ConfigHandler.AntiCheatMode
						&& Event.item.getItem().equals(Items.golden_apple)
						&& Event.item.getMetadata() == 1) {
					currentPlayer.clearActivePotions();
					currentPlayer.addChatComponentMessage(new ChatComponentText("DO NOT CHEAT!!!"));
				}
			}
		}
	}
}
