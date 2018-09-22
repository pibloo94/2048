package tp.pr2.control.commands;

import tp.pr2.exceptions.MoveException;
import tp.pr2.logic.multigames.Direction;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class MoveCommand extends Command{
	
	private static String nameMove = "move";
	private static String textMove = "Movimiento sobre el tablero.";
	private static final String moveCommandErrorMsg = " ERROR: Unknown direction";
	private Direction direction;

	/**
	 * constructor vacio de la clase MoveCommand
	 */
	public MoveCommand() {
		super(nameMove, textMove);
	}

	/**
	 * constructor de la clase MoveCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 * @param direction de tipo Direction
	 */
	public MoveCommand(Direction direction) {
		super(nameMove, textMove);
		this.direction = direction;
	}

	@Override
	public Command parse(String[] commandWords) throws MoveException{
		Command command = null;

		if (commandWords.length == 2 && commandWords[0].equals(nameMove)) {
			switch (commandWords[1]) {
			case "up":
				command = new MoveCommand(Direction.UP);
				break;

			case "down":
				command = new MoveCommand(Direction.DOWN);
				break;

			case "right":
				command = new MoveCommand(Direction.RIGHT);
				break;

			case "left":
				command = new MoveCommand(Direction.LEFT);
				break;
			default:
				throw new MoveException(moveCommandErrorMsg);
			}
		}
		
		return command;
	}
	
	/**
	 * metodo que ejecuta el comando move
	 */
	public boolean execute(Game game) {
		try {
			game.move(direction);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
