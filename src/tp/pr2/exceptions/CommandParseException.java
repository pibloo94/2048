package tp.pr2.exceptions;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class CommandParseException extends Exception {

	/**
	 * Constructor de la clase CommandParseException sin parametros
	 */
	public CommandParseException() {
		super();
	}
	
	/**
	 * Constructor de la clase CommandParseException
	 * @param message tipo String
	 */
	public CommandParseException(String message) {
		super(message);
	}
}
