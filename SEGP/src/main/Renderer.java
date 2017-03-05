package main;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * @author SEGP-Group3
 *
 */
public class Renderer {
	
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	
	/**
	 * constructor of web view renderer
	 */
	public Renderer(){
		
		//--------------------- Default url will be google--------------------------
		
		webEngine.load("http://www.google.com");
		//System.out.println("Title fo page:"+webEngine.getTitle());
	}

}
