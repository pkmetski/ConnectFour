import Logic.GameTree;
import Logic.PatternControl;
import Logic.TransitionController;
import Model.Action;
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
		currentState = new State(new int[x][y], new int[x]);
		transitionController = new TransitionController();
		tree = new GameTree();
	}

	public Winner gameFinished() {
		int result = pControl.finishedgame(currentState.getLastX(),
				currentState.getLastY(currentState.getLastX()),
				currentState.getBoard(), WINNING_SIZE);
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
		transitionController.transition(currentState, x, playerID);
	}

	public int decideNextMove() {
		Action ac = tree.Alpha_Beta_Search(currentState);
		return ac.getColumn();
	}
}
