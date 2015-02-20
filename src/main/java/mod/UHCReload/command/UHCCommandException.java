package mod.UHCReload.command;

import net.minecraft.command.CommandException;

public class UHCCommandException extends CommandException{

	/**
	 * Who can explain what is it for any way?! ---3tusk
	 */
	private static final long serialVersionUID = 1L;

	public UHCCommandException(String message, Object ... objects) {
		super(message, objects);
	}
	
	private final static String err = "Error: Invaild command/arugment!";
	
	public UHCCommandException(){
		this(err, new Object[0]);
	}

}
