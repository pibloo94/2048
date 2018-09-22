package tp.pr2.logic.multigames;

import tp.pr2.logic.multigames.GameRules;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class Cell{

	/**
	 * clase que permite representar cada una de las baldosas del tablero
	 */
	
	private int value;
	private boolean fushed;
		
	/**
	 * constructor de la clase Cell
	 * @param value de tipo int
	 */
	public Cell(int value) {
		this.value = value;
		this.fushed = false;
	}

	//getters
	
	/**
	 * metodo getValue
	 * @return value de tipo int
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * metodo getFushed
	 * @return fushed de tipo boolean
	 */
	public boolean getFushed() {
		return fushed;
	}

	//setters
	
	/**
	 * metodo setValue
	 * @param value de tipo int
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * metodo setFushed
	 * @param fushed de tipo boolean
	 */
	public void setFushed(boolean fushed) {
		this.fushed = fushed;
	}
	
	/**
	 * metodo isEmpty
	 * @return empty
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (value == 0) {
			empty = true;
		}
		return empty;
	}
	
	/**
	 * metodo que comprueba si se pueden fusionar 2 celdas
	 * @param neighbour de tipo Cell
	 * @param rules de tipo GameRules
	 * @return merge
	 */
	public boolean doMerge(Cell neighbour, GameRules rules) {
		boolean merge = false;
		if ( rules.merge(this, neighbour) != 0) {
			merge = true;
		}
		return merge;
	}
	
	/**
	 * metodo que prepara "pretty drawing"
	 */
	public String toString () {
		if(value == 0) {
			return "";
		}else {
		return String.valueOf(value);
		}
	}
}
