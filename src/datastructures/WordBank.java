/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;

import flogschema.LetterBag;

/**
 *
 * @author ashaindesilva
 */
public class WordBank extends LetterBag{
    private char[] word;
    private final static String  DICT_PATH= "dictioanry.ash";
    private final static int WRD_MAX_LEN=12;
    private Dictionary dictionary;

	public WordBank() {
            this.dictionary= new Dictionary(DICT_PATH);
        }

        public WordBank(ArrayList<String> collection) {
            this.dictionary= new Dictionary(collection);
            this.dictionary.writeToDisk(DICT_PATH);
        }

        public boolean verifyFirstTwoLetters(char[] firstTwoLetters){

		/*traverse the trie with
		 * first 2 characters
		 * in-order to find whether there are valid words
		*/
                return dictionary.verifyPrimaryLetters(firstTwoLetters);
	}

	public char[] getWord() {
		return word;
	}

	public void setWord(char[] word) {
		this.word = word;
	}

	public boolean isCompleteWord(char[] word){

		boolean isComplete;
		isComplete = (word.length == WRD_MAX_LEN);
		return isComplete;

	}

	// automatic method to find the longest word

	public char[] findLongestPossibleWord(char[] currentCharSequence){


            /* traverse the trie with
             * current Char Sequence
             * in-order to find a word with 12 characters
             */

            return this.dictionary.findLongestWord(currentCharSequence)[0].toCharArray();
        }


	// verify word existence

	public boolean verifyInputWord(char[] inputWord){

		/* traverse the trie with
		 * input word characters
		 * find whether last character is leaf node
		 */
                return this.dictionary.verifyInputWord(inputWord);
	}
}