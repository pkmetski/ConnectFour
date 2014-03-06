package Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Model.Action;
import Model.State;

public class GameTree {
	private TransitionController transition = new TransitionController();
	private Random rnd = new Random();
	private PatternControl pControl = new PatternControl();
	private ArrayList<Action> applicableActions;

	public Action Alpha_Beta_Search(State root) {
		applicableActions=new ArrayList<Action>();
		double v = Max_Value(root, Double.MIN_VALUE, Double.MAX_VALUE, 0);
		for (Action action : applicableActions) {
			if (action.getNewState().getValue() == v)
				return action;
		}
		return null;
	}

	public double Max_Value(State state, double alpha, double beta, int depth) {
		if (cutOff(depth, state) || pControl.terminateTest(state)) {
			return eval(state);
		}
		double v = Double.MIN_VALUE;
		for (int a : transition.getAvailableColumns(state)) {
			State newState = transition.createChild(a, state);
			if (depth == 0)
				applicableActions.add(new Action(a, newState));
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
		if (cutOff(depth, state) || pControl.terminateTest(state)) {
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
		return depth >= 5 || state.isBoardFull();
	}

	private double eval(State state) {
		int asda = rnd.nextInt(50);
		return asda;
	}
}
