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

package com.github.liachmodded.uhcreloaded.forge.commands;

import static com.github.liachmodded.uhcreloaded.forge.util.Misc.translate;

import com.github.liachmodded.uhcreloaded.forge.util.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameRules;
import net.minecraft.world.storage.WorldInfo;

public class CommandUshcMode extends CommandBase {

    private static final String NAME = "ushcmode";
    private static final String USAGE = "/ushcmode <on/off>";
    private static final long NIGHT_TIME = 16000;
    private static final long DAY_TIME = 0;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!ConfigHandler.allowUSHCCommand) {
            throw new UhcCommandException(translate("commands.uhcreloaded.error.disabled"));
        }

        int l = args.length;

        if (l == 1) {
            String mode = args[0];

            if (mode.equalsIgnoreCase("on")) {
                for (int a = 0; a < server.worlds.length; a++) {
                    WorldInfo info = server.worlds[a].getWorldInfo();
                    GameRules gamerules = server.worlds[a].getGameRules();
                    info.setHardcore(true);
                    gamerules.setOrCreateGameRule("naturalRegeneration", "false");
                    gamerules.setOrCreateGameRule("doDaylightCycle", "false");
                    server.worlds[a].setWorldTime(NIGHT_TIME);
                }
                sender.sendMessage(new TextComponentString(translate("commands.uhcreloaded.ushc.on")));
                notifyCommandListener(sender, this, "[UHC Reloaded] UltraSuperHardcore mode: ON.");
            }

            if (mode.equalsIgnoreCase("off")) {
                for (int a = 0; a < server.worlds.length; a++) {
                    WorldInfo info = server.worlds[a].getWorldInfo();
                    GameRules gamerules = server.worlds[a].getGameRules();
                    info.setHardcore(false);
                    gamerules.setOrCreateGameRule("naturalRegeneration", "true");
                    gamerules.setOrCreateGameRule("doDaylightCycle", "true");
                    server.worlds[a].setWorldTime(DAY_TIME);
                }
                sender.sendMessage(new TextComponentString(translate("commands.uhcreloaded.ushc.off")));
                notifyCommandListener(sender, this, "[UHC Reloaded] UltraSuperHardcore mode: OFF.");
            }

            throw new UhcCommandException(translate("commands.uhcreloaded.error.args"));
        }

    }

}
