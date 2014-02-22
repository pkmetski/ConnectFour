<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Cluster;
import Model.ClusterCollection;
import Model.Position;

=======
>>>>>>> 2b4dba0c02f67d6489f46afe5d409ca5079e9bc6
public class GameLogic implements IGameLogic {
	private int x = 0;
	private int y = 0;
	private int playerID;
<<<<<<< HEAD
	ClusterCollection CC;
=======
>>>>>>> 2b4dba0c02f67d6489f46afe5d409ca5079e9bc6

	public GameLogic() {
		// TODO Write your implementation for this method
	}

	public void initializeGame(int x, int y, int playerID) {
		this.x = x;
		this.y = y;
		this.playerID = playerID;
<<<<<<< HEAD
		 CC=new ClusterCollection(x, y);
=======
>>>>>>> 2b4dba0c02f67d6489f46afe5d409ca5079e9bc6
		// TODO Write your implementation for this method
	}

	public Winner gameFinished() {
		// TODO Write your implementation for this method
		return Winner.NOT_FINISHED;
	}

	public void insertCoin(int column, int playerID) {
		// TODO Write your implementation for this method
<<<<<<< HEAD
		
		
		//get the x,y coordinates of the coin to insert
		//add a new 
	}

	public int decideNextMove() {
		// TODO Write your implementation for this method
		return 0;
=======
>>>>>>> 2b4dba0c02f67d6489f46afe5d409ca5079e9bc6
	}

	public int decideNextMove() {
		// TODO Write your implementation for this method
		return 0;
	}
}
