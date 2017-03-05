package userInterface;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import controllers.LoginController;
import controllers.SignUpController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Setting implements Initializable {

	public Tab getSettingView(Tab settingTab, BorderPane borderpane) {

		JFXButton setting = new JFXButton("Setting");
		setting.setMinSize(100, 50);
		JFXButton history = new JFXButton("History");
		history.setMinSize(100, 50);

		/*
		 * Add two buttons in gridpane that will be put in
		 * drawer->drawerstack(container) -> left side of border to come ount
		 * whenever user click the setting button
		 */
		GridPane gridPane = new GridPane();
		gridPane.add(setting, 0, 0);
		gridPane.add(history, 0, 1);

		// ------------------------------------------------------Right----DrawerStack--------------------------------

		// Alredy detialed mention in Hamburger class about JFx DrawerStack and
		// JFXDrawer
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		JFXDrawer leftDrawer = new JFXDrawer();
		leftDrawer.setDirection(DrawerDirection.LEFT);
		leftDrawer.setDefaultDrawerSize(80);
		leftDrawer.setSidePane(gridPane);
		leftDrawer.setResizableOnDrag(true);

		// Setting the left side of Borderpane drawerstack container
		borderpane.setLeft(drawersStack);
		System.out.println("Setting fxml is ready ");
		try {
			// ScrollPane scrollPane = new ScrollPane();
			System.out.println("Setting fxml is ready to set");
			borderpane.setCenter(FXMLLoader.load(getClass().getResource("Setting.fxml")));
		} catch (Exception e1) {
			System.out.println("Exception: Setting fxml is not set");
			System.out.println("File is not find for setting! " + " \n " + e1);
			
		}

		// borderpane.setCenter(root);
		drawersStack.toggle(leftDrawer);
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
				loader = new  FXMLLoader(getClass().getResource("Login.fxml"));
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
				
				loader = new  FXMLLoader(getClass().getResource("SignUp.fxml"));
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
			ToggleGroup group = new ToggleGroup();
			JFXRadioButton noProxy = new JFXRadioButton("No Proxy");
			noProxy.setToggleGroup(group);
			noProxy.setSelected(true);
			JFXRadioButton useSystemProxySettings = new JFXRadioButton("Use system proxy setting");
			useSystemProxySettings.setToggleGroup(group);

			VBox root = new VBox();
			VBox.setMargin(noProxy, new Insets(5, 5, 5, 5));
			VBox.setMargin(useSystemProxySettings, new Insets(5, 5, 5, 5));
			root.setPadding(new Insets(5, 5, 5, 5));
			root.setSpacing(15);
			root.setMinSize(100, 100);
			root.getChildren().addAll(noProxy, useSystemProxySettings);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setGraphic(root);
			alert.setTitle("Proxy Setting");
			alert.setHeaderText("Configure Proxy to Access Internet");

			Optional<ButtonType> result = alert.showAndWait();
			Properties systemProperties = System.getProperties();
			if (result.get() == ButtonType.OK) {
				if (group.getSelectedToggle().equals(noProxy)) {
					System.out.println("No Proxy");
/*
					// Remove Proxy for Http
					systemProperties.setProperty("http.proxyHost", "");
					systemProperties.setProperty("http.proxyPort", "");

					// Remove Proxy for Https
					systemProperties.setProperty("https.proxyHost", "");
					systemProperties.setProperty("https.proxyPort", "");*/

				}
				if (group.getSelectedToggle().equals(useSystemProxySettings)) {
					System.out.println("Use system proxy");
/*
					// Set Proxy for Http
					systemProperties.setProperty("http.proxyHost", "172.16.0.2");
					systemProperties.setProperty("http.proxyPort", "8080");

					// Set Proxy for Https
					systemProperties.setProperty("https.proxyHost", "172.16.0.2");
					systemProperties.setProperty("https.proxyPort", "8080");*/
				}
				System.out.println("Ok click!");
			} else {
				System.out.println("Cancel Click!");
			}
		});
		//Start the userTreeView from herre
	}
	public static Stage getLoginSignInStage() {
		return loginSignInStage;
	}
	public void setLoginSignInStage(Stage loginSignInStage) {
		Setting.loginSignInStage = loginSignInStage;
	}

}
