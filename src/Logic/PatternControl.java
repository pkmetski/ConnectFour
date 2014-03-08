package Logic;

import Model.State;

public class PatternControl {

	// private enum Pattern {
	// HORIZONTAL, VERTICAL, DIAGONALPOS, DIAGONALNEG
	// };

	public int finishedgame(State state, int minSize) {
		int countH = 0, countV = 0, countD = 0, countDN = 0, y = state
				.getLastY(state.getLastX()), startPointX = state.getLastX()
				- minSize, startPointY = y - minSize;
		for (int i = 1; i < minSize * 2; i++) {
			// hor
			if (startPointX + i >= 0 && startPointX + i < state.getX()) {
				if (state.getBoard()[startPointX + i][y] == state.getPlayerId())
					countH++;
				else
					countH = 0;
				if (countH == minSize)
					return state.getPlayerId();
			}
			// ver
			if (startPointY + i >= 0 && startPointY + i < state.getY()) {
				if (state.getBoard()[state.getLastX()][startPointY + i] == state
						.getPlayerId())
					countV++;
				else
					countV = 0;
				if (countV == minSize)
					return state.getPlayerId();
			}
			// dia
			if (startPointY + i >= 0 && startPointX + i >= 0
					&& startPointY + i < state.getY()
					&& startPointX + i < state.getX()) {
				if (state.getBoard()[startPointX + i][startPointY + i] == state
						.getPlayerId())
					countD++;
				else
					countD = 0;
				if (countD == minSize)
					return state.getPlayerId();
			}
			// diaN
			if (y + minSize - i < state.getY() && startPointX + i >= 0
					&& y + minSize - i >= 0 && startPointX + i < state.getX()) {
				if (state.getBoard()[startPointX + i][y + minSize - i] == state
						.getPlayerId())
					countDN++;
				else
					countDN = 0;
				if (countDN == minSize)
					return state.getPlayerId();
			}
		}

		return 0;
	}

	public double Heuristic(State state, int minSize, int aiPlayerID) {
		double a = verHeuristic(state, minSize, aiPlayerID);
		double b = horHeuristic(state, minSize, aiPlayerID);
		double c = diaPosHeuristic(state, minSize, aiPlayerID);
		double d = diaNegHeuristic(state, minSize, aiPlayerID);
		return a + b + c + d;
	}

