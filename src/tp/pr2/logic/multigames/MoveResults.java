package tp.pr2.logic.multigames;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class MoveResults {

	// clase que permite representar los resultados de la ejecuci�n de un
	// movimiento
	private int highest;
	private int score;

	/**
	 * Constructor de la clase MoveResults
	 * @param highest de tipo int
	 * @param score de tipo int
	 */
	public MoveResults(int highest, int score) {
		this.highest = highest;
		this.score = score;
	}

	/**
	 * metodo getHighest
	 * @return int
	 */
	public int getHighest() {
		return highest;
	}

	/**
	 * metodo getScore
	 * @return int
	 */
	public int getScore() {
		return score;
	}


	/**
	 * metodo para imprimir valores
	 * @return String
	 */
	public String toString() {
		return "BestValue = " + highest + ", score = " + score + "";
	}

}
