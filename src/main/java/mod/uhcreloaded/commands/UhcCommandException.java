package mod.uhcreloaded.commands;

import net.minecraft.command.CommandException;

public class UhcCommandException extends CommandException {

	private static final long serialVersionUID = 1L;
	
	public UhcCommandException(String message) {
		super(message, new Object[0]);
	}

}
