package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import database.History_Managment;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import main.MainController;
import userInterface.Hamburger;
import userInterface.History;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 *
 * @author Segp-Group 3
 */
public class TabController implements Initializable {

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
	private JFXHamburger hamburger;
	@FXML
	private GridPane navigationBar;
	
	MainController main = new MainController();
	// Classes objects to get methods or set methods access
	private Hamburger ham = new Hamburger();

	public VBox drawerPane = new VBox();
	// make obejc to get the setter method for url
	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	private TabPane tabPane = main.getTabPane();
	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		/// All opens tabs should be closed so below line is for just closing tabs

		pageRender("https://www.google.com.pk/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8");
		ham.getHamburger(hamburger, borderpane, tabPane);

		// Search Button Listener
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			pageRender(searchField.getText()); // method call
		});
		// Search Field Listener
		searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				pageRender(searchField.getText()); // method call
			}
		});

		back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {

				WebHistory history = webEngine.getHistory();
				history.go(-1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});

		forward.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				WebHistory history = webEngine.getHistory();
				history.go(1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});

		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			webEngine.reload();
		});

	}// end intializer method

	// mehtod to rendere page
	public void pageRender(String url) {
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
					if (!(webEngine.getLocation().equals("about:blank")))
						History_Managment.insertUrl(webEngine.getLocation());
				}

			}

		});

		webEngine.load(url);
		borderpane.setCenter(browser);
	}
	// end class

}