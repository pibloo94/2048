package tp.pr2.logic.multigames;

import java.util.Random;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public interface GameRules {

	/**
	 * metodo que incorpora una c�lula con valor aleatorio en la posici�n pos del
	 * tablero board
	 * 
	 * @param board tipo Board
	 * @param pos tipo Position
	 * @param random tipo Random
	 */
	void addNewCellAt(Board board, Position pos, Random random);

	/**
	 * metodo fusiona dos c�lulas y devuelve el n�mero de puntos obtenido por la
	 * fusi�n
	 * 
	 * @param self tipo Cell
	 * @param other tipo Cell
	 * @return int
	 */
	int merge(Cell self, Cell other);

	/**
	 * metodo que devuelve el mejor valor del tablero, seg�n las reglas de ese
	 * juego, comprob�ndose si es un valor ganador y se ha ganado el juego
	 * 
	 * @param board Board
	 * @return int
	 */
	int getWinValue(Board board);

	/**
	 * metodo que devuelve si el juego se ha ganado o no
	 * 
	 * @param board Board
	 * @return boolean
	 */
	boolean win(Board board);

	/**
	 * metodo que devuelve si el juego se ha perdido o no.
	 * 
	 * @param board tipo Board
	 * @return boolean
	 */
	default boolean lose(Board board) {
		if (board.blockBoard(this)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * metodo cuya implementaci�n por defecto crea y devuelve un tablero size � size
	 * 
	 * @param size tipo int
	 * @return Board
	 */
	default Board createBoard(int size) {
		return new Board(size, this);
	}

	/**
	 * metodo que elige una posici�n libre de board e invoca el m�todo
	 * addNewCellAt() para a�adir una celda en esa posici�n
	 * 
	 * @param board tipo Board
	 * @param rand tipo Random
	 */
	default void addNewCell(Board board, Random rand) {
		Position pos = board.generatePosBox();
		addNewCellAt(board, pos, rand);
	}

	/**
	 * metodo cuya implementaci�n por defecto inicializa board eligiendo numCells
	 * posiciones libres, e invoca el m�todo addNewCellAt() para a�adir nuevas
	 * c�lulas en esas posiciones
	 * 
	 * @param board tipo Board
	 * @param numCells tipo int
	 * @param rand tipo Random
	 */
	default void initBoard(Board board, int numCells, Random rand) {
		for (int i = 0; i < numCells; i++) {
			addNewCell(board, rand);
		}
	}
}
