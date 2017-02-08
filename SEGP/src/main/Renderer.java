package main;

import com.jfoenix.controls.JFXTextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public interface  Renderer {
	
	void pageDisplay(String url, JFXTextField searchField,BorderPane borderpane);

}
