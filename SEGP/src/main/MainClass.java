package main;


import java.awt.event.KeyEvent;
import java.util.Properties;

import controllers.MainController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	FXMLLoader loader ;
	MainController object;
	static Stage stage;
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/MainFXML.fxml"));
		
		Scene scene = new Scene(root);
		
		//key listeners
		
		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		setStage(stage);
		stage.show();
	}

	private void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}
	
	public static  Stage getStage(){
		return stage;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		
		launch(args);
	}

}
