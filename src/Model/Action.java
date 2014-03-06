package Model;

//this represents an edge of the game tree
public class Action {

	private int column;
	private State newState;

	public Action(int column, State newState) {
		this.column = column;
		this.newState = newState;
	}

	public State getNewState() {
		return newState;
	}

	public int getColumn() {
		return column;
	}
}
