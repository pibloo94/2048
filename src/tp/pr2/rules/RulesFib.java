package tp.pr2.rules;

import java.util.Random;
import tp.pr2.logic.multigames.Board;
import tp.pr2.logic.multigames.Cell;
import tp.pr2.logic.multigames.GameRules;
import tp.pr2.logic.multigames.Position;
import tp.pr2.util.MyMathsUtil;

public class RulesFib implements GameRules {
	
	private static int MAX_PUNT = 144;

	public void addNewCellAt(Board board, Position pos,Random rand) {
		board.setCell(pos, newRandomValueCell(rand));
	}
	
	int newRandomValueCell(Random value) {
		if ((int)(value.nextDouble() * 10)== 0) {
			return 2;
		}else {
			return 1;
		}	
	}
	
	public int merge(Cell self, Cell neighbour) {
		int mergeAux = 0;
		if (!self.isEmpty() && !neighbour.getFushed()) {
			if (self.getValue() == 1 && neighbour.getValue() == 1) {
				mergeAux = MyMathsUtil.nextFib(self.getValue());
				neighbour.setValue(mergeAux);
				neighbour.setFushed(true);
				self.setValue(0);
			}
			else if (MyMathsUtil.nextFib(self.getValue()) == neighbour.getValue()) {
				mergeAux = MyMathsUtil.nextFib(neighbour.getValue());
				neighbour.setValue(mergeAux);
				neighbour.setFushed(true);
				self.setValue(0);
			}
			else if (MyMathsUtil.nextFib(neighbour.getValue()) == self.getValue()) {
				mergeAux = MyMathsUtil.nextFib(self.getValue());
				neighbour.setValue(mergeAux);
				neighbour.setFushed(true);
				self.setValue(0);
			}
		}
		return mergeAux;
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
		return "***Game Fibonacci***";
	}
}
