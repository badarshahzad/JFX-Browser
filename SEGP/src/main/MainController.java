package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import database.History_Managment;

import java.net.URL;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

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
import userInterface.History;
import userInterface.TabPaneView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 *
 * @author Segp-Group 3
 */
public class MainController implements Initializable {

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
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//Temporary set or url 
		searchField.setText("https://www.facebook.com");

		// ---------------All opens tabs should be closed so below line is for  just closing tabs------------------------
		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		pageRender("https://www.facebook.com");
		
		//Search Button Listener 
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			pageRender(searchField.getText()); //method call 
		});
			//Search Field Listener
			searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				pageRender(searchField.getText()); //method call
			}
		});

		// --------------Renderer
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
		//----------------------------------------TabPane-----------------------------------------------------//
		//Adding multiple tabs would be done later.
		
		TabPaneView tabpan_view = new TabPaneView();
		//----------------------------------------------------------------------------------------------------//
		
		// end intializer method
		
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					try{
					
					Document doc1 = (Document) webEngine.getDocument();
					currentUrlDoc(doc1);
					//System.out.println(webEngine.executeScript("document.documentElement.outerHTML"));
					
					}
					catch(Exception e){
						System.out.println(e);
					}
					//System.out.println("WebEngine Location: "+ webEngine.getLocation());
					
				}
				
			}
			
		});
		
	//end of init method
	}
	
	public Document currentUrlDoc(org.w3c.dom.Document document){
		return (Document) document;
	}

	//---------------------------------------------Curent Url Document Getting-----------------------------//
	
	
	//mehtod to rendere page
	public void pageRender(String url)
	{
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
					if(!(webEngine.getLocation().equals("about:blank")))
					History_Managment.insertUrl(webEngine.getLocation());
				}
				
			}
			
		});
		webEngine.load(url);
		borderpane.setCenter(browser);
		}
	
		
	
	
	
	// end class
}
