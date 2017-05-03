package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import database.CRUD;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.MainClass;

public class SettingController implements Initializable {

	@FXML
	private JFXButton signInBtn;

	@FXML
	private JFXCheckBox checkPasswordRemember;

	@FXML
	private JFXButton managePasswordBtn;

	@FXML
	private JFXButton changeProxyBtn;

	@FXML
	private ImageView userImage;

	@FXML
	private JFXTextField currentUser;

	@FXML
	private TableView<?> usersTable;


	private static Stage loginSignInStage = new Stage();
	private Scene loginSignInRoot;

	public FXMLLoader loader;
	public LoginController loginObject;
	public SignUpController signUpObject;	

	public static String currentUserName = "Jfx Browser";
	
	public static String getCurrentUserName() {
		return currentUserName;
	}

	public static void setCurrentUserName(String currentUserName) {
		SettingController.currentUserName = currentUserName;
	}

	public JFXTextField getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(JFXTextField currentUser) {
		this.currentUser = currentUser;
	}

	public Tab getSettingView(Tab settingTab, BorderPane borderpane) {

		/*
		 * Add two buttons in gridpane that will be put in
		 * drawer->drawerstack(container) -> left side of border to come ount
		 * whenever user click the setting button
		 */

		// ---------------------------------Right----DrawerStack--------------------------------

		// Setting the left side of Borderpane drawerstack container
		try {
			// ScrollPane scrollPane = new ScrollPane();
			System.out.println("Setting fxml is ready to set");
			borderpane.setCenter(FXMLLoader.load(getClass().getResource("/ui/Setting.fxml")));
		} catch (Exception e1) {
			System.out.println("Exception: Setting fxml is not set");
			System.out.println("File is not find for setting! " + " \n " + e1);

		}

		// borderpane.setCenter(root);
		settingTab.setContent(borderpane);

		return settingTab;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		currentUser.setText(currentUserName);

		userImage.setImage(new Image(getClass().getResourceAsStream("/user.png")));

		
		
		signInBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			try {
				System.out.println("Ready for set login");
				// LoginController object = new LoginController();
				// loginBorderpane = object.setLoginView(loginBorderpane);

				// Parent fxmlLoader =
				// FXMLLoader.load(getClass().getResource("Login.fxml"));
				loader = new FXMLLoader(getClass().getResource("/ui/Login.fxml"));
				loader.load();
				loginObject = loader.getController();

				loginSignInRoot = new Scene(loginObject.getLoginPane());
				loginSignInStage.setScene(loginSignInRoot);
				loginSignInStage.setMaximized(false);
				loginSignInStage.setResizable(false);
				loginSignInStage.centerOnScreen();
				loginSignInStage.show();

				
			} catch (Exception e1) {
				System.out.println("Login Fxml is not loading");
				e1.printStackTrace();
			}
		
			// LoginPane login button listenr
			loginObject.getLogin().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1) -> {
				

				boolean flag = loginObject.getUser().getText().isEmpty() | loginObject.getPassword().getText().isEmpty();

				if (flag) {

					Notifications noti = Notifications.create()
							.title("Empty Field")
							.text("Please fill the empty field!")
							// .graphic(new ImageView(null))
							.hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
					noti.showError();

				}else{
					if(CRUD.isLogin(loginObject.getUser().getText(), loginObject.getPassword().getText())){

						//Setting the name of current user
						currentUser.setText(loginObject.getUser().getText());
						
						Notifications noti = Notifications.create()
								.title("Successfull")
								.text("Congratulation! You successfully login. ")
								// .graphic(new ImageView(null))
								.hideAfter(Duration.seconds(3)).position(Pos.TOP_RIGHT);
						noti.showInformation();
					}else{
						Notifications noti = Notifications.create()
								.title("Username and Password Incorrect!")
								.text("Please give your valid username or password. ")
								// .graphic(new ImageView(null))
								.hideAfter(Duration.seconds(3)).position(Pos.TOP_RIGHT);
						noti.showError();
					}

				}
				
				System.out.println("Login click button ");
			});

			// LoinPane sing up butotn listner
			loginObject.getSingUp().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1) -> {

				loader = new FXMLLoader(getClass().getResource("/ui/SignUp.fxml"));
				try {

					loader.load();
					signUpObject = loader.getController();
					
					signUpObject.getBackLoginView().addEventHandler(MouseEvent.MOUSE_CLICKED, (ex)->{

						try {
							
							loader = new FXMLLoader(getClass().getResource("/ui/Login.fxml"));
							loader.load();
							loginObject = loader.getController();

							loginSignInRoot = new Scene(loginObject.getLoginPane());
							loginSignInStage.setScene(loginSignInRoot);
							loginSignInStage.setMaximized(false);
							loginSignInStage.setResizable(false);
							loginSignInStage.centerOnScreen();
							loginSignInStage.show();
							
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
							
					});
					
					loginSignInRoot = new Scene(signUpObject.getSignUpPane());
					loginSignInStage.setScene(loginSignInRoot);
					loginSignInStage.show();

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			});


		});

		changeProxyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			ProxyController proxyObject;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ProxySet.fxml"));

			try {

				loader.load();

			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			Stage primaryStage = new Stage();
			proxyObject = loader.getController();

			Scene root = new Scene(proxyObject.getProxyPane());
			primaryStage.setScene(root);
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			primaryStage.setMaximized(false);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setOpacity(.9);

			primaryStage.show();

			proxyObject.getOkBt().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1) -> {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						Notifications.create().title("Proxy Setting").text("You have successfully set your proxy.")
						.hideAfter(Duration.seconds(3)).showInformation();

					}
				});

				primaryStage.close();

			});

			proxyObject.getCancelBt().addEventHandler(MouseEvent.MOUSE_CLICKED, (e2) -> {

				primaryStage.close();
			});

			// ob.getStage().show();
		});
		// Start the userTreeView from herre
	}

	public static Stage getLoginSignInStage() {
		return loginSignInStage;
	}

	public void setLoginSignInStage(Stage loginSignInStage) {
		SettingController.loginSignInStage = loginSignInStage;
	}

}
