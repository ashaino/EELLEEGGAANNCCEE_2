package flogschema;

import datastructures.WordBank;

public class WordBoard extends WordBank{

	private int wordLength;
	private final int POWER_COUNT = 2;
	private static int autoSearchCount = 1;
	private int[] placedPowers;
	private char[] wordBoard;
	private final static int ST_TIME_GIVEN_IN_SECONDS = 180;
	private int timeTaken;


	// Initialize word board

	public WordBoard(){

		wordBoard = new char[12];
		placedPowers = new int[3];

		initialSetup(5);
	}


	public void initialSetup(int noVowels){

		placePowersOnTheWordBoard();
		giveFirst2Letters();
		give10letters(noVowels);

	}

	public int getWordLength() {
		return wordLength;
	}

	public static int getAutoSearch() {
		return autoSearchCount;
	}


	public static int getTimeGivenInSeconds() {
		return ST_TIME_GIVEN_IN_SECONDS;
	}

	public void setWordLength(int wordLength) {
		this.wordLength = wordLength;
	}

	public char[] getWordBoard() {
		return wordBoard;
	}

	public void setWordBoard(char[] wordBoard) {
		this.wordBoard = wordBoard;
	}

	public int getPowerCount() {
		return POWER_COUNT;
	}

	public int[] getPlacedPowers() {
		return placedPowers;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public void setPlacedPowers(int[] powerIndexes) {
		this.placedPowers = powerIndexes;
	}


	public void placePowersOnTheWordBoard(){

		randomizePowerPositions();

	}


	public int[] randomizePowerPositions(){

		int[] randomizedPowers = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};


		/* Powers double or triple the letter value
		 * First 6 letters are not given powers
		 * since player who only make longer words should
		 * be rewarded
		 *
		 * */
		int powerCount = 1;
		for (int i = 0; i < randomizedPowers.length; i++) {

			if(i > 6 && powerCount <= POWER_COUNT){


				/* does not matter
				 * whether powers are overwritten or not
				*/

				int randNumIndex = randInt(7, 11);
				int randNumMultiplier = randInt(2, 3);

				randomizedPowers[randNumIndex] = randNumMultiplier;
				powerCount += 1;
			}

		}

		return randomizedPowers;
	}

}
