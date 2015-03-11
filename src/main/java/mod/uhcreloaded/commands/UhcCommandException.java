package mod.uhcreloaded.commands;

import net.minecraft.command.CommandException;

public class UhcCommandException extends CommandException {

	/**
	 * Who can explain what is it for any way?! ---3tusk
	 */
	private static final long serialVersionUID = 1L;

	public UhcCommandException(String message, Object... objects) {
		super(message, objects);
	}

	private final static String err = "Error: Invaild command/arugment!";

	public UhcCommandException() {
		this(err, new Object[0]);
	}

}
