/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import DataBase.History;

import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import ui.Hamburger;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import ui.TabPaneView;

/**
 *
 * @author Segp-Group 3
 */
public class FXMLDocumentController implements Initializable {

	
	/* 1st rootBorderPane that is the actual root for scene and 2nd borderpane is the tabpane #pane
	 * Below is 4 buttons for navigation backward to go back page,forward to go the previous visited page,refresh will
	 * reload the page and search button is a specific url search 
	 * Textfield it to write a url.
	 * 
	 **/
	History hs=new History();
	@FXML private BorderPane rootBorderPane; @FXML private BorderPane borderpane;
	@FXML private JFXButton back;@FXML private JFXButton forward; @FXML private JFXButton refresh; @FXML private JFXButton search;
	@FXML private JFXTextField searchField; 
	@FXML private TabPane tabPane; @FXML private Tab addNewTab; 
	@FXML private JFXHamburger hamburger;
	@FXML private GridPane navigationBar;
	
	public VBox drawerPane = new VBox();
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// ----------------------css attach with Tabpane
		// tabpane.getStyleClass().addAll("tab-pane");

		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		
		//add css in addNewTab

		// ---------------------webView---------------------------webEngine----------------------------------------------

		// --------------------- Default url will be google
		System.out.println("Title fo page:"+webEngine.getTitle());
		webEngine.load("http://www.google.com");
		searchField.setText(webEngine.getLocation());
		
		hs.insertUrl(webEngine.getLocation());
		borderpane.setCenter(browser);

		// --------------------- Listener for search button
		/*
		 * Example of below listner search of lamda exprestion
		 * btn.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { System.out.println(
		 * "Hello World!"); } });
		 */
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			webEngine.load(searchField.getText());
			if(!(searchField.getText().equals("about:blank")))
			{
				hs.insertUrl(searchField.getText());
			}
			webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
				@Override
				public void changed(ObservableValue ov, State oldState, State newState) {

					if (newState == Worker.State.SUCCEEDED) {
						searchField.setText(webEngine.getLocation());
						
					}
					

				}
			});

		});
		// --------------------------------------UrlField--------------------------Url--GetLocation-------------------

		// ---------------------Listner for search textfield of search button
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				webEngine.load(searchField.getText());
				if(!(searchField.equals("about:blank")))
				{
					hs.insertUrl(searchField.getText());
				}
			}
		});

		/*
		 * We got this idea from this link Doc
		 * :https://docs.oracle.com/javase/8/javafx/api/index.html?
		 * javafx/scene/web/WebEngine.html We got the conceptual thought from
		 * Stack: this
		 * 
		 * http://stackoverflow.com/questions/32486758/detect-url-
		 * changes-in-javafx-webview The purpose of this is to show the chaning
		 * url
		 */

		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
				}

			}
		});

		/*
		 * bookmarks.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO
		 * Auto-generated method stub String url = engine.getLocation();
		 * System.out.println(url); // engine.get // write.println(url); }
		 * 
		 * }); History.setOnAction(new EventHandler<ActionEvent>(){
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO
		 * Auto-generated method stub WebHistory history = engine.getHistory();
		 * ObservableList<Entry> list = history.getEntries(); for(int i=0 ; i<
		 * list.size();i++){ System.out.println(list.get(i)); } } });
		 * 
		 */

		// --------------------------------------------------------Backward-------------------------------------------

		back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {

				WebHistory history = webEngine.getHistory();
				history.go(-1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});

		// --------------------------------------------------------Forward--------------------------------------------

		forward.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				WebHistory history = webEngine.getHistory();
				history.go(1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});

		// --------------------------------------------Refresh------------------------------------------------

		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			webEngine.reload();

		});

		// -------------------------------------------Hamburger----Drawer----Menu-----------------------------------

		Hamburger ham = new Hamburger();
		ham.getHamburger(hamburger, borderpane, tabPane);

		// --------------------------------------------------------TabPane---------------------------------------------

		TabPaneView tabpan_view = new TabPaneView();
		//tabpan_view.getTabPane(tabPane, addNewTab, navigationBar);
		
		/**
		 * There is well know error in Tabpane while you will be working with
		 * scenebuilder then comment the below tabpane! We gone through we
		 * severly face this #bug of tabpane many time so becareful!
		 */

		// ----------------------just put tabpane in vbox as to add new tab
		// button on click new tab pop up

		// end method
	}
	// end class
}