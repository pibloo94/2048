package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class GameException extends Exception {

	/**
	 * Constructor de la clase GameException sin parametros
	 */
	public GameException() {
		super();
	}
	
	/**
	 * Constructor de la clase GameException
	 * @param message tipo String
	 */
	public GameException(String message) {
		super(message);
	}
}
