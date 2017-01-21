/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segp3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory.Entry;

/**
 *
 * @author Segp-Group 3
 */
public class FXMLDocumentController implements Initializable {

	//@FXML
	//private BorderPane borderPaneRoot;

	@FXML
	private TabPane tabpane;

	@FXML
	private BorderPane borderpane;

	@FXML
	private JFXButton back;

	@FXML
	private JFXButton forward;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton search;

	@FXML
	private Tab addNewTab;

	// Hamburger
	@FXML
	private JFXHamburger hamburger;

	VBox drawerPane = new VBox();
	// JFXListView<Label> list = new JFXListView<Label>();

	// Webview and browser object is going to start
	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// ---------------------webView---------------------------webEngine----------------------------------------------

		// Default url will be google
		webEngine.load("http://www.google.com");
		searchField.setText("http://www.google.com");

		// in the borderpane center we are setting browser view in center of
		// borderpane
		borderpane.setCenter(browser);

		// listner for search button
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			webEngine.load(searchField.getText());
			webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
				@Override
				public void changed(ObservableValue ov, State oldState, State newState) {

					if (newState == Worker.State.SUCCEEDED) {
						System.out.println(webEngine.getLocation());
						searchField.setText(webEngine.getLocation());
					}

				}
			});

		});
		// --------------------------------------UrlField--------------------------Url--GetLocation-------------------
		// listner for search textfield of search button
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				webEngine.load(searchField.getText());

				/*
				 * I got this idea from this link Doc
				 * :https://docs.oracle.com/javase/8/javafx/api/index.html?
				 * javafx/scene/web/WebEngine.html I got the conceptual thought
				 * from Stack: this
				 * http://stackoverflow.com/questions/32486758/detect-url-
				 * changes-in-javafx-webview The purpose of this is to show the
				 * chaning url
				 */

				webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
					@Override
					public void changed(ObservableValue ov, State oldState, State newState) {

						if (newState == Worker.State.SUCCEEDED) {

							System.out.println(webEngine.getLocation());
							searchField.setText(webEngine.getLocation());
						}

					}
				});

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

		// -------------------------------------------------------Hamburger-------------------------------------------

		// Below Commited code is about the list view that will appear after
		// hamburger click

		// for(int i = 0 ; i < 6 ; i++){
		// list.getItems().add(new Label("Item " + i));
		// list.getStyleClass().add("mylistview");
		// }
		JFXButton b1 = new JFXButton("Button");
		 drawerPane.getChildren().add(b1);

		// Hamburger listner setRate 1 is active state and -1 is normal state in
		// ham
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			
			 if(transition.getRate()==1){ 
				 borderpane.setRight(drawerPane);
				 drawerPane.setVisible(true); 
				 } 
			 else{ 
				 borderpane.setRight(null);
				 drawerPane.setVisible(false); 
				 }
			

		});

		// ------------------------------------------------------TabPane---------------------------------------------

		/*
		 * SingleSelectionModel<Tab> selectionmodel =
		 * tabpane.getSelectionModel(); selectionmodel.select(1);
		 * addNewTab.setClosable(false);
		 * if(selectionmodel.getSelectedItem().getId().equals(addNewTab)){ Tab
		 * tab = new Tab(); tab.setText("New Tab"); tabpane.getTabs().add(tab);
		 * }
		 */

		// end method
	}
	// end class
}