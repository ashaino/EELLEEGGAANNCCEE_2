package gamesplayers;

import java.util.ArrayList;
import java.util.List;

import dataaccess.Database;

public class Player extends Game {

	private int playerID;
	private String playerUserName;
	private String playerPassword;
	private String playerEmail;
	private int playerRoundScore;
	private int playerTotalScore;
	private int playerActive;
	private boolean isPlaying;

	private static List<Player> onlinePlayersNotPlaying
						= new ArrayList<Player>();

	/* represents players who are online
	 * and currently playing a game
	*/
	private static List<Player> onlinePlayersPlaying
						= new ArrayList<Player>();


	public Player(){

	}


	public Player(String playerName, int isActive){

		super(Game.gameName, Game.playerCount,
				Game.levelInfo);
		this.playerUserName = playerName;
		this.playerActive = isActive;
	}

	public Player(int gameID, int playerID, int playerScore, int isActive){

		super(Game.gameName, Game.playerCount,
				Game.levelInfo);

		Game.gameID = gameID;
		this.playerID = playerID;
		this.playerRoundScore = playerScore;
		this.playerActive = isActive;
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

	public int getIsActive() {
		return playerActive;
	}

	public void setActive(int isActive) {
		this.playerActive = isActive;
	}

	public int getPlayerActive() {
		return playerActive;
	}

	public void setPlayerActive(int playerActive) {
		this.playerActive = playerActive;
	}


	public boolean isPlayerPlay() {
		return isPlaying;
	}


	public void setPlayerPlay(boolean playerPlay) {
		this.isPlaying = playerPlay;
	}



	// update players who are playing

	public List<Player> updatePlayerPlaying(int playerID){

		for (Player player: onlinePlayersNotPlaying) {

			if(player.playerID == playerID){

				player.isPlaying = true;
				onlinePlayersNotPlaying.remove(player);
				onlinePlayersPlaying.add(player);
				break;
			}
		}
		return onlinePlayersPlaying;
	}


	// update players who are not playing

	public List<Player> updatePlayerNotPlaying(int playerID){

		for (Player player: onlinePlayersPlaying) {

			if(player.playerID == playerID){

				player.isPlaying = false;
				onlinePlayersPlaying.remove(player);
				onlinePlayersNotPlaying.add(player);
				break;
			}
		}
		return onlinePlayersNotPlaying;
	}


	public boolean playerLogin(String userName, String password){

		boolean isValidCombination = false;
		Player player = new Player();

		database = new Database();
		player = database.loadPlayerData();
		isValidCombination = (player.playerEmail ==
				userName && player.playerPassword == password)?true:false;

		return isValidCombination;
	}




	public int getPlayerTotalScore() {
		return playerTotalScore;
	}


	public void setPlayerTotalScore(int playerTotalScore) {
		this.playerTotalScore = playerTotalScore;
	}


}


