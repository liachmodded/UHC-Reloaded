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

package com.github.liachmodded.uhcreloaded.forge.rule;

import static com.github.liachmodded.uhcreloaded.forge.util.Misc.translate;

import com.github.liachmodded.uhcreloaded.forge.UhcReloaded;
import com.github.liachmodded.uhcreloaded.forge.util.ConfigHandler;
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
                UhcReloaded.LOG.info("[{}] Set uncrafting mode to off.", translate("mod.uhcreloaded.name"));
                break;
            case (1): {
                regEasyUncraftingRecipe();
                UhcReloaded.LOG.info("[{}] Set uncrafting mode to easy.", translate("mod.uhcreloaded.name"));
                break;
            }
            case (2): {
                regNormalUncraftingRecipe();
                UhcReloaded.LOG.info("[{}] Set uncrafting mode to normal.", translate("mod.uhcreloaded.name"));
                break;
            }
            case (3): {
                regHardUncraftingRecipe();
                UhcReloaded.LOG.info("[{}] Set uncrafting mode to hard.", translate("mod.uhcreloaded.name"));
                break;
            }
            default:
                UhcReloaded.LOG.warn("[{}] Invalid uncrafting difficulty, please check!", translate("mod.uhcreloaded.name"));
                UhcReloaded.LOG.info("[{}] Set uncrafting mode to off.", translate("mod.uhcreloaded.name"));
                break;
        }
    }

    /*
     * =============================Switch method
     * start=====================================
     */

    public static void regEasyUncraftingRecipe() {
        regClockToGoldE();
        regPressurePlateToGoldE();
        regPowerRailToGoldE();
    }

    public static void regNormalUncraftingRecipe() {
        regClockToGoldN();
        regPressurePlateToGoldN();
        regPowerRailToGoldN();
    }

    // WIP.
    public static void regHardUncraftingRecipe() {
        regClockToGoldH();
        regPressurePlateToGoldH();
        if (Loader.isModLoaded("railcraft")) {
            regRailcraftPowerRailToGold();
        } else {
            regPowerRailToGoldH();
        }
    }

    public static void regClockToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLD_INGOT, 4), Items.CLOCK);
    }

    public static void regPressurePlateToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLD_INGOT, 2), Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
    }

    public static void regPowerRailToGoldE() {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLD_INGOT, 1), Blocks.GOLDEN_RAIL);
    }

    public static void regClockToGoldN() {
        GameRegistry.addSmelting(Items.CLOCK, new ItemStack(Items.GOLD_INGOT, 4), 2.0F);
    }

    public static void regPressurePlateToGoldN() {
        GameRegistry.addSmelting(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, new ItemStack(Items.GOLD_INGOT, 2), 1.0F);
    }

    public static void regPowerRailToGoldN() {
        GameRegistry.addSmelting(Blocks.GOLDEN_RAIL, new ItemStack(Items.GOLD_INGOT, 1), 0.15F);
    }

    public static void regClockToGoldH() {
        GameRegistry.addSmelting(Items.CLOCK, new ItemStack(Items.GOLD_INGOT, 2), 2.0F);
    }

    public static void regPressurePlateToGoldH() {
        GameRegistry.addSmelting(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, new ItemStack(Items.GOLD_INGOT, 1), 1.0F);
    }

    public static void regPowerRailToGoldH() {
        GameRegistry.addSmelting(Blocks.GOLDEN_RAIL, new ItemStack(Items.GOLD_NUGGET, 5), 0.15F);
    }

    /** Called only when Railcraft mod is installed.*/
    public static void regRailcraftPowerRailToGold() {
        GameRegistry.addSmelting(Blocks.GOLDEN_RAIL, new ItemStack(Items.GOLD_NUGGET, 2), 0.1F);
    }
}
