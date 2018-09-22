package tp.pr2.logic.multigames;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class Position {

	// clase que permite representar posiciones del tablero
	private int fila;
	private int columna;

	/**
	 * Constructor de la clase Position
	 * @param fila de tipo int
	 * @param columna de tipo int
	 */
	public Position(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * metodo getFila
	 * @return int
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * metodo getColmuna
	 * @return int
	 */
	public int getColumna() {
		return columna;
	}

	// setters
	/**
	 * metodo setFila
	 * @param fila de tipo int 
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * metodo setColumna
	 * @param columna de tipo int
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}
}
