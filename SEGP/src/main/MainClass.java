package main;


import controllers.MainController;
import database.HistoryManagment;
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
		MainClass.stage = stage;
	}
	
	public static  Stage getStage(){
		return stage;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
//		Properties systemProperties = System.getProperties();
//		systemProperties.setProperty("http.proxyHost","172.16.0.2");
//		systemProperties.setProperty("http.proxyPort","8080");
//		systemProperties.setProperty("https.proxyHost","172.16.0.2");
//		systemProperties.setProperty("https.proxyPort","8080");
		HistoryManagment.CreateDataBase();
//		BookMarksDataBase.createBookMarksDataBase();
		launch(args);
	}

}
