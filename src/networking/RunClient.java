package networking;

import dataaccess.Database;

public class RunClient {



	public static void main(String[] args) {

		Client.clientTimer();
		//Client.login("kaushalya815@gmail.com", "password");
		Client.requestCreateGame();
		//Client.signUp("Gayan", "gayan@gmail.com","pass");

		//Database db = new Database();
		// testing code
		/*
		Player player = new Player(null, null);

		player.setGameName("GameName");

		player.setPlayerName("Player");


		LetterBag letterBag = new LetterBag();

		char[] tenLetters = letterBag.give10letters(5);
		for (char c : tenLetters) {
			System.out.println(c);
		}

		System.out.println("----------------------");

		WordBoard wordBoard = new WordBoard();

		for (int power : wordBoard.randomizePowerPositions()) {
			System.out.println(power);
		}


		FileAccess fileAccess = new FileAccess();
		System.out.println(fileAccess.loadHelpTextFromXML("2"));





		Player[] p = new Player[4];
		Player p1 = new Player(1, "Kaushalya1","kaushalya815@gmail.com","password");
		Player p2 = new Player(1,"Kaushalya2","kaushalya815@gmail.com","password");
		Player p3 = new Player(1,"Kaushalya3","kaushalya815@gmail.com","password");
		Player p4 = new Player(1,"Kaushalya4","kaushalya815@gmail.com","password");

		p[0] = p1;
		p[1] = p2;
		p[2] = p3;
		p[3] = p4;
*/

//		System.out.println("Create Player: "+db.createPlayer(new Player("Gayan","gayan@gmail.com","pass")));

//		Game.setPlayers(p);

	//	int playerCount = 4;
	//	System.out.println("Create Game "+db.createGame("Hi game", playerCount,p));
		//System.out.println(" "+db.registerPlayersForGame(1, p, 4));


		//System.out.println("Load Player Data "+db.loadPlayerData("kaushalya815@gmail.com", "password").playerUserName);

		//System.out.println("HighScores "+db.saveHighScores(1, 50000));

		/*List data = db.loadHighScores();

		 for(Object object : data)
         {
            Map row = (Map)object;
            System.out.println("User Name: " + row.get("player_username"));
            System.out.println("Highscore: " + row.get("highscore"));
         }*/


		//db.updatePlayerScore(1, 54400);

		//System.out.println("Email "+db.isEmailAreadyExists("kaushalya815@gmail.com"));

		/*List games = db.loadGames();

		for (Iterator iterator = games.iterator(); iterator.hasNext();){

			Game employee = (Game) iterator.next();
			 System.out.println("Game Name: " + employee.getGameName());
			 System.out.println("Player Count: " + employee.getPlayerCount());


		}


		try {
			System.out.println("login valid: "+db.loadPlayerData("kaushalya815@gmail.com", "password").isLoginValid());
		}
		catch (Exception e) {
			e.printStackTrace();
		}


		db.removeGame(1);
		System.out.println("records deleted");
		*/

		//FileAccess fileaccess = new FileAccess();
		//fileaccess.initTrophyCabinet();
		//fileaccess.loadTrophyCabinet();
		//fileaccess.unlockTrophy(2);
		//fileaccess.loadTrophyCabinet();


	}

}
