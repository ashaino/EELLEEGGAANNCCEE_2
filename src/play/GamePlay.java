package play;


import flogschema.LetterBag;
import flogschema.WordBoard;
import gamesplayers.Player;

public class GamePlay {

	public static void main(String[] args) {
		
		// testing code
		
		Player player = new Player(null, null);
		
		player.setGameName("GameName");
		
		player.setPlayerName("Player");
		
		
		LetterBag letterBag = new LetterBag();
		
		char[] tenLetters = letterBag.give10letters(5);
		for (char c : tenLetters) {
			System.out.println(c);
		}
		
		System.out.println("----------------------");
		
		WordBoard wordBoard = new WordBoard();
		
		for (int power : wordBoard.randomizePowerPositions()) {
			System.out.println(power);
		}
		
	}

}
