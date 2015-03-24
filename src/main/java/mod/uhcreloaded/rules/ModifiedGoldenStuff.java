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

public class ModifiedGoldenStuff {

	public static void regGoldenSkull() {
		if (ConfigHandler.allowCraftingGoldenSkull) {
			GameRegistry.addShapedRecipe(Misc.getRenamedItemStack(
					Items.golden_apple, "§6Golden Skull", 1, 0), new Object[] {
					"GGG", "GSG", "GGG", 'G', Items.gold_ingot, 'S',
					new ItemStack(Items.skull, 1, 3) });
		}
	}

	public static void removeEnhancedGoldenApple() {
		if (!ConfigHandler.allowEnchantedGoldenApple)
			Misc.removeCertainRecipe(new ItemStack(Items.golden_apple, 1, 1));
	}

	public static void harderGoldenCarrot() {
		if (ConfigHandler.harderGoldenCarrot) {
			Misc.removeCertainRecipe(new ItemStack(Items.golden_carrot));
			GameRegistry.addShapedRecipe(new ItemStack(Items.golden_carrot),
					new Object[] { "GGG", "GCG", "GGG", 'G', Items.gold_ingot,
							'C', Items.carrot });
		}
	}

	public static void harderGlisteringMelon() {
		if (ConfigHandler.harderGlisteringMelon) {
			Misc.removeCertainRecipe(new ItemStack(Items.speckled_melon));
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
