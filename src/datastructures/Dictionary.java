package datastructures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author ashaindesilva
 */
public class Dictionary implements Serializable {

    private Trees dictionary;

    //initiate dictionnary by ArrayList collection
    public Dictionary(ArrayList<String> words){

        try{
            dictionary = new Trees();
            for(String word: words){
                dictionary.addWord(word);
            }
        }
        catch(Exception e){
        	e.printStackTrace();;}
    }

    //initiate dictionnary from disk
    public Dictionary(String filePath){
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(filePath);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                Dictionary result = (Dictionary) ois.readObject();
                this.dictionary=result.dictionary;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //write this dictionary to file
    public void writeToDisk(String filePath){
        try {
            // write object to file
            FileOutputStream fos = new FileOutputStream(filePath);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord(String word){
        try{
            dictionary.addWord(word);
        }
        catch(Exception e){
        	e.printStackTrace();
        	}
    }

    // verify word existence by string
    public boolean verifyInputWord(String word){
        try {
            return dictionary.searchWord(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // verify word existence by char array
    public boolean verifyInputWord(char[] inputWord){
        /* traverse the trie with
         * input word characters
         * find whether last character is leaf node
         */
        return verifyInputWord(new String(inputWord));
    }

    public boolean verifyPrimaryLetters(char[] firstLetters){

        /*traverse the trie with
         * first any # of characters
         * in-order to find whether there are valid words
        */

        try {

            for (String s : permute(new String(firstLetters)))
            {
                if(dictionary.searchSuffix(s)){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // automatic method to find the longest word
    public String[] findLongestWord(char[] currentCharSequence){
        /* traverse the trie with
         * current Char Sequence
         * in-order to find a word with 12 characters
        **/
        ArrayList<WordLetters> wordSet=dictionary.MaxWordSearch(new WordLetters(currentCharSequence));

        HashSet<String> wordList = new HashSet<>();

        for(int x=0;x<wordSet.size();x++){
            if(wordSet.get(x).isCompleteWord()){
                wordList.add(wordSet.get(x).getWord());
            }else{
                wordSet.remove(x);//remove incoplete words
            }
        }

        return wordList.toArray(new String[0]);

    }




    //posibility combinations
    public static Set<String> permute(String chars)
    {
        // Switch to HashSet for better performance
        Set<String> set = new TreeSet<>();

        // Termination condition: only 1 permutation for a string of length 1
        if (chars.length() == 1)
        {
          set.add(chars);
        }
        else
        {
            // Give each character a chance to be the first in the permuted string
            for (int i=0; i<chars.length(); i++)
            {
                // Remove the character at index i from the string
                String pre = chars.substring(0, i);
                String post = chars.substring(i+1);
                String remaining = pre+post;

                // Recurse to find all the permutations of the remaining chars
                for (String permutation : permute(remaining))
                {
                    // Concatenate the first character with the permutations of the remaining chars
                    set.add(chars.charAt(i) + permutation);
              }
            }
        }
        return set;
  }



}
