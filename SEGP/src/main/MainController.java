/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import downloader.MainDownload;

import java.beans.EventHandler;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
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
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import userInterface.Hamburger;
import userInterface.History;
import userInterface.MenuView;
import userInterface.TabPaneView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 *
 * @author Segp-Group 3
 */
public class MainController extends Renderer implements Initializable {

	/*
	 * Reference: We got this idea from this link Doc Link:
	 * https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/web/
	 * WebEngine.html We got the conceptual thought from Stack: Link:
	 * http://stackoverflow.com/questions/32486758/detect-url-changes-in-javafx-
	 * webview Description: 1st rootBorderPane that is the actual root for scene
	 * and 2nd borderpane is the tabpane #pane Below is 4 buttons for navigation
	 * backward to go back page,forward to go the previous visited page,refresh
	 * will reload the page and search button is a specific url search Textfield
	 * it to write a url.
	 * 
	 * We Extends this Main controller Class with Renderer to work more
	 * efficiently and can easily test any renderer
	 * 
	 * 
	 ***********************************************************************************************************/

	@FXML
	private BorderPane rootBorderPane;
	@FXML
	private BorderPane borderpane;
	@FXML
	private JFXButton back;
	@FXML
	private JFXButton forward;
	@FXML
	private JFXButton refresh;
	@FXML
	private JFXButton search;

	@FXML
	private JFXTextField searchField;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab addNewTab;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private GridPane navigationBar;
	
	//Classes objects to get methods or set methods access
	private History object1 = new History();
	private Hamburger ham = new Hamburger();
	
	public VBox drawerPane = new VBox();
	// make obejc to get the setter method for url

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// ---------------All opens tabs should be closed so below line is for
		// just closing tabs------------------------
		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

		// --------------Renderer
		// Class-------webView-----------webEngine----------------------------------------------
		searchField.setText(webEngine.getLocation());
		borderpane.setCenter(browser);

		// ---------------URL of addressbar load if user clicked search button
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			webEngine.load(searchField.getText());
			System.out.println(searchField.getText());
		});

		// ---------------------Listner for search textfield of
		// search--------------------------------------
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				webEngine.load(searchField.getText());
			}
		});

		/*
		 * Change of link in browser to set value in broser addressbar.
		 * Likewise we are storing the history.
		 */
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
					object1.setHistory("ad", "adf", "ADfa");//history
					webEngine.getLocation();
				}
				
			}
		});

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

		// -------------------------------------------Refresh--------------------------------------------------------

		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			webEngine.reload();

		});

		// -------------------------------------------Hamburger----Drawer----Menu------------------------------------
		
		ham.getHamburger(hamburger, borderpane, tabPane);
		// --------------------------------------------Hitory-------------------------------------------------------
		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 * DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); Date
		 * setDate = new Date(); //Calendar cal = Calendar.getInstance();
		 * //System.out.println("Time : "+ dateFormat.format(cal.getTime()));
		 * 
		 * String date = dateFormat.format(setDate); String time =
		 * timeFormat.format(setDate); String link = webEngine.getLocation();
		 * //System.out.println("Time: "+ time+ "\n"+ "Date :" + date +"\n"+
		 * "Link: "+link); History object = new History();
		 * object.setHistory(date, link, time);
		 */
		// -------------------------------------------TabPane-----------------------------------------------------

		/*
		 * New tabs will add and but due to some reasome the tabpan_view is
		 * comment as We cannont handle yet The Mulit view tabs yet our aim to
		 * handle singe tabs
		 ***********************************************************/

		TabPaneView tabpan_view = new TabPaneView();
		/*
		 * New tab add whenever clicked 
		 */
		//tabpan_view.getTabPane(tabPane, addNewTab, navigationBar);

		/**
		 * There is well know error in Tabpane while you will be working with
		 * scenebuilder then comment the below tabpane! We gone through we
		 * severly face this #bug of tabpane many time so becareful!
		 */

		// end method
	}
	// end class
}