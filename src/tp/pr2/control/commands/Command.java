package tp.pr2.control.commands;

import java.io.IOException;
import tp.pr2.exceptions.CommandParseException;
import tp.pr2.exceptions.MoveException;
import tp.pr2.exceptions.PlayException;
import tp.pr2.exceptions.RedoException;
import tp.pr2.exceptions.UndoException;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;

	/**
	 * constructor de la clase Command
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public Command(String commandInfo, String helpInfo) {
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}

	/**
	 * metodo abstracto execute 
	 * @param game de tipo Game 
	 * @param controller de tipo Controller
	 * @throws PlayException, ParseException 
	 * @throws UndoException 
	 * @throws RedoException 
	 * @throws IOException 
	 */
	public abstract boolean execute(Game game) throws PlayException, CommandParseException, UndoException, RedoException;

	/**
	 * metodo abstracto parse
	 * @param commandWords de tipo String[]
	 * @param controller de tipo Controller
	 * @return command
	 */
	public abstract Command parse(String[] commandWords) throws PlayException, MoveException;

	/**
	 * metodo helpText
	 * @return String
	 */
	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
	
	/**
	 * metodo comprobeFileName que comprueba que el nombre del fichero no contenga caracteres incorrectos
	 * @param fileName
	 * @return valid
	 */
	public boolean comprobeFileName(String fileName) {
		boolean valid = true;
		char [] invalid = {'!','"','|','$','%','&','/','(',')','=','?','¿','¡','ª','º','^','`','+','-','*','´','_','@'};
		
		for (int i = 0; i < fileName.length(); i++) {
			for (int j = 0; j < invalid.length; j++) {
				if (valid) {
					if (fileName.charAt(i) == invalid[j]) {
						valid = false;
						System.out.println("Invalid filename: the filename contains invalid characters");
						return valid;
					}
				}
			}
		}
		return valid;
	}
}