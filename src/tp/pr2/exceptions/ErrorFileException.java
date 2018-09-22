package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class ErrorFileException extends Exception {

	/**
	 * Constructor de la clase GameException sin parametros
	 */
	public ErrorFileException() {
		super();
	}
	
	/**
	 * Constructor de la clase GameException
	 * @param message tipo String
	 */
	public ErrorFileException(String message) {
		super(message);
	}
}
