package main;


import java.util.Properties;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import database.History;
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

		// following three lines are for proxy settings
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost","172.16.0.2");
		systemProperties.setProperty("http.proxyPort","8080");
<<<<<<< HEAD
		systemProperties.setProperty("https.proxyHost","172.16.0.2");
		systemProperties.setProperty("https.proxyPort","8080");
		History.CreateDataBase();//intializing the datebase when application starts
=======

		systemProperties.setProperty("https.proxyHost","172.16.0.2");
		systemProperties.setProperty("https.proxyPort","8080");
		
		
>>>>>>> upstream/master
		launch(args);
		
	}

}
