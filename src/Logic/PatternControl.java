package Logic;

import Model.State;

public class PatternControl {

	// check for connect 4 in the whole board and return the winner's ID or if nobody won return 0
	public int finishedgame(State state, int minSize) {
		int countH = 0, countV = 0, countD = 0, countDN = 0, y = state
				.getLastY(state.getLastX()), startPointX = state.getLastX()
				- minSize, startPointY = y - minSize;
		for (int i = 1; i < minSize * 2; i++) {
			// check for horizontal connect four
			if (startPointX + i >= 0 && startPointX + i < state.getX()) {
				if (state.getBoard()[startPointX + i][y] == state.getPlayerId())
					countH++;
				else
					countH = 0;
				if (countH == minSize)
					return state.getPlayerId();
			}
			// check for vertical connect four
			if (startPointY + i >= 0 && startPointY + i < state.getY()) {
				if (state.getBoard()[state.getLastX()][startPointY + i] == state
						.getPlayerId())
					countV++;
				else
					countV = 0;
				if (countV == minSize)
					return state.getPlayerId();
			}
			// check for positive diagonal connect four
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
			// check for negative diagonal connect four
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
		return verHeuristic(state, minSize, aiPlayerID)
				+ horHeuristic(state, minSize, aiPlayerID)
				+ diaPosHeuristic(state, minSize, aiPlayerID)
				+ diaNegHeuristic(state, minSize, aiPlayerID);
	}

	//calculate the heuristic for vertical possibilities
	private double verHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0;
		for (int j = state.getLastY(state.getLastX()); j >= 0; j--) {
			if (state.getBoard()[state.getLastX()][j] == state.getPlayerId())
				count++;
			else
				break;
		}
		if (state.getY() - state.getLastY(state.getLastX()) > minSize - count) {
			return getPoints(count, state.getPlayerId(), aiPlayerID);
		}
		return 0;
	}

	// calculate the heuristic for horizontal possibilities
	private double horHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int opPlayerId = state.getPlayerId() == 1 ? 2 : 1;
		int minBound = Math.max(0, state.getLastX() - minSize + 1);
		int maxBound = Math.min(state.getX(), state.getLastX() + minSize)
				- minSize + 1;
		for (int i = minBound; i < maxBound; i++) {
			for (int j = 0; j < minSize; j++) {
				if (state.getBoard()[i + j][y] == opPlayerId)
					break;
				else if (state.getBoard()[i + j][y] == state.getPlayerId())
					count++;
				if (j == minSize - 1) {
					total += getPoints(count, state.getPlayerId(), aiPlayerID);
				}
			}
			count = 0;
		}
		return total;
	}

	// calculate the heuristic for positive diagonal possibilities
	private double diaPosHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int opPlayerId = state.getPlayerId() == 1 ? 2 : 1;
		int startPointX = state.getLastX() - minSize + 1, startPointY = y
				- minSize + 1;
		for (int i = 0; i < minSize; i++) {
			if (startPointY + i >= 0 && startPointX + i >= 0
					&& y + i < state.getY()
					&& state.getLastX() + i < state.getX()) {
				for (int j = 0; j < minSize; j++) {
					if (state.getBoard()[startPointX + i + j][startPointY + i
							+ j] == opPlayerId)
						break;
					else if (state.getBoard()[startPointX + i + j][startPointY
							+ i + j] == state.getPlayerId())
						count++;
					if (j == minSize - 1) {
						total += getPoints(count, state.getPlayerId(),
								aiPlayerID);
					}
				}
				count = 0;
			}
		}
		return total;
	}

	// calculate the heuristic for negative diagonal possibilities
	private double diaNegHeuristic(State state, int minSize, int aiPlayerID) {
		int count = 0, total = 0, y = state.getLastY(state.getLastX());
		int opPlayerId = state.getPlayerId() == 1 ? 2 : 1;
		int startPointX = state.getLastX() - minSize + 1, startPointY = y
				+ minSize - 1;
		for (int i = 0; i < minSize; i++) {
			if (y - i >= 0 && startPointY - i < state.getY()
					&& startPointX + i >= 0
					&& state.getLastX() + i < state.getX()) {
				for (int j = 0; j < minSize; j++) {

					if (state.getBoard()[startPointX + i + j][startPointY
							- (i + j)] == opPlayerId)
						break;
					else if (state.getBoard()[startPointX + i + j][startPointY
							- (i + j)] == state.getPlayerId())
						count++;
					if (j == minSize - 1) {
						total += getPoints(count, state.getPlayerId(),
								aiPlayerID);
					}
				}
				count = 0;
			}
		}
		return total;
	}
	
	// return the worth of that token 
	private int getPoints(int count, int currentPlayerId, int maxPlayerId) {
		int coefficient = maxPlayerId == currentPlayerId ? 1 : -1;
		if (count == 3) // if there is 3 token 
			return coefficient * 32;
		else if (count == 2) // if there is 2 token 
			return coefficient * 4;
		else if (count == 1) // if there is 1 token 
			return coefficient * 1;
		else
			return 0;
	}
}
