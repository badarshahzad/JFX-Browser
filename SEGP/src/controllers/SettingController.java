package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingController implements Initializable {

	public Tab getSettingView(Tab settingTab, BorderPane borderpane) {

		/*
		 * Add two buttons in gridpane that will be put in
		 * drawer->drawerstack(container) -> left side of border to come ount
		 * whenever user click the setting button
		 */

		// ------------------------------------------------------Right----DrawerStack--------------------------------

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

	// tab.getStyleClass().addAll("tab-pane");
	@FXML
	private JFXButton signInBtn;
	@FXML
	private JFXButton disconnectAccountBtn;
	@FXML
	private Label userAccountNameLbl;
	
	//Users  table list
	@FXML
	private JFXTreeTableView<?> usersTreeTabelView;
	@FXML
	private JFXCheckBox checkPasswordRemember;
	@FXML
	private JFXButton managePasswordBtn;
	@FXML
	private JFXButton changeProxyBtn;
	
	private static Stage loginSignInStage = new Stage();
	private Scene loginSignInRoot ;
	
	private FXMLLoader loader;
	private LoginController loginObject ;
	private SignUpController signUpObject;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		signInBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			
			try {
				System.out.println("Ready for set login");
				//LoginController object = new LoginController();
				//loginBorderpane = object.setLoginView(loginBorderpane);
				
				//Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Login.fxml"));
				loader = new  FXMLLoader(getClass().getResource("/ui/Login.fxml"));
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
			//LoginPane  login button listenr
				loginObject.getLogin().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1)->{
				System.out.println("Login click button ");
			});
			
			//LoinPane  sing up butotn listner
			loginObject.getSingUp().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1)->{
				
				loader = new  FXMLLoader(getClass().getResource("/ui/SignUp.fxml"));
				try {
					
					loader.load();
					signUpObject = loader.getController();
					loginSignInRoot = new Scene(signUpObject.getSignUpPane());
					loginSignInStage.setScene(loginSignInRoot);
					loginSignInStage.show();
					
				
				
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			});
			
			
		});
	
		changeProxyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			
			ProxyController proxyObject ;
			FXMLLoader loader = new  FXMLLoader(getClass().getResource("/ui/ProxySet.fxml"));			
			
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
			
			proxyObject.getOkBt().addEventHandler(MouseEvent.MOUSE_CLICKED, (e1)->{
					
				 Platform.runLater(new Runnable() {
			            @Override
			            public void run() {
			            	
						 Notifications.create()
						 .title("Proxy Set")
						 .text("You have successfully set your system proxy.")
						 .hideAfter(Duration.seconds(3))
						 .showInformation();
						 
			            }
				 });          
						
			primaryStage.close();
				
			});
			//ob.getStage().show();
		});
		//Start the userTreeView from herre
	}
	
	
	public static Stage getLoginSignInStage() {
		return loginSignInStage;
	}
	public void setLoginSignInStage(Stage loginSignInStage) {
		SettingController.loginSignInStage = loginSignInStage;
	}

}
