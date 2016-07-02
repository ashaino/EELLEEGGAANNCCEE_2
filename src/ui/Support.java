package ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Support  {


	// open URL from web browser
	public void openURL(String url) throws IOException, URISyntaxException{

		if(Desktop.isDesktopSupported())
		{
		  Desktop.getDesktop().browse(new URI(url));
		}

	}


	  public boolean isValidEmailAddress(String email) {

		  String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]"
          		+ "{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
          		+ "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

          java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
          java.util.regex.Matcher m = p.matcher(email);
          return m.matches();
   }

}