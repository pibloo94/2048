package tp.pr2.control.commands;

import java.text.ParseException;
import tp.pr2.exceptions.CommandParseException;
import tp.pr2.exceptions.MoveException;
import tp.pr2.exceptions.PlayException;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class CommandParser{

	private static Command[] availableCommands = {new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand(),new SaveCommand(), new LoadCommand()} ;
	private static final String ParseSErrorMsg = " ERROR: Comando desconocido";

	/**
	 * metodo que invoca el metodo parse de cada subclase de command
	 * @param commandWords de tipo String[]
	 * @param controller de tipo controller
	 * @return command
	 * @throws ParseException 
	 * @throws PlayException 
	 * @throws MoveException 
	 */
	public static Command parseCommand(String[] commandWords) throws CommandParseException, PlayException, MoveException{
		Command command = null;
		for (Command commandAvaiable : availableCommands) {
			command = commandAvaiable.parse(commandWords);
			if (command != null) {
				return command;
			}
		}
		throw new CommandParseException(ParseSErrorMsg);
	}

	/**
	 * metodo commandHelp que muestra la ayuda del command
	 * @return String
	 */
	public static String commandHelp() {
		String help = null;
		for (Command command : availableCommands) {
			help = command.helpText();
		}
		return help;
	}
}
