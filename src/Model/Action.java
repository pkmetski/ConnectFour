package Model;

//this represents an edge of the game tree
public class Action {

	private int column;
	private State oldState;
	private State newState;

	public Action(int column, State oldState, State newState) {
		this.column = column;
		this.oldState = oldState;
		this.newState = newState;
	}
}
