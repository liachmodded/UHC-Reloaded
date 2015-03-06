package mod.uhcreloaded.rules;

import mod.uhcreloaded.util.ConfigHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CancalEnderPeralDamage {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerUseEnderPeral(EnderTeleportEvent Event) {
		if (Event.entity instanceof EntityPlayerMP) {
			if (!ConfigHandler.openEnderPearlFallingDamage) {
				Event.attackDamage = 0.0F;
			}
		}
	}
}
