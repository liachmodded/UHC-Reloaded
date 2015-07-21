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
package mod.uhcreloaded.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static boolean antiCheatMode;

	public static int healAmountSkull;
	public static int uncraftingDifficulty;
	public static boolean allowCraftingGoldenSkull;
	public static boolean harderGoldenCarrot;
	public static boolean harderGlisteringMelon;

	public static boolean allowBrewingPotionLevelII;
	public static boolean allowBrewingPotionRegen;
	public static boolean allowBrewingPotionSplash;
	public static boolean allowEnchantedGoldenApple;
	public static boolean allowGhastTear;
	public static boolean allowGoldenAppleRegen;
	public static boolean allowUSHCCommand;
	public static boolean openEnderPearlFallingDamage;
	public static boolean playerDropSkull;

	private static final String ANTI_CHEAT = "Anti-Cheating Mode Opition";
	private static final String CRAFT = "Crafting Configuration";
	private static final String CUSTOM_RULES = "General UHC Rules Configuration";

	public static void initConfig(File file) {
		Configuration config = new Configuration(file);

		config.load();

		antiCheatMode = config.getBoolean("AntiCheating", ANTI_CHEAT, true,
				"To prevent any kind of possible cheating way, set it to true. All rights reserved.");
		
		allowCraftingGoldenSkull = config.getBoolean("AllowCraftingGoldenSkull", CRAFT, true,
				"Set false if you don't want such a bloody thing.");
		healAmountSkull = config.getInt("HealAmountSkull", CRAFT, 8, 4, 127,
				"The health that a golden skull restores. Limited by potion levels.");

		harderGoldenCarrot = config.getBoolean("HarderGoldenCarrotRecipe", CRAFT, true,
				"This recipe will use 8 ingots rather than nuggets.");
		harderGlisteringMelon = config.getBoolean("HarderGlisteringMelonRecipe", CRAFT, true,
				"This recipe will use one gold BLOCK rather than nuggets. Crazy, isn't it?");
		uncraftingDifficulty = config.getInt("UncraftingGoldenItems", CRAFT, 1, 0, 3,
				"Variable difficulty of uncrafting your golden tools or armors.");

		allowBrewingPotionLevelII = config.getBoolean("AllowBrewingLevelIIPotion", CUSTOM_RULES, false,
				"set true if you want level II potion.");
		allowBrewingPotionRegen = config.getBoolean("AllowBrewingRegenerationPotion", CUSTOM_RULES, false,
				"set true if you want regeneration potion, maybe it's for special loot.");
		allowBrewingPotionSplash = config.getBoolean("AllowBrewingSplashPotion", CUSTOM_RULES, false,
				"set true if you want splash potion.");
		allowEnchantedGoldenApple = config.getBoolean("AllowCraftingEnchantedGoldenApple", CUSTOM_RULES, false,
				"set true if you want to allow player crafting enchanted golden apple.");
		allowGhastTear = config.getBoolean("AllowGhastDropGhastTear", CUSTOM_RULES, false,
				"If you want ghast tear to brew regeneration potion, set it true, which is NOT recommended.");
		allowGoldenAppleRegen = config.getBoolean("AllGoldenAppleEffect", CUSTOM_RULES, false,
				"Set true if you want the effect from enchanted golden apple. Default value is false.");
		allowUSHCCommand = config.getBoolean("AllowUSHCModeCommand", CUSTOM_RULES, false,
				"USHC means UltraSuperHardcore, which is equivalent to UHC plus no sunlight plus time stop. "
						+ "Set true if you want to try it.");
		openEnderPearlFallingDamage = config.getBoolean("AllowEnderPearlDamage", CUSTOM_RULES, false,
				"Set true if you want ender pearl to cause 2.5 heart falling damage as in vanilla.");
		playerDropSkull = config.getBoolean("PlayerDropSkull", CUSTOM_RULES, true,
				"Set to false if you don't want excess skull or some other mod drops it.");

		config.save();
	}
}
