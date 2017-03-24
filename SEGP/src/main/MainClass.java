package main;
<<<<<<< HEAD


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
=======
import database.History_Managment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userInterface.Proxy;


>>>>>>> phase5
/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

<<<<<<< HEAD
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

=======
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

>>>>>>> phase5
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		// Set Proxy for Http
		/*Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost", "172.16.0.2");
		systemProperties.setProperty("http.proxyPort", "8080");

		// Set Proxy for Https
		systemProperties.setProperty("https.proxyHost", "172.16.0.2");
		systemProperties.setProperty("https.proxyPort", "8080");*/
=======
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("172.16.0.2", "8080");
//		proxy.setHttpsProxy("172.16.0.2", "8080");
		

		// following three lines are for proxy settings
		History_Managment.CreateDataBase(); //if datbase is deleted then create new one
>>>>>>> phase5
		launch(args);
	}

}
