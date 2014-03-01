import java.util.Random;

import Logic.ClusterManager;
import Logic.GameTree;
import Logic.PatternControl;
import Logic.TransitionController;
import Model.Action;
import Model.Position;
import Model.State;

public class GameLogic implements IGameLogic {
	private int x = 0;
	private int y = 0;
	private int playerID;
	private PatternControl pControl;
	private final int WINNING_SIZE = 4;
	private State currentState;
	private TransitionController transitionController;
	private GameTree tree;

	public GameLogic() {
		// TODO Write your implementation for this method
	}

	public void initializeGame(int x, int y, int playerID) {
		this.x = x;
		this.y = y;
		this.playerID = playerID;
		pControl = new PatternControl();
		currentState = new State(x, y);
		transitionController = new TransitionController();
		tree = new GameTree();
	}

	public Winner gameFinished() {
		int result = pControl.playerWithContiguousLineOfSize(
				currentState.getClustersOfMinSize(WINNING_SIZE), WINNING_SIZE);

		if (result == 1) {
			return Winner.PLAYER1;
		} else if (result == 2) {
			return Winner.PLAYER2;
		} else if (currentState.isBoardFull()) {
			return Winner.TIE;
		} else {
			return Winner.NOT_FINISHED;
		}
	}

	public void insertCoin(int x, int playerID) {
		currentState = transitionController.transition(currentState, x,
				playerID);

	}

	public int decideNextMove() {
		Action ac = tree.Alpha_Beta_Search(currentState);
		currentState= ac.getNewState();
		return ac.getColumn();
	}
}
