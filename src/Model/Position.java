package Model;

import java.util.Comparator;

public class Position {

	private int x;
	private int y;
	private int value = 0;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(int x, int y, int value) {
		this(x, y);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 23, 27, 31,
			37 };

	@Override
	/**
	 * hashCode functioned used internally in Hashtable
	 */
	public int hashCode() {
		int code = 0;
		for (int i = 0; i < PRIMES.length; i++) {
			code += getX() * PRIMES[i] + getY();
		}
		return code;
	}

	@Override
	/**
	 * Used to determine whether two ItemSet objects are equal
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Position)) {
			return false;
		}
		Position other = (Position) o;
		return this.getX() == other.getX() && this.getY() == other.getY();
	}

	public class XYComparator implements Comparator<Position> {
		@Override
		public int compare(Position o1, Position o2) {
			if (o1.x > o2.x)
				return 1;
			else if (o1.x == o2.x) {
				if (o1.y > o2.y)
					return 1;
				else if (o1.y < o2.y)
					return -1;
			} else if (o1.x < o2.x)
				return -1;
			return 0;
		}
	}

	public class YXComparator implements Comparator<Position> {
		@Override
		public int compare(Position o1, Position o2) {
			if (o1.y > o2.y)
				return 1;
			else if (o1.y == o2.y) {
				if (o1.x > o2.x)
					return 1;
				else if (o1.x < o2.x)
					return -1;
			} else if (o1.y < o2.y)
				return -1;
			return 0;
		}
	}
}
