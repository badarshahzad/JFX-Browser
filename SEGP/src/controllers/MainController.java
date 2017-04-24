/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.IOException;
import java.net.URL;

import java.util.EventListener;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;

import javafx.scene.input.KeyCode;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import userInterface.Hamburger;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 *
 * @author Segp-Group 3
 */
public class MainController implements Initializable {

	@FXML
	private BorderPane rootBorderPane;
	public Hamburger ham = new Hamburger();
	public VBox drawerPane = new VBox();

	public static TabPane tabPane = new TabPane();

	private Tab firstTab = new Tab("First Tab");

	private Tab addNewTab = new Tab("+");

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// ------All opens tabs should be closed so below line is for just
		// closing tabs
		addNewTab.setClosable(false);

		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		// ------tabPane.setFocusTraversable(false);

		try {
			// -----here below adding page title of tab
			firstTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Tab.fxml")));
			
			

		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		tabPane.getTabs().addAll(firstTab, addNewTab);
		rootBorderPane.setCenter(tabPane);

		getTabPaneView(tabPane, addNewTab);
		tabPaneChangeLiten(tabPane);

		//-------------------Key codes to for browser----
		EventListener listener = new EventListener() {
			public void handleEvent(Event event) {
				if (event.getEventType().equals(KeyCode.ALT) || event.getEventType().equals(KeyCode.BACK_SPACE)) {
					System.out.println("yes alt");
				}
			}
		};
		
		
	
	}// end intializer method
	
	// ---set method for TabPane---
	public void setTabPane(TabPane tabPane) {
		MainController.tabPane = tabPane;

		//System.out.println("Setter title:"+TabController.getTitle());
		getTabPaneView(tabPane, addNewTab);
		tabPaneChangeLiten(tabPane);

		// -------------------Key codes to for browser----
		/*
		 * EventListener listener = new EventListener() {
		 * 
		 * @SuppressWarnings("unused") public void handleEvent(Event event) { if
		 * (event.getEventType().equals(KeyCode.ALT) ||
		 * event.getEventType().equals(KeyCode.BACK_SPACE)) {
		 * System.out.println("yes alt"); } } };
		 */

	}// end intializer method

	// ---set method for TabPane---


	// ---get method for TabPane---
	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getAddNewTab() {
		return addNewTab;
	}

	public void setAddNewTab(Tab addNewTab) {
		this.addNewTab = addNewTab;
	}

	public void tabPaneChangeLiten(TabPane tabpane) {
		tabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {
				ham.hideHamburgerPane();
			}
		});
	}

	public TabPane getTabPaneView(TabPane tabpane, Tab addNewTab) {
		tabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {

				//Closeing tab if first index tab close and size will be the 2
				//https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/package-summary.html
				
				tabPane.getTabs().get(0).setOnCloseRequest(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						if(tabPane.getTabs().size()==2)
							Platform.exit();
					}
					
				});
/*
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {

				// Closeing tab if first index tab close and size will be the 2
				// https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/package-summary.html

				// -- to close a browser when last tab will be close
				if (tabPane.getTabs().size() == 1) {
					// Platform.exit();
					System.exit(0);
				}
*/
				if (newSelectedTab == addNewTab) {
					// ---------------New tab is created --------------------
					creatNewTab(tabpane, addNewTab);
				}
			}
		});
		return tabpane;
	}

	
	// end class

	public void creatNewTab(TabPane tabpane, Tab addNewTab) {
		Tab tab = new Tab("New tab");
		try {
			tab.setContent(FXMLLoader.load(getClass().getResource("/ui/Tab.fxml")));
		} catch (IOException e) {
			System.out.println("Exception: New tab click but not working in TabPaneView Class");
			e.printStackTrace();
		}

		tab.getStyleClass().addAll("tab-pane");
		final ObservableList<Tab> tabs = tabpane.getTabs();
		tabs.add(tabs.size() - 1, tab);

		SingleSelectionModel<Tab> selectedTab = tabpane.getSelectionModel();
		selectedTab.select(tab);
	}

	// end class
}