package mod.UHCReload.UHCrules;

import mod.UHCReload.util.configHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CancalEnderPeralDamage {

	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerUseEnderPeral(EnderTeleportEvent Event){
		if (Event.entity instanceof EntityPlayerMP){
			if (!configHandler.openEnderPearlFallingDamage){
				Event.attackDamage = 0.0F;
			}
		}
	}
}
