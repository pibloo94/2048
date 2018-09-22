package tp.pr2.rules;

import java.util.Random;
import tp.pr2.logic.multigames.Board;
import tp.pr2.logic.multigames.Cell;
import tp.pr2.logic.multigames.GameRules;
import tp.pr2.logic.multigames.Position;

public class Rules2048 implements GameRules{
	
	private static int MAX_PUNT = 2048;
	
	public void addNewCellAt(Board board, Position pos,Random rand) {
		board.setCell(pos, newRandomValueCell(rand));
	}
	
	int newRandomValueCell(Random value) {
		
		if ((int)(value.nextDouble() * 10)== 0) {
			return 4;
		}else {
			return 2;
		}	
	}

	public int merge(Cell self, Cell neighbour) {

		if (self.getValue() == neighbour.getValue() && !self.isEmpty() && !neighbour.getFushed()) {
			neighbour.setValue(self.getValue() * 2);
			self.setValue(0);
			neighbour.setFushed(true);
			return neighbour.getValue();
		} else {
			return 0;
		}
	}

	public int getWinValue(Board board) {
		return board.maxValue();
	}

	public boolean win(Board board) {
		if(getWinValue(board) == MAX_PUNT ) {
			return true;
		}
		else {
			return false;
		}
	}
	public String toString() {
		return "***Game 2048***";
	}
}
