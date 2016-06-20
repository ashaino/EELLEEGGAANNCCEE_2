package gamesplayers;

public class Games {

	protected String GameName;
	protected String[] players;
	private final int MAX_PLAYERS = 4;
	
	
	public String[] getPlayers() {
		return players;
	}

	public void setPlayers(String[] players) {
		this.players = players;
	}
	

	public String getGameName() {
		return GameName;
	}

	public void setGameName(String gameName) {
		GameName = gameName;
	}

	public Games(String gameName, String[] players) {
		
		GameName = gameName;
		this.players = players;
	}

	public int getMaxPlayers() {
		return MAX_PLAYERS;
	}
	
	
	
}
