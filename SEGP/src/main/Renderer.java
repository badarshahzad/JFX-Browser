package main;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

<<<<<<< HEAD
/**
 * @author SEGP-Group3
 *
 */
=======
>>>>>>> upstream/master
public class Renderer {
	
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	
<<<<<<< HEAD
	/**
	 * constructor of web view renderer
	 */
=======
>>>>>>> upstream/master
	public Renderer(){
		
		//--------------------- Default url will be google--------------------------
		
		webEngine.load("http://www.google.com");
		//System.out.println("Title fo page:"+webEngine.getTitle());
	}

}
