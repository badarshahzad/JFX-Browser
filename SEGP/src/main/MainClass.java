package main;

import controllers.MainController;

import java.util.Properties;

import org.controlsfx.control.Notifications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	FXMLLoader loader;
	MainController object;
	static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/MainFXML.fxml"));
		Scene scene = new Scene(root);

		scene.setOnKeyPressed(event -> {
 
			if (event.getCode() == KeyCode.P && event.isControlDown()) {
				Notifications.create().title("Pin").text("Please type your pin!").hideAfter(Duration.seconds(5))
						.showInformation();
			}

			if (event.getCode() == KeyCode.T && event.isControlDown()) {
				MainController mainCont = new MainController();
				mainCont.creatNewTab(mainCont.getTabPane(), mainCont.getAddNewTab());

			}
		});

		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		setStage(stage);
		stage.show();
	}
/*
	private void setStage(Stage stage) {
		// TODO Auto-generated method stub
		MainClass.stage = stage;
	}
	*/
	
	@SuppressWarnings("static-access")
	private void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// Set Proxy for Http
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost", "172.16.0.2");
		systemProperties.setProperty("http.proxyPort", "8080");

		// Set Proxy for Https
		systemProperties.setProperty("https.proxyHost", "172.16.0.2");
		systemProperties.setProperty("https.proxyPort", "8080");
		
		launch(args);
		System.exit(1);
	}

}
