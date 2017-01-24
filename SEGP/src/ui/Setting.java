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
		
		
		
		JFXButton home = new JFXButton();home.setMinWidth(30);home.setMinHeight(36);
		home.setGraphic(new ImageView(new Image("/home1.png")));
		//home.setButtonType(ButtonType.RAISED);
		//home.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
	
		
		JFXButton history = new JFXButton();history.setMinWidth(30);history.setMinHeight(36);
		history.setGraphic(new ImageView(new Image("/history.png")));
		//history.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton downloads = new JFXButton();downloads.setMinWidth(30);downloads.setMinHeight(36);
		downloads.setGraphic(new ImageView(new Image("/downloads.png")));
		//downloads.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton bookmarks = new JFXButton();bookmarks.setMinWidth(30);bookmarks.setMinHeight(36);
		bookmarks.setGraphic(new ImageView(new Image("/bookMarks.png")));
		//bookmarks.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton saveAsPdf = new JFXButton();saveAsPdf.setMinWidth(30);saveAsPdf.setMinHeight(36);
		saveAsPdf.setGraphic(new ImageView(new Image("/pdf.png")));
		//saveAsPdf.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton setting = new JFXButton();setting.setMinWidth(30);setting.setMinHeight(36);
		setting.setGraphic(new ImageView(new Image("/setting.png")));
		
		//Setting Listner
		
		//setting.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		
		GridPane  gridPane = new GridPane();
		gridPane.add(home,0,0);
		gridPane.add(history,0,1);
		gridPane.add(downloads,0,2);
		gridPane.add(bookmarks,0,3);
		gridPane.add(saveAsPdf,0,4);
		gridPane.add(setting,0,5);
		
		 //------------------------------------------------------Right----DrawerStack-------------------------------- 
		
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		JFXDrawer leftDrawer = new JFXDrawer();
		leftDrawer.setDirection(DrawerDirection.LEFT);		
		leftDrawer.setDefaultDrawerSize(80);
		leftDrawer.setSidePane(gridPane);
		leftDrawer.setOverLayVisible(false);
		leftDrawer.setResizableOnDrag(true);
		
		 borderpane.setLeft(drawersStack);
		 drawersStack.toggle(leftDrawer);
		 
		 settingTab.setContent(borderpane);
		 //--------------------------------------------------------Hamburger------------------------------------------
		 
		// Hamburger listner setRate 1 is active state and -1 is normal state in hamburger
		
		
		/*setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			
			rootBorder.setLeft(drawersStack);
			//drawersStack.toggle(rightDrawer);
			drawersStack.toggle(leftDrawer);
		});
		//
*/		
		//setting_Pane.setCenter(gridpane);
		/*
		//setting left
		VBox vbox = new VBox();
		vbox.setMaxSize(100, 800);
		JFXButton b_list1 = new JFXButton("Btton1");
		JFXButton b_list2= new JFXButton("Btton2");
		JFXButton b_list3= new JFXButton("Btton3");
		JFXButton b_list4= new JFXButton("Btton4");
		vbox.getChildren().addAll(b_list1,b_list2,b_list3,b_list4);
		*/
/*
		//setting center
		JFXButton b1 = new JFXButton("Btton");
		GridPane gridpane = new GridPane();
		gridpane.add(b1, 0, 0);
		setting_Pane.setCenter(gridpane);
		setting_Pane.setLeft(vbox);
		settingTab.setContent(setting_Pane);
		
		
*/		
		
		
		return settingTab;
	}

/*	@Override
	public void start(Stage primaryStage) throws Exception {
	//setting_Pane.setCenter(new JFXButton("adfa"));
	//setting.setContent(setting_Pane);
		
	s1.setContent(new JFXButton("ADfadf"));
	s2.setContent(new JFXButton("ADfadf"));
	s3.setContent(new JFXButton("ADfadf"));
	
	Tab newTab = new Tab();
	JFXTabPane tabpane = new JFXTabPane();
	tabpane.getTabs().addAll(settingView(newTab));
	
	
	BorderPane root = new BorderPane ();
	//root.setCenter(tabpane);
	root.setCenter(tabpane);
	root.setLeft(setting_Pane);
	
	Scene scene = new Scene(root,800,600);
	primaryStage.setScene(scene);
	primaryStage.show();
		
	}
	
	public static void main(String [] args){
		launch(args);
	}*/
	
	

	
	
}
