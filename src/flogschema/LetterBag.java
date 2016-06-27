package flogschema;

import java.util.Random;

public class LetterBag extends FlogGame{

	private char[] tenLetters;
	private char[] first2Letters;
	private char[] letterBag;
	private final static int NO_LETTERS_GIVEN = 10;
	private final static int LETTER_BAG_SIZE = 20;
	private Random rand;



	public LetterBag() {
		super();
		first2Letters = new char[2];
		tenLetters = new char[10];
		letterBag = new char[LETTER_BAG_SIZE];

	}

	public static int getLetterBagSize() {
		return LETTER_BAG_SIZE;
	}

	public char[] getLetterBag() {
		return letterBag;
	}


	public char[] give10letters(int noVowels){

		simulateScrabbleLetterBag(noVowels);
		rand = new Random();

		for (int i = 0; i < NO_LETTERS_GIVEN; i++) {
			tenLetters[i] = letterBag[rand.nextInt(noVowels)];

			if(i >= noVowels){

				tenLetters[i] = letterBag
					[randInt(noVowels, NO_LETTERS_GIVEN)];
			}
		}



		return tenLetters;
	}


	public void simulateScrabbleLetterBag(int noVowels){

		letterBag = new char[20];
		rand = new Random();
		int noVowelsInBag = noVowels + 3;

		for (int j = 0; j < noVowelsInBag; j++) {

			letterBag[j] = giveVowel();
		}

		for (int i = noVowelsInBag; i < LETTER_BAG_SIZE ; i++) {

			letterBag[i] = giveConsonant();
		}

		// for testing
		System.out.println(letterBag);
	}


	public char[] giveFirst2Letters(){

		WordBank wordBag = new WordBank();
		Random rand = new Random();

		while(!(wordBag.verifyFirstTwoLetters(first2Letters))){

			int randNo = rand.nextInt(3);

			switch(randNo){

				case 0:
					first2Letters[0] = giveVowel();
					first2Letters[1] = giveVowel();
					break;

				case 1:
					first2Letters[0] = giveVowel();
					first2Letters[1] = giveConsonant();
					break;

				case 2:
					first2Letters[0] = giveConsonant();
					first2Letters[1] = giveVowel();
					break;

				case 3:
					first2Letters[0] = giveConsonant();
					first2Letters[1] = giveConsonant();
					break;
			}

		}

		return first2Letters;
	}


	public char exchangeLetters(char exchangeLetter){

		boolean isVowel = false;

		isVowel = checkForVowel(exchangeLetter);;
		char newLetter = (isVowel)?giveConsonant():giveVowel();

		return newLetter;
	}

	public boolean checkForVowel(char exchangeLetter){

		boolean isVowel = false;
		for(Vowels vowel: Vowels.values()){
			if(vowel.getVowel() == exchangeLetter){

				isVowel = true;
			}
			else{
				isVowel = false;
			}
		}
		return isVowel;
	}


	public char giveConsonant(){
		char consonant='c';

		rand = new Random();
		int randomNum = rand.nextInt(21);

		switch(randomNum){

			case 0:
				consonant = Consonants.B.getConsonant();
				break;
			case 1:
				consonant = Consonants.C.getConsonant();
				break;
			case 2:
				consonant = Consonants.D.getConsonant();
				break;
			case 3:
				consonant = Consonants.F.getConsonant();
				break;
			case 4:
				consonant = Consonants.G.getConsonant();
				break;
			case 5:
				consonant = Consonants.H.getConsonant();
				break;
			case 6:
				consonant = Consonants.J.getConsonant();
				break;
			case 7:
				consonant = Consonants.K.getConsonant();
				break;
			case 8:
				consonant = Consonants.L.getConsonant();
				break;
			case 9:
				consonant = Consonants.M.getConsonant();
				break;
			case 10:
				consonant = Consonants.N.getConsonant();
				break;
			case 11:
				consonant = Consonants.P.getConsonant();
				break;
			case 12:
				consonant = Consonants.Q.getConsonant();
				break;
			case 13:
				consonant = Consonants.R.getConsonant();
				break;
			case 14:
				consonant = Consonants.S.getConsonant();
				break;
			case 15:
				consonant = Consonants.T.getConsonant();
				break;
			case 16:
				consonant = Consonants.V.getConsonant();
				break;
			case 17:
				consonant = Consonants.W.getConsonant();
				break;
			case 18:
				consonant = Consonants.X.getConsonant();
				break;
			case 19:
				consonant = Consonants.Y.getConsonant();
				break;
			case 20:
				consonant = Consonants.Z.getConsonant();
				break;

		}
		return consonant;
	}

	public char giveVowel(){
		char vowel = 'a';

		rand = new Random();
		int randomNum = rand.nextInt(5);

		switch(randomNum){

			case 0:
				vowel = Vowels.A.getVowel();
				break;
			case 1:
				vowel = Vowels.E.getVowel();
				break;
			case 2:
				vowel = Vowels.I.getVowel();
				break;
			case 3:
				vowel = Vowels.O.getVowel();
				break;
			case 4:
				vowel = Vowels.U.getVowel();
				break;

		}

		return vowel;
	}







}
