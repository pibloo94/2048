package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class PlayException extends Exception{

	/**
	 * Constructor de la clase PlayException sin parametros
	 */
	public PlayException(){
		super();
	}

	/**
	 * Constructor de la clase PlayException
	 * @param message tipo String
	 */
	public PlayException(String message) {
		super(message);
	}
}
