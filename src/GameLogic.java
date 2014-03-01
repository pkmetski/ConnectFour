import java.util.Random;

import Logic.ClusterManager;
import Logic.GameTree;
import Logic.PatternControl;
import Logic.TransitionController;
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
		
		GameTree tree= new GameTree(currentState);
	}

	public int decideNextMove() {
		// TODO Write your implementation for this method
		Random rand = new Random();
		return rand.nextInt(x);
	}
}
