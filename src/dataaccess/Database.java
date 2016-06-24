package dataaccess;

import java.util.Iterator;
import java.util.List;

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
							String levelInfo){

		 session = factory.openSession();
	      Transaction transaction = null;
	      Integer gameID = null;
	      
	      try{
	         
	    	  transaction = session.beginTransaction();
	         Game game = new Game(gameName, playerCount,
	        		 levelInfo);
	         gameID = (Integer) session.save(game); 
	         transaction.commit();
	         
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
/*
	public void loadHighScores(){
		
		   Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         List employees = session.createQuery("FROM Employee").list(); 
		         for (Iterator iterator = 
		                           employees.iterator(); iterator.hasNext();){
		            Employee employee = (Employee) iterator.next(); 
		            System.out.print("First Name: " + employee.getFirstName()); 
		            System.out.print("  Last Name: " + employee.getLastName()); 
		            System.out.println("  Salary: " + employee.getSalary()); 
		         }
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
	}
	
   

	public void updatePlayerScore(){
		
		   Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         Employee employee = 
		                    (Employee)session.get(Employee.class, EmployeeID); 
		         employee.setSalary( salary );
				 session.update(employee); 
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
	}
	
	public void loadGames(){
		
		   Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         List employees = session.createQuery("FROM Employee").list(); 
		         for (Iterator iterator = 
		                           employees.iterator(); iterator.hasNext();){
		            Employee employee = (Employee) iterator.next(); 
		            System.out.print("First Name: " + employee.getFirstName()); 
		            System.out.print("  Last Name: " + employee.getLastName()); 
		            System.out.println("  Salary: " + employee.getSalary()); 
		         }
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
			
	}
	
	
	// load active player data
	
	public void loadActivePlayers(){
	
		Session session = factory.openSession();
		      Transaction tx = null;
		      try{
		         tx = session.beginTransaction();
		         List employees = session.createQuery("FROM Employee").list(); 
		         for (Iterator iterator = 
		                           employees.iterator(); iterator.hasNext();){
		            Employee employee = (Employee) iterator.next(); 
		            System.out.print("First Name: " + employee.getFirstName()); 
		            System.out.print("  Last Name: " + employee.getLastName()); 
		            System.out.println("  Salary: " + employee.getSalary()); 
		         }
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
	
		
	}
	*/
	// load single player data
	
	
	public void loadPlayerData() {

		Session session = factory.openSession();
		Player player = null;
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			List employees = session.createQuery("FROM tbl_Player").list();
			System.out.println(employees);
			
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				player = (Player) iterator.next();

			}

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		//return player;

	}
	
   
}
