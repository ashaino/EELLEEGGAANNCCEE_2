package gamesplayers;


public class TrophyCabinet extends Player{

	private String trophyID;
	private String trophyName;
	private int trophyUnlocked;
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

	public int isTrophyUnlocked() {
		return trophyUnlocked;
	}

	public void setTrophyUnlocked(int isTrophyUnlocked) {
		this.trophyUnlocked = isTrophyUnlocked;
	}

	public String getTrophyID() {
		return trophyID;
	}

	public void setTrophyID(String trophyID) {
		this.trophyID = trophyID;
	}
}
