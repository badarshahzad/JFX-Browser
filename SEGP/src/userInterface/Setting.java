package userInterface;

import java.awt.Panel;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Setting implements Initializable{
	

	public Tab getSettingView(Tab settingTab, BorderPane borderpane) {

		
		JFXButton setting = new JFXButton("Setting");setting.setMinSize(100, 50);
		JFXButton history = new JFXButton("History");history.setMinSize(100, 50);

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

		//Setting the left side of Borderpane drawerstack container
		borderpane.setLeft(drawersStack);
		
	
		
		
		try{
				//ScrollPane scrollPane = new ScrollPane();
				
				borderpane.setCenter(FXMLLoader.load(getClass().getResource("Setting.fxml")));
			}catch(Exception e1){
				System.out.println("File is not find for setting! "+ " \n "+e1);
			}
			
		 
		//borderpane.setCenter(root);
		drawersStack.toggle(leftDrawer);
		settingTab.setContent(borderpane);
		
		return settingTab;
	}
	
	//tab.getStyleClass().addAll("tab-pane");
	 @FXML private JFXButton signInBtn;
	 @FXML private JFXButton disconnectAccountBtn;
	 @FXML private Label userAccountNameLbl;
	 
	 @FXML private JFXTreeTableView<?> usersTreeTabelView;
	 @FXML private JFXCheckBox checkPasswordRemember;
	 @FXML private JFXButton managePasswordBtn;
	 @FXML private JFXButton changeProxyBtn;
	 
	 
	 @Override
	public void initialize(URL location, ResourceBundle resources) {
		 Stage primaryStage = new Stage();
		 
		 changeProxyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
		
			 JFXTextField urlTextField = new JFXTextField();
			 JFXButton b1 = new JFXButton("Set Proxy");
			 VBox vbox = new VBox();
			 vbox.getChildren().addAll(urlTextField,b1);
			 
			 Alert alert = new Alert(AlertType.CONFIRMATION);
			 alert.setGraphic(vbox);
		
			 
			 alert.setTitle("Proxy Setting");
			 alert.setHeaderText("Proxy Setting Dialog Confirmation?");
			
			 //alert.setContentText("Are you ok with this text?");
			 
			 Optional<ButtonType> result = alert.showAndWait();
			 if(result.get()==ButtonType.OK){
				 System.out.println("Yes! working");
			 }else{
				 System.out.println("No! working");
			 }
			 
			 
			/* JFXDialog dialog = new JFXDialog();
			 StackPane root = new StackPane();
			 dialog.setTransitionType(DialogTransition.CENTER);
			// dialog.show((StackPane) );
			 
			  * We are going to work on Dialog to pop-up for proxy setting ... Do it now
			  * Example:https://github.com/jfoenixadmin/JFoenix/blob/master/demo/demos/gui/uicomponents/DialogController.java
			  
			 
			 JFXButton b1 = new JFXButton();
			 JFXButton b2 = new JFXButton();
			 
			 
			 
			 
			 urlTextField.setMaxSize(60, 30);
			 //HBox root = new HBox();
			 root.getChildren().add(urlTextField);
			 Scene scene = new Scene(root,400,400);
			 
			 
			 primaryStage.setScene(scene);
			 primaryStage.show();
*/			 
		 });
	}

}
