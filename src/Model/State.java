package Model;

import java.util.ArrayList;

//this represents a node of the game tree
public class State {
	private int[][] board;
	private int[] yPos;
	private int playerId;
	private double value;
	private int lastX;

	public State(int[][] board, int[] yPos) {
		this.board = board;
		this.yPos = yPos;
	}

	public void playPosition(int x, int playerId) {
		this.lastX = x;
		this.playerId = playerId;
		board[x][yPos[x]++] = playerId;
	}

	public boolean isBoardFull() {
		for (int i = 0; i < yPos.length; i++) {
			if (yPos[i] < getY()) {
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

	public int[][] getBoard() {
		return this.board;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public State clone() {
		int[] newYPos = new int[yPos.length];
		System.arraycopy(yPos, 0, newYPos, 0, this.getX());

		int[][] newBoard = new int[this.getX()][this.getY()];
		for (int i = 0; i < this.getX(); i++) {
			System.arraycopy(this.board[i], 0, newBoard[i], 0, this.getY());
		}
		return new State(newBoard, newYPos);
	}
}
