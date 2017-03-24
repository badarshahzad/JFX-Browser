package userInterface;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import bookmarks.BookMarks;
import controllers.HistoryController;
import controllers.SettingController;

import htmlToPdf.HTMLtoPDF;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import main.Renderer;

public class MenuView {

	/*
	 * Below we are going to use different listener to learn the taste of
	 * different listners used in java,javafx and for jfoenix librarys.
	 * 
	 */

	private Tab tab = new Tab();
	private BorderPane settingBorderPane = new BorderPane();
	private JFXDrawersStack drawersStack;
	private JFXDrawer rightDrawer;
	//Hamburger object = new Hamburger();

	public void setMenuViewListener( JFXButton history, JFXButton downloads, JFXButton bookmarks,
			JFXButton saveAsPdf, JFXButton setting, TabPane tabPane,
			JFXDrawersStack drawersStack, JFXDrawer rightDrawer) {

		
		this.drawersStack = drawersStack;
		this.rightDrawer = rightDrawer;

		final ObservableList<Tab> tabs = tabPane.getTabs();
		SingleSelectionModel<Tab> selectedTab = tabPane.getSelectionModel();
		
		// -------------------------------------------------------Historylistener-------------------------------------------------------
		history.setOnAction((ActionEvent) -> {

			System.out.println("History");

			// tab name and id for accessing
			tab.setText("History");
			tab.setId("history");

			HistoryController ob = new HistoryController();
			
			tab = ob.getHistoryView(tab, settingBorderPane);
			System.out.println("Size before history added"+tabs.size());
			tabs.add(tabs.size() - 1, tab);

			// The below is just select the current tab
			selectedTab.select(tab);

			if (tabs.get(tabs.size() - 2).getId() == ("history")) {
				selectedTab.select(tabs.size() - 2);
				return;
			}

		});

		// -------------------------------------------------------Downloads
		// listener-----------------------------------------------------
		downloads.setOnAction((e) -> {

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			/*
			 * Thread th = new Thread(new Runnable() {
			 * 
			 * @Override public void run() { // TODO Auto-generated method stub
			 * // download.startDownload();
			 * 
			 * MainDownload object = new MainDownload(); //new
			 * MainDownload().startDownload(""); //object.startDownload(
			 * "https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ"
			 * );new MainDownload().startDownload(
			 * "https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ"
			 * ); object.startDownload(getUrl()); } }); th.start();
			 */

			System.out.println("Downloads");

			// tab.setText("Downloads");
			// tab.setId("downloads");
		});

		// -------------------------------------------------------Bookmarks
		// listener-----------------------------------------------------
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();

				System.out.println("Bookmarks");
				tab.setText("Bookmarks");
				tab.setId("bookmarks");
				BookMarks ob = new BookMarks();
				
				tab = ob.getBookmarkView(tab, settingBorderPane);
				System.out.println("Size before history added"+tabs.size());
				tabs.add(tabs.size() - 1, tab);

				// The below is just select the current tab
				selectedTab.select(tab);

				if (tabs.get(tabs.size() - 2).getId() == ("Bookmarks")) {
					selectedTab.select(tabs.size() - 2);
					return;
				}

			
			}
		});

		// -------------------------------------------------------SaveAsPdf
		// listener-----------------------------------------------------
		saveAsPdf.addEventHandler(MouseEvent.MOUSE_CLICKED, (ActionEvent) -> {

			HTMLtoPDF object = new HTMLtoPDF();
			object.convertHtmlToPdf();
			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			Thread th = new Thread(new Runnable() {
				public void run() {
					

				}
			});
			th.start();

			System.out.println("Save As PDF");
			// tab.setText("Save As Pdf");
			// tab.setId("saveAsPdf");

		});

		// -------------------------------------------------------Setting
		// listener-------------------------------------------------------
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();

			System.out.println("Setting");

			// tab name and id for accessing
			tab.setText("Setting");
			tab.setId("setting");
			/*
			 * Here we developed a tab and its borderpane for setting we made
			 * setting class that design the layout of setting then a single tab
			 * for all menus set a Tab and only in one tab all menu can be seen
			 * Is this cool? or not give me constructive feedback! Setting is
			 * class that will only desinge the layout of setting tab and we are
			 * just calling its method getSettingView and give two arguments
			 * that is tab and setting pane
			 */

			SettingController ob = new SettingController();
			tab = ob.getSettingView(tab, settingBorderPane);

			// System.out.println(tabs.get(tabs.size()-2).getId());
			// This is just selecitng the just now opened tab
			tabs.add(tabs.size() - 1, tab);

			// System.out.println(tab.getId());
			// System.out.println(tabs.size());

			// The below is just select the current tab
			selectedTab.select(tab);

			if (tabs.get(tabs.size() - 2).getId() == ("setting")) {
				System.out.println("Adfa");
				selectedTab.select(tabs.size() - 2);
				return;
			}
		});
	}

	
	
	public void onClickHideHamburger() {
			
			// TODO Auto-generated method stub
			//transition.setRate(transition.getRate() * -1);
			//transition.play();
			drawersStack.toggle(rightDrawer);	
	}

}
