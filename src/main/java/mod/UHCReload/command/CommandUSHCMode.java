package mod.UHCReload.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandUSHCMode extends CommandBase{

	private static final String NAME = "ushcmode";
	private static final String USAGE = "/ushcmode <on/off>";
	private static final long NIGHT_TIME = 16000;
	private static final long DAY_TIME = 0;
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public int getRequiredPermissionLevel(){
		return 0;
	}

    public boolean canCommandSenderUse(ICommandSender sender)
    {
        return true;
    }
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return USAGE;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		GameRules gamerules = MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
		int l = args.length;
		
		if (l > 2 || l < 1){
			throw new UHCCommandException();
		}
		
		if (l == 1){
			String mode = args[0];
			if (mode.equals("on")){
				gamerules.setOrCreateGameRule("naturalRegeneration", "false");
				gamerules.setOrCreateGameRule("doDaylightCycle", "false");
				MinecraftServer.getServer().worldServers[0].setWorldTime(NIGHT_TIME);
				sender.addChatMessage(new ChatComponentText("Successfully open USHC"));
			}
			
			if (mode.equals("off")){
				gamerules.setOrCreateGameRule("naturalRegeneration", "true");
				gamerules.setOrCreateGameRule("doDaylightCycle", "true");
				MinecraftServer.getServer().worldServers[0].setWorldTime(DAY_TIME);
				sender.addChatMessage(new ChatComponentText("Successfully close USHC"));
			}
			
			if (!mode.equals("on") && !mode.equals("off")){
				sender.addChatMessage(new ChatComponentText("Wrong argument, please check!"));
				sender.addChatMessage(new ChatComponentText("parameter: /uhcmode <on/off>"));
			}
		}
		
		
	}

}
