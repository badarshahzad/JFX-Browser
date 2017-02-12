package main;


import java.util.Properties;

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
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.16.0.2", "8080");
		proxy.setHttpsProxy("172.16.0.2", "8080");
		

		// following three lines are for proxy settings
		launch(args);
	}

}
