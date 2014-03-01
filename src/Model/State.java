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
	private Set<Cluster> clusters;
	private ArrayList<Action> applicableActions;
	private int playerId;
	private double value;

	public State(int x, int y) {
		this.x = x;
		this.y = y;
		this.reverseIndex = new HashMap<Position, Cluster>();
		this.clusters = new HashSet<Cluster>();
		board = new Position[x][y];
		yPos = new int[x];
		applicableActions = new ArrayList<Action>();
	}

	public Set<Cluster> getClusters() {
		return clusters;
	}

	public void playPosition(int x, int playerId) {
		this.playerId = playerId;
		Position position = new Position(x, yPos[x], playerId);
		board[x][yPos[x]++] = position;
		Cluster cluster = new Cluster(position);
		this.reverseIndex.put(position, cluster);
		this.clusters.add(cluster);

		Set<Cluster> neighbours = ClusterManager.getNeighbouringClusters(
				cluster, reverseIndex, this.x, y);

		if (0 < neighbours.size()) {
			ClusterManager.merge(cluster, neighbours, reverseIndex, clusters);
		}
	}

	public Set<Cluster> getClustersOfMinSize(int minSize) {
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

	public ArrayList<Action> getApplicableActions() {
		return applicableActions;
	}

	public void addApplicableAction(Action action) {
		this.applicableActions.add(action);
	}

	public int[] getYPos() {
		return yPos;
	}

	public int getY() {
		return y;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public State clone() {
		Cloner cloner = new Cloner();
		return cloner.deepClone(this);
	}
}
