package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class UndoException extends Exception {

	/**
	 * Constructor de la clase UndoException sin parametros
	 */
	public UndoException(){
		super();
	}

	/**
	 * Constructor de la clase UndoException
	 * @param message tipo String
	 */
	public UndoException(String message) {
		super(message);
	}
}
