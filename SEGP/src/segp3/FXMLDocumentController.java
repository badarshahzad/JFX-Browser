/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segp3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory.Entry;

/**
 *
 * @author Segp-Group 3
 */
public class FXMLDocumentController implements Initializable {



    @FXML
    private BorderPane rootBorderPane;

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
	private TabPane tabpane;

	@FXML
	private Tab addNewTab;

	@FXML
	private JFXHamburger hamburger;
	
	@FXML
    private JFXButton refresh;
	
	@FXML
    private GridPane navigationBar;

	VBox drawerPane = new VBox();

	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// ---------------------webView---------------------------webEngine----------------------------------------------

		// Default url will be google
		webEngine.load("http://www.google.com");
		searchField.setText(webEngine.getLocation());
		borderpane.setCenter(browser);

		// Listener for search button
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
			}
		});
		/*
		 * We got this idea from this link Doc
		 * :https://docs.oracle.com/javase/8/javafx/api/index.html?
		 * javafx/scene/web/WebEngine.html We got the conceptual thought
		 * from Stack: this
		 * 
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
		//--------------------------------------------Refresh------------------------------------------------
		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			webEngine.load(webEngine.getLocation());
			
		});
		

		// -------------------------------------------Hamburger----Drawer----Menu-----------------------------------

		
		
		//---------------------------------------------Drawer--Menu--Buttons------------------------------------------
		
		JFXButton home = new JFXButton();home.setMinWidth(30);home.setMinHeight(36);
		home.setGraphic(new ImageView(new Image("/home1.png")));
		//home.setButtonType(ButtonType.RAISED);
		//home.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
	
		
		JFXButton history = new JFXButton();history.setMinWidth(30);history.setMinHeight(36);
		history.setGraphic(new ImageView(new Image("/history.png")));
		//history.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton downloads = new JFXButton();downloads.setMinWidth(30);downloads.setMinHeight(36);
		downloads.setGraphic(new ImageView(new Image("/downloads.png")));
		//downloads.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton bookmarks = new JFXButton();bookmarks.setMinWidth(30);bookmarks.setMinHeight(36);
		bookmarks.setGraphic(new ImageView(new Image("/bookMarks.png")));
		//bookmarks.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton saveAsPdf = new JFXButton();saveAsPdf.setMinWidth(30);saveAsPdf.setMinHeight(36);
		saveAsPdf.setGraphic(new ImageView(new Image("/pdf.png")));
		//saveAsPdf.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		JFXButton setting = new JFXButton();setting.setMinWidth(30);setting.setMinHeight(36);
		setting.setGraphic(new ImageView(new Image("/setting.png")));
		//setting.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
		
		GridPane  gridPane = new GridPane();
		gridPane.add(home,0,0);
		gridPane.add(history,0,1);
		gridPane.add(downloads,0,2);
		gridPane.add(bookmarks,0,3);
		gridPane.add(saveAsPdf,0,4);
		gridPane.add(setting,0,5);
		
		 //------------------------------------------------------Right----DrawerStack-------------------------------- 
		
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		JFXDrawer rightDrawer = new JFXDrawer();
		rightDrawer.setDirection(DrawerDirection.RIGHT);		
		rightDrawer.setDefaultDrawerSize(40);
		rightDrawer.setSidePane(gridPane);
		rightDrawer.setOverLayVisible(false);
		rightDrawer.setResizableOnDrag(true);
		
		 
		 //--------------------------------------------------------Hamburger------------------------------------------
		 
		// Hamburger listner setRate 1 is active state and -1 is normal state in hamburger
		
		hamburger.setMinWidth(10);
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			borderpane.setRight(drawersStack);
			drawersStack.toggle(rightDrawer);
		});

		// --------------------------------------------------------TabPane---------------------------------------------

		
		//----------------------just put tabpane in vbox as to add new tab button on click new tab pop up
	
		tabpane.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {
			        	if(newSelectedTab==addNewTab){
			           
			        		/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--Problem---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			        		 * Problem is when If I will put previous navigation bar in new pane then
			        		 * it works for previous listeners? Here his a debate start how we will do it?
			        		*/
			        		//New Tab new rowser pane
			        		WebView browser = new WebView();
			        		WebEngine webEngine1 = browser.getEngine();
			        		webEngine1.load("http://www.google.com");
			        		BorderPane pane = new BorderPane();
			        		pane.setTop(navigationBar);
			        		//pane.setCenter(browser);
			        		
			        		
			            	Tab tab = new Tab();
			    			tab.setText("1 Tab");
			    			tab.setContent(new Label("Message "));
			    			tab.setContent(pane);
			    			
			    			tab.getStyleClass().addAll("tab-pane");
			    			final ObservableList<Tab> tabs = tabpane.getTabs();
			    			tabs.add(tabs.size()-1,tab);
			    			tabpane.getSelectionModel().select(tab);
			    			//System.out.println("Now Size"+tabs.size());
			    			
			        	}
			        }
			    }
			);
		
		// end method
	}
	// end class
}