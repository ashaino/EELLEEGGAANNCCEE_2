package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import gamesplayers.TrophyCabinet;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileAccess {

	private Preferences preferences;

	private static String idMusic;
	private static String idFullScreen;
	private static final String KEY = "flog is similar to countdown";
	private File file;


	private final static String ENCRYPTED_FILE_PATH = System.getProperty
			("user.dir")+"/resources/encrypted_trophy_cabinet.txt";
	private final static String DECRYPTED_FILE_PATH = System.getProperty
			("user.dir")+"/resources/decrypted_trophy_cabinet.txt";

	public FileAccess() {
		super();
		this.preferences = Preferences.userRoot().node(this.getClass().getName());
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public boolean[]  getSettingValues(){

		boolean[] settings = new boolean[2];

		settings[0] = preferences.getBoolean(idFullScreen, true);
	    settings[1] = preferences.getBoolean(idMusic, true);

	    return settings;
	}

	public void resetToDefaultValues(){

		preferences.putBoolean(idFullScreen, true);
	    preferences.putBoolean(idMusic, true );

	}


	public void saveSettingValues(boolean fullScreen, boolean music){

		preferences.putBoolean(idFullScreen, fullScreen);
	    preferences.putBoolean(idMusic, music);

	}



	public List<String> loadHelpTextFromXML(String stepNo){

		List<String> stepDescription = new ArrayList<String>();

		try {

			File file = new File(System.getProperty
								("user.dir")+"/resources/help.xml");
			DocumentBuilderFactory documentBuilderFactory
								= DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder
								= documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("step");

			for (int temp = 0; temp < nodeList.getLength(); temp++) {

				Node node = nodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;


						stepDescription.add(element
								.getElementsByTagName("desc").item(0).getTextContent());



				}
			}
		    } catch (Exception e) {

		    	e.printStackTrace();
		    }


		return stepDescription;
	}


	public void initTrophyCabinet(){

		try {


			FileInputStream inputStream = new FileInputStream(System.getProperty
					("user.dir")+"/resources/trophy_cabinet.txt");
			file = new File(System.getProperty
					("user.dir")+"/resources/trophy_cabinet.txt");


			FileOutputStream outputStream = new FileOutputStream(ENCRYPTED_FILE_PATH);

			FileEncryptDecrypt.encrypt(KEY, inputStream, outputStream);

			file.delete();


		}
		catch (Throwable e) {

			e.printStackTrace();
		}

	}


	public void decryptTrophyFile() throws Throwable{

		FileInputStream inputStream = new FileInputStream(ENCRYPTED_FILE_PATH);
		FileOutputStream outputStream = new FileOutputStream(DECRYPTED_FILE_PATH);

		FileEncryptDecrypt.decrypt(KEY, inputStream, outputStream);
		System.out.println("Decryption done");
	}

	public void ecryptTrophyFile() throws Throwable{

		FileInputStream inputStream = new FileInputStream(DECRYPTED_FILE_PATH);
		FileOutputStream outputStream = new FileOutputStream(ENCRYPTED_FILE_PATH);

		FileEncryptDecrypt.encrypt(KEY, inputStream, outputStream);
		System.out.println("Encryption done");
	}


	public List<TrophyCabinet> loadTrophyCabinet(){

		List<TrophyCabinet> trophies = new ArrayList<TrophyCabinet>();
		TrophyCabinet trophy = new TrophyCabinet();

		String line = "";
		String[] trophyDetails = new String[3];

		try {

			decryptTrophyFile();

			// read decrypted file

			file = new File(DECRYPTED_FILE_PATH);

	        FileReader fileReader = new FileReader(file);
	        BufferedReader bufferReader = new BufferedReader(fileReader);

	        while ((line = bufferReader.readLine()) != null) {

	        	trophyDetails = line.split(";");

				// instantiate trophies list

	        	trophy.setTrophyID(trophyDetails[0]);
	        	trophy.setTrophyName(trophyDetails[1]);
	        	trophy.setTrophyDescription(trophyDetails[2]);
	        	trophy.setTrophyUnlocked(Boolean.parseBoolean(trophyDetails[3]));

	        	trophies.add(trophy);

	        }

	        fileReader.close();
	        bufferReader.close();

	        file = new File(DECRYPTED_FILE_PATH);
	    	file.delete();

		}
		catch (Throwable exception) {

			initTrophyCabinet();
			exception.printStackTrace();
		}


		return trophies;
	}


	public void unlockTrophy(int trophyID){

		String line = "";
		String modifiedLine = "";
		File fileForWrite = null;

		final String tmpFilePath = System.getProperty
				("user.dir")+"/resources/tmp_trophy_cabinet.txt";

		String[] trophyDetails = new String[3];

		try {

			decryptTrophyFile();

			// read decrypted file

			file = new File(DECRYPTED_FILE_PATH);
			fileForWrite = new File(tmpFilePath);

	        FileReader fileReader = new FileReader(file);
	        BufferedReader bufferReader = new BufferedReader(fileReader);


	        FileWriter fileWriter = new FileWriter(fileForWrite);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



	        while ((line = bufferReader.readLine()) != null) {

	        	trophyDetails = line.split(";");

				if(trophyDetails[0].equals(Integer.toString(trophyID))){

					// write to file after modifying

					trophyDetails[3]= "1";
					modifiedLine = trophyDetails[0]+";"+trophyDetails[1]+";"
									+trophyDetails[2]+";"+trophyDetails[3];

					System.out.println("Modified "+modifiedLine);
					bufferedWriter.append(modifiedLine+"\n");

				}
				else{
					// write to file without modifying

					System.out.println("Unmodified "+line);
					bufferedWriter.append(line+"\n");
				}
	        }

	        bufferedWriter.flush();
            bufferedWriter.close();

	        fileReader.close();
	        bufferReader.close();

	        fileForWrite.renameTo(file);


	        // encrypting  the updated file
	        ecryptTrophyFile();

	        file.delete();
	        fileForWrite.delete();


		}
		catch (Throwable exception) {

			initTrophyCabinet();
			exception.printStackTrace();
		}
	}

}
