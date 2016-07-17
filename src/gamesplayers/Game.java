package gamesplayers;


import java.util.ArrayList;
import java.util.List;

import dataaccess.Database;


public class Game {

	protected int gameID;
	protected static String gameName;
	protected static int playerCount;
	protected static int gameStatus;
	protected static Player[] players;
	protected int gameRoundNo;
	protected Database database;


	// TODO: efficient data structure
	protected static List<Player> onlinePlayersNotPlaying
										= new ArrayList<Player>();

	/* represents players who are online
		* and currently playing a game
		*/
	protected static List<Player> onlinePlayersPlaying
										= new ArrayList<Player>();

	public Game(){

	}


	public String sendScores(int gameid, int score){

		String playerScores = "";


		if (score<0) {
			// -1  connected not playing
			playerScores = "-1";
		}
		else {
			// >-1 connected and playing
			// TODO: update player scores according to received score
			// TODO: send scores of other players according to game id

		}


		return playerScores;
	}


	public static List<Player> getOnlinePlayersNotPlaying() {
		return onlinePlayersNotPlaying;
	}


	public static void setOnlinePlayersNotPlaying(List<Player> onlinePlayersNotPlaying) {
		Player.onlinePlayersNotPlaying = onlinePlayersNotPlaying;
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

	public boolean isAlreadyInGameGroup(int gameid, int playerid){

		boolean isMember = false;
		database = new Database();
		isMember = database.checkAlreadyMember(gameid, playerid);

		return isMember;
	}


	public void saveGame(){

		// TODO: save game


	}

	public void leaveGame(int gameid, int playerid){

		// remove from game
		database = new Database();
		database.removeLeavePlayer(gameid, playerid);

		Player.updatePlayerNotPlaying(playerid);


	}


}
