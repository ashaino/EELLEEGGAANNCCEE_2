package flogschema;

import triedictionary.WordTrie;

public class WordBank extends LetterBag {

	private char[] word;
	private WordTrie wordTrie;

	public WordBank() {

		super();
		wordTrie = new WordTrie();
	}



	public boolean verifyFirstTwoLetters(char[] firstTwoLetters){

		/*traverse the trie with
		 * first 2 characters
		 * in-order to find whether there are valid words
		*/

		return true;
	}

	public char[] getWord() {
		return word;
	}

	public void setWord(char[] word) {
		this.word = word;
	}

	public boolean isCompleteWord(char[] word){

		boolean isComplete;
		isComplete = (word.length == 12)?true:false;
		return isComplete;

	}

	// automatic method to find the longest word

	public char[] findLongestWord(char[] currentCharSequence){
		char[] longestWord  ={};

		/* traverse the trie with
		 * current Char Sequence
		 * in-order to find a word with 12 characters
		 */

		return longestWord;


	}


	// verify word existence

	public boolean verifyInputWord(char[] inputWord){

		boolean isValid = true;

		/* traverse the trie with
		 * input word characters
		 * find whether last character is leaf node
		 */

		return isValid;
	}


	public String automaticWordSearch(char[] suppliedCharacters){

		String longestPossibleWords ="";

		/* traverse the trie with
		 * supplied characters
		 * in-order to find the suffixes for complete words
		 */

		return longestPossibleWords;
	}
}
