package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import controllers.MainController;
import controllers.TabController;
import database.BookMarksDataBase;
import database.CRUD;
import database.DownloadDatabase;
import database.HistoryManagment;
import database.UserAccounts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Segp-Group 3
 */
public class MainClass extends Application {

	MainController object = new MainController();
	static Stage stage;

	public static Scene sceneCopy;

	private static StackPane pane = new StackPane();

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/ui/MainFXML.fxml"));

		// the RootBorder is get to show pin dialoge box that will appear on a
		// screen
		pane.getChildren().add(root);

		Scene scene = new Scene(pane);

		scene.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.P && event.isControlDown()) {

				JFXButton button = new JFXButton("Ok");
				JFXTextField textfield = new JFXTextField();

				button.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6) -> {
					System.out.println("Pin is :" + textfield.getText());

					Pattern ipAddress = Pattern.compile("[0-9]{4}");
					Matcher m1 = ipAddress.matcher(textfield.getText());
					boolean b1 = m1.matches();


					if (b1) {
						//TabController ob  = new TabController();
						/*			 
					 FXMLLoader loader  = new FXMLLoader(getClass().getResource("/ui/Tab.fxml"));
					 try {
						loader.load();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 ob = loader.getController();

					// ob.getHtmlAsPdf().setVisible(true);
					 ob.getHamburger().setDisable(true);

					 //ob.getHamburger().setDisable(false);
					 //ob.getBookmark().setDisable(false);
						 */

					} else {

						Notifications.create().title("Wronge Pin")
						.text("Your pin is exceeding limit or your pin is consists\n" + "of invalid characters")
						.hideAfter(Duration.seconds(5)).showError();

					}

				});

				setDialouge(button, "Pin", "Please type your pin", textfield);

				Notifications.create().title("Pin Activation").text("You are going to access Pro-Verion.")
				.hideAfter(Duration.seconds(3)).showInformation();
			}

			if (event.getCode() == KeyCode.T && event.isControlDown()) {

				MainController mainCont = new MainController();
				mainCont.creatNewTab(mainCont.getTabPane(), mainCont.getAddNewTab());

			}
		});

		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setTitle("Jfx Browser");
		stage.setScene(scene);
		stage.show();

		setStage(stage);
		setScene(scene);

	}

	public static StackPane getPane() {
		return pane;
	}

	public void setPane(StackPane pane) {
		this.pane = pane;
	}

	private void setScene(Scene scene) {
		sceneCopy = scene;
	}

	public static Scene getScene() {
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

	private static JFXDialogLayout content = new JFXDialogLayout();

	public static void setDialouge(JFXButton applyButton, String heading, String text, Node ob) {

		JFXButton button = applyButton;

		content.setHeading(new Text(heading));
		content.setBody(new Text(text));

		JFXDialog dialoge = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6) -> {
			dialoge.close();
		});

		content.setActions(ob, button);

		// To show overlay dialougge box
		dialoge.show();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {

		HistoryManagment.CreateDataBase();
		UserAccounts.createUserAccountsDataBase();
		BookMarksDataBase.createBookMarksDataBase();
		DownloadDatabase.createDownloadDataBase();
		CRUD.createUserDataBase();
	
		launch(args);
		System.exit(1);
	}

}
