package Logic;

import java.util.HashMap;
import java.util.Map;

import Model.State;

public class GameTree {
	private TransitionController transition = new TransitionController();
	private PatternControl pControl = new PatternControl();
	private Map<Double, Integer> applicableActions;
	private int maxPlayerID;
	private static final int MAX_DEPTH = 11;

	public GameTree(int playerID) {
		this.maxPlayerID = playerID;
	}

	// start min/max alpha-beta search from the root
	public int Alpha_Beta_Search(State root) {
		applicableActions = new HashMap<Double, Integer>();
		double v = Max_Value(root, Double.MIN_VALUE, Double.MAX_VALUE, 0);
		return applicableActions.get(v);
	}

	public double Max_Value(State state, double alpha, double beta, int depth) {
		int winner = pControl.finishedgame(state, 4);
		boolean tie = state.isBoardFull();
		if (finished(depth, tie, winner)) {
			return eval(state, winner, tie, depth);
		}
		double v = -100000;
		// get a list of non-full columns
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			v = Math.max(v, Min_Value(newState, alpha, beta, depth + 1));
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
		double v = 100000;
		// get a list of non-full columns
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			v = Math.min(v, Max_Value(newState, alpha, beta, depth + 1));
			if (v <= alpha) {
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

	// is the recursion finished
	// either when max depth is reached, or a tie game is reached, or somebody
	// won
	private boolean finished(int depth, boolean tie, int winner) {
		return depth >= MAX_DEPTH || tie || winner != 0;
	}

	// evaluate he state
	private double eval(State state, int winner, boolean tie, int depth) {
		// if the winner is max player
		if (winner == this.maxPlayerID)
			return 512 - depth * 5;
		// if the winner is min player
		else if (winner != this.maxPlayerID && winner > 0)
			return -512 + depth * 5;
		else if (tie)
			return 0;
		// if none of the above, evaluate current state
		return pControl.Heuristic(state, 4, maxPlayerID);
	}
}
