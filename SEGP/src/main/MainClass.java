package main;

import controllers.MainController;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	FXMLLoader loader;
	MainController object = new MainController();
	static Stage stage;
	
	public static Scene sceneCopy ;
	

	private StackPane pane = new StackPane() ;

	@Override
	public void start(Stage stage) throws Exception {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("/ui/MainFXML.fxml"));
		
		//borderpane.setCenter(button);
		
		//the RootBorder is get to show pin dialoge box that will appear on a screen
		pane.getChildren().add(root);
		
		//JFXButton button1 = new JFXButton("Button1");
		
		
		Scene scene = new Scene(pane);

		scene.setOnKeyPressed(event -> {
 
			if (event.getCode() == KeyCode.P && event.isControlDown()) {
				
				JFXButton okBt = new JFXButton("Ok");
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Hidden Key"));
				content.setBody(new Text("Please type your key to access Pro-Version. "));
				JFXDialog dialoge = new JFXDialog(pane,content,JFXDialog.DialogTransition.CENTER);
				okBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6)->{
					dialoge.close();
				});

				JFXTextField textfiled = new JFXTextField();
				content.setActions(textfiled,okBt);

				//To show overlay dialougge box
				dialoge.show();	

				Notifications.create()
				.title("Pin Activation")
				.text("You are going to access Pro-Verion.")
				.hideAfter(Duration.seconds(3))
				.showInformation();
			}

			if (event.getCode() == KeyCode.T && event.isControlDown()) {
				
				MainController mainCont = new MainController();
				mainCont.creatNewTab(mainCont.getTabPane(), mainCont.getAddNewTab());
				
			}
		});

		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		setStage(stage);
		setScene(scene);
		
	}

	private void setScene(Scene scene) {
		sceneCopy = scene;
	}
	
	public static Scene getScene(){
		return sceneCopy;
	}
	

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

		launch(args);
		System.exit(1);
	}

}
