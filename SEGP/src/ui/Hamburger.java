package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Hamburger {
	
	public JFXHamburger  getHamburger(JFXHamburger hamburger, BorderPane borderpane,JFXTabPane settingTabPane){
		
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
	/*	setting.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{
			
			Tab tab = new Tab();
			BorderPane settingBorderPane = new BorderPane();
			Setting ob = new Setting();			
			ob.getSettingView(tab, settingBorderPane);

			final ObservableList<Tab> tabs = settingTabPane.getTabs();
			
			//System.out.println("Now Size"+tabs.size());
			tabs.add(tabs.size()+1,tab);
			
		});*/
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
		JFXDrawer rightDrawer = new JFXDrawer();
		rightDrawer.setDirection(DrawerDirection.RIGHT);		
		rightDrawer.setDefaultDrawerSize(40);
		rightDrawer.setSidePane(gridPane);
		rightDrawer.setOverLayVisible(false);
		rightDrawer.setResizableOnDrag(true);
		
		 
		 //--------------------------------------------------------Hamburger------------------------------------------
		 
		// Hamburger listner setRate 1 is active state and -1 is normal state in hamburger
		
		hamburger.setMinWidth(10);
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			borderpane.setRight(drawersStack);
			//drawersStack.toggle(rightDrawer);
			drawersStack.toggle(rightDrawer);
		});
		
		return hamburger;

			
	}
	
}
