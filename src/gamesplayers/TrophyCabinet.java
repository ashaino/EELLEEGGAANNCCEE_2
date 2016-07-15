package gamesplayers;

public class TrophyCabinet extends Player{

	private String trophyID;
	private String trophyName;
	private boolean trophyUnlocked;
	private String trophyDescription;
	private int matchCount;
	private int twelveWordCount;
	private int matchWinCount;



	public String isTrophyDescription() {
		return trophyDescription;
	}

	public void setTrophyDescription(String trophyDescription) {
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
		return trophyUnlocked;
	}

	public void setTrophyUnlocked(boolean isTrophyUnlocked) {
		this.trophyUnlocked = isTrophyUnlocked;
	}

	public String getTrophyID() {
		return trophyID;
	}

	public void setTrophyID(String trophyID) {
		this.trophyID = trophyID;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void seMatchCount(int trophyCount) {
		this.matchCount = trophyCount;
	}

	public int getTwelveWordCount() {
		return twelveWordCount;
	}

	public void setTwelveWordCount(int twelveWordCount) {
		this.twelveWordCount = twelveWordCount;
	}

	public int getMatchWinCount() {
		return matchWinCount;
	}

	public void setMatchWinCount(int matchWinCount) {
		this.matchWinCount = matchWinCount;
	}
}
