package main;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Renderer {

	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	public Renderer(){

		//--------------------- Default url will be google--------------------------

		webEngine.load("http://www.google.com");

	}

}
