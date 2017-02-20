package userInterface;

import java.awt.Desktop.Action;
import java.util.Scanner;

import javax.swing.text.html.HTML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import downloader.MainDownload;
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
	private HamburgerSlideCloseTransition transition;
	private JFXDrawersStack drawersStack;
	private JFXDrawer rightDrawer;

	public void setMenuViewListener(JFXButton home, JFXButton history, JFXButton downloads, JFXButton bookmarks,
			JFXButton saveAsPdf, JFXButton setting, TabPane tabPane, HamburgerSlideCloseTransition transition,
			JFXDrawersStack drawersStack, JFXDrawer rightDrawer) {

		this.transition = transition;
		this.drawersStack = drawersStack;
		this.rightDrawer = rightDrawer;

		final ObservableList<Tab> tabs = tabPane.getTabs();
		SingleSelectionModel<Tab> selectedTab = tabPane.getSelectionModel();

		// -------------------------------------------------------Home
		// listener----------------------------------------------------------
		home.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
				System.out.println("Home");
				tab.setText("Home");
				tab.setId("home");
				tab.setContent(null);
				tab.setContent(new JFXButton("kaka"));
			}
		});

		
		
		//-------------------------------------------------------History listener-------------------------------------------------------
		history.setOnAction((ActionEvent)->{
			
				//When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
			
				System.out.println("History");

				//tab name and id for accessing
				tab.setText("History");
				tab.setId("history");
				
				if(tabs.get(tabs.size()-2).getId()!=("history")){
				History ob = new History();
				System.out.println("@BorderPane is set@");
				tab = ob.getHistoryView(tab, settingBorderPane);
				tabs.add(tabs.size()-1,tab);

				//System.out.println(tab.getId());
				//System.out.println(tabs.size());
				
				//The below is just select the current tab 
				selectedTab.select(tab);
				}
				
				if(tabs.get(tabs.size()-2).getId()==("history")){
					System.out.println("Adfa");
					selectedTab.select(tabs.size()-2);
					return;
				}
				


		// -------------------------------------------------------Historylistener-------------------------------------------------------
		history.setOnAction((e) -> {

			Renderer b = new Renderer();
			WebHistory History = b.webEngine.getHistory();
			ObservableList<Entry> list = History.getEntries();

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			// tab name and id for accessing
			tab.setText("History");
			tab.setId("History");

			// New History Tab borderpane will be add
			History ob = new History();
			tab = ob.getHistoryView(tab, settingBorderPane);
			tabs.add(tabs.size() - 1, tab);

			// The below is just select the current tab
			selectedTab.select(tab);

			// In case History tab already open
			if (tabs.get(tabs.size() - 2).getId() == ("History")) {
				selectedTab.select(tabs.size() - 2);
				return;
			}
		});

		// -------------------------------------------------------Downloads
		// listener-----------------------------------------------------
		downloads.setOnAction((e) -> {

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();

			
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
//					download.startDownload();
					
				MainDownload object = new MainDownload();
				object.startDownload("hello");
//					object.DownloaderStart();
				}
			});
			th.start();
			
			//System.out.println("Downloads");
			tab.setText("Downloads");
			tab.setId("downloads");

		});

		// -------------------------------------------------------Bookmarks
		// listener-----------------------------------------------------
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
				tab.setText("Bookmarks");
				tab.setId("bookmarks");

			}
		});

		// -------------------------------------------------------SaveAsPdf
		// listener-----------------------------------------------------
		saveAsPdf.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			Thread th = new Thread(new Runnable() {
				public void run() {
					HTMLtoPDF object = new HTMLtoPDF();
					object.convertHtmlToPdf();
				}
			});
			th.start();
		});

		// -------------------------------------------------------Setting
		// listener-------------------------------------------------------
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			// When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			// tab name and id for accessing
			tab.setText("Setting");
			tab.setId("Setting");

			// Setting is class to design the layout of setting tab
			Setting ob = new Setting();
			tab = ob.getSettingView(tab, settingBorderPane);
			// This is just selecitng the just now opened tab
			tabs.add(tabs.size() - 1, tab);
			// The below is just select the current tab
			selectedTab.select(tab);

			if (tabs.get(tabs.size() - 2).getId() == ("Setting")) {
				selectedTab.select(tabs.size() - 2);
				return;
			}
		});
	}

	public void onClickHideHamburger() {
		transition.setRate(transition.getRate() * -1);
		transition.play();
		drawersStack.toggle(rightDrawer);
	}

}
