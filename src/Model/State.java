package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Logic.ClusterManager;

import com.rits.cloning.Cloner;

//this represents a node of the game tree
public class State {
	private int x = 0;
	private int y = 0;
	private Position[][] board;
	private int[] yPos;
	private Map<Position, Cluster> reverseIndex;
	private ArrayList<Cluster> clusters;

	public State(int x, int y) {
		this.x = x;
		this.y = y;
		this.reverseIndex = new HashMap<Position, Cluster>();
		this.clusters = new ArrayList<Cluster>();
		board = new Position[x][y];
		yPos = new int[x];
	}

	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public void addPosition(int x, int playerId) {
		Position position = new Position(x, yPos[x], playerId);
		board[x][yPos[x]++] = position;
		Cluster cluster = new Cluster(position);
		this.reverseIndex.put(position, cluster);
		this.clusters.add(cluster);

		Set<Cluster> neighbours = ClusterManager.getNeighbouringClusters(
				cluster, reverseIndex, x, y);

		if (0 < neighbours.size()) {
			ClusterManager.merge(cluster, neighbours, reverseIndex, clusters);
		}
	}

	public Iterable<Cluster> getClustersOfMinSize(int minSize) {
		Set<Cluster> filteredClusters = new HashSet<Cluster>();
		for (Cluster cluster : clusters) {
			if (minSize <= cluster.size()) {
				filteredClusters.add(cluster);
			}
		}
		return filteredClusters;
	}

	public boolean isBoardFull() {
		for (int i = 0; i < yPos.length; i++) {
			if (yPos[i] < y - 1) {
				return false;
			}
		}
		return true;
	}

	public State clone() {
		Cloner cloner = new Cloner();
		return cloner.deepClone(this);
	}
}
