package gamesplayers;


public class Player extends Games {

	private String playerName;
	private String playerPassword;
	
	public Player(String gameName, String[] players) {
		super(gameName, players);
		
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
	
	
	
}
