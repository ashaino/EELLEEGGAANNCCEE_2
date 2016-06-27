package gamesplayers;


public class TrophyCabinet extends Player{

	private String trophyName;
	private boolean isTrophyUnlocked;
	private boolean trophyDescription;
	
	
	public boolean isTrophyDescription() {
		return trophyDescription;
	}

	public void setTrophyDescription(boolean trophyDescription) {
		this.trophyDescription = trophyDescription;
	}

	public TrophyCabinet(){
		
		super();
	}

	public String getTrophyName() {
		return trophyName;
	}

	public void setTrophyName(String trophyName) {
		this.trophyName = trophyName;
	}

	public boolean isTrophyUnlocked() {
		return isTrophyUnlocked;
	}

	public void setTrophyUnlocked(boolean isTrophyUnlocked) {
		this.isTrophyUnlocked = isTrophyUnlocked;
	}
}
