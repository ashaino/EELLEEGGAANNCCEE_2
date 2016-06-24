package flogschema;

import java.util.Random;

public class Scorer extends WordBank {

	private static double roundScore = 0;
	private static double currentScore = 0;
	private static double highScore;
	private double powerBonus;
	private double wordLengthBonus;
	private final static int TWELVE_LETTER_WORD_MULTIPLICATION = 20;
	private final static int roundBonus = 100;
	private static boolean roundEnd = false;
	private int timeBonus;
	private static double totalScore;

	public static int getTwelveLetterWordMultiplication() {

		return TWELVE_LETTER_WORD_MULTIPLICATION;
	}

	public double getRoundScore() {
		return roundScore;
	}

	public void setRoundScore(int roundScore) {
		Scorer.roundScore = roundScore;
	}

	public double getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(double currentScore) {
		Scorer.currentScore = currentScore;
	}

	public static double getHighScore() {
		return highScore;
	}

	public static void setHighScore(double highScore) {
		Scorer.highScore = highScore;
	}

	public double getPowerBonus() {
		return powerBonus;
	}

	public void setPowerBonus(double powerBonus) {
		this.powerBonus = powerBonus;
	}

	public double getWordLengthBonus() {
		return wordLengthBonus;
	}

	public void setWordLengthBonus(double wordLengthBonus) {
		this.wordLengthBonus = wordLengthBonus;
	}

	public static int getRoundbonus() {
		return roundBonus;
	}

	public static void setRoundEnd(boolean roundEnd) {
		Scorer.roundEnd = roundEnd;
	}

	public int getTimeBonus() {
		return timeBonus;
	}

	public void setTimeBonus(int timeBonus) {
		this.timeBonus = timeBonus;
	}

	public static double getTotalScore() {
		return totalScore;
	}

	public static void setTotalScore(double roundScore2) {
		Scorer.totalScore = roundScore2;
	}

	public void calculateTotalScore() {

		roundScore = (Scorer.roundEnd) ? 
				getCurrentScore() + roundBonus : 0;
		
		// updating total score
		setTotalScore(getTotalScore() + getRoundScore() 
				+ getTimeBonus() + getWordLengthBonus() + getPowerBonus());
	}

	/*
	 * calculate penalty given to the weakest players based on option choosed
	 */

	public double calculatePenalty(int selectedPenaltyOption) {

		/*
		 * Incase of several weakest links their degree of punishment depends on
		 * option choosed which is randomized
		 */

		double scoreWithPenalty = 0.0;

		switch (selectedPenaltyOption) {

		case 0:
			scoreWithPenalty = getTotalScore() * 
				PenaltyOptions.LOW_DAMAGE.getDAMAGE();
			break;
		case 1:
			scoreWithPenalty = getTotalScore() * 
				PenaltyOptions.MEDIUM_DAMAGE.getDAMAGE();
			break;
		case 2:
			scoreWithPenalty = getTotalScore() * 
				PenaltyOptions.HIGH_DAMAGE.getDAMAGE();
			break;
		}

		return scoreWithPenalty;
	}

	
	public double calculateScoreForValidWord(char[] inputWord) {

		boolean isCompleteWord;
		boolean isValidWord;

		isValidWord = verifyInputWord(getWord());
		isCompleteWord = isCompleteWord(getWord());

		currentScore = (isValidWord && isCompleteWord) ?
				calculate12LetterWordScore(inputWord)
				: calculateLetterScores(inputWord);

		return currentScore;
	}

	
	public double calculate12LetterWordScore(char[] inputWord) {

		double score = getCurrentScore();
		score = (score + calculateLetterScores(inputWord)) 
					* getTwelveLetterWordMultiplication();

		return score;

	}

	public double calculateLetterScores(char[] inputWord) {

		double score = getCurrentScore();

		/*
		 * use enum Consonants and enum Vowel values to calculate word score
		 * 
		 */

		/*
		 * performance can be improved to O(n1*log(n2) + n1*log(n3)) using an
		 * binary Search to do that have to change Enums to an array array that
		 * is filled from a text file
		 * 
		 * In this case since n1, n2, n3 small not a bigger issue currently
		 * performance is close to O(n1*n2 + n1*n3)
		 * 
		 */

		for (char letter : inputWord) {

			for (Vowels vowel : Vowels.values()) {

				if (letter == vowel.getVowel()) {
					score += vowel.getVowelValues();
					break;
				}
			}
		}

		for (char letter : inputWord) {

			for (Consonants consonants : Consonants.values()) {

				if (letter == consonants.getConsonant()) {

					score += consonants.getConsonantValue();
					break;
				}
			}
		}

		setCurrentScore(score);
		return score;
	}

	
	public void calculateInit2LetterScore() {

		int count = 0;
		double[] first2LetterScores = new double[2];
		double score = getCurrentScore();

		char[] twoLetters = giveFirst2Letters();

		for (char letter : twoLetters) {

			for (Vowels vowel : Vowels.values()) {

				if (letter == vowel.getVowel() && count < 3) {

					count += 1;
					score += vowel.getVowelValues();
					first2LetterScores[count - 1] = vowel.getVowelValues();

					break;
				}
			}
		}

		for (char letter : twoLetters) {

			for (Consonants consonants : Consonants.values()) {

				if (letter == consonants.getConsonant() && count < 3) {

					count += 1;
					score += consonants.getConsonantValue();
					first2LetterScores[count - 1] = consonants.getConsonantValue();
					break;
				}
			}
		}

		score += Math.pow(first2LetterScores[0], 2) +
					Math.pow(first2LetterScores[1], 2);
		setCurrentScore(score);
	}

