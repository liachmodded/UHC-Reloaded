/*
 * The MIT License (MIT)
 *
 * Copyright (c) liachmodded <https://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mod.uhcreloaded.rules;

import static mod.uhcreloaded.util.Misc.translate;

import mod.uhcreloaded.UhcReloaded;
import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GoldenItemToGold {

    public static void regUncraftingGoldenToolsAndArmor() {
        int mode = ConfigHandler.uncraftingDifficulty;
        switch (mode) {
            case (0):
                UhcReloaded.LOG.info("[{}]Set uncrafting mode to off.", translate("mod.uhcreloaded.name"));
                break;
            case (1): {
                regEasyUncraftingRecipe();
                UhcReloaded.LOG.info("[{}]Set uncrafting mode to easy.", translate("mod.uhcreloaded.name"));
                break;
            }
            case (2): {
                regNormalUncraftingRecipe();
                UhcReloaded.LOG.info("[{}]Set uncrafting mode to normal.", translate("mod.uhcreloaded.name"));
                break;
            }
            case (3): {
                regHardUncraftingRecipe();
                UhcReloaded.LOG.info("[{}]Set uncrafting mode to hard.", translate("mod.uhcreloaded.name"));
                break;
            }
            default:
                UhcReloaded.LOG.warn("[{}]Invalid uncrafting difficlty, please check!", translate("mod.uhcreloaded.name"));
                UhcReloaded.LOG.info("[{}]Set uncrafting mode to off.", translate("mod.uhcreloaded.name"));
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
        if (Loader.isModLoaded("Railcraft")) {
            regRailcraftPowerRailToGold();
        } else {
            regPowerRailToGoldH();
        }
    }

    public static void regGoldenToolsToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 3), new Object[]{Items.golden_axe});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[]{Items.golden_hoe});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 3), new Object[]{Items.golden_pickaxe});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 1), new Object[]{Items.golden_shovel});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[]{Items.golden_sword});
    }

    public static void regGoldenArmorsToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 5), new Object[]{Items.golden_helmet});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 8), new Object[]{Items.golden_chestplate});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 7), new Object[]{Items.golden_leggings});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), new Object[]{Items.golden_boots});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 6), new Object[]{Items.golden_horse_armor});
    }

    public static void regClockToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), new Object[]{Items.clock});
    }

    public static void regPressurePlateToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 2), new Object[]{Blocks.light_weighted_pressure_plate});
    }

    public static void regPowerRailToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 1), new Object[]{Blocks.golden_rail});
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
        GameRegistry.addSmelting(Items.clock, new ItemStack(Items.gold_ingot, 4), 2.0F);
    }

    public static void regPressurePlateToGoldN() {
        GameRegistry.addSmelting(Blocks.light_weighted_pressure_plate, new ItemStack(Items.gold_ingot, 2), 1.0F);
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

    /** Called only when Railcraft mod is installed.*/
    public static void regRailcraftPowerRailToGold() {
        GameRegistry.addSmelting(Blocks.golden_rail, new ItemStack(Items.gold_nugget, 2), 0.1F);
    }
}
