package gamesplayers;

import dataaccess.Database;

public class Game {

	protected static int gameID;
	protected static String gameName;
	protected static String levelInfo;
	protected static int playerCount;
	protected static Player[] players;
	protected static int gameRoundNo;
	protected Database database;


	public Game(){

	}

	public Game(String gameName, int playerCount, String levelInfo){

		Game.gameName = gameName;
		Game.playerCount = playerCount;
		Game.levelInfo = levelInfo;

	}

	public String getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		Game.levelInfo = levelInfo;
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
		Game.gameID = gameID;
	}

	public void createNewGame(String gameName, int playerCount, String levelInfo, Player[] players){

		setGameName(gameName);
		setPlayerCount(playerCount);
		setLevelInfo(levelInfo);
		database.createGame(gameName, playerCount, levelInfo, players);


	}

	public void joinGame(){


	}

	public void resumeGame(){


	}


}
