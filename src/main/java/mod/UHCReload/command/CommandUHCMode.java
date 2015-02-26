package mod.UHCReload.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;
import net.minecraft.world.storage.WorldInfo;

public class CommandUHCMode extends CommandBase{

	private static final String NAME = "uhcmode";
	private static final String USAGE = "/uhcmode <on/off>";
	
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
        return true;
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
			if (mode.equalsIgnoreCase("on")){
				for(int a = 0; a < MinecraftServer.getServer().worldServers.length; a++){
					WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
					GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
					info.setHardcore(true);
					gamerules.setOrCreateGameRule("naturalRegeneration", "false");
				}	
				sender.addChatMessage(new ChatComponentText("Successfully open UHC"));
				notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: ON.", new Object[] {});
			}
			
			if (mode.equalsIgnoreCase("off")){
				for(int a = 0; a < MinecraftServer.getServer().worldServers.length; a++){
					WorldInfo info = MinecraftServer.getServer().worldServers[a].getWorldInfo();
					GameRules gamerules = MinecraftServer.getServer().worldServers[a].getGameRules();
					info.setHardcore(false);
					gamerules.setOrCreateGameRule("naturalRegeneration", "true");
				}	
				sender.addChatMessage(new ChatComponentText("Successfully close UHC"));
				notifyOperators(sender, this, "[UHCReload]UltraHardcore mode: OFF.", new Object[] {});
			}
			
			if (!mode.equalsIgnoreCase("on") && !mode.equalsIgnoreCase("off")){
				sender.addChatMessage(new ChatComponentText("Wrong argument, please check!"));
				sender.addChatMessage(new ChatComponentText("parameter: /uhcmode <on/off>"));
			}
		}
		
	}

}
