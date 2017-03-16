	package userInterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author SEGP-group-3
 *
 */
/**
 * @author SEGP-Group3
 *
 */
public class Hamburger {

	public MenuView menuView = new MenuView();
<<<<<<< HEAD
	public HamburgerSlideCloseTransition transition;
	public JFXDrawersStack drawersStack = new JFXDrawersStack();
	public JFXDrawer rightDrawer = new JFXDrawer();
	
	/**
	 * @param hamburger method requires JFXHamburger object
	 * @param borderpane to add hamburger at right side of the panel
	 * @param settingTabPane pane to set browser's settings.
	 * @return javaFx hamburger object with home,history,downloads,bookmarks,saveAsPdf,settings buttons and auto transitions.
	 */
	
	public JFXHamburger getHamburger(JFXHamburger hamburger, BorderPane borderpane, TabPane settingTabPane) {
		
		transition = new HamburgerSlideCloseTransition(hamburger);
		JFXButton home = new JFXButton();home.setMinSize(30, 40);home.setGraphic(new ImageView(new Image("/home1.png")));// home.setButtonType(ButtonType.RAISED);// home.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		JFXButton history = new JFXButton();history.setMinSize(30, 40);history.setGraphic(new ImageView(new Image("/history.png")));
		JFXButton downloads = new JFXButton();downloads.setMinSize(30, 40);downloads.setGraphic(new ImageView(new Image("/downloads.png")));
		JFXButton bookmarks = new JFXButton();bookmarks.setMinSize(30, 40);bookmarks.setGraphic(new ImageView(new Image("/bookMarks.png")));
		JFXButton saveAsPdf = new JFXButton();saveAsPdf.setMinSize(30, 40);saveAsPdf.setGraphic(new ImageView(new Image("/pdf.png")));
		JFXButton setting = new JFXButton();setting.setMinSize(30, 40);setting.setGraphic(new ImageView(new Image("/setting.png")));
		
		//Tooltips Referance: menus https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Tooltip.html
		home.setTooltip(new Tooltip("Home"));
=======
	public  JFXDrawersStack drawersStack = new JFXDrawersStack();
	public  JFXDrawer rightDrawer = new JFXDrawer();
	JFXButton home = new JFXButton();
	JFXButton history = new JFXButton();
	JFXButton downloads = new JFXButton();
	JFXButton bookmarks = new JFXButton();
	JFXButton saveAsPdf = new JFXButton();
	JFXButton setting = new JFXButton();
	
	public  JFXHamburger getHamburger(JFXHamburger hamburger, BorderPane borderpane, TabPane settingTabPane) {

		history.setMinSize(48, 48);
		history.setGraphic(new ImageView(new Image("/history.png")));
>>>>>>> 9aea2a2650906addf4f66007e806c19f082ee5d9
		history.setTooltip(new Tooltip("History"));
		
		downloads.setMinSize(48, 48);
		downloads.setGraphic(new ImageView(new Image("/downloads.png")));
		downloads.setTooltip(new Tooltip("Downloads"));
		
		bookmarks.setMinSize(48, 48);
		bookmarks.setGraphic(new ImageView(new Image("/bookmarks.png")));
		bookmarks.setTooltip(new Tooltip("Bookmarks"));
		
		saveAsPdf.setMinSize(48, 48);
		saveAsPdf.setGraphic(new ImageView(new Image("/pdf.png")));
		saveAsPdf.setTooltip(new Tooltip("Save As PDF"));
	
		setting.setMinSize(48, 48);
		setting.setGraphic(new ImageView(new Image("/setting.png")));
		setting.setTooltip(new Tooltip("Setting"));

		// ---------Drawer-Menus---------
		/*
		 * Below is the menu view list that will appear from right side when
		 * clicked the hamburger.
		 */
		
	
		VBox vbox = new VBox();
		vbox.getChildren().addAll(history,downloads,bookmarks,saveAsPdf,setting);
		vbox.setSpacing(25);
		vbox.setId("righDrawerVbox");
		
		
		
		// ---------Right----DrawerStack----Add Drawer pane-------
		/*
		 * Drawerstack is container for drawer. We set the place of drawer is
		 * right whenever user clicked the hamburger it will appear from right
		 * side.Its defulte size 40 and we placed the --Draw-Menus-- in this
		 * drawer.
		 */

		rightDrawer.setDirection(DrawerDirection.RIGHT);
		rightDrawer.setDefaultDrawerSize(80);
		rightDrawer.setSidePane(vbox);
		rightDrawer.setOpacity(0.5);
				
		// -----------Hamburger-----------------

		borderpane.setRight(drawersStack);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			showHamburgerPane();
		});

		// ---------Menview-Class method ----menuView--------------
		/*
		 * We are just sending all listener to another class as in this way can
		 * easily manage the listener Menu view. The purpose whenever one menu
		 * is clicked then this menView method send action event to MenuView
		 * Class method menuView.
		 */
		menuView.setMenuViewListener(history, downloads, bookmarks, saveAsPdf, setting, settingTabPane,
				 drawersStack, rightDrawer);
		// setting.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		setHistoryBtn(history);
		return hamburger;
	}

<<<<<<< HEAD
	JFXButton historyBtn;
	/**
	 * @param b1 setter for history button
	 */
	public void setHistoryBtn(JFXButton b1){
		this.historyBtn = b1;
	}
	/**
	 * @return getter for history button
	 */
	
	public JFXButton getHistory(){
		return historyBtn;
=======
	public  void showHamburgerPane() {
		drawersStack.toggle(rightDrawer);
	}

	public void hideHamburgerPane() {

		drawersStack.toggle(rightDrawer, false);

	}


	public void setHistoryBtn(JFXButton history) {
		this.history = history;
	}

	public JFXButton getHistory() {
		return history;
>>>>>>> 9aea2a2650906addf4f66007e806c19f082ee5d9
	}
}
