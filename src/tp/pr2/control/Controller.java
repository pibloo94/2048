package tp.pr2.control;

import tp.pr2.exceptions.CommandParseException;
import tp.pr2.exceptions.GameException;
import java.io.IOException;
import java.util.Scanner;
import tp.pr2.control.commands.Command;
import tp.pr2.control.commands.CommandParser;
import tp.pr2.exceptions.MoveException;
import tp.pr2.exceptions.PlayException;
import tp.pr2.exceptions.RedoException;
import tp.pr2.exceptions.UndoException;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class Controller {

	/**
	 * clase para controlar la ejecucion del juego
	 */
	private Game game;
	private Scanner in;

	/**
	 * Constructor de Controller
	 * @param game de tipo Game 
	 * @param in de tipo Scanner
	 */
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}
	
	/**
	 * metodo getIn
	 * @return Scanner
	 */
	public Scanner getIn() {
		return in;
	}
	
	/**
	 * metodo que realiza la simulacion del juego
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws PlayException 
	 * @throws MoveException 
	 */
	public void run(){
		String[] action;
		Command command;
		boolean finished = false;

		do{
			try {			
				System.out.print("Comand > ");
				action = in.nextLine().toLowerCase().trim().split("\\s");
				command = CommandParser.parseCommand(action);
				if (command.execute(game) && !action[0].equals("exit")) {
					System.out.println(game.toString());
				}
			} catch (PlayException | CommandParseException | MoveException | RedoException | UndoException  e ) {
				System.out.println(e.getMessage());
			}
			
			try {
				game.finishGame();
			}catch(GameException ge) {
				finished = true;
				System.out.println(ge.getMessage());
			}
			
		} while (!finished);
		
		if (game.getWin()) {
			System.out.println("***Congratulations, YOU WIN***");
		} else if (game.getLose()) {
			System.out.println("***Game Over***");
		}
	}
}