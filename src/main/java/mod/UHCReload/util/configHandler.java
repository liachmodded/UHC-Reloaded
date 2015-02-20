package mod.UHCReload.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class configHandler{
	
	public static int uncraftingDifficulty;
	public static boolean allowCraftingGoldenSkull;
	public static boolean harderGoldenCarrot;
	public static boolean harderGlisteringMelon;
	
	public static boolean allowGoldenAppleRegen;
	public static boolean openEnderPearlFallingDamage;
	
	private static final String CRAFT = "Crafting Configuration";
	private static final String CUSTOM_RULES = "General UHC rules Configuration";
	
	public static void init(File file){
	Configuration config = new Configuration(file);

	config.load();

	allowCraftingGoldenSkull = config.getBoolean("AllowCraftingGoldenSkull", CRAFT, true, "Set false if you don't want such a bleedy thing.");
	harderGoldenCarrot = config.getBoolean("HarderGoldenCarrotRecipe", CRAFT, true, "This recipe will use 8 ingots rather than nuggets.");
	harderGlisteringMelon = config.getBoolean("HarderGlistringMelonRecipe", CRAFT, true, "This recipe will use one gold BLOCK rather than nuggets. Crazy, isn't it?");
	uncraftingDifficulty = config.getInt("UncraftingGoldenItems", CRAFT, 1, 0, 3, "Variable difficulty of uncrafting your golden tools or armors. The range is from 0 to 3.");
	
	allowGoldenAppleRegen = config.getBoolean("AllGoldenAppleEffect", CUSTOM_RULES, false, "Set true if you want the effect from golden apple. Default value is false.");
	openEnderPearlFallingDamage = config.getBoolean("AllowEnderPeralDamage", CUSTOM_RULES, false, "Set true if you want ender peral to cause 2.5 heart falling damage as in vanilla.");
	
	config.save();
	}
}
