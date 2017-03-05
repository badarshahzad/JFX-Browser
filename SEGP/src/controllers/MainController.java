/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import database.History_Managment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
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
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	public VBox drawerPane = new VBox();
	
	public static TabPane tabPane = new TabPane();
	
	private Tab firstTab = new Tab("First Tab");
	private Tab addNewTab = new Tab("+");

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		
		// All opens tabs should be closed so below line is for just closing tabs
		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		tabPane.setFocusTraversable(false);
		
		try {
			firstTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Tab.fxml")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		addNewTab.setClosable(false);
		tabPane.getTabs().addAll(firstTab, addNewTab);
		rootBorderPane.setCenter(tabPane);

		getTabPaneView(tabPane, addNewTab);
		tabPaneChangeLiten(tabPane);
	
	}// end intializer method

	// ---set method for TabPane---
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	// ---get method for TabPane---
	public TabPane getTabPane() {
		return tabPane;
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
				if (newSelectedTab == addNewTab) {

					// ---------------New tab is created --------------------
					Tab tab = new Tab("New Tab");

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
			}
		});
		return tabpane;
	}

	// end class

}