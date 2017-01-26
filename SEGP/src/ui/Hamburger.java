package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Hamburger {
	
	
	public MenuView menuView = new MenuView();
	
	public JFXHamburger  getHamburger(JFXHamburger hamburger, BorderPane borderpane,TabPane settingTabPane){
		
		JFXButton home = new JFXButton();home.setMinWidth(30);home.setMinHeight(36);home.setGraphic(new ImageView(new Image("/home1.png")));
		//home.setButtonType(ButtonType.RAISED);
		//home.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton history = new JFXButton();history.setMinWidth(30);history.setMinHeight(36);history.setGraphic(new ImageView(new Image("/history.png")));
		//history.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton downloads = new JFXButton();downloads.setMinWidth(30);downloads.setMinHeight(36);downloads.setGraphic(new ImageView(new Image("/downloads.png")));
		//downloads.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton bookmarks = new JFXButton();bookmarks.setMinWidth(30);bookmarks.setMinHeight(36);	bookmarks.setGraphic(new ImageView(new Image("/bookMarks.png")));
		//bookmarks.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton saveAsPdf = new JFXButton();saveAsPdf.setMinWidth(30);saveAsPdf.setMinHeight(36);saveAsPdf.setGraphic(new ImageView(new Image("/pdf.png")));
		//saveAsPdf.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton setting = new JFXButton();setting.setMinWidth(30);setting.setMinHeight(36);setting.setGraphic(new ImageView(new Image("/setting.png")));
		setting.setId("Setting");
		
		//-------------------------Below is the listener class method----------------------
		
		//menuView.setMenuViewListener(home,history,downloads,bookmarks,saveAsPdf,setting);
		
		
		//Setting Listner
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			
			final ObservableList<Tab> tabs = settingTabPane.getTabs();
			SingleSelectionModel<Tab> selectedTab = settingTabPane.getSelectionModel();
			
			Tab tab = new Tab("Setting");
			
			if(tabs.get(tabs.size()-2).getId()!=("Setting")){
					
			//System.out.println("Yes!");
			/*Here I developing a tab and its borderpane for setting we made setting class that 
			 *		design the layout of setting then a single tab for all menus set a Tab and only in one
			 *		tab all menu can be seen Is this cool? or not give me constructive feedback!*/
			//-----------------------------------------Bug of New Tab open then click setting and if index is not less than -2 then 
			//-----------------------------------------it will not find setting and again same setting tab will open ! issue can be resolved but need time!
			
			tab.setId("Setting");
			
			BorderPane settingBorderPane = new BorderPane();
			Setting ob = new Setting();		
			tab = ob.getSettingView(tab, settingBorderPane);
			//setMenuTabView(tab);
			
			
			//System.out.println(tabs.get(tabs.size()-2).getId());
			//This is just selecitng the just now opened tab
			tabs.add(tabs.size()-1,tab);
			System.out.println(tab.getId());
			System.out.println(tabs.size());
			
			selectedTab.select(tab);
			
			}
			if(tabs.get(tabs.size()-2).getId()==("Setting")){
				System.out.println("Adfa");
				selectedTab.select(tabs.size()-2);
				return;
			}
		
			//setMenuTabView(tab);
			
		});
		
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
		//rightDrawer.setOverLayVisible(false);
		//rightDrawer.setResizableOnDrag(true);
		
		 
		 //--------------------------------------------------------Hamburger------------------------------------------
		 
		// Hamburger listner setRate 1 is active state and -1 is normal state in hamburger
		
		hamburger.setMinWidth(10);
		
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			
			borderpane.setRight(drawersStack);
			
			
			//The purposes of toggle is to bring the drawer once it click and hide when it click again!
			drawersStack.toggle(rightDrawer);
		});
		
		return hamburger;

			
	}
	
}
