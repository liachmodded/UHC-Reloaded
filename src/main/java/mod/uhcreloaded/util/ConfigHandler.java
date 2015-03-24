package mod.uhcreloaded.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static boolean AntiCheatMode;

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

	private static final String ANTI_CHEAT = "Anti-Cheating Mode Opition";
	private static final String CRAFT = "Crafting Configuration";
	private static final String CUSTOM_RULES = "General UHC Rules Configuration";

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		AntiCheatMode = config.getBoolean("AntiCheating", ANTI_CHEAT, true, "To prevent any kind of possible cheating way, set it to true. All rights reserved.");
		
		allowCraftingGoldenSkull = config.getBoolean("AllowCraftingGoldenSkull", CRAFT, true, "Set false if you don't want such a bleedy thing.");
		harderGoldenCarrot = config.getBoolean("HarderGoldenCarrotRecipe", CRAFT, true, "This recipe will use 8 ingots rather than nuggets.");
		harderGlisteringMelon = config.getBoolean("HarderGlistringMelonRecipe", CRAFT, true, "This recipe will use one gold BLOCK rather than nuggets. Crazy, isn't it?");
		uncraftingDifficulty = config.getInt("UncraftingGoldenItems", CRAFT, 1, 0, 3, "Variable difficulty of uncrafting your golden tools or armors.");

		allowBrewingPotionLevelII = config.getBoolean("AllowBrewingLevelIIPotion", CUSTOM_RULES, false,"set true if you want level II potion.");
		allowBrewingPotionRegen = config.getBoolean("AllowBrewingRegenerationPotion", CUSTOM_RULES, false, "set true if you want regeneration potion, maybe it's for speical loot.");
		allowBrewingPotionSplash = config.getBoolean("AllowBrewingSplashPotion", CUSTOM_RULES, false, "set true if you want splash potion.");
		allowEnchantedGoldenApple = config.getBoolean("AllowCraftingEnchantedGoldenApple", CUSTOM_RULES, false, "set true if you want to allow player crafting enchanted golden apple.");
		allowGhastTear = config.getBoolean("AllowGhastDropGhastTear", CUSTOM_RULES, false, "If you want ghast tear to brew regeneration potion, set it true, which is NOT recommended.");
		allowGoldenAppleRegen = config.getBoolean("AllGoldenAppleEffect", CUSTOM_RULES, false, "Set true if you want the effect from golden apple. Default value is false.");
		allowUSHCCommand = config.getBoolean("AllowUSHCModeCommand", CUSTOM_RULES, false, "USHC means UltraSuperHarcore, which equals to UHC+no day time. Set true if you want to try it.");
		openEnderPearlFallingDamage = config.getBoolean("AllowEnderPeralDamage", CUSTOM_RULES, false, "Set true if you want ender peral to cause 2.5 heart falling damage as in vanilla.");

		config.save();
	}
}
