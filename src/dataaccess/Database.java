package dataaccess;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import gamesplayers.Game;
import gamesplayers.Player;


public class Database {

	private static SessionFactory factory;
	private static Session session;
	private static List results;
	private static String hql;

	public Database(){

		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable exception) {
	         System.err.println("Failed to create sessionFactory object."
	        		 	+ exception);
	         throw new ExceptionInInitializerError(exception);
	      }
	}


	public Integer createPlayer(String playerName, String playerEmail,
								String playerPassword){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer playerID = null;
	      String passwordHash = "";
	      try{


			passwordHash = PasswordHash.getSaltedHash(playerPassword);

	    	  transaction = session.beginTransaction();
	         Player player = new Player(playerName, playerEmail, passwordHash);
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


	public boolean isEmailAreadyExists(){



		return true;
	}

	public Integer createGame(String gameName, int playerCount,
							int gameStatus, Player[] players){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer gameID = null;

	      try{

	    	  transaction = session.beginTransaction();
	         Game game = new Game(gameName, playerCount,
	        		 gameStatus);
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


	public Integer savePlayerScore(int gameID, int playerID, int score){

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


	// save High Scores

	public Integer saveHighScores(int playerID, int highScore){

		  session = factory.openSession();
	      Transaction transaction = null;
	      Integer gamePlayerID = null;

	      try{

	    	 transaction = session.beginTransaction();
	         Player player = new Player(playerID, highScore);
	         gamePlayerID = (Integer) session.save(player);
	         transaction.commit();

	      }catch (HibernateException exception) {

	    	  if (transaction!=null){
	        	 transaction.rollback();
	         }
	         exception.printStackTrace();

	      }finally {
	         session.close();
	      }

		return gamePlayerID;

	}


	// load High Scores

	public int[] loadHighScores(){

		int[] highscore = new int[]{};

		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	         transaction = session.beginTransaction();
	         results = session.createQuery("FROM tbl_HighScores").list();

	         System.out.println("High Scores " + results);

	         transaction.commit();
	    }
	    catch (HibernateException exception) {

	    	  if (transaction!=null) transaction.rollback();
	         exception.printStackTrace();

	    }
	    finally {
	         session.close();
	    }
		return highscore;
	}



	// update player score when saving a game

	public void updatePlayerScore(int newScore){

		 Session session = factory.openSession();
         Transaction transaction = null;

	      try{

	         transaction = session.beginTransaction();
	       //  Employee employee =
	          //          (Employee)session.get(Employee.class, EmployeeID);
	         //employee.setSalary( salary );
			// session.update(employee);
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
	}



	public Game[] loadGames(){

		Game[] games = new Game[]{};
		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	         transaction = session.beginTransaction();
	         Query query = session.createQuery("FROM tbl_Game");

	         results = query.list();

	         System.out.println(results);


	         transaction.commit();

	    }
	    catch (HibernateException e) {

	    	  if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	    }
	    finally {
	         session.close();
	    }

		return games;
	}


	// load active player data

	public List loadFreePlayers(){

/*
		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	         transaction = session.beginTransaction();
	         hql = "FROM tbl_Player P WHERE P.player_status = :p_status";
	         Query query = session.createQuery(hql);
	         query.setParameter("p_status", 1);
	         results = query.list();

	         System.out.println(results);


	         transaction.commit();

	    }
	    catch (HibernateException e) {

	    	  if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	    }
	    finally {
	         session.close();
	    }
*/
	    return results;
	}


	// load single player data

	public Player loadPlayerData(int playerID) {

		Player player = new Player();

		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	         transaction = session.beginTransaction();
	         hql = "FROM tbl_Player P WHERE P.player_id = :p_id";
	         Query query = session.createQuery(hql);
	         query.setParameter("p_id", playerID);
	         results = query.list();

	         System.out.println(results);


	         transaction.commit();

	    }
	    catch (HibernateException e) {

	    	  if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	    }
	    finally {
	         session.close();
	    }

		return player;
	}



	public boolean loadPlayerData(String userName, String password) {

		boolean isPresent = false;

		Session session = factory.openSession();
	    Transaction transaction = null;

	    try{

	         transaction = session.beginTransaction();
	         hql = "FROM tbl_Player P WHERE P.player_username = :p_username "
	         		+ "AND P.player_password =: p_password";
	         Query query = session.createQuery(hql);

	         query.setParameter("p_id", userName);
	         query.setParameter("p_password", password);
	         results = query.list();
	         isPresent = results.isEmpty()? false: true;

	         System.out.println(results);


	         transaction.commit();

	    }
	    catch (HibernateException e) {

	    	  if (transaction!=null) transaction.rollback();
	         e.printStackTrace();

	    }
	    finally {
	         session.close();
	    }

		return isPresent;
	}



}
