package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileAccess {

	private Preferences preferences;

	private static String idMusic;
	private static String idFullScreen;
	
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

}
