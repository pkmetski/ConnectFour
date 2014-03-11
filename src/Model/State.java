package Model;

//this represents a node of the game tree
public class State {
	private byte[][] board;
	private int[] yPos;
	private int playerId;
	private int lastX=0;

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

	public int getLastY(int x) {
		return this.yPos[x] - 1;
	}

	public int getLastX() {
		return this.lastX;
	}

	public byte[][] getBoard() {
		return this.board;
	}

	public int getPlayerId() {
		return this.playerId;
	}

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
