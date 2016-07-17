package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import dataaccess.FileAccess;

public class Client extends TimerTask {

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
	private static final String TAG_SAVE_RESPOND = "SR";
	private static final String TAG_PLAYER_LIST = "PL";
	private static final String TAG_NEW_GAME_INVITE = "NGI";
	private static final String TAG_NEW_GAME_INVITE_RESPONSE = "NGIR";
	private static final String TAG_SAVE_RESPONSE_SERVER = "SRS";

    private static final String SEVER_NAME = "localhost";
    private static final int PORT = 6066;
    private static int score = 6066;
    private static OutputStream outToServer;
    private static DataOutputStream out;
    private static InputStream inFromServer;
    private static Socket clientSocket;
    private static DataInputStream in;


    public static void clientTimer(){

    	  TimerTask sendStatus = new Client();
		  Timer timer = new Timer();

		   // sending client status for every 30 seconds
		   timer.schedule(sendStatus,100, 30*100);
    }


    @Override
	public void run() {

    	System.out.println("sending status");
    	sendScore(score);

	}


	public static void connect(){

		System.out.println("Connecting to " + SEVER_NAME +
		 " on port " + PORT);

        try {
			clientSocket = new Socket(SEVER_NAME, PORT);
		}
        catch (UnknownHostException e) {
			e.printStackTrace();
		}
        catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println("Just connected to "
		 + clientSocket.getRemoteSocketAddress());


	}

	public static void login(String email, String password){

		connect();
		String loginValid = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);


        	out.writeUTF(TAG_LOGIN+";"+email+";"+password);

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

        	loginValid = in.readUTF();
        	clientSocket.close();
		}
		catch (IOException e) {

			e.printStackTrace();
		}

		if(!loginValid.equals("")){

			FileAccess fileAccess = new FileAccess();
			fileAccess.saveUserData(loginValid);
		}



	}

	public static String signUp(String userName, String email, String password){

		connect();
		String signUpValid = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SIGNUP+";"+userName+";"+email+";"+password);

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			signUpValid = in.readUTF();
			clientSocket.close();
		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return signUpValid;
	}

	public static void sendScore(int score){

		connect();


		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SCORE+";"+score);

        	//inFromServer = clientSocket.getInputStream();
			//in = new DataInputStream(inFromServer);

			//scoreRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void requestCreateGame(){

		connect();
		String playerList = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_CREATE_GAME_REQUEST+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			playerList = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println(playerList);


	}

	public static void sendSelectedPlayerList(String selectedlayerList){

		connect();

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_CREATE_GAME_START+";"+selectedlayerList);

        	//inFromServer = clientSocket.getInputStream();
			//in = new DataInputStream(inFromServer);

			//gameApproved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}


	}

	public static void respondGameInvite(boolean response){

		connect();
		//String gameApproved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_NEW_GAME_INVITE_RESPONSE+";"+response);

        	//inFromServer = clientSocket.getInputStream();
			//in = new DataInputStream(inFromServer);

			//gameApproved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static String requestJoinGameList(){

		connect();
		String joinGameResponse = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_REQUEST_JOIN+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			joinGameResponse = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return joinGameResponse;
	}

	public static String getHighScores(){

		connect();
		String highScoresRecieved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_HIGHSCORE+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			highScoresRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return highScoresRecieved;
	}

	public static String requestSave(){

		connect();
		String saveResult = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SAVE+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			saveResult = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return saveResult;
	}

	public static void respondForSaveRequest(String response){

		connect();
		//String saveResult = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SAVE_RESPOND+";"+response);

        	//inFromServer = clientSocket.getInputStream();
			//in = new DataInputStream(inFromServer);

			//saveResult = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		//return saveResult;
	}

	public static void leaveGame(){

		connect();
		//		String leaveResult = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_LEAVE+";");

        //	inFromServer = clientSocket.getInputStream();
			//in = new DataInputStream(inFromServer);

			//leaveResult = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}


	}



}
