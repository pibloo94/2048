package tp.pr2.rules;

import java.util.Random;

import tp.pr2.logic.multigames.Board;
import tp.pr2.logic.multigames.Cell;
import tp.pr2.logic.multigames.GameRules;
import tp.pr2.logic.multigames.Position;

public class RulesInverse implements GameRules {
	
	private static int MAX_PUNT = 1;
	private static int BASE = 11;
	
	public void addNewCellAt(Board board, Position pos,Random rand) {
		board.setCell(pos, newRandomValueCell(rand));
	}


	int newRandomValueCell(Random value) {
		if ((int)(value.nextDouble() * 10)== 0) {
			return 1024;
		}else {
			return 2048;
		}	
	}

	public int merge(Cell self, Cell neighbour) {
		int veces = 0, valueNeighbour;

		if (self.getValue() == neighbour.getValue() && !self.isEmpty() && !neighbour.getFushed()) {
			neighbour.setValue(self.getValue() / 2);
			self.setValue(0);
			neighbour.setFushed(true);
			valueNeighbour = neighbour.getValue();
			while( valueNeighbour!=1 ){
				valueNeighbour = valueNeighbour/2;
				veces++;
			}
			
			veces = BASE - veces;
			return (int) Math.pow(2, veces);
		} else {
			return 0;
		}
	}

	public int getWinValue(Board board) {
		
		return board.minValue();
	}

	public boolean win(Board board) {
		if(getWinValue(board) == MAX_PUNT ) {
			return true;
		} else {
			return false;
		}
	}
	public String toString() {
		return "***Game Inverse***";
	}
}
