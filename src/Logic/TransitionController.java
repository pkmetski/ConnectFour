package Logic;

import java.util.ArrayList;
import Model.State;

public class TransitionController {

	public ArrayList<Integer> getAvailableColumns(State state) {
		ArrayList<Integer> columns = new ArrayList<Integer>();

		for (int col = 0; col < state.getYPos().length; col++) {
			if (state.getYPos()[col] < state.getY()) {
				columns.add(col);
			}
		}
		return columns;
	}

	public State createChild(int action, State state) {
		// int player =state.getPlayerId()==2 ? 1: 2;
		State newState = state.clone();
		newState.getApplicableActions().clear();
		return transition(newState, action, (state.getPlayerId() % 2) + 1);
	}

	public State transition(State currentState, int x, int playerId) {
		currentState.playPosition(x, playerId);
		return currentState;
	}
}
