package Logic;

import java.util.ArrayList;
import java.util.Collections;
import Model.State;

public class TransitionController {

	public ArrayList<Integer> getAvailableColumns(State state) {
		ArrayList<Integer> columns = new ArrayList<Integer>();

		for (int col = 0; col < state.getX(); col++) {
			if (state.getBoard()[col][state.getY()-1] ==0) {
				columns.add(col);
			}
		}
		 Collections.shuffle(columns);
		return columns;
	}

	public State createChild(int action, State state) {
		State newState = state.clone();
		return transition(newState, action, (state.getPlayerId() % 2) + 1);
	}

	public State transition(State currentState, int x, int playerId) {
		currentState.playPosition(x, playerId);
		return currentState;
	}
}
