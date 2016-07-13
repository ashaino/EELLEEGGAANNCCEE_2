package gamesplayers;

import java.util.List;
import java.util.Map;

import dataaccess.Database;

public class HighScores extends GamePlayer{

	private int highScoreID;
	private int highScore;
	private int oldHighScore;
	private Player player;

	private Database database;

	public HighScores(){}

	public HighScores(int playerID, int highScore) {
		this.playerID = playerID;
		this.highScore = highScore;
	}


	public int getHighScore() {
		return highScore;
	}


	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}




	public void updateHighScores(){

		for (Player player : onlinePlayersPlaying) {

			if(player.getPlayerTotalScore() > getOldHighScore()){

				setOldHighScore(player.getPlayerTotalScore());
				database.saveHighScores(highScoreID, player.getPlayerTotalScore());
			}
		}
	}


	public int getHighScoreID() {
		return highScoreID;
	}


	public void setHighScoreID(int highScoreID) {
		this.highScoreID = highScoreID;
	}


	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getOldHighScore() {
		return oldHighScore;
	}

	public void setOldHighScore(int newHighScore) {

		oldHighScore = newHighScore;
	}

	public void setOldHighScore() {

		 List highScoreRecords = database.loadHighScores();

		 for(Object object : highScoreRecords){

          Map row = (Map)object;
          oldHighScore = (int) row.get("highscore");
          break;
       }
	}
}
