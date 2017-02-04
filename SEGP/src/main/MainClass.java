package main;

import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Segp-Group 3
 * This is the main class to launch the whole application. Scene buildr is being used to launch this application.
 */

public class MainClass extends Application {

/*
 * (non-Javadoc)
 * @see javafx.application.Application#start(javafx.stage.Stage)
 * The start method is created to overwrite the built in start method. In this method fxml is being loaded 
 * and opended with scene builder.
 */
	
@Override
public void start(Stage stage) throws Exception {
	Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));//object to get parent fxml file to load and the launch it.
	
	Scene scene = new Scene(root);//object of scenebuilder to open fxml file.
	scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());//adding style to fxml file and its contents.
	stage.setScene(scene);
	stage.show();
}

/**
 * @param args
 * the command line arguments
 * This is the main method and launching the application.
 */
public static void main(String[] args) {

//		following three lines are for proxy settings
//		Properties systemProperties = System.getProperties();
//		systemProperties.setProperty("http.proxyHost","172.16.0.2");
//		systemProperties.setProperty("http.proxyPort","8080");
//		

	launch(args);
}

}
