import Logic.ClusterManager;
import Logic.PatternControl;
import Model.Position;

public class GameLogic implements IGameLogic {
	private int x = 0;
	private int y = 0;
	private int playerID;
	private ClusterManager cManager;
	private PatternControl pControl;
	private Position[][] board;
	private int[] yPos;
	private final int WINNING_SIZE = 4;

	public GameLogic() {
		// TODO Write your implementation for this method
	}

	public void initializeGame(int x, int y, int playerID) {
		this.x = x;
		this.y = y;
		this.playerID = playerID;
		cManager = new ClusterManager(x, y);
		pControl = new PatternControl();
		board = new Position[x][y];
		yPos = new int[x];
	}

	public Winner gameFinished() {
		int result = pControl.playerWithContiguousLineOfSize(
				cManager.getClustersOfMinSize(WINNING_SIZE), WINNING_SIZE);

		if (result == 1) {
			return Winner.PLAYER1;
		} else if (result == 2) {
			return Winner.PLAYER2;
		} else if (isBoardFull()) {
			return Winner.TIE;
		} else {
			return Winner.NOT_FINISHED;
		}
	}

	public void insertCoin(int x, int playerID) {
		Position pos = new Position(x, yPos[x], playerID);
		board[x][yPos[x]++] = pos;
		cManager.addPosition(pos);
	}

	public int decideNextMove() {
		// TODO Write your implementation for this method
		return 0;
	}

	private boolean isBoardFull() {
		for (int i = 0; i < yPos.length; i++) {
			if (yPos[i] < y - 1) {
				return false;
			}
		}
		return true;
	}
}
