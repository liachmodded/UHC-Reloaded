package mod.UHCReload.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandUHCHelp extends CommandBase{

	@Override
	public String getName() {
		return "uhchelp";
	}
	
	@Override
	public int getRequiredPermissionLevel(){
		return 0;
	}
	
	@Override
    public boolean canCommandSenderUse(ICommandSender sender)
    {
        return true;
    }

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/uhchelp";
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		sender.addChatMessage(new ChatComponentText("Enter the following page to learn more!"));
		sender.addChatMessage(new ChatComponentText("http://www.reddit.com/r/ultrahardcore/wiki/playerfaq"));
	}

}
