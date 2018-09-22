package tp.pr2.logic.multigames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import tp.pr2.exceptions.ErrorFileException;
import tp.pr2.exceptions.PlayException;
import tp.pr2.util.MyStringUtils;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class Board {

	/**
	 * clase que almacena el estado de un tablero y proporciona metodos necesarios para la gestion de dicho estado
	 */
	
	private Cell[][] _board;
	private int _boardSize;
	private MoveResults moveResults;
	private boolean _boardMoved;
	private GameState gameState;

	private static String firstLine =  "This file stores a saved 2048 game";
	private static String errorFile =  "This file is invalid";

	/**
	 * constructor de la clase Board
	 * @param _boardSize de tipo int
	 * @param currentRules de tipo GameRules
	 */
	public Board(int _boardSize, GameRules currentRules) {
		this._boardSize = _boardSize;
		this._board = new Cell[_boardSize][_boardSize];
		this.moveResults = new MoveResults(0, 0);
		this._boardMoved = false;
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				_board[i][j] = new Cell(0);
			}
		}
	}

	//getters
	
	/**
	 * metodo getMoveResults
	 * @return moveResults
	 */
	public MoveResults getMoveResults() {
		return moveResults;
	}

	/**
	 * metodo get_boardMoved
	 * @return _boardMoved
	 */
	public boolean get_boardMoved() {
		return _boardMoved;
	}
	/**
	 * metodo get_boardSize
	 * @return _boardSize
	 */
	public int get_boardSize() {
		return _boardSize;
	}
	// setters
	
	/**
	 * metodo set_boardMoved
	 * @param _boardMoved de tipo boolean
	 */
	public void set_boardMoved(boolean _boardMoved) {
		this._boardMoved = _boardMoved;
	}
	
	/**
	 * metodo setMoveResults
	 * @param moveResults de tipo MoveResults
	 */
	public void setMoveResults(MoveResults moveResults) {
		this.moveResults = moveResults;
	}
	
	/**
	 * metodo setCell
	 * @param pos de tipo Position
	 * @param value de tipo int
	 */
	public void setCell(Position pos, int value) {
		_board[pos.getFila()][pos.getColumna()].setValue(value);
	}

	/**
	 * metodo que muestra el tablero
	 */
	public String toString() {
		String result = "";
		int cellSize = 7;
		String vDelimiter = "|";
		String hDelimiter = "-";
		for (int i = 0; i < _boardSize; i++) {
			result = result + MyStringUtils.repeat(hDelimiter, (cellSize + 1) * _boardSize) + "\n";
			for (int j = 0; j < _boardSize; j++) {
				result = result + vDelimiter + MyStringUtils.centre(_board[i][j].toString(), cellSize);
			}
			result = result + vDelimiter + "\n";
		}
		result = result + MyStringUtils.repeat(hDelimiter, (cellSize + 1) * _boardSize) + "\n";
			return result;
	}

	/**
	 * metodo para trasponer matriz
	 */
	public void trasponerBoard() {
		Cell[][] _boardAux = new Cell[_boardSize][_boardSize];
		
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				_boardAux[j][i] = _board[i][j];
			}
		}
		_board = _boardAux;
	}

	/**
	 * metodo para invertir matriz
	 */
	public void invertirBoard() {
		Cell[][] _boardAux = new Cell[_boardSize][_boardSize];

		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				_boardAux[_boardSize - 1 - i][j] = _board[i][j];
			}
		}
		_board = _boardAux;
	}

	/**
	 * metodo que ejecuta un movimiento
	 * @param dir de tipo Direction
	 * @param rules de tipo GameRules
	 * @return moveResults
	 */
	public MoveResults executeMove(Direction dir, GameRules rules) {
		switch (dir) {
		case UP:
			displaceUp(rules);
			break;

		case DOWN:
			displaceDown(rules);
			break;

		case LEFT:
			displaceLeft(rules);
			break;

		case RIGHT:
			displaceRight(rules);
			break;
		}
		return moveResults;
	}

	/**
	 * metodo que encuentra la celda vecina fila
	 * @param i de tipo int
	 * @param j de tipo int
	 * @return i
	 */
	public int findNeighbourCell(int i, int j) {
		boolean encontrada = false;

		do {
			i--;
			if (_board[i][j].getValue() != 0) {
				encontrada = true;
			}
		} while (i > 0 && !encontrada);
		return i;
	}

	/**
	 * metodo que genera el nuevo valor de una celda a partir de un movimiento
	 * @param celAct de tipo Cell
	 * @param celVec de tipo Cell
	 * @param i de tipo int
	 * @param j de tipo int
	 */
	public void newValueCell(Cell celAct, Cell celVec, int i, int j,GameRules rules) {
		int aux, valueMerge;
		valueMerge = rules.merge(celAct, celVec);
		if (valueMerge > 0) { // se fusionan
			moveResults = new MoveResults(rules.getWinValue(this),moveResults.getScore() + valueMerge);
			_boardMoved = true;
		} else { // no se fusionan
			if (celVec.getValue() == 0 && celAct.getValue() != 0 ) {
				celVec.setValue(celAct.getValue());
				celAct.setValue(0);
				_boardMoved = true;
			} else if (celVec.getValue() != 0 && celAct.getValue() != 0 && _board[i + 1][j].getValue() != celAct.getValue()) {
				aux = celAct.getValue();
				celAct.setValue(0);
				celAct = _board[i + 1][j];
				celAct.setValue(aux);
				_boardMoved = true;
			}
		}
	}
	
	/**
	 * metodo que pone a false el movimiento de las celdas del tablero
	 */
	public void restartFushed() {
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
					_board[i][j].setFushed(false);
			}
		}
	}
	
	/**
	 * metodo que desplaza hacia arriba
	 */
	public void displaceUp(GameRules rules) {
		Cell celAct, celVec;
		
		for (int i = 1; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
					celAct = _board[i][j];
					celVec = _board[findNeighbourCell(i, j)][j];
					newValueCell(celAct, celVec, findNeighbourCell(i, j), j,rules);
			}
		}
		restartFushed();
	}
	
	/**
	 * metodo que desplaza hacia abajo
	 */
	public void displaceDown(GameRules rules) {
		invertirBoard();
		displaceUp(rules);
		invertirBoard();
	}
	
	/**
	 * metodo que desplaza hacia la izquierda
	 */
	public void displaceLeft(GameRules rules) {
		trasponerBoard();
		displaceUp(rules);
		trasponerBoard();
	}
	
	/**
	 * metodo que desplaza hacia la derecha
	 */
	public void displaceRight(GameRules rules) {
		trasponerBoard();
		displaceDown(rules);
		trasponerBoard();
	}
	
	/**
	 * metodo que representa de forma compacta el estado del tablero
	 * @return _boardStateAux
	 */
	public int[][] getState(){
		int[][] _boardStateAux = new int[_boardSize][_boardSize];
		
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				_boardStateAux[i][j] = _board[i][j].getValue();
			}
		}
		return _boardStateAux;
	}
	
	/**
	 * metodo que establece el estado del tablero actual a partir de la representacion compacta
	 * @param aState int[][] 
	 */
	public void setState(int[][] aState) {
		for (int i = 0; i < aState.length; i++) {
			for (int j = 0; j < aState.length; j++) {
				_board[i][j].setValue(gameState.getBoardState()[i][j]);
			}
		}
	}
				
	/**
	 * metodo que genera random en casilla vacia
	 * @return position
	 */
	public Position generatePosBox() {
		int filAle, columAle;
		
		do {
			columAle = (int) (Math.random() * _boardSize);
			filAle = (int) (Math.random() * _boardSize);
		} while (!_board[filAle][columAle].isEmpty());
		return new Position(filAle, columAle);
	}
	
	/**
	 * metodo que devuelve una copia del tablero
	 * @param board de tipo Board
	 */
	public void copyBoard(Board board) {
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				board.setCell(new Position(i, j), _board[i][j].getValue());
			}
		}
	}
	
	/**
	 * metodo que mira si el tablero esta bloqueado
	 * @return lose
	 */
	public boolean blockBoard(GameRules rules) {
		boolean empty = true, lose = false;
		
		//comprobamos que esta lleno
		for (int l = 0; l < _boardSize; l++) {
			for (int m = 0; m < _boardSize; m++) {
				if (!_board[l][m].isEmpty()) {
					empty = false;
				}
			}
		}

		// realizamos movimiento hacia todas direcciones y comparamos con boardAux
		if (!empty) {
			Board boardAux = new Board(_boardSize, rules);
			copyBoard(boardAux);
			boardAux.executeMove(Direction.UP, rules);
			if (!boardAux._boardMoved) {
				boardAux.executeMove(Direction.DOWN, rules);
				if (!boardAux._boardMoved) {
					boardAux.executeMove(Direction.LEFT, rules);
					if (!boardAux._boardMoved) {
						boardAux.executeMove(Direction.RIGHT, rules);
						if (!boardAux._boardMoved) {
							lose = true;
						}
					}
				}
			}
		}
		return lose;
	}
	
	/**
	 * metodo que coge el valor maximo del tablero
	 * @return maxAux
	 */
	public int maxValue() {
		int maxAux = 0;
		
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				if (_board[i][j].getValue() > maxAux) {
					maxAux = _board[i][j].getValue();
				}
			}
		}
		return maxAux;
	}
	
	/**
	 * metodo que coge el valor minimo del tablero
	 * @return minAux
	 */
	public int minValue() {
		int minAux = 2048;
		
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				if (_board[i][j].getValue() < minAux && !_board[i][j].isEmpty()) {
					minAux = _board[i][j].getValue();
				}
			}
		}
		return minAux;
	}
	
	/**
	 * metodo que almacena el tablero en un fichero
	 * @throws IOException 
	 */
	public void store(BufferedWriter bfw) {
		
		try {
			bfw.write(firstLine);
			bfw.newLine();
			
			for (int i = 0; i < _board.length; i++) {
				bfw.newLine();
				for (int j = 0; j < _board.length; j++) {
					bfw.write(_board[i][j].getValue() + " ");
				}
			}
			bfw.newLine();
			bfw.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * metodo que carga el tablero en un fichero
	 * @throws PlayException 
	 * @throws ErrorFileException 
	 */
	public void load(BufferedReader bfr) throws PlayException, ErrorFileException {
		String[] aux;
		Cell[][] _boardAux;
		int _boardAuxSize = _boardSize;
		
		try {
			bfr.readLine();
			aux = bfr.readLine().split("\\s");// ignoramos espacios y leemos el tablero actualizandolo del archivo
			_boardAux = new Cell[aux.length][aux.length];
			_boardSize = aux.length;
			this._boardMoved = false;

			// inicializamos las celdas a 0
			for (int i = 0; i < _boardSize; i++) {
				for (int j = 0; j < _boardSize; j++) {
					_boardAux[i][j] = new Cell(0);
				}
			}

			// rellenamos _boardAux
			try {
			for (int i = 0; i < _boardSize; i++) {
				for (int j = 0; j < _boardSize; j++) {
					_boardAux[i][j].setValue(Integer.parseInt(aux[j]));

				}
				aux = bfr.readLine().split("\\s");
				
				if(_boardSize!= aux.length && i < _boardSize - 1) {
					_boardSize = _boardAuxSize;
					throw new  ErrorFileException(errorFile);
				}
			}
			}catch(NumberFormatException nf) {
				_boardSize = _boardAuxSize;
				throw new ErrorFileException(errorFile);
			}

			// igualamos tableros
			_board = _boardAux;
		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
