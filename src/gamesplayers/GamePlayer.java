package gamesplayers;



public class GamePlayer extends Player{

	private int gamePlayerID;
	private int[] gamePlayerIDs;



	public GamePlayer(){}

	public GamePlayer(Integer gameID, int playerID, int playerScore) {

		this.gameID = gameID;
		this.playerID = playerID;
		this.setPlayerScore(playerScore);

	}



	public int getGameID() {
		return gameID;
	}


	public void setGameID(int gameID) {
		this.gameID = gameID;
	}


	public int getPlayerID() {
		return playerID;
	}


	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


	public int getGamePlayerID() {
		return gamePlayerID;
	}


	public void setGamePlayerID(int gamePlayerID) {
		this.gamePlayerID = gamePlayerID;
	}

	public int[] getGamePlayerIDs() {
		return gamePlayerIDs;
	}

	public void setGamePlayerIDs(int[] gamePlayerIDs) {
		this.gamePlayerIDs = gamePlayerIDs;
	}




}
