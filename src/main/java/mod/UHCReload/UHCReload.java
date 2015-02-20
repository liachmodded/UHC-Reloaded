package mod.UHCReload;

/**
 * @author ThaumicTechTinker Urey.S.Knowledge(aka 3TUSK)
 * This is a mod which brings the original UHC mode rules to 1.8 release
 * Forge Compatible, flexible configuration, and firing contribution :)
 */

import java.io.File;

import mod.UHCReload.UHCrules.CancalEnderPeralDamage;
import mod.UHCReload.UHCrules.GoldenItemToGold;
import mod.UHCReload.UHCrules.ModifiedGoldenStuff;
import mod.UHCReload.UHCrules.EnforceNoGhastTear;
import mod.UHCReload.command.CommandUHCMode;
import mod.UHCReload.command.CommandUSHCMode;
import mod.UHCReload.util.configHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "UHCReload", name = "UltraHardcore-Mode: Reload", version = "Aplha 0.0.1")
public class UHCReload {

	@Instance("UHCReload")
	public static UHCReload instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent Event){
		File cfgFile = new File(Event.getModConfigurationDirectory(), "UHCReload.cfg");
		configHandler.init(cfgFile);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent Event){
		MinecraftForge.EVENT_BUS.register(new EnforceNoGhastTear());
		MinecraftForge.EVENT_BUS.register(new ModifiedGoldenStuff());
		MinecraftForge.EVENT_BUS.register(new CancalEnderPeralDamage());
		
		GoldenItemToGold.regUncraftingGoldenToolsAndArmor();
		
		ModifiedGoldenStuff.regGoldenSkull();
		
		ModifiedGoldenStuff.removeEnhancedGoldenApple();
		ModifiedGoldenStuff.harderGoldenCarrot();
		ModifiedGoldenStuff.harderGlisteringMelon();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent Event){
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent Event){
		Event.registerServerCommand(new CommandUHCMode());
		Event.registerServerCommand(new CommandUSHCMode());
	}
}
