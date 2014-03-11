package Model;

//this represents a node of the game tree
public class State {
	private byte[][] board; // the board game
	private int[] yPos; // keep the track of the empty places in each column
	private int playerId; // last player ID that create this state
	private int lastX=0; // last column number that create this state

	public State(byte[][] board, int[] yPos) {
		this.board = board;
		this.yPos = yPos;
	}

	public void playPosition(int x, int playerId) {
		this.lastX = x;
		this.playerId = playerId;
		board[x][yPos[x]++] =(byte) playerId;
	}

	public boolean isBoardFull() {
		for (int i = 0; i < yPos.length; i++) {
			if (yPos[i] < getY()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isBoardEmpty() {
		for (int i = 0; i < yPos.length; i++) {
			if (yPos[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public int[] getYPos() {
		return yPos;
	}

	public int getX() {
		return this.board.length;
	}

	public int getY() {
		return this.board[0].length;
	}

	// return the next available position in column x
	public int getLastY(int x) {
		return this.yPos[x] - 1;
	}

	// return the last column that created this state
	public int getLastX() {
		return this.lastX;
	}

	public byte[][] getBoard() {
		return this.board;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	//has been override to keep the arrays in the new state
	@Override
	public State clone() {
		int[] newYPos = new int[yPos.length];
		System.arraycopy(yPos, 0, newYPos, 0, this.getX());

		byte[][] newBoard = new byte[this.getX()][this.getY()];
		for (int i = 0; i < this.getX(); i++) {
			System.arraycopy(this.board[i], 0, newBoard[i], 0, this.getY());
		}
		return new State(newBoard, newYPos);
	}
}
