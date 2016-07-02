package gamesplayers;

public class HighScores extends Player{

	private int highScoreID;
	public int getHighScore() {
		return highScore;
	}


	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}


	private int highScore;


	public void updateHighScores(){

		// TODO: update high scores
	}


	public int getHighScoreID() {
		return highScoreID;
	}


	public void setHighScoreID(int highScoreID) {
		this.highScoreID = highScoreID;
	}
}
