package dataaccess;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import gamesplayers.Game;
import gamesplayers.Player;

public class Database {

	private static SessionFactory factory;
	private static Session session;

	public Database(){

		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable exception) {
	         System.err.println("Failed to create sessionFactory object."
	        		 	+ exception);
	         throw new ExceptionInInitializerError(exception);
	      }
	}


	public Integer createPlayer(String playerName, int isActive){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer playerID = null;

	      try{

	    	  transaction = session.beginTransaction();
	         Player player = new Player(playerName, isActive);
	         playerID = (Integer) session.save(player);
	         transaction.commit();

	      }catch (HibernateException e) {
	         if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	      }finally {
	         session.close();
	      }

		return playerID;
	}

	public Integer createGame(String gameName, int playerCount,
							String levelInfo, Player[] players){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer gameID = null;

	      try{

	    	  transaction = session.beginTransaction();
	         Game game = new Game(gameName, playerCount,
	        		 levelInfo);
	         gameID = (Integer) session.save(game);
	         transaction.commit();

	         // insert players to gameplayer table



	      }catch (HibernateException e) {
	         if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	      }finally {
	         session.close();
	      }

		return gameID;


	}

	public Integer savePlayerScores(int gameID, int playerID, int score){

		session = factory.openSession();
	      Transaction transaction = null;
	      Integer gamePlayerID = null;

	      try{

	    	  transaction = session.beginTransaction();
	         Player player = new Player(gameID, playerID,
	        		 					score);
	         gamePlayerID = (Integer) session.save(player);
	         transaction.commit();

	      }catch (HibernateException e) {
	         if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	      }finally {
	         session.close();
	      }

		return gamePlayerID;


	}



	public Integer saveHighScores(String gameName, String playerName, int playerScore){

		session = factory.openSession();
	      Transaction transaction = null;
	      Integer gamePlayerID = null;

	      try{

	    	  transaction = session.beginTransaction();
	         Player player = new Player(gameName, playerName,
	        		 					playerScore);
	         gamePlayerID = (Integer) session.save(player);
	         transaction.commit();

	      }catch (HibernateException e) {
	         if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	      }finally {
	         session.close();
	      }

		return gamePlayerID;

	}

	public int[] loadHighScores(){

		int[] highscore = new int[]{};


		return highscore;
	}



	public void updatePlayerScore(){

	}

	public Game[] loadGames(){

		Game[] games = new Game[]{};


		return games;
	}


	// load active player data

	public Player[] loadActivePlayers(){

		Player[] player = new Player[]{};


		return player;

	}

	// load single player data

	public Player loadPlayerData() {

		Player player = new Player();


		return player;
	}


}
