package flogschema;

//define score given to each vowel
public enum Vowels {

	A('a',2),
	E('e',1),
	I('i',3),
	O('o',4),
	U('u',5);
	
	private final char VOWEL;
	private final int VOWEL_VALUES;
	
	private Vowels(char vowel, int vowelValues) {
		VOWEL = vowel;
		VOWEL_VALUES = vowelValues;
		
	}

	public int getVowelValues() {
		return VOWEL_VALUES;
	}

	public char getVowel() {
		return VOWEL;
	}
	
}
