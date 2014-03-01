package Logic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Model.Cluster;
import Model.Position;

public class ClusterManager {

	// return all the clusters around this position
	public static Set<Cluster> getNeighbouringClusters(Cluster cluster,
			Map<Position, Cluster> reverseIndex, int col, int row) {
		Set<Cluster> neighbouringClusters = new HashSet<Cluster>();
		for (Position position : cluster.getPositions()) {
			Set<Position> neighbours = getNeighbouringPositions(position, col,
					row);
			for (Position pos : neighbours) {
				if (reverseIndex.containsKey(pos)
						&& reverseIndex.get(pos).getValue() == cluster
								.getValue()) {
					neighbouringClusters.add(reverseIndex.get(pos));
				}
			}
		}
		return neighbouringClusters;
	}

	private static Set<Position> getNeighbouringPositions(Position position,
			int col, int row) {
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

	public static Cluster merge(Cluster cluster, Set<Cluster> neighbours,
			Map<Position, Cluster> reverseIndex, Set<Cluster> clusters) {
		for (Cluster neighbourCluster : neighbours) {
			cluster.addRange(neighbourCluster);
			// update the reversed index
			for (Position pos : neighbourCluster.getPositions()) {
				reverseIndex.put(pos, cluster);
			}
			// remove from clusters collection
			clusters.remove(neighbourCluster);
		}
		return cluster;
	}
}
