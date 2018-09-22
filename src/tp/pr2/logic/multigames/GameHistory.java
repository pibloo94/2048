package tp.pr2.logic.multigames;




/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class GameHistory {

	private static final int CAPACITY = 20;
	private GameState[] boardListUndo;
	private GameState[] boardListRedo;
	private int indexUndo, indexRedo;
	
	/**
	 * Constructor de la clase GameStack
	 */
	public GameHistory() {
		this.boardListUndo = new GameState[CAPACITY];
		this.boardListRedo = new GameState[CAPACITY];
		this.indexUndo = 0;
		this.indexRedo = 0;
	}

	/**
	 * metodo getIndexRedo
	 * 
	 * @return int
	 */
	public int getIndexRedo() {
		return indexRedo;
	}

	/**
	 * metodo getIndexUndo
	 * 
	 * @return int
	 */
	public int getIndexUndo() {
		return indexUndo;
	}



	/**
	 * metodo que devuelve el último estado almacenado
	 * @return GameState
	 * @throws UndoException 
	 */
	public GameState popUndo(){

		GameState gameState = null;

		if (indexUndo == 0) {
			indexUndo = CAPACITY;
		}
		gameState = boardListUndo[indexUndo - 1];
		indexUndo--;
		return gameState;

	}

	/**
	 * metodo que almacena un nuevo estado
	 * @param state tipo GameState
	 */
	public void pushUndo(GameState state) {
		
		if (indexUndo == CAPACITY) {
			indexUndo = 0;
		}
		boardListUndo[indexUndo] = state;
		indexUndo++;
	}

	/**
	 * metodo que devuelve el último estado almacenado
	 * @return GameState
	 * @throws RedoException 
	 */
	public GameState popRedo(){
		
		GameState gameState = null;

		if (indexRedo == 0) {
			indexRedo = CAPACITY;
		}
		gameState = boardListRedo[indexRedo - 1];
		boardListRedo[indexRedo - 1] = null;
		indexRedo--;
		return gameState;
	}

	/**
	 * metodo que almacena un nuevo estado
	 * @param state de tipo GameState
	 */
	public void pushRedo(GameState state) {
		if (indexRedo == CAPACITY) {
			indexRedo = 0;
		}

		boardListRedo[indexRedo] = state;
		indexRedo++;
	}

	/**
	 * metodo que devuelve si la estructura de datos esta vacia
	 * @return boolean
	 */
	public boolean isEmptyUndo() {
		boolean vacio = true;
		for (int i = 0; i < CAPACITY; i++) {
			if (boardListUndo[i] != null) {
				vacio = false;
			}
		}
		return vacio;
	}

	/**
	 * metodo que devuelve si la listaRedo esta vacia
	 * @return boolean
	 */
	public boolean isEmptyRedo() {
		boolean vacio = true;
		for (int i = 0; i < CAPACITY; i++) {
			if (boardListRedo[i] != null) {
				vacio = false;
			}
		}
		return vacio;
	}

	/**
	 * metodo que vacia la pila de estadosUndo
	 */
	public void emptyStackUndo() {
		for (int i = 0; i < boardListUndo.length; i++) {
			boardListUndo[i] = null;
		}
	}

	/**
	 * metodo que vacia la pila de estadosRedo
	 */
	public void emptyStackRedo() {
		for (int i = 0; i < boardListRedo.length; i++) {
			boardListRedo[i] = null;
		}
	}
	public void showUndo() {
		System.out.println("Undoing one move...");
	}
	public void showRedo() {
		System.out.println("Redoing one move...");
	}
}
