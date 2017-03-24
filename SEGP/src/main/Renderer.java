package main;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

<<<<<<< HEAD
=======
/**
 * @author SEGP-Group3
 *
 */
>>>>>>> phase5
public class Renderer {
	
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	
<<<<<<< HEAD
=======
	/**
	 * constructor of web view renderer
	 */
>>>>>>> phase5
	public Renderer(){
		
		//--------------------- Default url will be google--------------------------
		
		webEngine.load("http://www.google.com");
<<<<<<< HEAD
		
=======
>>>>>>> phase5
		//System.out.println("Title fo page:"+webEngine.getTitle());
	}

}
