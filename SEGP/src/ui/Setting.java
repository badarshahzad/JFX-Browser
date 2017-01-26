package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Setting {


	
	public Tab getSettingView(Tab settingTab,BorderPane borderpane){
		
		JFXButton setting = new JFXButton("Setting");setting.setMinWidth(100);setting.setMinHeight(50);

		//home.setButtonType(ButtonType.RAISED);
		//home.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton history = new JFXButton("History");history.setMinWidth(100);history.setMinHeight(50);
		//history.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		GridPane  gridPane = new GridPane();
		gridPane.add(setting,0,0);
		gridPane.add(history,0,1);
		
		 //------------------------------------------------------Right----DrawerStack-------------------------------- 
		
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		
		JFXDrawer leftDrawer = new JFXDrawer();
		
		leftDrawer.setDirection(DrawerDirection.LEFT);		
		leftDrawer.setDefaultDrawerSize(80);
		leftDrawer.setSidePane(gridPane);
		//leftDrawer.setOverLayVisible(false);
		leftDrawer.setResizableOnDrag(true);
		
		 borderpane.setLeft(drawersStack);
		 drawersStack.toggle(leftDrawer);
		 
		 settingTab.setContent(borderpane);

		 return settingTab;
	}
	
}
