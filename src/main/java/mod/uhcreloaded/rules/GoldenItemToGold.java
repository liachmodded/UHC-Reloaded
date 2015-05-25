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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GoldenItemToGold {

	public static void regUncraftingGoldenToolsAndArmor() {
		int mode = ConfigHandler.uncraftingDifficulty;
		switch (mode) {
		case (0):
			System.out.print("[UHC:Reload]Set uncrafting mode to off.");
			break;
		case (1): {
			regEasyUncraftingRecipe();
			System.out.print("[UHC:Reload]Set uncrafting mode to Easy.");
			break;
		}
		case (2): {
			regNormalUncraftingRecipe();
			System.out.print("[UHC:Reload]Set uncrafting mode to Normal.");
			break;
		}
		case (3): {
			regHardUncraftingRecipe();
			System.out.print("[UHC:Reload]Set uncrafting mode to Hard.");
			break;
		}
		default:
			System.out.print("[UHC:Reload]Invalid uncrafting difficlty, please check!");
			System.out.print("[UHC:Reload]Set uncrafting mode to off.");
			break;
		}
	}

	/*
	 * =============================Switch method
	 * start=====================================
	 */

	public static void regEasyUncraftingRecipe() {
		regGoldenToolsToGoldE();
		regGoldenArmorsToGoldE();
		regClockToGoldE();
		regPressurePlateToGoldE();
		regPowerRailToGoldE();
	}

	public static void regNormalUncraftingRecipe() {
		regGoldenToolsToGoldN();
		regGoldenArmorsToGoldN();
		regClockToGoldN();
		regPressurePlateToGoldN();
		regPowerRailToGoldN();
	}

	// WIP.
	public static void regHardUncraftingRecipe() {
		regGoldenToolsToGoldH();
		regGoldenArmorsToGoldH();
		regClockToGoldH();
		regPressurePlateToGoldH();
		regPowerRailToGoldH();
	}

	public static void regGoldenToolsToGoldE() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 3), new Object[] { Items.golden_axe });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[] { Items.golden_hoe });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 3), new Object[] { Items.golden_pickaxe });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 1), new Object[] { Items.golden_shovel });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[] { Items.golden_sword });
	}

	public static void regGoldenArmorsToGoldE() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 5), new Object[] { Items.golden_helmet });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 8), new Object[] { Items.golden_chestplate });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 7), new Object[] { Items.golden_leggings });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), new Object[] { Items.golden_boots });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 6), new Object[] { Items.golden_horse_armor });
	}

	public static void regClockToGoldE() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), new Object[] { Items.clock });
	}

	public static void regPressurePlateToGoldE() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[] { Blocks.light_weighted_pressure_plate });
	}

	public static void regPowerRailToGoldE() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 1), new Object[] { Blocks.golden_rail });
	}

	public static void regGoldenToolsToGoldN() {
		GameRegistry.addSmelting(Items.golden_axe, new ItemStack(Items.gold_ingot, 3), 3.0F);
		GameRegistry.addSmelting(Items.golden_hoe, new ItemStack(Items.gold_ingot, 2), 2.0F);
		GameRegistry.addSmelting(Items.golden_pickaxe, new ItemStack(Items.gold_ingot, 3), 3.0F);
		GameRegistry.addSmelting(Items.golden_shovel, new ItemStack(Items.gold_ingot, 1), 1.0F);
		GameRegistry.addSmelting(Items.golden_sword, new ItemStack(Items.gold_ingot, 2), 2.0F);
	}

	public static void regGoldenArmorsToGoldN() {
		GameRegistry.addSmelting(Items.golden_helmet, new ItemStack(Items.gold_ingot, 5), 2.5F);
		GameRegistry.addSmelting(Items.golden_chestplate, new ItemStack(Items.gold_ingot, 8), 4.0F);
		GameRegistry.addSmelting(Items.golden_leggings, new ItemStack(Items.gold_ingot, 7), 3.5F);
		GameRegistry.addSmelting(Items.golden_boots, new ItemStack(Items.gold_ingot, 4), 2.0F);
		GameRegistry.addSmelting(Items.golden_horse_armor, new ItemStack(Items.gold_ingot, 6), 3.0F);
	}

	public static void regClockToGoldN() {
		GameRegistry.addSmelting(Items.clock,new ItemStack(Items.gold_ingot, 4), 2.0F);
	}

	public static void regPressurePlateToGoldN() {
		GameRegistry.addSmelting(Blocks.light_weighted_pressure_plate,new ItemStack(Items.gold_ingot, 2), 1.0F);
	}

	public static void regPowerRailToGoldN() {
		GameRegistry.addSmelting(Blocks.golden_rail, new ItemStack(Items.gold_ingot, 1), 0.15F);
	}
	
	public static void regGoldenToolsToGoldH() {
		GameRegistry.addSmelting(Items.golden_axe, new ItemStack(Items.gold_ingot, 2), 3.0F);
		GameRegistry.addSmelting(Items.golden_hoe, new ItemStack(Items.gold_ingot, 1), 2.0F);
		GameRegistry.addSmelting(Items.golden_pickaxe, new ItemStack(Items.gold_ingot, 2), 3.0F);
		GameRegistry.addSmelting(Items.golden_shovel, new ItemStack(Items.gold_nugget, 5), 1.0F);
		GameRegistry.addSmelting(Items.golden_sword, new ItemStack(Items.gold_ingot, 1), 2.0F);
	}

	public static void regGoldenArmorsToGoldH() {
		GameRegistry.addSmelting(Items.golden_helmet, new ItemStack(Items.gold_ingot, 2), 2.5F);
		GameRegistry.addSmelting(Items.golden_chestplate, new ItemStack(Items.gold_ingot, 4), 4.0F);
		GameRegistry.addSmelting(Items.golden_leggings, new ItemStack(Items.gold_ingot, 3), 3.5F);
		GameRegistry.addSmelting(Items.golden_boots, new ItemStack(Items.gold_ingot, 2), 2.0F);
		GameRegistry.addSmelting(Items.golden_horse_armor, new ItemStack(Items.gold_ingot, 3), 3.0F);
	}

	public static void regClockToGoldH() {
		GameRegistry.addSmelting(Items.clock, new ItemStack(Items.gold_ingot, 2), 2.0F);
	}

	public static void regPressurePlateToGoldH() {
		GameRegistry.addSmelting(Blocks.light_weighted_pressure_plate, new ItemStack(Items.gold_ingot, 1), 1.0F);
	}

	public static void regPowerRailToGoldH() {
		GameRegistry.addSmelting(Blocks.golden_rail, new ItemStack(Items.gold_nugget, 5), 0.15F);
	}

	/**WIP, Only if Railcraft is installed*/
	public static void regRailcraftPowerRailToGold() {
		GameRegistry.addSmelting(Blocks.golden_rail, new ItemStack(Items.gold_nugget, 2), 0.1F);
	}
}
