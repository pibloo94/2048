package tp.pr2.control.commands;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public abstract class NoParamsCommand extends Command{

	/**
	 * constructor de la clase NoParamsCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if (commandWords.length == 1) {
			if (commandWords[0].equalsIgnoreCase(commandName)) {
				return this;
			}
		}
		return command;
	}
}
