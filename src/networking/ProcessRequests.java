package networking;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class ProcessRequests implements Runnable {

	private Socket clientSocket = null;

	public ProcessRequests(Socket clientSocket) {

		this.clientSocket = clientSocket;

	}


	public void run() {

		try{

			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			long startTime = System.currentTimeMillis();

			// TODO: processing request





			// closing streams
			output.close();
			input.close();

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;

			System.out.println("Request processed: " + startTime);
			System.out.println("Processing time: " + elapsedTime);

		}
		catch (IOException exception) {

			exception.printStackTrace();
		}
	}
}