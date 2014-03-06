package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import Model.State;

public class PatternControl {

	private enum Pattern {
		HORIZONTAL, VERTICAL, DIAGONALPOS, DIAGONALNEG
	};

	// public int playerWithContiguousLineOfSize(Set<Cluster> clusters, int
	// minSize) {
	// if (clusters.isEmpty())
	// return 0;
	// for (Cluster cluster : clusters) {
	// Set<Position> positions = cluster.getPositions();
	// List<Position> verticalList = sortPositionsByXY(positions);
	// for (Position pos : verticalList) {
	// int count = 1;
	// for (int i = 1; i < minSize; i++) {
	// pos = nextPositionNeeded(pos, Pattern.VERTICAL);
	// if (verticalList.contains(pos))
	// count++;
	// }
	// if (count == 4)
	// return pos.getValue();
	// }
	// List<Position> horizontalList = sortPositionsByYX(positions);
	// for (Position pos : horizontalList) {
	// int count = 1;
	// for (int i = 1; i < minSize; i++) {
	// pos = nextPositionNeeded(pos, Pattern.HORIZONTAL);
	// if (horizontalList.contains(pos))
	// count++;
	// }
	// if (count == 4)
	// return pos.getValue();
	// }
	// for (Position pos : horizontalList) {
	// int count = 1;
	// for (int i = 1; i < minSize; i++) {
	// pos = nextPositionNeeded(pos, Pattern.DIAGONALPOS);
	// if (horizontalList.contains(pos))
	// count++;
	// }
	// if (count == 4)
	// return pos.getValue();
	// }
	// for (Position pos : horizontalList) {
	// int count = 1;
	// for (int i = 1; i < minSize; i++) {
	// pos = nextPositionNeeded(pos, Pattern.DIAGONALNEG);
	// if (horizontalList.contains(pos))
	// count++;
	// }
	// if (count == 4)
	// return pos.getValue();
	// }
	// }
	// return 0;
	//
	// // for each cluster:
	// // sort clusters by x and y
	// // for each point
	// }

	public boolean terminateTest(State state) {
		return finishedgame(state.getLastX(),
				state.getLastY(state.getLastX()), state.getBoard(), 4) != 0;
	}

	public int finishedgame(int x, int y, int[][] gameBoard, int minSize) {

		// ////////////////////

		int playerId = gameBoard[x][y];
		int countH = 0;
		int countV = 0;
		int countD = 0;
		int countDN = 0;
		// int startPointX=
		for (int i = 1; i < minSize * 2; i++) {
			// hor
			if (x - minSize + i >= 0 && x - minSize + i < gameBoard.length) {
				if (gameBoard[x - minSize + i][y] == playerId)
					countH++;
				else
					countH = 0;
				if (countH == minSize)
					return playerId;
			}
			// ver
			if (y - minSize + i >= 0 && y - minSize + i < gameBoard[0].length) {
				if (gameBoard[x][y - minSize + i] == playerId)
					countV++;
				else
					countV = 0;
				if (countV == minSize)
					return playerId;
			}
			// dia
			if (y - minSize + i >= 0 && x - minSize + i >= 0
					&& y - minSize + i < gameBoard[0].length
					&& x - minSize + i < gameBoard.length) {
				if (gameBoard[x - minSize + i][y - minSize + i] == playerId)
					countD++;
				else
					countD = 0;
				if (countD == minSize)
					return playerId;
			}
			// diaN
			if (y + minSize - i < gameBoard[0].length && x - minSize + i >= 0
					&& y + minSize - i >= 0
					&& x - minSize + i < gameBoard.length) {
				if (gameBoard[x - minSize + i][y + minSize - i] == playerId)
					countDN++;
				else
					countDN = 0;
				if (countDN == minSize)
					return playerId;
			}
		}

		return 0;

		// ////////////////////
		// horizontal
		// int count = 0;
		// int minX = Math.max(newPosition.getX() - 4, 0);
		// int maxX = Math.min(newPosition.getX() + 4, BOARD_WIDTH);
		// for (int i = minX; i < maxX; i++) {
		// if (gameBoard[i][newPosition.getY()].getValue() == newPosition
		// .getValue())
		// count++;
		// else
		// count = 0;
		// if (count == minSize)
		// return newPosition.getValue();
		// }
		// int minY = Math.max(newPosition.getY() - 4, 0);
		// int maxY = Math.min(newPosition.getY() + 4, BOARD_HEIGHT);
		// count = 0;
		// for (int i = minY; i < maxY; i++) {
		// if (gameBoard[newPosition.getX()][i].getValue() == newPosition
		// .getValue())
		// count++;
		// else
		// count = 0;
		// if (count == minSize)
		// return newPosition.getValue();
		// }
		//
		// int minSq = Math.max(minX, minY);
		// int maxSq = Math.min(maxX, maxY);
		// int difXY = newPosition.getX() - newPosition.getY();
		// count = 0;
		// for (int i = minSq; i < maxSq; i++) {
		// if (gameBoard[i + difXY][i].getValue() == newPosition.getValue())
		// count++;
		// else
		// count = 0;
		// if (count == minSize)
		// return newPosition.getValue();
		// }
		// // still need to think about it
		// int minSq2 = Math.max(minX, maxY);
		// int maxSq2 = Math.min(maxX, minY);
		// int difXY2 = newPosition.getY() - newPosition.getX();
		// count = 0;
		// for (int i = maxSq2; minSq2 < i; i--) {
		// if (gameBoard[i + difXY2][i].getValue() == newPosition.getValue())
		// count++;
		// else
		// count = 0;
		// if (count == minSize)
		// return newPosition.getValue();
		// }
		//
		// Position minPos = new Position(newPosition.getX() - (minSize - 1),
		// newPosition.getY() + (minSize - 1));
		//
		// Position maxPos = new Position(newPosition.getX() + (minSize - 1),
		// newPosition.getY() - (minSize - 1));
		//
		// return 0;
	}
	//
	// private Position nextPositionNeeded(Position position, Pattern pattern) {
	// switch (pattern) {
	// case HORIZONTAL:
	// return new Position(position.getX() + 1, position.getY(),
	// position.getValue());
	// case VERTICAL:
	// return new Position(position.getX(), position.getY() + 1,
	// position.getValue());
	// case DIAGONALPOS:
	// return new Position(position.getX() + 1, position.getY() + 1,
	// position.getValue());
	// case DIAGONALNEG:
	// return new Position(position.getX() + 1, position.getY() - 1,
	// position.getValue());
	// default:
	// return position;
	// }
	// }
	//
	// private List<Position> sortPositionsByXY(Set<Position> positions) {
	//
	// List<Position> list = new ArrayList<Position>();
	// list.addAll(positions);
	// Position.XYComparator comparator = new Position(0, 0).new XYComparator();
	// Collections.sort(list, comparator);
	// return list;
	// }
	//
	// private List<Position> sortPositionsByYX(Set<Position> positions) {
	//
	// List<Position> list = new ArrayList<Position>();
	// list.addAll(positions);
	// Position.YXComparator comparator = new Position(0, 0).new YXComparator();
	// Collections.sort(list, comparator);
	// return list;
	// }
}
