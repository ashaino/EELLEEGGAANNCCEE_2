package dataaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import gamesplayers.Game;
import gamesplayers.GamePlayer;
import gamesplayers.HighScores;
import gamesplayers.Player;


public class Database {

	private static SessionFactory factory;
	private static Session session;
	private static List results;
	private static String sql;

	public Database(){

		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable exception) {
	         System.err.println("Failed to create sessionFactory object."
	        		 	+ exception);
	         throw new ExceptionInInitializerError(exception);
	      }
	}


	public Integer createPlayer(Player player){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer playerID = null;

	      try{

			player.setPlayerPassword(PasswordHash.getSaltedHash(player.getPlayerPassword()));
	    	transaction = session.beginTransaction();
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


	// checking whether provided email already exists

	public boolean isEmailAreadyExists(String inputEmail){


		boolean isAlreadyExists = false;
		Session session = factory.openSession();
	    Transaction transaction = null;
	    results = new ArrayList<Player>();

	    try{

	         transaction = session.beginTransaction();
	         results = session.createSQLQuery("SELECT player_id from tbl_Player "
	         									+ "where player_email = :email")
	        		 		  .setString("email", inputEmail).list();
	         System.out.println(results);


	         transaction.commit();
	    }
	    catch (HibernateException exception) {

	    	  if (transaction!=null) {
	    		  transaction.rollback();
	    	  }
	         exception.printStackTrace();

	    }
	    finally {
	         session.close();
	    }

	    isAlreadyExists =(results.isEmpty())?false:true;

		return isAlreadyExists;
	}


	public Integer createGame(String gameName,int playerCount, Player[] players){


		  session = factory.openSession();
	      Transaction transaction = null;
	      Integer newGameID = null;
	      final byte GAME_STATUS = 0;

	      try{

	    	 transaction = session.beginTransaction();

	  		Game game = new Game(gameName, playerCount, GAME_STATUS);
	        newGameID = (Integer) session.save(game);

	        transaction.commit();



	      }
	      catch (HibernateException e) {
	         if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	      }finally {
	         session.close();
	      }

	      registerPlayersForGame(newGameID, players, playerCount);


		return newGameID;


	}


	public int[] registerPlayersForGame(int newGameID, Player[] players, int playerCount){

		session = factory.openSession();
	    Transaction transaction = null;
	    int[] gamePlayerIDs = new int[playerCount-1];
        final int INIT_SCORE = 0;
        GamePlayer gamePlayer = null;


	      try{

	    	  transaction = session.beginTransaction();


	         for (int i = 0; i < playerCount; i++) {

	        	 gamePlayer = new GamePlayer(newGameID,
	        			 					 players[i].getPlayerID(), INIT_SCORE);
		         gamePlayerIDs[i] = (Integer) session.save(gamePlayer);


			}

	        transaction.commit();
	        gamePlayer.setGamePlayerIDs(gamePlayerIDs);

	      }
	      catch (HibernateException e) {

	         if (transaction!=null){

	        	 transaction.rollback();
	        	 e.printStackTrace();
	         }

	      }

	      finally{

	    	  session.close();
	      }

	      return gamePlayerIDs;

	}


	// save High Scores

	public Integer saveHighScores(int playerID, int highScore){

		  session = factory.openSession();
	      Transaction transaction = null;
	      Integer highScoreID = null;

	      try{

	    	 transaction = session.beginTransaction();
	         HighScores highscore = new HighScores(playerID, highScore);
	         highScoreID = (Integer) session.save(highscore);
	         transaction.commit();

	      }catch (HibernateException exception) {

	    	  if (transaction!=null){
	        	 transaction.rollback();
	         }
	         exception.printStackTrace();

	      }finally {
	         session.close();
	      }

		return highScoreID;

	}


	// load High Scores

	public List loadHighScores(){

		Session session = factory.openSession();
		Transaction transaction = null;
		List highscoresRecords = null;

	    try{
	    	transaction = session.beginTransaction();
	    	 sql ="SELECT p.player_username, hs.highscore FROM tbl_HighScores hs,"
	    	 		+ " tbl_Player p WHERE hs.playerid = p.player_id ORDER BY hs.highscore DESC";
	    	 SQLQuery query = session.createSQLQuery(sql);

	    	 query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	    	 highscoresRecords = query.list();



	    }
	    catch (HibernateException exception) {

	    	if (transaction!=null){

	        	 transaction.rollback();
	        	 exception.printStackTrace();
	         }
	    }
	    finally {
	         session.close();
      }

	    return highscoresRecords;
	}

	// update player score when saving a game

	public int updatePlayerScore(int gamePlayerID, int playerTotalScore){

		 Session session = factory.openSession();
         Transaction transaction = null;
         int result = 0;

	      try{

	    	  	 transaction = session.beginTransaction();

	    	  	 sql ="UPDATE tbl_GamePlayer SET player_score = "
		    	 		+ ":newscore  WHERE gameplayer_id = :gameplayerid";

		    	 SQLQuery query = session.createSQLQuery(sql);
		    	 query.setParameter("newscore", playerTotalScore);
		    	 query.setParameter("gameplayerid", gamePlayerID);

		    	 result = query.executeUpdate();
		    	 System.out.println(result);

		    	 transaction.commit();

	      }


	      catch (HibernateException exception) {

	    	if (transaction!=null) {
	    		  transaction.rollback();
	    	}

	    	exception.printStackTrace();

	      }
	      finally {
	         session.close();
	      }

	      return result;
	}


	public List loadGames(){

		Game[] games = new Game[]{};
		Session session = factory.openSession();
	    Transaction transaction = null;


	    try{

	         transaction = session.beginTransaction();

	         // 0 = not finished games

	         sql ="SELECT * FROM tbl_Game WHERE game_status = 0";

	         SQLQuery query = session.createSQLQuery(sql);
	         query.addEntity(Game.class);

	         results = query.list();

	         transaction.commit();

	    }
	    catch (HibernateException exception) {

	    	  if (transaction!=null){

	    		  transaction.rollback();
	    		  exception.printStackTrace();
	    	  }


	    }
	    finally {

	         session.close();
	    }

		return results;
	}


	// remove finished games

	public void removeGame(int gameID){

		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{
	    	 Serializable id = new Integer(gameID);
	         transaction = session.beginTransaction();

	         Object persistentInstance = session.load(Game.class, id);

	         if (persistentInstance != null) {

	             session.delete(persistentInstance);
	         }

	         sql ="DELETE FROM tbl_GamePlayer WHERE gameid = :id";

		      SQLQuery query = session.createSQLQuery(sql);
		    	 				query.setParameter("id", gameID);

    	      query.executeUpdate();

	         transaction.commit();

	    }
	      catch (HibernateException exception) {

	    	  if (transaction!=null){

	    		  transaction.rollback();
	    		  exception.printStackTrace();
	    	  }

	      }
	      finally {
	         session.close();
	      }

	}


	// load single player data

	public Player loadPlayerData(String playerEmail, String playerPassword) {

		Player player = new Player();

		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	    	transaction = session.beginTransaction();
	    	sql = "SELECT * FROM tbl_Player WHERE player_email = :email ";

            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("email", playerEmail);
            query.addEntity(Player.class);

            results = query.list();

            for (Iterator iterator = results.iterator(); iterator.hasNext();){

	            player = (Player) iterator.next();

	            try {
					player.setLoginValid(PasswordHash.check(playerPassword,
							player.getPlayerPassword()));

				} catch (Exception exception) {

					exception.printStackTrace();
				}

	         }

	         transaction.commit();

	    }
	    catch (HibernateException exception) {

	    	if (transaction!=null){

	    		transaction.rollback();
    	        exception.printStackTrace();
	    	}

	    }
	    finally {

	    	session.close();
	    }

	    return player;
	}




}
