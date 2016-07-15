package networking;

import java.io.IOException;

public class RunServer {

	private static final int PORT_NO = 6066;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  try
	      {
	         Thread serverThread = new ThreadedServer(PORT_NO);
	         serverThread.setName("Server Thread");
	         serverThread.start();
	      }
	      catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}

}
