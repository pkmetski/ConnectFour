import Logic.ClusterManager;
import Logic.PatternControl;
import Model.Position;
import Model.State;

public class GameLogic implements IGameLogic {
	private int x = 0;
	private int y = 0;
	private int playerID;
	private PatternControl pControl;
	private final int WINNING_SIZE = 4;
	private State state;

	public GameLogic() {
		// TODO Write your implementation for this method
	}

	public void initializeGame(int x, int y, int playerID) {
		this.x = x;
		this.y = y;
		this.playerID = playerID;
		pControl = new PatternControl();
		state = new State(x, y);
	}

	public Winner gameFinished() {
		int result = pControl.playerWithContiguousLineOfSize(
				state.getClustersOfMinSize(WINNING_SIZE), WINNING_SIZE);

		if (result == 1) {
			return Winner.PLAYER1;
		} else if (result == 2) {
			return Winner.PLAYER2;
		} else if (state.isBoardFull()) {
			return Winner.TIE;
		} else {
			return Winner.NOT_FINISHED;
		}
	}

	public void insertCoin(int x, int playerID) {
		state.addPosition(x, playerID);
	}

	public int decideNextMove() {
		// TODO Write your implementation for this method
		return 0;
	}
}
