package Logic;

import java.util.Random;

import Model.Cluster;
import Model.Position;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PatternControl {

	private enum Pattern {
		HORIZONTAL, VERTICAL, DIAGONAL
	};

	public int playerWithContiguousLineOfSize(Iterable<Cluster> clusters,
			int minSize) {

		Random rand = new Random();
		return rand.nextInt(3);

		//for each cluster:
		//sort clusters by x and y
		//for each point
		
	}

	private Position nextPositionNeeded(Position position, Pattern pattern) {
		switch (pattern) {
		case HORIZONTAL:
			return new Position(position.getX() + 1, position.getY());
		case VERTICAL:
			return new Position(position.getX(), position.getY() + 1);
		case DIAGONAL:
			return new Position(position.getX() + 1, position.getY() + 1);
		default:
			return position;
		}
	}
}
