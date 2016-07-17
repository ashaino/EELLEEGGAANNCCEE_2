package gamesplayers;


import java.util.List;
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
	private boolean saveApproved;
	private int roundNo;
	private int gamePlayerID;





	public String sendFreePlayerList(){

		String playerList = "";

		for (Player player: Game.onlinePlayersPlaying) {

			playerList+=player.getPlayerID()+";"+player.getPlayerName()+";";
		}

		playerList ="samplelist";
		return playerList;
	}

	public String getPlayerUserName() {
		return playerUserName;
	}


	public void setPlayerUserName(String playerUserName) {
		this.playerUserName = playerUserName;
	}


	public void updatePlayerScore(int score) {

		// find efficient data structure
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

		Game.onlinePlayersNotPlaying.add(player);

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

		for (Player player: Game.onlinePlayersNotPlaying) {

			if(player.playerID == playerID){

				player.playerStatus = 1;
				onlinePlayersNotPlaying.remove(player);
				break;
			}
		}
		return onlinePlayersPlaying;
	}



	// update existing players on the not playing list who are playing

	public static List<Player> updatePlayerPlaying(int playerID){

		for (Player player: Game.onlinePlayersNotPlaying) {

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


	public static List<Player> updatePlayerNotPlaying(int playerID){

		for (Player player: Game.onlinePlayersPlaying) {

			if(player.playerID == playerID){

				player.playerStatus = 0;
				onlinePlayersPlaying.remove(player);
				onlinePlayersNotPlaying.add(player);
				break;
			}
		}
		return onlinePlayersNotPlaying;
	}




	public void updateSaveResponse(String saveResponse){

		// update save response variable

	}


	public boolean requestToSave(int gameID){

		boolean requestApproved = false;
		// requesting to save
		// sending save request to other members

			for (Player player : onlinePlayersPlaying) {

			}

		return requestApproved;
	}



	public void playerRespondSaveRequest(){

		// TODO: responding to player requests

	}

	public void seeHighScores(){


	}

	public void invitePlayers(int gameid, int[] players){



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

	public boolean isSaveApproved() {
		return saveApproved;
	}

	public void setSaveApproved(boolean saveApproved) {
		this.saveApproved = saveApproved;
	}

	public int getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}

	public int getGamePlayerID() {
		return gamePlayerID;
	}

	public void setGamePlayerID(int gamePlayerID) {
		this.gamePlayerID = gamePlayerID;
	}




}


