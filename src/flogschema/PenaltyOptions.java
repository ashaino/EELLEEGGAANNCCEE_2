package flogschema;

//define damage ratio options given to weakest player
public enum PenaltyOptions {

	LOW_DAMAGE(0.5),
	MEDIUM_DAMAGE(0.7),
	HIGH_DAMAGE(0.9);
	
	private final double DAMAGE;

	private PenaltyOptions(double damage) {
		this.DAMAGE = damage;
	}

	public double getDAMAGE() {
		return DAMAGE;
	}
	
	
	
}
