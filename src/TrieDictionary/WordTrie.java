package TrieDictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordTrie {

	TreeVocabulary treeVocabulary;
	LowercaseTrieVocabulary trieVocabulary;
	
	public WordTrie() {
		
		ArrayList<String> words = new ArrayList<>(150000);
		
		/*
		 * this will build a trie using a text file.(dictionary)
		 */

		try {
		
			InputStream in = new FileInputStream(new File(
					System.getProperty("user.dir")+"/resources/words_en.txt"));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			ArrayList<byte[]> wordsInt = new ArrayList<>(150000);
			
			do {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				if (line.matches("[a-z]+")) {
					words.add(line);
					wordsInt.add(Alphabet.LOWERCASE.toInt(line));
				}
			} while (true);
			reader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		

		treeVocabulary = new TreeVocabulary(words);
		trieVocabulary = new LowercaseTrieVocabulary(words);
	}

}
