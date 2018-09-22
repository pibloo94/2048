package tp.pr2.control.commands;

import tp.pr2.exceptions.UndoException;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class UndoCommand extends NoParamsCommand{

	private static String nameUndo = "undo";
	private static String textUndo = "Deshace el movimiento.";
	public static final String UndoExceptionstr = "Cant do UndoCommand";

	
	/**
	 * constructor vacio de la clase UndoCommand
	 */
	public UndoCommand() {
		super(nameUndo, textUndo);
	}	

	/**
	 * constructor de la clase UndoCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public UndoCommand(String commandInfo, String helpInfo) {
		super(nameUndo, textUndo);
	}

	/**
	 * metodo que ejecuta el comando undo
	 * @throws UndoException 
	 */
	public boolean execute(Game game) throws UndoException {
		try {
			game.undo();
			return true;
		} catch (NullPointerException ne) {//lanzamos el error creado RedoException para realizar el SystemOutPrint en controller
			throw new UndoException(UndoExceptionstr);
		}
	}
}
