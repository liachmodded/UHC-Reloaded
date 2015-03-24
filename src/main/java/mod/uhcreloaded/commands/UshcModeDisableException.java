package mod.uhcreloaded.commands;

import net.minecraft.command.CommandException;

public class UshcModeDisableException extends CommandException{

	private static final long serialVersionUID = 1L;

	public UshcModeDisableException(String message, Object[] objects) {
		super(message, objects);
	}
	
	private static final String err = "USHC Mode disabled. Open it in config file.";
	
	public UshcModeDisableException(){
		this(err, new Object[0]);
	}

}
