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

	// Message IDs
	private static final String TAG_LOGIN = "LI";
	private static final String TAG_SIGNUP = "SU";
	private static final String TAG_SAVE = "SV";
	private static final String TAG_LEAVE = "LV";
	private static final String TAG_JOIN = "JN";
	private static final String TAG_CREATE_GAME = "CG";
	private static final String TAG_HIGHSCORE = "HS";
	private static final String TAG_SCORE = "HS";
	private static final String TAG_SAVE_RESPOND = "SR";

    private static final String SEVER_NAME = "localhost";
    private static final int PORT = 6066;
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
    	//sendScore(score);

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


	public static String sendScore(int score){

		connect();
		String scoreRecieved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SCORE+";"+score);

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			scoreRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return scoreRecieved;
	}





	public static String createGame(){

		connect();
		String scoreRecieved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_CREATE_GAME+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			scoreRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return scoreRecieved;
	}


	public static String joinGame(){

		connect();
		String scoreRecieved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_JOIN+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			scoreRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return scoreRecieved;
	}


	public static String getHighScores(){

		connect();
		String scoreRecieved = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_HIGHSCORE+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			scoreRecieved = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return scoreRecieved;
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


	public static String respondForSaveRequest(String response){

		connect();
		String saveResult = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_SAVE_RESPOND+";"+response);

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			saveResult = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return saveResult;
	}

	public static String leaveGame(int score){

		connect();
		String leaveResult = "";

		try {

			outToServer = clientSocket.getOutputStream();

			out = new DataOutputStream(outToServer);

        	out.writeUTF(TAG_LEAVE+";");

        	inFromServer = clientSocket.getInputStream();
			in = new DataInputStream(inFromServer);

			leaveResult = in.readUTF();

		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return leaveResult;
	}


	public static String getTagSaveRespond() {
		return TAG_SAVE_RESPOND;
	}

}
