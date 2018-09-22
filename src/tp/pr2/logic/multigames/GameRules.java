package tp.pr2.logic.multigames;

import java.util.Random;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public interface GameRules {

	/**
	 * metodo que incorpora una célula con valor aleatorio en la posición pos del
	 * tablero board
	 * 
	 * @param board tipo Board
	 * @param pos tipo Position
	 * @param random tipo Random
	 */
	void addNewCellAt(Board board, Position pos, Random random);

	/**
	 * metodo fusiona dos células y devuelve el número de puntos obtenido por la
	 * fusión
	 * 
	 * @param self tipo Cell
	 * @param other tipo Cell
	 * @return int
	 */
	int merge(Cell self, Cell other);

	/**
	 * metodo que devuelve el mejor valor del tablero, según las reglas de ese
	 * juego, comprobándose si es un valor ganador y se ha ganado el juego
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
	 * metodo cuya implementación por defecto crea y devuelve un tablero size × size
	 * 
	 * @param size tipo int
	 * @return Board
	 */
	default Board createBoard(int size) {
		return new Board(size, this);
	}

	/**
	 * metodo que elige una posición libre de board e invoca el método
	 * addNewCellAt() para añadir una celda en esa posición
	 * 
	 * @param board tipo Board
	 * @param rand tipo Random
	 */
	default void addNewCell(Board board, Random rand) {
		Position pos = board.generatePosBox();
		addNewCellAt(board, pos, rand);
	}

	/**
	 * metodo cuya implementación por defecto inicializa board eligiendo numCells
	 * posiciones libres, e invoca el método addNewCellAt() para añadir nuevas
	 * células en esas posiciones
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