	public double[] randomizePaneltyOptions() {

		double[] shuffledPenaltyOptions = new double[3];

		Random rand = new Random();
		int randomNum = rand.nextInt(6);

		switch (randomNum) {

		case 0:
			shuffledPenaltyOptions[0] = 0.5;
			shuffledPenaltyOptions[0] = 0.7;
			shuffledPenaltyOptions[0] = 0.9;
			break;
		case 1:

			shuffledPenaltyOptions[0] = 0.5;
			shuffledPenaltyOptions[0] = 0.9;
			shuffledPenaltyOptions[0] = 0.7;
			break;
		case 2:

			shuffledPenaltyOptions[0] = 0.7;
			shuffledPenaltyOptions[0] = 0.5;
			shuffledPenaltyOptions[0] = 0.9;
			break;
		case 3:

			shuffledPenaltyOptions[0] = 0.7;
			shuffledPenaltyOptions[0] = 0.9;
			shuffledPenaltyOptions[0] = 0.5;
			break;
		case 4:

			shuffledPenaltyOptions[0] = 0.9;
			shuffledPenaltyOptions[0] = 0.5;
			shuffledPenaltyOptions[0] = 0.7;
			break;
		case 5:

			shuffledPenaltyOptions[0] = 0.9;
			shuffledPenaltyOptions[0] = 0.7;
			shuffledPenaltyOptions[0] = 0.5;
			break;
		}

		return shuffledPenaltyOptions;
	}

	public void addWordLengthBonus() {

		double score = getCurrentScore();
		int wordLength = getWord().length;

		switch (wordLength) {

		case 4:
			wordLengthBonus = getCurrentScore() * 4;
			break;

		case 5:
			wordLengthBonus = getCurrentScore() * 5;
			break;

		case 6:
			wordLengthBonus = getCurrentScore() * 6;
			break;

		case 7:
			wordLengthBonus = getCurrentScore() * 7;
			break;

		case 8:
			wordLengthBonus = getCurrentScore() * 8;
			break;

		case 9:
			wordLengthBonus = getCurrentScore() * 9;
			break;

		case 10:
			wordLengthBonus = getCurrentScore() * 10;
			break;

		case 11:
			wordLengthBonus = getCurrentScore() * 11;
			break;

		// 12 letter word event is handled specially

		default:
			wordLengthBonus = 0;
			break;

		}

		score += wordLengthBonus;
		currentScore = score;

	}


	public void calculateTimeBonus(int timeRemainingInSeconds) {

		double score = getCurrentScore();
		timeBonus = timeRemainingInSeconds * 2;
		score += timeBonus;

		setCurrentScore(score);
	}

	public String findLongestWordInRound(String[] playerNames, 
										 String[] playerWords) {

		int max = 0;

		for (int i = 0; i < playerWords.length; i++) {

			if (playerWords[i].length() > max) {
				max = i;
			}
		}

		return playerNames[max];

	}
	
	
	// finds the weakest link
	public String findWeakestLink(String[] playerNames, 
			 					  int[] playerRoundScores) {

		int min = 0;
		
		for (int i = 0; i < playerRoundScores.length; i++) {
		
			if (playerRoundScores[i] < min) {
				min = i;
			}
		}
		
		return playerNames[min];

	}
	
	public void calculatePowerBonus(char[] inputWord, int[] randomizedPowerPositions){
		
		String strInputWord = inputWord.toString().substring(7);
		inputWord = strInputWord.toCharArray();
		int powerStartIndex = 7;
		
		for (char letter : inputWord) {

			for (Vowels vowel : Vowels.values()) {

				if (letter == vowel.getVowel()) {
					powerBonus += vowel.getVowelValues()
									* randomizedPowerPositions[powerStartIndex];
					break;
				}
			}
			powerStartIndex++;
		}

		powerStartIndex = 7;
		
		for (char letter : inputWord) {

			for (Consonants consonants : Consonants.values()) {

				if (letter == consonants.getConsonant()) {

					powerBonus += consonants.getConsonantValue()
									* randomizedPowerPositions[powerStartIndex];
					break;
				}
			}
			powerStartIndex++;
		}

		setPowerBonus(powerBonus);
		

	}

	
	public void checkForHighScores(){
		
		
	}

}
