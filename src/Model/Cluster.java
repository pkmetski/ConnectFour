package Model;

import java.util.HashSet;
import java.util.Set;

public class Cluster {

	private Set<Position> positions;

	public Cluster(Position position) {
		this.positions = new HashSet<Position>();
		this.positions.add(position);
	}

	public void addPosition(Position position) {
		positions.add(position);
	}

	public boolean contains(Position position) {
		return positions.contains(position);
	}

	public int count() {
		return positions.size();
	}

	public Set<Position> getPositions() {
		return this.positions;
	}

	public void addRange(Cluster cluster) {
		this.positions.addAll(cluster.getPositions());
	}
}
