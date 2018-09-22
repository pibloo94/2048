package tp.pr2.logic.multigames;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class GameState {

	private MoveResults moveResults;
	private int[][] boardState;

	/**
	 * Constructor de la clase GameState
	 * @param moveResults tipo MoveResults
	 * @param boardState tipo int[][]]
	 */
	public GameState(MoveResults moveResults, int[][] boardState) {
		this.moveResults = moveResults;
		this.boardState = boardState;
	}

	/**
	 * metodo getMoveResults
	 * @return MoveResults
	 */
	public MoveResults getMoveResults() {
		return moveResults;
	}

	/**
	 * metodo getBoardState
	 * @return int[][]
	 */
	public int[][] getBoardState() {
		return boardState;
	}
}
