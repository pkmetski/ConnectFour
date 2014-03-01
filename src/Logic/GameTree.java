package Logic;

import java.util.Random;

import Model.Action;
import Model.State;

public class GameTree {
	private TransitionController transition = new TransitionController();
	private Random rnd = new Random();
	private PatternControl pControl = new PatternControl();

	public Action Alpha_Beta_Search(State root) {
		double v = Max_Value(root, Double.MIN_VALUE, Double.MAX_VALUE, 0);
		for (Action action : root.getApplicableActions()) {
			if (action.getNewState().getValue() == v)
				return action;
		}
		return null;
	}

	public double Max_Value(State state, double alpha, double beta, int depth) {
		if (pControl.terminateTest(state) || cutOff(depth)) {
			return eval(state);
		}
		double v = Double.MIN_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			state.addApplicableAction(new Action(a, state, newState));
			v = Math.max(v, Min_Value(newState, alpha, beta, depth + 1));
			newState.setValue(v);
			if (v >= beta) {
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	public double Min_Value(State state, double alpha, double beta, int depth) {
		if (pControl.terminateTest(state) || cutOff(depth)) {
			return eval(state);
		}
		double v = Double.MAX_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			state.addApplicableAction(new Action(a, state, newState));
			v = Math.min(v, Max_Value(newState, alpha, beta, depth + 1));
			newState.setValue(v);
			if (v <= alpha) {
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

	private boolean cutOff(int depth) {
		return depth >= 5;
	}

	private double eval(State state) {
		return rnd.nextInt(50);
	}
}
