package main;

import com.jfoenix.controls.JFXTextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public interface  Renderer {
	
<<<<<<< HEAD
	void pageDisplay(String url, JFXTextField searchField,BorderPane borderpane);
=======
	public Renderer(){
		
		//--------------------- Default url will be google--------------------------
		
		webEngine.load("http://www.google.com");
		//System.out.println("Title fo page:"+webEngine.getTitle());
	}
>>>>>>> upstream/master

}
