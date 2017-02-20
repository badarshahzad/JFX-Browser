package main;

import database.History_Managment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import userInterface.Proxy;

=======
>>>>>>> 5e52cfb48be6a20f5a9073c870c2fa4f6f4ec81d
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
<<<<<<< HEAD
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.16.0.2", "8080");
		proxy.setHttpsProxy("172.16.0.2", "8080");
		

		// following three lines are for proxy settings
=======
		History_Managment.CreateDataBase(); //if datbase is deleted then create new one
>>>>>>> 5e52cfb48be6a20f5a9073c870c2fa4f6f4ec81d
		launch(args);
	}

}
