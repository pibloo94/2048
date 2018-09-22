package tp.pr2.control.commands;

import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class ExitCommand extends NoParamsCommand{
	
	private static String nameExit = "exit";
	private static String textExit = "Sale del juego";

	/**
	 * constructor vacio de la clase ExitCommand
	 */
	public ExitCommand() {
		super(nameExit, textExit);
	}

	/**
	 * metodo que ejecuta el comando exit
	 */
	public boolean execute(Game game) {
		try {
			game.exit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
