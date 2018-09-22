package tp.pr2.control.commands;

import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class ResetCommand extends NoParamsCommand{

	private static String nameReset = "reset";
	private static String textReset = "Resetea el juego.";
	
	/**
	 * constructor vacio de la clase ResetCommand
	 */
	public ResetCommand() {
		super(nameReset, textReset);
	}

	/**
	 * constructor de la clase ResetCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public ResetCommand(String commandInfo, String helpInfo) {
		super(nameReset, textReset);
	}
	
	/**
	 * metodo que ejecuta el comando reset
	 */
	public boolean execute(Game game){
		try {
			game.reset();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
