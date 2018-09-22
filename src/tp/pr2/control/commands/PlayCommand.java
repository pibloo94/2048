package tp.pr2.control.commands;

import java.util.Random;
import java.util.Scanner;
import tp.pr2.exceptions.PlayException;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.multigames.GameType;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class PlayCommand extends Command {
	
	protected int randomSeed ,boardSize , initialCells;
	protected GameType gameType;
	private static String namePlay = "play";
	private static String textPlay = "Modo de juego.";
	public static final String PlayException = "Game unknown";
	public static final String PlayException2 = "Choose a game: " + GameType.externaliseAll();
	private Scanner aux;
	
	/**
	 * constructor vacio de la clase PlayCommand
	 */
	public PlayCommand() {
		super(namePlay, textPlay);
	}
	
	/**
	 * constructor de la clase PlayCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 * @param gameType de tipo GameType
	 * @param gameRules de tipo GameRules
	 */
	public PlayCommand(String commandInfo, String helpInfo, GameType gameType) {
		super(commandInfo, helpInfo);
		this.gameType = gameType;
	}

	@Override
	public Command parse(String[] commandWords) throws PlayException {
		Command command = null;

		if (commandWords.length >= 2 && commandWords[0].equals(namePlay)) {
			try {
				String newMessage = "";
				for(int i = 1; i < commandWords.length;i++) {
					newMessage = newMessage + commandWords[i];
				}
				gameType = GameType.parse(newMessage);
				command = new PlayCommand(namePlay, textPlay, gameType);

			} catch(PlayException pe) {
				throw new PlayException(PlayException);
			}
		}else if (commandWords.length == 1 && commandWords[0].equals(namePlay)) {
			throw new PlayException(PlayException2);
		}
		return command;
	}
	
	/**
	 * metodo que ejecuta el comando play
	 */
	public boolean execute(Game game) throws PlayException {
		String line;
		aux = new Scanner(System.in);
		boolean valido = true;
		
		//Leemos y establecemos el tamano del tablero
		do {
			System.out.print("Please enter the size of the board:");
			line = aux.nextLine();
			if(line.equals("")){
				System.out.println("Using the default size of the board: 4");
				boardSize=4;
			} else {
				do {
					valido = true;
					try {
						boardSize = Integer.parseInt(line);
					} catch (NumberFormatException nfe) {
						System.err.println("\nThe size of the board must be a number");
						System.out.print("Please enter the size of the board:");
						line = aux.nextLine();
						valido = false;
					}
				} while (!valido);
			}
			if (boardSize <= 0) {
				System.out.println("The size must be a positive integer");
			}
		} while (boardSize <= 0);
		
		//Leemos y estableces numero de celdas iniciales
		do {
			System.out.print("Please enter the number of initial cells:");
			line = aux.nextLine();
			if(line.equals("")){
				System.out.println("Using the default number of initial cells: 2");
				initialCells=2;
			}else {
				do {
					valido = true;
					try {
						initialCells= Integer.parseInt(line);
					} catch (NumberFormatException nfe) {
						System.err.println("\nThe size of the board must be a number");
						System.out.print("Please enter the number of initial cells:");
						line = aux.nextLine();
						valido = false;
					}
				} while (!valido);
				
			}
			if (initialCells > boardSize*boardSize) {
				System.out.println("The number of initial cells must be less than the number of cells on the board");
			}
			else if(initialCells <= 0) {
				System.out.println("The number of initial cells must be more than 0");
			}
		}while(initialCells > boardSize*boardSize || initialCells <=0);
		
		//Leemos y estableces el caracter aleatorio del juego
		System.out.print("Please enter the seed for the pseudo-random number generator:");
		line = aux.nextLine();
		if(line.equals("")){
			System.out.println("Using the default number of initial cells: 924");
			randomSeed=924;
		}else {
			do {
				valido = true;
				try {
					randomSeed= Integer.parseInt(line);
				} catch (NumberFormatException nfe) {
					System.err.println("\nThe seed for the pseudo-random number generator must be a number");
					System.out.print("Please enter the seed for the pseudo-random number generator:");
					line = aux.nextLine();
					valido = false;
				}
			} while (!valido);
		}
		
		System.out.println(gameType.getRules().toString());
		game.play(gameType, boardSize, initialCells, new Random(randomSeed));
		System.out.println(game.toString());
		return false;
	}

}
