package Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Model.State;

public class GameTree {
	private TransitionController transition = new TransitionController();
	private Random rnd = new Random();
	private PatternControl pControl = new PatternControl();
	private Map<Double, Integer> applicableActions;

	public int Alpha_Beta_Search(State root) {
		applicableActions= new HashMap<Double, Integer>();
		return applicableActions.get(Max_Value(root, Double.MIN_VALUE, Double.MAX_VALUE, 0));
	}

	public double Max_Value(State state, double alpha, double beta, int depth) {
		if (cutOff(depth, state)) {
			return eval(state);
		}
		double v = Double.MIN_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			v = Math.max(v, Min_Value(newState, alpha, beta, depth + 1));
			newState.setValue(v);
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
		if (cutOff(depth, state)) {
			return eval(state);
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

	private boolean cutOff(int depth, State state) {
		return depth >= 1 || state.isBoardFull() || pControl.terminateTest(state);
	}

	private double eval(State state) {
		double winner= pControl.finishedgame(state, 4);
		if(winner==2) return Double.MAX_VALUE;
		if(winner==1) return Double.MIN_VALUE;
		return pControl.verHeuristic(state, 4);
//		return rnd.nextInt(50);
	}
}
