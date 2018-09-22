package tp.pr2.exceptions;

import java.lang.Exception;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class MoveException extends Exception {
	
	/**
	 * Constructor de la clase MoveException sin parametros
	 */
	public MoveException() {
		super();
	}
	
	/**
	 * Constructor de la clase MoveException
	 * @param message tipo String
	 */
	public MoveException(String message) {
		super(message);
	}
}
