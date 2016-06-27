package gamesplayers;

public class Trophies extends Player{

	private static String trophyName;
	private static String description;
	private static boolean unlocked;


	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		Trophies.description = description;
	}

	public static String getTrophyName() {
		return trophyName;
	}

	public static void setTrophyName(String trophyName) {
		Trophies.trophyName = trophyName;
	}

	public static boolean isUnlocked() {
		return unlocked;
	}

	public static void setUnlocked(boolean unlocked) {
		Trophies.unlocked = unlocked;
	}



}
