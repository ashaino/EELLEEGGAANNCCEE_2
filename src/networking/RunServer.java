package networking;

public class RunServer {
	
	public static void main(String[] args) {
		
		
		// testing code
		
		Server server = new Server(9000);
		new Thread(server).start();

		try {
			Thread.sleep(100000);
			
		} catch (InterruptedException exception) {    
			exception.printStackTrace();
		}
		
		System.out.println("Stopping Server");
		server.stop();

	}
}
