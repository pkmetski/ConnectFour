package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClusterCollection {

	private Map<Position, Cluster> reverseIndex;
	private ArrayList<Cluster> clusters;
	private int col, row;

	public ClusterCollection(int col, int row) {
		this.reverseIndex = new HashMap<Position, Cluster>();
		this.clusters = new ArrayList<Cluster>();
		this.col = col;
		this.row = row;
	}

	public void addPosition(Position position) {
		Set<Cluster> neighbours = getNeighbouringClusters(position);

		Cluster cluster = new Cluster(position);
		if (0 < neighbours.size()) {
			merge(cluster, neighbours);
		} else {
			this.reverseIndex.put(position, cluster);
			this.clusters.add(cluster);
		}
	}

	private Set<Cluster> getNeighbouringClusters(Position position) {

		Set<Cluster> neighbouringClusters = new HashSet<Cluster>();
		Set<Position> neighbours = getNeighbouringPositions(position);
		for (Position pos : neighbours) {
			neighbouringClusters.add(this.reverseIndex.get(pos));
		}
		return neighbouringClusters;
	}

	private Set<Position> getNeighbouringPositions(Position position) {
		Set<Position> positions = new HashSet<Position>();

		int maxX = position.getX() + 1, maxY = position.getY() + 1, minX = position
				.getX() - 1, minY = position.getY() - 1;

		maxX = Math.min(maxX, col);
		minX = Math.max(minX, 0);

		maxY = Math.min(maxY, row);
		minY = Math.max(minY, 0);

		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				if (x == position.getX() && y == position.getY()) {
					continue;
				}
				positions.add(new Position(x, y));
			}
		}
		return positions;
	}

	private Cluster merge(Cluster cluster, Set<Cluster> neighbours) {
		for (Cluster neighbourCluster : neighbours) {
			cluster.addRange(neighbourCluster);
			// update the reversed index
			for (Position pos : neighbourCluster.getPositions()) {
				this.reverseIndex.put(pos, cluster);
			}
			// remove from clusters collection
			this.clusters.remove(neighbourCluster);
		}
		return cluster;
	}
}
