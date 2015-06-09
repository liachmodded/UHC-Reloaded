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
import mod.uhcreloaded.rules.CancelEnderPearlDamage;
import mod.uhcreloaded.rules.CancelPotionBrewing;
import mod.uhcreloaded.rules.EnforceNoGhastTear;
import mod.uhcreloaded.rules.GoldenItemToGold;
import mod.uhcreloaded.rules.GoldenSkull;
import mod.uhcreloaded.rules.ModdedGoldenStuff;
import mod.uhcreloaded.util.ConfigHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class UhcReloaded {

	@Instance(MODID)
	public static UhcReloaded instance;

	public static final Logger LOG = LogManager.getLogger(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		File cfgFile = new File(event.getModConfigurationDirectory(), MODID + ".cfg");
		ConfigHandler.init(cfgFile);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (ConfigHandler.allowGhastTear) registerBus(new EnforceNoGhastTear());
		registerBus(new ModdedGoldenStuff());
		registerBus(new CancelEnderPearlDamage());
		registerBus(new CancelPotionBrewing());

		new GoldenSkull.SkullRecipe().registerRecipe();

		GoldenItemToGold.regUncraftingGoldenToolsAndArmor();

		ModdedGoldenStuff.removeEnhancedGoldenApple();
		ModdedGoldenStuff.harderGoldenCarrot();
		ModdedGoldenStuff.harderGlisteringMelon();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandUhcMode());
		event.registerServerCommand(new CommandUshcMode());
		event.registerServerCommand(new CommandUhcHelp());
	}
}
