package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Model.State;

public class GameTree {
	private TransitionController transition = new TransitionController();
	private Random rnd = new Random();
	private PatternControl pControl = new PatternControl();
	private Map<Double, Integer> applicableActions;
	private ArrayList<Double> applicableActions1;
	private int AIplayerID;
	private static final int MAX_DEPTH = 11;

	public GameTree(int playerID) {
		this.AIplayerID = playerID;
	}

	public int Alpha_Beta_Search(State root) {
		applicableActions = new HashMap<Double, Integer>();
		applicableActions1 = new ArrayList<Double>();
		pControl.clearStateDataBase();
		double v = Max_Value(root, Double.MIN_VALUE, Double.MAX_VALUE, 0);
		return applicableActions.get(v);
	}

	public double Max_Value(State state, double alpha, double beta, int depth) {
		int winner = pControl.finishedgame(state, 4);
		boolean tie = state.isBoardFull();
		if (finished(depth, tie, winner)) {
			return eval(state, winner, tie, depth);
		}
		double v = Double.MIN_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			v = Math.max(v, Min_Value(newState, alpha, beta, depth + 1));
			newState.setValue(v);

			if (depth == 0) {
				applicableActions1.add(v);
			}
			if (depth == 0 && !applicableActions.containsKey(v)) {
				applicableActions.put(v, a);
			}
			if (v >= beta) {
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	public double Min_Value(State state, double alpha, double beta, int depth) {
		int winner = pControl.finishedgame(state, 4);
		boolean tie = state.isBoardFull();
		if (finished(depth, tie, winner)) {
			return eval(state, winner, tie, depth);
		}
		double v = Double.MAX_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			v = Math.min(v, Max_Value(newState, alpha, beta, depth + 1));
			newState.setValue(v);
			if (v <= alpha) {
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

	private boolean finished(int depth, boolean tie, int winner) {
		return depth >= MAX_DEPTH || tie || winner != 0;
	}

	private double eval(State state, int winner, boolean tie, int depth) {
		if (winner == this.AIplayerID)
			return 512 - depth * 5;
		else if (winner != this.AIplayerID && winner > 0)
			return -512 + depth * 5;
		else if (tie)
			return 0;
		else
			return pControl.fastHeuristic(state, 4, AIplayerID);
		// return pControl.Heuristic(state, 4, AIplayerID);
	}
}
