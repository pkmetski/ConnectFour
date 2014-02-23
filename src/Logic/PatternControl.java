package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import Model.Cluster;
import Model.Position;

public class PatternControl {

	private enum Pattern {
		HORIZONTAL, VERTICAL, DIAGONALPOS, DIAGONALNEG
	};

	public int playerWithContiguousLineOfSize(Set<Cluster> clusters, int minSize) {
		if (clusters.isEmpty())
			return 0;
		for (Cluster cluster : clusters) {
			Set<Position> positions = cluster.getPositions();
			List<Position> verticalList = sortPositionsByXY(positions);
			for (Position pos : verticalList) {
				int count = 1;
				for (int i = 1; i < minSize; i++) {
					pos = nextPositionNeeded(pos, Pattern.VERTICAL);
					if (verticalList.contains(pos))
						count++;
				}
				if (count == 4)
					return pos.getValue();
			}
			List<Position> horizontalList = sortPositionsByYX(positions);
			for (Position pos : horizontalList) {
				int count = 1;
				for (int i = 1; i < minSize; i++) {
					pos = nextPositionNeeded(pos, Pattern.HORIZONTAL);
					if (horizontalList.contains(pos))
						count++;
				}
				if (count == 4)
					return pos.getValue();
			}
			for (Position pos : horizontalList) {
				int count = 1;
				for (int i = 1; i < minSize; i++) {
					pos = nextPositionNeeded(pos, Pattern.DIAGONALPOS);
					if (horizontalList.contains(pos))
						count++;
				}
				if (count == 4)
					return pos.getValue();
			}
			for (Position pos : horizontalList) {
				int count = 1;
				for (int i = 1; i < minSize; i++) {
					pos = nextPositionNeeded(pos, Pattern.DIAGONALNEG);
					if (horizontalList.contains(pos))
						count++;
				}
				if (count == 4)
					return pos.getValue();
			}
		}
		return 0;

		// for each cluster:
		// sort clusters by x and y
		// for each point
	}

	private Position nextPositionNeeded(Position position, Pattern pattern) {
		switch (pattern) {
		case HORIZONTAL:
			return new Position(position.getX() + 1, position.getY(),
					position.getValue());
		case VERTICAL:
			return new Position(position.getX(), position.getY() + 1,
					position.getValue());
		case DIAGONALPOS:
			return new Position(position.getX() + 1, position.getY() + 1,
					position.getValue());
		case DIAGONALNEG:
			return new Position(position.getX() + 1, position.getY() - 1,
					position.getValue());
		default:
			return position;
		}
	}

	private List<Position> sortPositionsByXY(Set<Position> positions) {

		List<Position> list = new ArrayList<Position>();
		list.addAll(positions);
		Position.XYComparator comparator = new Position(0, 0).new XYComparator();
		Collections.sort(list, comparator);
		return list;
	}

	private List<Position> sortPositionsByYX(Set<Position> positions) {

		List<Position> list = new ArrayList<Position>();
		list.addAll(positions);
		Position.YXComparator comparator = new Position(0, 0).new YXComparator();
		Collections.sort(list, comparator);
		return list;
	}
}
