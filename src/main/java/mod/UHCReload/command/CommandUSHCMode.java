package mod.UHCReload.command;

import mod.UHCReload.util.configHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;
import net.minecraft.world.storage.WorldInfo;

public class CommandUSHCMode extends CommandBase{

	private static final String NAME = "ushcmode";
	private static final String USAGE = "/ushcmode <on/off> (Note: USHC means UHC+noDaylightCycle=true)";
	private static final long NIGHT_TIME = 16000;
	private static final long DAY_TIME = 0;
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public int getRequiredPermissionLevel(){
		return 2;
	}

    public boolean canCommandSenderUse(ICommandSender sender)
    {
        return configHandler.allowUSHCCommand;
    }
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return USAGE;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		int l = args.length;
		
		if (l > 2 || l < 1){
			throw new UHCCommandException();
		}
		
		if (l == 1){
			String mode = args[0];
			if (mode.equals("on")){
				for(int a = 0; a < MinecraftServer.getServer().worldServers.length; a++){
					WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
					GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
					info.setHardcore(true);
					gamerules.setOrCreateGameRule("naturalRegeneration", "false");
					gamerules.setOrCreateGameRule("doDaylightCycle", "false");
					MinecraftServer.getServer().worldServers[a].setWorldTime(NIGHT_TIME);
				}	
				sender.addChatMessage(new ChatComponentText("Successfully open USHC"));
				notifyOperators(sender, this, "[UHCReload]UltraSuperHardcore mode: ON.", new Object[] {});
			}
			
			if (mode.equals("off")){
				for(int a = 0; a < MinecraftServer.getServer().worldServers.length; a++){
					WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
					GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
					info.setHardcore(false);
					gamerules.setOrCreateGameRule("naturalRegeneration", "true");
					gamerules.setOrCreateGameRule("doDaylightCycle", "true");
					MinecraftServer.getServer().worldServers[a].setWorldTime(DAY_TIME);
				}
				sender.addChatMessage(new ChatComponentText("Successfully close USHC"));
				notifyOperators(sender, this, "[UHCReload]UltraSuperHardcore mode: OFF.", new Object[] {});
			}
			
			if (!mode.equals("on") && !mode.equals("off")){
				sender.addChatMessage(new ChatComponentText("Wrong argument, please check!"));
				sender.addChatMessage(new ChatComponentText("parameter: /uhcmode <on/off>"));
			}
		}
		
		
	}

}
