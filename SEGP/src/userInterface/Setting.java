package userInterface;

import java.awt.Panel;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		 changeProxyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			 System.out.println("Yes!");
		 });
	}

}
