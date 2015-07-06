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
	public String getName() {
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
	public void execute(ICommandSender sender, String[] args) throws CommandException {
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
				notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: ON.", new Object[] {});
			}

			if (mode.equalsIgnoreCase("off")) {
				for (int a = 0; a < MinecraftServer.getServer().worldServers.length; a++) {
					WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
					GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
					info.setHardcore(false);
					gamerules.setOrCreateGameRule("naturalRegeneration", "true");
				}
				sender.addChatMessage(new ChatComponentText(translate("commands.uhcreloaded.uhc.off")));
				notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: OFF.", new Object[] {});
			}
		}
		
		throw new UhcCommandException(translate("commands.uhcreloaded.error.args"));
	}

}
