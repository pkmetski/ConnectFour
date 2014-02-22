package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Model.Cluster;
import Model.Position;

public class ClusterManager {

	private Map<Position, Cluster> reverseIndex;
	private ArrayList<Cluster> clusters;
	private int col, row;

	public ClusterManager(int col, int row) {
		this.reverseIndex = new HashMap<Position, Cluster>();
		this.clusters = new ArrayList<Cluster>();
		this.col = col;
		this.row = row;
	}

	public void addPosition(Position position) {

		Cluster cluster = new Cluster(position);
		this.reverseIndex.put(position, cluster);
		this.clusters.add(cluster);

		Set<Cluster> neighbours = getNeighbouringClusters(cluster);

		if (0 < neighbours.size()) {
			merge(cluster, neighbours);
		}
	}

	// return all the clusters around this position
	private Set<Cluster> getNeighbouringClusters(Cluster cluster) {
		Set<Cluster> neighbouringClusters = new HashSet<Cluster>();
		for (Position position : cluster.getPositions()) {
			Set<Position> neighbours = getNeighbouringPositions(position);
			for (Position pos : neighbours) {
				if (this.reverseIndex.containsKey(pos)
						&& this.reverseIndex.get(pos).getValue() == cluster
								.getValue()) {
					neighbouringClusters.add(this.reverseIndex.get(pos));
				}
			}
		}
		return neighbouringClusters;
	}

	private Set<Position> getNeighbouringPositions(Position position) {
		Set<Position> positions = new HashSet<Position>();

		int maxX = position.getX() + 1, maxY = position.getY() + 1, minX = position
				.getX() - 1, minY = position.getY() - 1;

		maxX = Math.min(maxX, col - 1);
		minX = Math.max(minX, 0);

		maxY = Math.min(maxY, row - 1);
		minY = Math.max(minY, 0);

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
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

	public Iterable<Cluster> getClustersOfMinSize(int minSize) {
		Set<Cluster> filteredClusters = new HashSet<Cluster>();
		for (Cluster cluster : this.clusters) {
			if (minSize <= cluster.size()) {
				filteredClusters.add(cluster);
			}
		}
		return filteredClusters;
	}
}
