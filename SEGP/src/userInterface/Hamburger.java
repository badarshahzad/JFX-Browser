	package userInterface;

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
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author SEGP-group-3
 *
 */
public class Hamburger {

	public MenuView menuView = new MenuView();
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
		history.setTooltip(new Tooltip("History"));
		downloads.setTooltip(new Tooltip("Downloads"));
		bookmarks.setTooltip(new Tooltip("Bookmarks"));
		saveAsPdf.setTooltip(new Tooltip("Save As PDF"));
		setting.setTooltip(new Tooltip("Setting"));
		
		// ------------------------------------------------------Drawer-Menus----------------------------------------
		/*
		 * Below is the menu view list that will appear from right side when
		 * clicked the hamburger.
		 */
		
		GridPane gridPane = new GridPane();
		gridPane.add(home, 0, 0);
		gridPane.add(history, 0, 1);
		gridPane.add(downloads, 0, 2);
		gridPane.add(bookmarks, 0, 3);
		gridPane.add(saveAsPdf, 0, 4);
		gridPane.add(setting, 0, 5);
		
		// ----------------------------------------------Right----DrawerStack----Add Drawer pane----------------------------
				/*
				 * Drawerstack is container for drawer. We set the place of drawer is
				 * right whenever user clicked the hamburger it will appear from right
				 * side.Its defulte size 40 and we placed the --Draw-Menus-- in this
				 * drawer.
				 */
				
				rightDrawer.setDirection(DrawerDirection.RIGHT);
				rightDrawer.setDefaultDrawerSize(40);
				rightDrawer.setSidePane(gridPane);
				// rightDrawer.setOverLayVisible(false);
				// rightDrawer.setResizableOnDrag(true);
				
		// --------------------------------------------------------Hamburger------------------------------------------------
						/*
						 * Hamburger listener setRate 1 is active state when user click its
						 * become -1 that is normal state in hamburger. Below is the Hmaburger
						 * slide close transition. Hamburger in jfoenix have 4 transition state
						 * and we use the slide close trasition and We placed the Hamburger in
						 * its parameter
						 */
						//HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
						transition.setRate(-1);
						/*
						 * Above setRate -1 is detail mention above passage. This is the
						 * Listener for Hamburger When user clicked the hamburger first its
						 * change is actual shape and animation will play. Moreover, we set the
						 * right border pane of Tab pane is drawerstack. (Drawer stack discussed
						 * above) After that the drawstack method toggle call "A toggle button
						 * allows the user to change a setting between two states". Like on and
						 * off
						 */
						
						
						hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							transition.setRate(transition.getRate() * -1);
							transition.play();
							borderpane.setRight(drawersStack);
							// The purposes of toggle is to bring the drawer once it click and
							// hide when it click again!
							drawersStack.toggle(rightDrawer);
						});
						
		// -----------------------------------------------------Menview-Class method ----menuView----------------------------------------------
		/*
		 * We are just sending all listener to another class as in this way can
		 * easily manage the listener Menu view. The purpose whenever one menu
		 * is clicked then this menView method send action event to MenuView
		 * Class method menuView.
		 */
		menuView.setMenuViewListener(home, history, downloads, bookmarks, saveAsPdf, setting, settingTabPane,transition,
				drawersStack,rightDrawer);
		// setting.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		setHistoryBtn(history);
		return hamburger;
	}

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
	}
}
