package Logic;

import java.util.ArrayList;
import java.util.Random;

import javax.activity.InvalidActivityException;
import javax.management.InvalidApplicationException;

import Model.Action;
import Model.State;

public class GameTree {
	private State root;
	TransitionController transition = new TransitionController();
	private PatternControl pControl = new PatternControl();

	public GameTree(State currentState) {
		this.root = currentState;
		Alpha_Beta_Search(root);
	}

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
		Random rnd = new Random();
		return rnd.nextInt(5);
	}
}
