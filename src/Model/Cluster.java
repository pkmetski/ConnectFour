package Model;

import java.util.HashSet;
import java.util.Set;

public class Cluster {

	private Set<Position> positions;
	private int value = 0;

	public Cluster(Position position) {
		this.positions = new HashSet<Position>();
		this.positions.add(position);
		this.value = position.getValue();
	}

	public void addPosition(Position position) {
		positions.add(position);
	}

	public boolean contains(Position position) {
		return positions.contains(position);
	}

	public int size() {
		return positions.size();
	}

	public Set<Position> getPositions() {
		return this.positions;
	}

	public void addRange(Cluster cluster) {
		this.positions.addAll(cluster.getPositions());
	}

	public int getValue() {
		return this.value;
	}
}
