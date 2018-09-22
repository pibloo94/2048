package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class RedoException extends Exception {
	
	/**
	 * Constructor de la clase RedoException sin parametros
	 */
	public RedoException(){
		super();
	}

	/**
	 * Constructor de la clase RedoException
	 * @param message tipo String
	 */
	public RedoException(String message) {
		super(message);
	}
}
