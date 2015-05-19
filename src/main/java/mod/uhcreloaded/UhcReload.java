package mod.uhcreloaded;

/**
 * @author ThaumicTechTinker Urey.S.Knowledge (a.k.a. 3TUSK/3tusk)
 * This is a mod which brings the original UHC mode rules to 1.8 release
 * Forge Compatible, flexible configuration, and firing contribution :)
 * Sort of meaningless, but I will insist on it, since there used to be an era!
 */
import static mod.uhcreloaded.util.Misc.*;

import java.io.File;

import mod.uhcreloaded.commands.CommandUhcHelp;
import mod.uhcreloaded.commands.CommandUhcMode;
import mod.uhcreloaded.commands.CommandUshcMode;
import mod.uhcreloaded.rules.CancalEnderPeralDamage;
import mod.uhcreloaded.rules.EnforceNoGhastTear;
import mod.uhcreloaded.rules.GoldenItemToGold;
import mod.uhcreloaded.rules.ModdedGoldenStuff;
import mod.uhcreloaded.util.ConfigHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class UhcReload {

	@Instance("UHCReload")
	public static UhcReload instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent Event) {
		File cfgFile = new File(Event.getModConfigurationDirectory(), "UHCReload.cfg");
		ConfigHandler.init(cfgFile);
	}

	@EventHandler
	public void Init(FMLInitializationEvent Event) {
		MinecraftForge.EVENT_BUS.register(new EnforceNoGhastTear());
		MinecraftForge.EVENT_BUS.register(new ModdedGoldenStuff());
		MinecraftForge.EVENT_BUS.register(new CancalEnderPeralDamage());

		GoldenItemToGold.regUncraftingGoldenToolsAndArmor();

		ModdedGoldenStuff.regGoldenSkull();

		ModdedGoldenStuff.removeEnhancedGoldenApple();
		ModdedGoldenStuff.harderGoldenCarrot();
		ModdedGoldenStuff.harderGlisteringMelon();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent Event) {
		Event.registerServerCommand(new CommandUhcMode());
		Event.registerServerCommand(new CommandUshcMode());
		Event.registerServerCommand(new CommandUhcHelp());
	}
}
