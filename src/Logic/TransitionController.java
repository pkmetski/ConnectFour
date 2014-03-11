package Logic;

import java.util.ArrayList;
import java.util.Collections;
import Model.State;

public class TransitionController {

	// returns a list of non-full columns available in this state
	public ArrayList<Integer> getAvailableColumns(State state) {
		ArrayList<Integer> columns = new ArrayList<Integer>();

		for (int col = 0; col < state.getX(); col++) {
			if (state.getBoard()[col][state.getY() - 1] == 0) {
				columns.add(col);
			}
		}
		// in order to make the alpha-beta pruning more efficient, the columns
		// are shuffled
		Collections.shuffle(columns);
		return columns;
	}

	// create a child state by cloning the parent state
	// switch the player and apply the action
	public State createChild(int action, State state) {
		State newState = state.clone();
		return transition(newState, action, (state.getPlayerId() % 2) + 1);
	}

	// apply an action to the state
	// this changes the game board within the state object
	public State transition(State currentState, int x, int playerId) {
		currentState.playPosition(x, playerId);
		return currentState;
	}
}
