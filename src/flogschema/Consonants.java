package flogschema;

// define score given to each consonant
public enum Consonants {

	B('b',19),
	C('c',18),
	D('d',17),
	F('f',13),
	G('g',14),
	H('h',8),
	J('j',7),
	K('k',9),
	L('l',10),
	M('m',12),
	N('n',11),
	P('p',16),
	Q('q',21),
	R('r',20),
	S('s',21),
	T('t',15),
	V('v',23), 
	W('w',22),
	X('x',24),
	Y('y',25),
	Z('z',26);

	private final char CONSONANT;
	private final int CONSONANT_VALUE;
	
	
	private Consonants(char consonant, int consonantValue) {
		CONSONANT = consonant;
		CONSONANT_VALUE = consonantValue;
		
	}

	public int getConsonantValue() {
		return CONSONANT_VALUE;
	}

	public char getConsonant() {
		return CONSONANT;
	}

}
