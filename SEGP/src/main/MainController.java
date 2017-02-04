/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

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
import userInterface.Hamburger;
import userInterface.TabPaneView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 * @author Segp-Group 3
 * This class is controlling overall functionality of the application. Different buttons are created and each button is asigned
 * with different task. 
 */

public class MainController implements Initializable {

	@FXML private BorderPane rootBorderPane;//rootBorderPane that is the actual root for scene
	@FXML private BorderPane borderpane;//borderpane is the tabpane #pane
	@FXML private JFXButton back;//to go to the previous page 
	@FXML private JFXButton forward;//to go to the forward page you have visited earlier.
	@FXML private JFXButton refresh;//to reload the current page.
	@FXML private JFXButton search;//to search about the text given in text field.
	@FXML private JFXTextField searchField;//to add text to be searched.
	@FXML private TabPane tabPane;//tabpane to open different tabs.
	@FXML private Tab addNewTab;//add new tab into tabpane.
	@FXML private JFXHamburger hamburger;//hamburger contains different buttons which will be explained later.
	@FXML private GridPane navigationBar;//
	
	public VBox drawerPane = new VBox();
	public WebView browser = new WebView();//object for creating view of the result we searched from the internet.
	public WebEngine webEngine = browser.getEngine();//object for calling webEngine to run and search about the text given in text field.

/*
 * (non-Javadoc)
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 * We are overiding the builtin initialize method. In this method webengine is being start and searching of the given URL or text
 * is being returned. In this method different functionality is assigned to each button. Each button is being handled if the mouse
 * is clicked the buttons will perform thier functionality. When search Engine returns the request of user a tab pane is created
 * and different tabes can be opened in the tab pane.
 */
	
@Override
public void initialize(URL url, ResourceBundle rb) {

	// ----------------------css attach with Tabpane
	// tabpane.getStyleClass().addAll("tab-pane");
	tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);//all tabs will be closed while performing this functionality.
		
	// ---------------------webView---------------------------webEngine----------------------------------------------

	System.out.println("Title fo page:"+webEngine.getTitle());//printing title of searched page on console.
	webEngine.load("http://www.google.com");//default page will be google main page.
	searchField.setText(webEngine.getLocation());//webengine will return the query of the text written in text field.
	borderpane.setCenter(browser);//all the query will be displayed in the center of browser.

/*
 * Listener for search button
 * Example of below listner search of lamda exprestion
 * btn.setOnAction(new EventHandler<ActionEvent>() {
 * 
 * @Override public void handle(ActionEvent e) { System.out.println(
 * "Hello World!"); } });
 */
	search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {//an action is being assinged to search button
			
		webEngine.load(searchField.getText());//webengine will load the text written into search field.
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {//loading the page which has been returned by search engine.
			
			/*
			 * overriding builtin changed method.This method checks that if search engine has succeeded in searching the query
			 * and new page has been displayed then search engine set text area to the result of the textfield.
			 */
			
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {
				if (newState == Worker.State.SUCCEEDED) {//checking whether search engine has succeeded in searching the required page or not.
					System.out.println(webEngine.getLocation());//printing location of new page onconsole.
					searchField.setText(webEngine.getLocation());//assigning searchfield the location of current page.
				}

			}
		});

	});
		// --------------------------------------UrlField--------------------------Url--GetLocation-------------------

		// ---------------------Listner for search textfield of search button
	/*
	 * search field will get a key to be pressed and then perform action according to key.
	 * Now "Enter" key is assigned to the searched field that if this key will be pressed 
	 * webengine will search about text written in search field.
	 */
	
	searchField.setOnKeyPressed(event -> {//perform action when key pressed.
		if (event.getCode() == KeyCode.ENTER) {//"Enter" key is assigned to search field.
			System.out.println(searchField.getText());//print text of search field on console
			webEngine.load(searchField.getText());//web engine will load the result of text written in search field.
		}
	});
			
/*
 * We got this idea from this link Doc
 * :https://docs.oracle.com/javase/8/javafx/api/index.html?
 * javafx/scene/web/WebEngine.html We got the conceptual thought from
 * Stack:
 * 
 * http://stackoverflow.com/questions/32486758/detect-url-
 * changes-in-javafx-webview The purpose of this is to show the changing
 * url
 */

/* webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
		@Override
		public void changed(ObservableValue ov, State oldState, State newState) {

			if (newState == Worker.State.SUCCEEDED) {
				System.out.println(webEngine.getLocation());
				searchField.setText(webEngine.getLocation());
			}

		}
	});*/

/*
bookmarks.setOnAction(new EventHandler<ActionEvent>() {
 
	@Override public void handle(ActionEvent event) { // TODO
	Auto-generated method stub String url = engine.getLocation();
	System.out.println(url); // engine.get // write.println(url); }
 
}); 

History.setOnAction(new EventHandler<ActionEvent>(){
 
	@Override public void handle(ActionEvent event) { // TODO
	Auto-generated method stub WebHistory history = engine.getHistory();
	ObservableList<Entry> list = history.getEntries(); for(int i=0 ; i<
	list.size();i++){ System.out.println(list.get(i)); } } });
 
*/

		// --------------------------------------------------------Backward-------------------------------------------

	back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {//assigning back button that when mouse click on this button perform action
		try {

			WebHistory history = webEngine.getHistory();//looking for history of the webengine.
			history.go(-1);//if web history has previous page then go to previuos page other wise remain on same page
		}
		catch (IndexOutOfBoundsException e1) {
			System.out.println(e1);//print error on console if provious page is not available.
		}
	});

		// --------------------------------------------------------Forward--------------------------------------------

	forward.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {//assigning forward button that when mouse click on this button perform action
		try {
			WebHistory history = webEngine.getHistory();//looking for history of the webengine.
			history.go(1);//if web history has next page then go to next page other wise remain on same page
		}
		catch (IndexOutOfBoundsException e1) {
			System.out.println(e1);//print error on console if next page is not available.
		}
	});

		// --------------------------------------------Refresh------------------------------------------------

	refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {//assigning refresh button that when mouse click on this button perform action
		webEngine.reload();//when this button will be pressed browser will reload the same page.

	});

		// -------------------------------------------Hamburger----Drawer----Menu-----------------------------------

	Hamburger ham = new Hamburger();//object of hamburger class is created.
	ham.getHamburger(hamburger, borderpane, tabPane);//assigning hamburger,borderpane and tabe pane to this class.

		// --------------------------------------------------------TabPane---------------------------------------------

	TabPaneView tabpan_view = new TabPaneView();//creating object of TabPaneView class.
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