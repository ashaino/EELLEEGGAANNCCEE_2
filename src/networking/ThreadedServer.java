package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gamesplayers.Game;
import gamesplayers.Player;

public class ThreadedServer extends Thread
{
	private static final String TAG_LOGIN = "LI";
	private static final String TAG_SIGNUP = "SU";
	private static final String TAG_SAVE = "SV";
	private static final String TAG_LEAVE = "LV";
	private static final String TAG_CREATE_GAME_START = "CGS";
	private static final String TAG_CREATE_GAME_START_RESPONSE = "CGSR";
	private static final String TAG_CREATE_GAME_REQUEST = "CGR";
	private static final String TAG_HIGHSCORE = "HS";
	private static final String TAG_SCORE = "SC";
	private static final String TAG_REQUEST_JOIN = "RJ";
	private static final String TAG_RESPONSE_JOIN = "RSJ";
	private static final String TAG_START = "STR";
	private static final String TAG_SAVE_RESPOND = "SR";
	private static final String TAG_PLAYER_LIST = "PL";
	private static final String TAG_NEW_GAME_INVITE = "NGI";
	private static final String TAG_NEW_GAME_INVITE_RESPONSE = "NGIR";
	private static final String TAG_SAVE_RESPONSE_SERVER = "SRS";

	private String[] parameters;


   private ServerSocket serverSocket;

   public ThreadedServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000*1000);
   }



   public String processRequest(DataInputStream input){

	   long startTime = System.currentTimeMillis();

	   Player player = new Player();
	   Game game = new Game();

	   String result = "";

	   parameters = new String[]{};

	   try {

		   parameters = input.readUTF().split(";");
	   }
	   catch (IOException e) {

		   e.printStackTrace();
	   }


	   switch(parameters[0]){

			case TAG_LOGIN:
							result = TAG_LOGIN+";"+player.playerLogin(parameters[1],parameters[2]).getPlayerID()+";"
													+player.playerLogin(parameters[1],parameters[2]).getPlayerUserName();
							player.addPlayerToNotPlayingList(player);
							break;

			case TAG_SIGNUP:
							result = TAG_SIGNUP+";"+Boolean.toString(player.registerPlayer
													(parameters[1],parameters[2],parameters[3]));
							break;

			case TAG_SAVE:
							// send save request to other members
							result = TAG_SAVE_RESPONSE_SERVER+";"+
										Boolean.toString(player.requestToSave(Integer.parseInt(parameters[1])));

							break;

			case TAG_SAVE_RESPOND:
							player.updateSaveResponse(parameters[1]);
							break;

			case TAG_LEAVE:
							game.leaveGame(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));

							break;


			case TAG_SCORE:
							result = game.sendScores(Integer.parseInt(parameters[1]),
															Integer.parseInt(parameters[2])).toString();
							break;


			case TAG_CREATE_GAME_REQUEST:

							result = TAG_PLAYER_LIST+";"+player.sendFreePlayerList();
							break;


			case TAG_CREATE_GAME_START:

										player.invitePlayers(Integer.parseInt(parameters[1]),new int[]
												{Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]),
												Integer.parseInt(parameters[4]),Integer.parseInt(parameters[5])});

							// start game if at least 2 players accepted invite
							// send new game start msg to accepted and game created client

							break;


			case TAG_NEW_GAME_INVITE_RESPONSE:
								// TODO: process the response and start game


								break;


			case TAG_REQUEST_JOIN:

								boolean isMember = game.isAlreadyInGameGroup(Integer.parseInt(parameters[1])
																			,Integer.parseInt(parameters[2]));
								if(isMember){

									// TODO: send notify others and get their approve
									result = TAG_RESPONSE_JOIN+";"+TAG_START;

								}
								else{

								}

								// send response to initialize game round screen
								break;

			default:
								System.err.println("No such type of request");
								break;

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


}
