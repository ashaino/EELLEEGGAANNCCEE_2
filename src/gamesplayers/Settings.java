package gamesplayers;

import dataaccess.FileAccess;

public class Settings{


	private static boolean fullScreen;
	private static boolean music;

	private static FileAccess fileAccess;
	
	
	

	public static boolean isFullScreen() {
		return fullScreen;
	}



	public static void setFullScreen(boolean fullScreen) {
		Settings.fullScreen = fullScreen;
	}



	public static boolean isMusic() {
		return music;
	}



	public static void setMusic(boolean music) {
		Settings.music = music;
	}

	public void saveSettings(){
		
		fileAccess = new FileAccess();
		fileAccess.saveSettingValues(fullScreen, music);
	
	}
	
	public void loadSetting(){
	
		boolean[] settings = new boolean[2];
		
		fileAccess = new FileAccess();
		settings = fileAccess.getSettingValues();
		
		setFullScreen(settings[0]);
		setMusic(settings[1]);
		
	}
	
	public void resetToDefaults(){
		
		fileAccess = new FileAccess();
		fileAccess.resetToDefaultValues();
		
	}


	
}
