package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SupportUI  {


	// open URL from web browser
	public void openURL(String url) throws IOException, URISyntaxException{

		if(Desktop.isDesktopSupported())
		{
		  Desktop.getDesktop().browse(new URI(url));
		}

	}

}