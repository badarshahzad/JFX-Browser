package main;
<<<<<<< HEAD
import database.History_Managment;
=======


import java.util.Properties;

import controllers.MainController;
>>>>>>> 9aea2a2650906addf4f66007e806c19f082ee5d9
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userInterface.Proxy;


/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	FXMLLoader loader ;
	MainController object;
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/MainFXML.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.16.0.2", "8080");
		proxy.setHttpsProxy("172.16.0.2", "8080");
		

		// following three lines are for proxy settings
		History_Managment.CreateDataBase(); //if datbase is deleted then create new one
=======
		// Set Proxy for Http
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost", "172.16.0.2");
		systemProperties.setProperty("http.proxyPort", "8080");

		// Set Proxy for Https
		systemProperties.setProperty("https.proxyHost", "172.16.0.2");
		systemProperties.setProperty("https.proxyPort", "8080");
>>>>>>> 9aea2a2650906addf4f66007e806c19f082ee5d9
		launch(args);
	}

}
