package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import gamesplayers.Player;

public class ThreadedServer extends Thread
{
	private static final String TAG_LOGIN = "LI";
	private static final String TAG_SIGNUP = "SU";
	private static final String TAG_SAVE = "SV";
	private static final String TAG_LEAVE = "LV";
	private static final String TAG_JOIN = "JN";
	private static final String TAG_CREATE_GAME = "CG";
	private static final String TAG_HIGHSCORE = "HS";
	private static final String TAG_SCORE = "HS";
	private static final String TAG_SAVE_RESPOND = "SR";


	private String[] data;


   private ServerSocket serverSocket;

   public ThreadedServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000*1000);
   }



   public String processRequest(DataInputStream input){

	   long startTime = System.currentTimeMillis();

	   Player player = new Player();

	   String result = "";

	   data = new String[]{};

	   try {

		   data = input.readUTF().split(";");
	   }
	   catch (IOException e) {

		   e.printStackTrace();
	   }


	   // TODO: processing request

	   switch(data[0]){

			case TAG_LOGIN:
							result = player.playerLogin(data[1],data[2]).getPlayerUserName();
							player.addPlayerToNotPlayingList(player);
							break;

			case TAG_SIGNUP:
							result = Boolean.toString(player.registerPlayer(data[1],data[2],data[3]));
							break;

			case TAG_SAVE:

							break;

			case TAG_SAVE_RESPOND:

							break;

			case TAG_LEAVE:

							break;

			case TAG_JOIN:
							break;


			case TAG_SCORE:
							break;

			default:break;

	}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		System.out.println("Request processed: " + startTime);
		System.out.println("Processing time: " + elapsedTime);

		return result;
   }


   public void run()
   {
      while(true)
      {
         try
         {

        	System.out.println("Waiting for client on port " +
        						serverSocket.getLocalPort() + "...");

            Socket server = serverSocket.accept();

            System.out.println("Just connected to "
            						+ server.getRemoteSocketAddress());

            Player connectedPlayer = new Player();
            connectedPlayer.setPlayerIP(server.getRemoteSocketAddress()
            									.toString());

            DataInputStream input =
            				new DataInputStream(server.getInputStream());


            DataOutputStream output =
                    new DataOutputStream(server.getOutputStream());

            // processing the client request

            String result = processRequest(input);
            System.out.println("Server Process result: "+result);
            output.writeUTF(result);

            server.close();

         }
         catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }
         catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }


public static String getTagCreateGame() {
	return TAG_CREATE_GAME;
}


public static String getTagHighscore() {
	return TAG_HIGHSCORE;
}

}
