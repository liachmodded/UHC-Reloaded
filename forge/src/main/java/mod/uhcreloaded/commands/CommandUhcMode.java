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

package mod.uhcreloaded.commands;

import static mod.uhcreloaded.util.Misc.translate;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;
import net.minecraft.world.storage.WorldInfo;

public class CommandUhcMode extends CommandBase {

    private static final String NAME = "uhcmode";
    private static final String USAGE = "/uhcmode <on/off>";

    @Override
    public String getCommandName() {
        return NAME;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public boolean canCommandSenderUse(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return USAGE;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        int l = args.length;

        if (l == 1) {
            String mode = args[0];
            if (mode.equalsIgnoreCase("on")) {
                for (int a = 0; a < MinecraftServer.getServer().worldServers.length; a++) {
                    WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
                    GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
                    info.setHardcore(true);
                    gamerules.setOrCreateGameRule("naturalRegeneration", "false");
                }
                sender.addChatMessage(new ChatComponentText(translate("commands.uhcreloaded.uhc.on")));
                notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: ON.", new Object[]{});
            }

            if (mode.equalsIgnoreCase("off")) {
                for (int a = 0; a < MinecraftServer.getServer().worldServers.length; a++) {
                    WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
                    GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
                    info.setHardcore(false);
                    gamerules.setOrCreateGameRule("naturalRegeneration", "true");
                }
                sender.addChatMessage(new ChatComponentText(translate("commands.uhcreloaded.uhc.off")));
                notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: OFF.", new Object[]{});
            }
        }

        throw new UhcCommandException(translate("commands.uhcreloaded.error.args"));
    }

}
