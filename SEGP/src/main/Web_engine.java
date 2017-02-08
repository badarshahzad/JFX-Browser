package main;

import com.jfoenix.controls.JFXTextField;

import database.History;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web_engine implements Renderer
{
	private WebView browser = new WebView();
	private  WebEngine webEngine = browser.getEngine();	
	@Override
	
	public void pageDisplay(String url,JFXTextField searchField,BorderPane borderpane){
		webEngine.load(url);
		searchField.setText(webEngine.getLocation());
		borderpane.setCenter(browser);
		if(!(searchField.getText().equals("about:blank")))
		{
			History.insertUrl(webEngine.getLocation()); //insertUrl function call to save the url in database
		}
		
	}
	

}
