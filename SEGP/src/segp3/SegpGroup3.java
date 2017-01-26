package segp3;


import java.util.Properties;

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
    	systemProperties.setProperty("http.proxyHost","172.16.0.2");
    	systemProperties.setProperty("http.proxyPort","8080");

		launch(args);
	}

}
