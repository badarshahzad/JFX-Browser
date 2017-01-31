package main;

import java.util.Properties;

import DataBase.History;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Segp-Group 3
 */
public class SegpGroup3 extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
<<<<<<< HEAD:SEGP/src/segp3/SegpGroup3.java
    	systemProperties.setProperty("http.proxyHost","172.16.0.2");
    	systemProperties.setProperty("http.proxyPort","8080");
		History hs=new History();
		hs.CreateDataBase();
=======
		systemProperties.setProperty("http.proxyHost","172.16.0.2");
		systemProperties.setProperty("http.proxyPort","8080");
    	//systemProperties.setProperty("http.proxyHost","");
    	//systemProperties.setProperty("http.proxyPort","");
		

>>>>>>> upstream/master:SEGP/src/main/SegpGroup3.java
		launch(args);
	}

}
