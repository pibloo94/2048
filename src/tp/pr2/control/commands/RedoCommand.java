package tp.pr2.control.commands;

import tp.pr2.exceptions.RedoException;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class RedoCommand extends NoParamsCommand{
	
	private static String nameRedo = "redo";
	private static String textRedo = "Rehace el movimiento.";
	public static final String RedoExceptionstr = "Cant do RedoCommand";

	
	/**
	 * constructor vacio de la clase RedoCommand
	 */
	public RedoCommand() {
		super(nameRedo, textRedo);	
	}

	/**
	 * constructor de la clase RedoCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public RedoCommand(String commandInfo, String helpInfo) {
		super(nameRedo, textRedo);
	}

	/**
	 * metodo que ejecuta el comando redo
	 * @throws RedoException 
	 */
	public boolean execute(Game game) throws RedoException {
		try {
			game.redo();
			return true;
		} catch (NullPointerException ne) {//lanzamos el error creado RedoException para realizar el SystemOutPrint en controller
			throw new RedoException(RedoExceptionstr);
		}
	}
}
