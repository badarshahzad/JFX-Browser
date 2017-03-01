package main;

import java.util.Properties;

import database.History_Managment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
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
		/*Properties systemProperties = System.getProperties();
		// Set Proxy for Http
		systemProperties.setProperty("http.proxyHost", "172.16.0.2");
		systemProperties.setProperty("http.proxyPort", "8080");

		// Set Proxy for Https
		systemProperties.setProperty("https.proxyHost", "172.16.0.2");
		systemProperties.setProperty("https.proxyPort", "8080");*/
		//History_Managment.CreateDataBase(); //if datbase is deleted then create new one
		launch(args);
	}

}
