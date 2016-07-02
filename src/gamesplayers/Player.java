package gamesplayers;

import java.util.ArrayList;
import java.util.List;

import dataaccess.Database;

public class Player extends Game {

	protected int playerID;
	protected String playerUserName;
	private String playerPassword;
	private String playerEmail;
	private int playerRoundScore;
	private int playerTotalScore;
	private int playerStatus;
	private int highScore;

	private static List<Player> onlinePlayersNotPlaying
						= new ArrayList<Player>();

	public String getPlayerUserName() {
		return playerUserName;
	}


	public void setPlayerUserName(String playerUserName) {
		this.playerUserName = playerUserName;
	}


	public int getPlayerRoundScore() {
		return playerRoundScore;
	}


	public void setPlayerRoundScore(int playerRoundScore) {
		this.playerRoundScore = playerRoundScore;
	}


	public static List<Player> getOnlinePlayersNotPlaying() {
		return onlinePlayersNotPlaying;
	}


	public static void setOnlinePlayersNotPlaying(List<Player> onlinePlayersNotPlaying) {
		Player.onlinePlayersNotPlaying = onlinePlayersNotPlaying;
	}


	/* represents players who are online
	 * and currently playing a game
	*/
	private static List<Player> onlinePlayersPlaying
						= new ArrayList<Player>();


	public Player(){

	}


	public Player(String playerName, int playerStatus){

		super();
		this.playerUserName = playerName;
		this.playerStatus = playerStatus;
	}

	public Player(int gameID, int playerID, int playerScore, int playerStatus){

		super();

		Game.gameID = gameID;
		this.playerID = playerID;
		this.playerRoundScore = playerScore;
		this.playerStatus = playerStatus;
	}


	public Player(int gameID, int playerID, int score) {
		Game.gameID = gameID;
		this.playerID = playerID;
		this.playerRoundScore = score;
	}

	public Player(String gameName, String playerName, int playerScore) {
		Game.gameName = gameName;
		this.playerUserName = playerName;
		this.playerRoundScore = playerScore;
	}



	public Player(int playerID, int highScore) {
		this.playerID = playerID;
		this.setHighScore(highScore);
	}


	public Player(String playerName, String playerEmail, String playerPassword) {

		this.playerUserName = playerName;
		this.playerEmail = playerEmail;
		this.playerPassword = playerPassword;

	}


	public String getPlayerName() {
		return playerUserName;
	}

	public void setPlayerName(String playerName) {
		this.playerUserName = playerName;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}


	public boolean playerAuthentication(){

		boolean authSuccess = false;

		// use dbaccess to validate playerName, Password

		return authSuccess;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void registerPlayer(){


	}

	public void getPlayerDetails(){


	}


	public int getPlayerScore() {
		return playerRoundScore;
	}



	public void setPlayerScore(int playerScore) {
		this.playerRoundScore = playerScore;
	}


	// update players who are playing

	public List<Player> updatePlayerPlaying(int playerID){

		for (Player player: onlinePlayersNotPlaying) {

			if(player.playerID == playerID){

				player.playerStatus = 1;
				onlinePlayersNotPlaying.remove(player);
				onlinePlayersPlaying.add(player);
				break;
			}
		}
		return onlinePlayersPlaying;
	}


	/* update players who are not playing
	 * 0 disconnected
	 * 1 connected & free
	 * 2 engaged
	*/


	public List<Player> updatePlayerNotPlaying(int playerID){

		for (Player player: onlinePlayersPlaying) {

			if(player.playerID == playerID){

				player.playerStatus = 0;
				onlinePlayersPlaying.remove(player);
				onlinePlayersNotPlaying.add(player);
				break;
			}
		}
		return onlinePlayersNotPlaying;
	}


	public boolean playerLogin(String userName, String password){

		database = new Database();

		return database.loadPlayerData(userName, password);

	}




	public int getPlayerTotalScore() {
		return playerTotalScore;
	}


	public void setPlayerTotalScore(int playerTotalScore) {
		this.playerTotalScore = playerTotalScore;
	}


	public void playerRequestToSave(){

		// TODO: requesting to quit

		playerSendSaveRequests();
	}

	public void playerSendSaveRequests(){

		// TODO: sending save request to other members
	}

	public void playerRespondSaveRequest(){

		// TODO:

	}

	public void seeHighScores(){


	}


	public int getPlayerStatus() {
		return playerStatus;
	}


	public void setPlayerStatus(int playerStatus) {
		this.playerStatus = playerStatus;
	}


	public int getHighScore() {
		return highScore;
	}


	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}


}


