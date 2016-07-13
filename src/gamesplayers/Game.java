package gamesplayers;


import dataaccess.Database;


public class Game {

	protected int gameID;
	protected static String gameName;
	protected static int playerCount;
	protected static int gameStatus;


	protected static Player[] players;
	protected static int gameRoundNo;
	protected Database database;


	public Game(){

	}


	public Player[] getPlayers() {
		return players;
	}


	public static void setPlayers(Player[] players) {
		Game.players = players;
	}


	public Game(String gameName, int playerCount, int gameStatus) {

		Game.gameName = gameName;
		Game.playerCount = playerCount;
		Game.gameStatus = gameStatus;
	}

	public int getGameStatus() {
		return gameStatus;
	}



	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		Game.playerCount = playerCount;
	}

	private final int MAX_PLAYERS = 4;


	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		Game.gameName = gameName;
	}



	public int getMaxPlayers() {
		return MAX_PLAYERS;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public void createNewGame(String gameName, int playerCount, int gameStatus, Player[] players){

		setGameName(gameName);
		setPlayerCount(playerCount);
		setGameStatus(gameStatus);



	}

	private void setGameStatus(int gameStatus) {

		Game.gameStatus = gameStatus;

	}


	public void joinGame(){

		// TODO: join game

	}

	public boolean isAlreadyInGameGroup(){

		// TODO: check already a member of that game

		return true;
	}

	public void resumeGame(){

		// TODO: resume game

	}

	public boolean checkForSaveResponses(){

		// TODO: check save responses


		return true;
	}

	public void saveGame(){

		// TODO: save game

	}

}
