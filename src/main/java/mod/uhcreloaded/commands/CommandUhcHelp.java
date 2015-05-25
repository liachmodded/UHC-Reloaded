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
import net.minecraft.util.ChatComponentText;

public class CommandUhcHelp extends CommandBase {

	@Override
	public String getName() {
		return "uhchelp";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		return true;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/uhchelp";
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		int a = args.length;
		if (a == 1) {
			String arg = args[0];
			if (arg.equalsIgnoreCase("banrule") || arg.equalsIgnoreCase("banguideline")) {
				sender.addChatMessage(new ChatComponentText(translate("commands.uhcreload.help1")));
				sender.addChatMessage(new ChatComponentText("http://www.reddit.com/r/uhccourtroom/wiki/banguidelines"));
			} else {
				sender.addChatMessage(new ChatComponentText(translate("commands.uhcreload.help1")));
				sender.addChatMessage(new ChatComponentText("http://www.reddit.com/r/ultrahardcore/wiki/playerfaq"));
			}
		}
		
		throw new UhcCommandException(translate("commands.uhcreload.error.unknown"));
	}
}
