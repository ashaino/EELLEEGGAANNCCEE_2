package networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



// Threaded Pool Server
public class Server implements Runnable {
	
	private int serverPort = 8080;
	private ServerSocket serverSocket = null;
	private boolean isStopped = false;
	private Thread runningThread = null;
	private static final int POOL_SIZE = 100;
	
	// defining pool size
	protected ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
	
	public Server(int port) {
		
		this.serverPort = port;
	}

	public void run() {
		
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		
		openServerSocket();
		
		while (!isStopped()) {
		
			Socket clientSocket = null;
			
			try {
				
				clientSocket = this.serverSocket.accept();
			
			}
			catch (IOException exception){
				
				if (isStopped()) {
					
					System.out.println("Server Stopped.");
					break;
		
				}
				throw new RuntimeException("Error accepting client connection", exception);
			}

			
			  /* passing requests to a thread pool
			   * for processing
			 */

			this.threadPool.execute(new ProcessRequests
					(clientSocket));
		}
		this.threadPool.shutdown();
		System.out.println("Server Stopped.");
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} 
		catch (IOException exception) {
			throw new RuntimeException("Error closing server", exception);
		}
	}

	private void openServerSocket() {
		
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} 
		catch (IOException exception) {
			throw new RuntimeException("Cannot open port: 8080 ", exception);
		}
	}

}