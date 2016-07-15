package gamesplayers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dataaccess.Database;


public class Player extends Game {

	protected int playerID;
	protected String playerUserName;
	private String playerIP;
	private String playerPassword;
	private String playerEmail;
	private boolean loginValid;
	private int playerScore;
	private int playerRoundScore;
	private int playerTotalScore;
	private int playerStatus;


	protected static List<Player> onlinePlayersNotPlaying
						= new ArrayList<Player>();

	/* represents players who are online
	 * and currently playing a game
	*/

	protected static List<Player> onlinePlayersPlaying
						= new ArrayList<Player>();



	public String getPlayerUserName() {
		return playerUserName;
	}


	public void setPlayerUserName(String playerUserName) {
		this.playerUserName = playerUserName;
	}





	public static List<Player> getOnlinePlayersNotPlaying() {
		return onlinePlayersNotPlaying;
	}


	public static void setOnlinePlayersNotPlaying(List<Player> onlinePlayersNotPlaying) {
		Player.onlinePlayersNotPlaying = onlinePlayersNotPlaying;
	}



	public Player(){

	}


	public Player(String playerName, int playerStatus){

		super();
		this.playerUserName = playerName;
		this.playerStatus = playerStatus;
	}

	public Player(int gameID, int playerID, int playerScore, int playerStatus){

		super();

		//Game.gameID = gameID;
		this.playerID = playerID;
		this.playerStatus = playerStatus;
	}


	public Player(int gameID, int playerID, int score) {
		//Game.gameID = gameID;
		this.playerID = playerID;
	}

	public Player(String gameName, String playerName, int playerScore) {

		this.playerUserName = playerName;
	}


	public Player(int playerID, String playerName, String email, String password) {

		this.playerID = playerID;
		this.playerUserName = playerName;
		this.playerEmail = email;
		this.playerPassword = password;
	}


	public Player(int playerID) {
		this.playerID = playerID;

	}


	// registration constructor

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


	public Player playerLogin(String email, String password){

		Player playerLogin = new Player();

		// use dbaccess to validate playerName, Password

		database = new Database();
		playerLogin = database.loadPlayerData(email, password);


		return playerLogin;

	}

	public void addPlayerToNotPlayingList(Player player){

		onlinePlayersNotPlaying.add(player);

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

	public boolean registerPlayer(String userName, String email, String password){

		database = new Database();
		Player player = new Player(userName, email,password);
		database.createPlayer(player);

		return true;
	}

	// remove offline players from the list

	public List<Player> removePlayerNotPlaying(int playerID){

		for (Player player: onlinePlayersNotPlaying) {

			if(player.playerID == playerID){

				player.playerStatus = 1;
				onlinePlayersNotPlaying.remove(player);
				break;
			}
		}
		return onlinePlayersPlaying;
	}



	// update existing players on the not playing list who are playing

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


	/* update players on the  playing list who are not playing
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







	public void playerRequestToSave(int gameID){

		// requesting to save
		// sending save request to other members

			for (Player player : onlinePlayersPlaying) {

			}
	}



	public void playerRespondSaveRequest(){

		// TODO: responding to player requests

	}

	public void seeHighScores(){


	}


	public int getPlayerStatus() {
		return playerStatus;
	}


	public void setPlayerStatus(int playerStatus) {
		this.playerStatus = playerStatus;
	}


	public boolean isLoginValid() {
		return loginValid;
	}


	public void setLoginValid(boolean loginValid) {
		this.loginValid = loginValid;
	}


	public int getPlayerScore() {
		return playerScore;
	}


	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}


	public int getPlayerRoundScore() {
		return playerRoundScore;
	}


	public void setPlayerRoundScore(int playerRoundScore) {
		this.playerRoundScore = playerRoundScore;
	}


	public int getPlayerTotalScore() {
		return playerTotalScore;
	}


	public void setPlayerTotalScore(int playerTotalScore) {
		this.playerTotalScore = playerTotalScore;
	}


	public String getPlayerIP() {
		return playerIP;
	}


	public void setPlayerIP(String playerIP) {
		this.playerIP = playerIP;
	}




}


