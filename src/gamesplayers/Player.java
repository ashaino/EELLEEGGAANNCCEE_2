package gamesplayers;


public class Player extends Game {
	
	private int playerID;
	private String playerName;
	private String playerPassword;
	private String playerEmail;
	private int playerScore;
	private int playerActive;
	
	
	public Player(String playerName, int isActive){
		
		super(Game.gameName, Game.playerCount,
				Game.levelInfo);
		this.playerName = playerName;
		this.playerActive = isActive;
	}
	
	public Player(int gameID, int playerID, int playerScore, int isActive){
		
		super(Game.gameName, Game.playerCount,
				Game.levelInfo);
		
		Game.gameID = gameID;
		this.playerID = playerID;
		this.playerScore = playerScore;
		this.playerActive = isActive;
	}
	

	public Player(int gameID, int playerID, int score) {
		this.gameID = gameID;
		this.playerID = playerID;
		this.playerScore = score;
	}

	public Player(String gameName, String playerName, int playerScore) {
		this.gameName = gameName;
		this.playerName = playerName;
		this.playerScore = playerScore;
	}

	

	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
		return playerScore;
	}



	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
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
	
	
}
