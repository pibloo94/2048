package tp.pr2.control.commands;

import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class HelpCommand extends NoParamsCommand{
	
	private static String nameHelp = "help";
	private static String textHelp = "Ayuda del juego";
	
	/**
	 * constructor vacio de la clase HelpCommand
	 */	
	public HelpCommand() {
		super(nameHelp, textHelp);
	}

	/**
	 * constructor de la clase HelpCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public HelpCommand(String commandInfo, String helpInfo) {
		super(nameHelp, textHelp);
	}

	/**
	 * metodo que ejecuta el comando help
	 */
	public boolean execute(Game game) {
		try {
			System.out.println(game.help());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