	private double verHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0;
		for (int j = state.getLastY(state.getLastX()); j >= 0; j--) {
			if (state.getBoard()[state.getLastX()][j] == state.getPlayerId())
				count++;
			else
				break;
		}
		if (state.getY() - state.getLastY(state.getLastX()) > minSize - count) {
			if (count == 3)
				return 32;
			else if (count == 2)
				return 4;
			else if (count == 1)
				return 1;
		}
		return 0;
	}

	// private double verHeuristicOp(State state, int minSize, int aiPlayerID) {
	// int count = 0, total = 0;
	// int humanPlayerID = aiPlayerID == 1 ? 2 : 1;
	// for (int i = 0; i < state.getX(); i++) {
	// for (int j = state.getLastY(i); j >= 0; j--) {
	// if (state.getBoard()[i][j] == humanPlayerID)
	// count++;
	// else
	// break;
	// }
	// if (state.getY() - state.getLastY(state.getLastX()) > minSize
	// - count) {
	// if (count == 3)
	// return Double.MIN_NORMAL;
	// if (count == 2)
	// total += -4;
	// else if (count == 1)
	// total += -1;
	// else if (count == 4)
	// return Double.MIN_NORMAL;
	// }
	// count = 0;
	// }
	// return total;
	// }

	private double horHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int humanPlayerID = aiPlayerID == 1 ? 2 : 1;
		int minBound = Math.max(0, state.getLastX() - minSize + 1);
		int maxBound = Math.min(state.getX(), state.getLastX() + minSize)
				- minSize + 1;
		for (int i = minBound; i < maxBound; i++) {
			for (int j = 0; j < minSize; j++) {
				if (state.getBoard()[i + j][y] == humanPlayerID)
					break;
				else if (state.getBoard()[i + j][y] == aiPlayerID)
					count++;
				if (j == minSize - 1) {
					if (count == 3)
						total += 32;
					else if (count == 2)
						total += 4;
					else if (count == 1)
						total += 1;
				}
			}
			count = 0;
		}
		return total;
	}

	private double diaPosHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int humanPlayerID = aiPlayerID == 1 ? 2 : 1;
		int startPointX = state.getLastX() - minSize + 1, startPointY = y
				- minSize + 1;
		for (int i = 0; i < minSize; i++) {
			for (int j = 0; j < minSize; j++) {
				if (startPointY + i >= 0 && startPointX + i >= 0
						&& startPointY + i + j < state.getY()
						&& startPointX + i + j < state.getX()) {
					if (state.getBoard()[startPointX + i + j][startPointY + i
							+ j] == humanPlayerID)
						break;
					else if (state.getBoard()[startPointX + i + j][startPointY
							+ i + j] == aiPlayerID)
						count++;
					if (j == minSize - 1) {
						if (count == 3)
							total += 32;
						else if (count == 2)
							total += 4;
						else if (count == 1)
							total += 1;
					}
				} else {
					break;
				}
			}
			count = 0;
		}
		return total;
	}

	private double diaNegHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int humanPlayerID = aiPlayerID == 1 ? 2 : 1;
		int startPointX = state.getLastX() - minSize + 1, startPointY = y
				+ minSize - 1;
		for (int i = 0; i < minSize; i++) {
			for (int j = 0; j < minSize; j++) {
				if (startPointY - (i + j) >= 0
						&& startPointY - i < state.getY()
						&& startPointX + i >= 0
						&& startPointX + i + j < state.getX()) {
					if (state.getBoard()[startPointX + i + j][startPointY
							- (i + j)] == humanPlayerID)
						break;
					else if (state.getBoard()[startPointX + i + j][startPointY
							- (i + j)] == aiPlayerID)
						count++;
					if (j == minSize - 1) {
						if (count == 3)
							total += 32;
						else if (count == 2)
							total += 4;
						else if (count == 1)
							total += 1;
					}
				} else {
					break;
				}
			}
			count = 0;
		}
		return total;
	}

	// public double verCounter(State state, int minSize) {
	// int total = 0, count = 0;
	// for (int i = 0; i < state.getX(); i++) {
	// for (int j = state.getLastY(i); j >= 0; j--) {
	// if (state.getBoard()[i][j] == state.getBoard()[i][state
	// .getLastY(i)]) {
	// if (state.getBoard()[i][j] == state.getPlayerId())
	// count++;
	// else {
	// count--;
	// }
	// } else {
	// break;
	// }
	// }
	// if (state.getY() - state.getLastY(i) > minSize - count) {
	// if (count > 1)
	// total += Math.pow(2, count);
	// else if (count < -1)
	// total += Math.pow(2, Math.abs(count));
	// else
	// total += count;
	// }
	// count = 0;
	// }
	// return total;
	// }

	// public double verCounterPos(State state, int minSize) {
	// int total = 0, count = 0;
	// for (int i = 0; i < state.getX(); i++) {
	// for (int j = state.getLastY(i); j >= 0; j--) {
	// if (state.getBoard()[i][j] == state.getPlayerId())
	// count++;
	// else {
	// break;
	// }
	// }
	// if (state.getY() - state.getLastY(i) > minSize - count) {
	// if (count > 1)
	// total += Math.pow(2, count);
	// else
	// total += count;
	// } else if(state.getBoard()[i][state.getLastY(i)]==state.getPlayerId())
	// total += -64;
	// count = 0;
	// }
	// return total;
	// }

	// public int horCounter(State state, int minSize) {
	// for (int i = 0; i < state.getX() - minSize; i++) {
	// for (int j = 0; j < state.getLastY(i); j++) {
	//
	// }
	// }
	// return -1;
	// }
	//
	// private int evalFour(State state, int x, int y, Pattern pattern) {
	// int count = 0;
	// if (pattern == Pattern.HORIZONTAL) {
	// for (int i = 0; i < 4; i++) {
	// if (state.getBoard()[x + i][y] == 1)
	// return 0;
	// if (state.getBoard()[x + i][y] == 2)
	// count++;
	// }
	// }
	// // if (pattern == Pattern.VERTICAL) {
	// // for (int i = 0; i < 4; i++) {
	// // if (state.getBoard()[x][y + i] == 1)
	// // return 0;
	// // if (state.getBoard()[x][y + i] == 2)
	// // count++;
	// // }
	// // }
	// return count;
	// }

	public double newHeu(State state, int minSize, int aiPlayerID) {
		int rows = state.getY();
		int cols = state.getX();
		double eval = 0;
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < cols - 5; column++) {
				if (row % 2 == 0)
				// _XXX_
				{
					if (state.getBoard()[row][column] == 0
							&& state.getBoard()[row][column + 1] == 1
							&& state.getBoard()[row][column + 1] == state
									.getBoard()[row][column + 2]
							&& state.getBoard()[row][column + 2] == state
									.getBoard()[row][column + 3]
							&& state.getBoard()[row][column + 4] == 0) {
						eval = -999;
					}

					// X_XX_
					if (state.getBoard()[row][column] == 1
							&& state.getBoard()[row][column + 1] == 0
							&& state.getBoard()[row][column] == state
									.getBoard()[row][column + 2]
							&& state.getBoard()[row][column] == state
									.getBoard()[row][column + 3]
							&& state.getBoard()[row][column + 4] == 0) {
						eval = -900;
					}
					// XX_X_
					if (state.getBoard()[row][column] == 1
							&& state.getBoard()[row][column + 1] == 1
							&& state.getBoard()[row][column + 2] == 0
							&& state.getBoard()[row][column] == state
									.getBoard()[row][column + 3]
							&& state.getBoard()[row][column + 4] == 0) {
						eval = -900;
					}
				}
			}
		}
		return eval;
	}
}
