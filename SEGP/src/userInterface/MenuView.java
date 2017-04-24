package userInterface;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;

import controllers.DownloadController;
import controllers.HistoryController;
import controllers.SettingController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MenuView {

	/*
	 * Below we are going to use different listener to learn the taste of
	 * different listners used in java,javafx and for jfoenix librarys.
	 * 
	 */

	private Tab tab = new Tab();

	private JFXTabPane fxTabpane = new JFXTabPane();
	private Tab historyTab = new Tab("History");

	private Tab downloadsTab = new Tab("Downloads");
	private Tab bookmarksTab = new Tab("Bookmarks");
	private Tab settingTab = new Tab("Setting");

	private BorderPane settingBorderPane = new BorderPane();
	private JFXDrawersStack drawersStack;
	private JFXDrawer rightDrawer;
	// Hamburger object = new Hamburger();

	public void setMenuViewListener(JFXButton history, JFXButton downloads, JFXButton bookmarks, JFXButton setting,
			TabPane tabPane, JFXDrawersStack drawersStack, JFXDrawer rightDrawer) {

		// BorderPane settingTabBPane = new BorderPane();
		// tab.setContent(settingTabBPane);
		try {
			historyTab.setText("History");
			historyTab.setContent(FXMLLoader.load(getClass().getResource("/ui/History.fxml")));

			downloadsTab.setText("Downloads");
			downloadsTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Downloads.fxml")));

			bookmarksTab.setText("Bookmarks");
			// bookmarkTab.setContent(FXMLLoader.load(getClass().getResource("/ui/bookmarks.fxml")));

			settingTab.setText("Setting");
			settingTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Setting.fxml")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Setting tabs fxml files not loading");
		}

		this.drawersStack = drawersStack;
		this.rightDrawer = rightDrawer;

		final ObservableList<Tab> tabs = tabPane.getTabs();
		final ObservableList<Tab> fxTabs = fxTabpane.getTabs();

		SingleSelectionModel<Tab> selectedTab = tabPane.getSelectionModel();
		SingleSelectionModel<Tab> fxSelectedTab = fxTabpane.getSelectionModel();

		
		/*
		 * Here we developed a tab and its borderpane for setting we made
		 * setting class that design the layout of setting then a single tab for
		 * all menus set a Tab and only in one tab all menu can be seen Is this
		 * cool? or not give me constructive feedback! Setting is class that
		 * will only desinge the layout of setting tab and we are just calling
		 * its method getSettingView and give two arguments that is tab and
		 * setting pane
		 */

		//tab.setText("Settig");
		// -------------------------------------------------------Historylistener-------------------------------------------------------
		history.setOnAction((ActionEvent) -> {


			tabs.add(tabs.size() - 1, tab);
			selectedTab.select(tab);

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			tab.setText("History");
			fxSelectedTab.select(fxTabs.get(0));

		});

		// ------------------------------------------------------Downloads
		// listener-----------------------------------------------------
		downloads.setOnAction((e) -> {

			tabs.add(tabs.size() - 1, tab);
			selectedTab.select(tab);

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			tab.setText("Downloads");
			fxSelectedTab.select(fxTabs.get(1));

		});

		// -------------------------------------------------------Bookmarks
		// listener-----------------------------------------------------
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tabs.add(tabs.size() - 1, tab);
				selectedTab.select(tab);

				// When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
				tab.setText("Bookmarks");
				fxSelectedTab.select(fxTabs.get(2));

			}

		});

		// -------------------------------------------------------Setting
		// listener-------------------------------------------------------
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			tabs.add(tabs.size() - 1, tab);
			selectedTab.select(tab);

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();

			tab.setText("Setting");
			fxSelectedTab.select(fxTabs.get(3));

		});
		
		for(int a=0;a<tabs.size();a++){
			if(tabs.get(a).getText().equals("Setting")){
				System.out.println("Setting");
			}
		}

		tab.setContent(fxTabpane);
		fxTabpane.getTabs().addAll(historyTab, downloadsTab, bookmarksTab, settingTab);

	}

	public void onClickHideHamburger() {

		// TODO Auto-generated method stub
		// transition.setRate(transition.getRate() * -1);
		// transition.play();
		drawersStack.toggle(rightDrawer);
	}

}
