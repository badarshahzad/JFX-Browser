/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.MainClass;
import userInterface.Hamburger;

/**
 *
 * @author Segp-Group 3
 */

public class MainController implements Initializable {

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private BorderPane rootBorderPane;

	public Hamburger ham = new Hamburger();
	public VBox drawerPane = new VBox();

	public static TabPane tabPane = new TabPane();

	private static Tab firstTab = new Tab("New Tab");

	private static Tab addNewTab = new Tab("+");

	public void setFirstTab(Tab firstTab) {
		this.firstTab = firstTab;
	}

	public static Tab getFirstTab() {
		return firstTab;
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {


		//addNewTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/newtab.png"))));
		
		// ------All opens tabs should be closed so below line is for just
		// closing tabs
		addNewTab.setClosable(false);
		addNewTab.setId("addNewTab");
		tabPane.setId("tabadded");


		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		// ------tabPane.setFocusTraversable(false);

		try {
			// -----here below adding page title of tab
			firstTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Tab.fxml")));

			// firstTab.setText("Google");

		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		tabPane.getTabs().addAll(firstTab, addNewTab);
		rootBorderPane.setCenter(tabPane);

		getTabPaneView(tabPane, addNewTab);
		tabPaneChangeListener(tabPane);

	}// end intializer method

	// ---set method for TabPane---
	public void setTabPane(TabPane tabPane) {
		MainController.tabPane = tabPane;

		// System.out.println("Setter title:"+TabController.getTitle());
		getTabPaneView(tabPane, addNewTab);
		tabPaneChangeListener(tabPane);

	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public static Tab getAddNewTab() {
		return addNewTab;
	}

	public void setAddNewTab(Tab addNewTab) {
		this.addNewTab = addNewTab;
	}

	public void tabPaneChangeListener(TabPane tabpane) {
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

				// Closeing tab if first index tab close and size will be the 2
				// https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/package-summary.html

				if (tabPane.getTabs().size() == 1) {
					Platform.exit();
				}

				// The current tab title is set the stage title
				MainClass.getStage().setTitle(tabPane.getSelectionModel().getSelectedItem().getText());

				System.out.println("Current tab title: "+tabPane.getSelectionModel().getSelectedItem().getText());

				if (newSelectedTab == addNewTab) {
					
					// ---------------New tab is created --------------------
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							
							creatNewTab(tabpane, addNewTab);

						}
					});
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
			// tab.setText(TabController.getWebEngine().getTitle());

		} catch (IOException e) {
			System.out.println("Exception: New tab click but not working in TabPaneView Class");
			e.printStackTrace();
		}

		tab.getStyleClass().addAll("tab-pane");

		ObservableList<Tab> tabs = tabpane.getTabs();
		tabs.add(tabs.size() - 1, tab);

		SingleSelectionModel<Tab> selectedTab = tabpane.getSelectionModel();
		selectedTab.select(tab);

	}

	// end class
}