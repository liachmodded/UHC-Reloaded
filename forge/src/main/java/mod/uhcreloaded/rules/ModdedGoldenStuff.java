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

import mod.uhcreloaded.util.ConfigHandler;
import mod.uhcreloaded.util.Misc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModdedGoldenStuff {

    public static void removeEnhancedGoldenApple() {
        if (!ConfigHandler.allowEnchantedGoldenApple) {
            Misc.removeRecipe(new ItemStack(Items.golden_apple, 1, 1));
        }
    }

    public static void harderGoldenCarrot() {
        if (ConfigHandler.harderGoldenCarrot) {
            Misc.removeRecipe(new ItemStack(Items.golden_carrot));
            GameRegistry.addShapedRecipe(new ItemStack(Items.golden_carrot),
                    "GGG", "GCG", "GGG", 'G', Items.gold_ingot, 'C', Items.carrot);
        }
    }

    public static void harderGlisteringMelon() {
        if (ConfigHandler.harderGlisteringMelon) {
            Misc.removeRecipe(new ItemStack(Items.speckled_melon));
            GameRegistry.addShapelessRecipe(
                    new ItemStack(Items.speckled_melon),
                    Items.melon, Blocks.gold_block);
        }
    }

    /** Make the player drop a skull. */
    @SubscribeEvent
    public void onPlayerDeath(PlayerDropsEvent e) {
        e.entityPlayer.dropItem(
                Misc.getSkullFromOwner(e.entityPlayer.getGameProfile()), true,
                false);
    }

    //@SubscribeEvent TODO: Remove this!!!
    public void onPlayerEatGoldenApple(PlayerUseItemEvent.Finish e) {
        EntityPlayer currentPlayer = e.entityPlayer;
        if (!ConfigHandler.allowGoldenAppleRegen) {
            if (e.item.getItem().equals(Items.golden_apple)
                    && e.item.getMetadata() == 0) {
                if (e.item.getDisplayName().equals(EnumChatFormatting.ITALIC.toString()
                        + EnumChatFormatting.GOLD + "Golden Skull")) { // Could be hacked
                    currentPlayer.clearActivePotions();
                    currentPlayer.heal(8.0F);
                } else {
                    currentPlayer.clearActivePotions();
                    currentPlayer.heal(4.0F);
                }
            } else {
                if (ConfigHandler.antiCheatMode
                        && e.item.getItem().equals(Items.golden_apple)
                        && e.item.getMetadata() == 1) {
                    currentPlayer.clearActivePotions();
                    currentPlayer.addChatComponentMessage(new ChatComponentText("DO NOT CHEAT!!!"));
                }
            }
        }
    }
}
