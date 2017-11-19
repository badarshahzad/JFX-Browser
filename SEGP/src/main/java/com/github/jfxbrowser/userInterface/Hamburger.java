package main.java.com.github.jfxbrowser.userInterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.java.com.github.jfxbrowser.application.Main;

public class Hamburger {
	
	public MenuView menuView = new MenuView();
	public JFXDrawersStack drawersStack = new JFXDrawersStack();
	public JFXDrawer rightDrawer = new JFXDrawer();
	JFXButton home = new JFXButton();
	JFXButton history = new JFXButton();
	JFXButton downloads = new JFXButton();
	JFXButton bookmarks = new JFXButton();
	//JFXButton saveAsPdf = new JFXButton();
	JFXButton setting = new JFXButton();
	
	public JFXHamburger getHamburger(JFXHamburger hamburger , BorderPane borderpane , TabPane settingTabPane) {
		
		history.setMinSize(48, 48);
		history.setGraphic(new ImageView(new Image(Main.IMAGES + "history.png")));
		history.setTooltip(new Tooltip("History"));
		
		downloads.setMinSize(48, 48);
		downloads.setGraphic(new ImageView(new Image(Main.IMAGES + "downloads.png")));
		downloads.setTooltip(new Tooltip("Downloads"));
		
		bookmarks.setMinSize(48, 48);
		bookmarks.setGraphic(new ImageView(new Image(Main.IMAGES + "bookmarks.png")));
		bookmarks.setTooltip(new Tooltip("Bookmarks"));
		
		//saveAsPdf.setMinSize(48, 48);
		//saveAsPdf.setGraphic(new ImageView(new Image(Main.IMAGES+"pdf.png")));
		//saveAsPdf.setTooltip(new Tooltip("Save As PDF"));
		
		setting.setMinSize(48, 48);
		setting.setGraphic(new ImageView(new Image(Main.IMAGES + "setting.png")));
		setting.setTooltip(new Tooltip("Setting"));
		
		// ---------Drawer-Menus---------
		/*
		 * Below is the menu view list that will appear from right side when
		 * clicked the hamburger.
		 */
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(history, downloads, bookmarks, setting);
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
		 * easily manager the listener Menu view. The purpose whenever one menu
		 * is clicked then this menView method send action event to MenuView
		 * Class method menuView.
		 */
		menuView.setMenuViewListener(history, downloads, bookmarks, setting, settingTabPane, drawersStack, rightDrawer);
		// setting.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		setHistoryBtn(history);
		return hamburger;
	}
	
	public void showHamburgerPane() {
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
	}
}
