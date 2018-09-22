package tp.pr2.logic.multigames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import tp.pr2.exceptions.ErrorFileException;
import tp.pr2.exceptions.GameException;
import tp.pr2.exceptions.PlayException;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class Game{
		
	/**
	 * clase que se encarga de la gestion del juego
	 */
	
	private Board _board;
	private Random myRandom;
	private GameHistory gameStack;
	private GameState gameState;
	private GameType gameType;
	private int _size, _initCells, cuentaUndo;
	private boolean win, lose, exit;
	
	private static final String gameWin = "Salida por --> Juego ganado.";
	private static final String gameLose = "Salida por --> Juego perdido.";
	private static final String gameExit = "Salida por --> Juego abandonado.";
	
	
	/**
	 * Constructor de la clase Game
	 * @param _size tipo int
	 * @param _initCells tipo int
	 * @param _myRandom tipon Random
	 * @param rules tipo GameRules
	 */
	public Game(int _size, int _initCells, Random _myRandom, GameType gameType) { //falta un atributo
		this._size = _size;
		this.myRandom = _myRandom;
		this._initCells = _initCells;
		this.gameStack = new GameHistory();
		this.cuentaUndo = 0;
		this.gameType = gameType;
		this._board = gameType.getRules().createBoard(_size);
		reset();
	}
	
	/**
	 * metodo getWin
	 * @return boolean
	 */
	public boolean getWin() {
		return win;
	}
	
	/**
	 * metodo getLose
	 * @return boolean
	 */
	public boolean getLose() {
		return lose;
	}
	
	/**
	 * metodo get_size
	 * @return int
	 */
	public int get_size() {
		return _size;
	}

	
	/**
	 * metodo que realiza los movimientos en la direccion deseada
	 * @param dir tipo Direction
	 */
	public void move(Direction dir) {
		gameState = getState();
		
		if (cuentaUndo > 0) {
			gameStack.emptyStackUndo();
			gameStack.emptyStackRedo();
			cuentaUndo = 0;
		}
		// Realizamos movimiento
		_board.executeMove(dir, gameType.getRules());

		// Comprobamos que se ha ganado
		if (gameType.getRules().win(_board)) {
			win = true;
		}

		// Comprobamos si hay mov o displaz para generar aleatorio
		if (_board.get_boardMoved()) {
			gameType.getRules().addNewCell(_board, myRandom);
			// introducimos nuevo estado y movemos indice
			gameStack.pushUndo(gameState);
		}

		// comprobamos si se ha perdido
		if (gameType.getRules().lose(_board)) {
			lose = true;
		}
		_board.set_boardMoved(false);

		cuentaUndo = 0;
		
	}
	
	/**
	 * metodo que resetea la partida
	 */
	public void reset() {
		cuentaUndo = 0;
		gameStack.emptyStackRedo();
		gameStack.emptyStackUndo();
		_board = new Board(_size, gameType.getRules());
		gameType.getRules().initBoard(_board, _initCells,myRandom);
	}
	
	/**
	 * metodo que muestra la ayuda del juego
	 */
	public String help() {
		return "Reset: start a new game\r\nHelp: print this help message\r\nExit: terminate the program\nUndo: undo the last command\nRedo: redo the undo command\nMove <direction>: execute a move in one of the four directions, up, down, left, right\r\nPlay <game>: start a new game of one of the game types: original, fib, inverse";
	}
	
	/**
	 * muestra el estado del juego (tablero + puntuacion)
	 * @return String
	 */
	public String toString(){
		return _board.toString() + _board.getMoveResults().toString();
	}
	
	/**
	 * metodo que imprime el mensaje de game over
	 */
	public boolean exit() {
		exit = true;
		return exit;
	}
	
	/**
	 * metodo para deshacer un movimiento
	 */
	public void undo(){
		int i = gameStack.getIndexUndo();
		if (i == 0) {
			i = gameStack.getIndexUndo();
		}
		GameState aux = getState();
		try {
		setState(gameStack.popUndo());
		gameStack.pushRedo(aux);
		gameStack.showUndo();
		cuentaUndo++;
		}
		catch(NullPointerException ne) {//lanzamos otro NullPointerException para controlarlo
			throw new NullPointerException();
		}

	}

	/**
	 * metodo para rehacer un movimiento
	 * @throws RedoException 
	 */
	public void redo(){
		int i = gameStack.getIndexRedo();
		if (i == 0) {
			i = gameStack.getIndexRedo();
		}
		GameState aux = getState();
		try {
			setState(gameStack.popRedo());
			gameStack.pushUndo(aux);
			gameStack.showRedo();
		} catch (NullPointerException re) {//lanzamos otro NullPointerException para controlarlo
			throw new NullPointerException();
		}
	}
	
	/**
	 * metodo que devuelve el estado actual del juego, invocando, para ello, el metodo getState de la clase Board
	 * @return GameState
	 */
	public GameState getState() {
		return new GameState(new MoveResults(_board.getMoveResults().getHighest(), _board.getMoveResults().getScore()), _board.getState());
	}
	
	/**
	 * metodo que restablece el juego al que determina el estado pasado como argumento
	 * @param aState tipo GameState
	 */
	public void setState(GameState aState) {
		Position position;
		
		// actualizamos tablero
		for (int i = 0; i < _size; i++) {
			for (int j = 0; j < _size; j++) {
				position = new Position(i, j);
				_board.setCell(position, aState.getBoardState()[i][j]);
			}
		}
		// actualizamos puntuacion
		_board.setMoveResults(aState.getMoveResults());
	}
	
	/**
	 * metodo finishGame que lanza una exception indicando el tipo de salida del juego
	 * @throws GameException
	 */
	public void finishGame() throws GameException {
		if(win || lose || exit) {
			if (win) {
				throw new GameException(gameWin);
			}
			else if (lose) {
				throw new GameException(gameLose);
			}
			else if (exit) {
				throw new GameException(gameExit);
			}
			throw new GameException(gameWin);	
		} 
	}
	
	/**
	 * metodo para ejecutar el comando play
	 * @param gameType tipo GameType
	 * @param rules tipo GameRules
	 * @param _size tipo int
	 * @param _initCells tipo int
	 * @param random tipo Random
	 */
	public void play(GameType gameType, int _size, int _initCells, Random random) {
		this._size = _size;
		this.myRandom = random;
		this._initCells = _initCells;
		this.gameType = gameType;
		this._board = gameType.getRules().createBoard(_size);
		this.gameStack = new GameHistory();
		this.cuentaUndo = 0;
		reset();
	}
		
	/**
	 * metodo para ejecutar el comando store
	 * @throws PlayException 
	 * @throws FileException 
	 */
	public void store(BufferedWriter bfw) throws PlayException {
		String infoGame = null;
		
		try {
			_board.store( bfw);
			infoGame =  _initCells + " " + _board.getMoveResults().getScore() + " " + gameType.externalise();
			bfw.write(infoGame);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * metodo para ejecutar el comando load
	 * @return GameType 
	 * @throws PlayException 
	 * @throws  
	 * @throws IOException 
	 */
	public GameType load(BufferedReader bfr) throws PlayException {
		String [] aux = null;
		
		try {
			gameState = getState();
			_board.load(bfr);
			aux = bfr.readLine().split("\\s");
			_initCells = Integer.parseInt(aux[0]);
			gameType = GameType.parse(aux[2]);
			_board.setMoveResults(new MoveResults(gameType.getRules().getWinValue(_board),  Integer.parseInt(aux[1])));
			_size = _board.get_boardSize();
			gameStack.emptyStackUndo();
			gameStack.emptyStackRedo();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}catch (ErrorFileException efe) {
			efe.getMessage();
			System.out.println(" ERROR: Tablero alterado --> Volviendo a la partida anterior");
			setState(gameState);
		}
		return gameType;
	}

}
