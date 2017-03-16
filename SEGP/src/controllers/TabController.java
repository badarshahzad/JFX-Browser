package controllers;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import database.History_Managment;

import java.awt.Event;
import java.awt.TextField;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.controlsfx.control.textfield.TextFields;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SingleSelectionModel;
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
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Segp-Group 3
 */
public class TabController implements Initializable {

	@FXML
	private BorderPane borderpane;
	@FXML
	private Label back;
	@FXML
	private Label forward;
	@FXML
	private Label refresh;
	@FXML
	private Label search;
	@FXML
	private JFXTextField searchField;
	@FXML
	private Label download;
	@FXML
	private Label bookmark;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private GridPane navigationBar;
	@FXML
	private JFXProgressBar progressbar;

	MainController main = new MainController();
	// Classes objects to get methods or set methods access
	private Hamburger ham = new Hamburger();
	public VBox drawerPane = new VBox();

	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	public Worker<Void> worker;

	private TabPane tabPane = main.getTabPane();

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		back.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/backword1.png"))));
		forward.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/forward1.png"))));
		refresh.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/refresh.png"))));
		search.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/search.png"))));
		search.setOpacity(.5);
		download.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/download.png"))));
		download.setOpacity(.6);
		bookmark.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/bookmark.png"))));
		bookmark.setOpacity(.7);
		
		
		// Worker load the page
		worker = webEngine.getLoadWorker();
		worker.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				System.out.println("Loading state: " + newValue.toString());
				if (newValue == Worker.State.SUCCEEDED) {
					System.out.println("Finish!");
				}
			}
		});

		progressbar.progressProperty().bind(worker.progressProperty());

		pageRender("https://www.google.com.pk/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8");

		ham.getHamburger(hamburger, borderpane, tabPane);

		// Search Button Listener

		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			pageRender(searchField.getText());

		});
		
		// Search Field Listener
		
		searchField.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.ENTER) {
				pageRender(searchField.getText()); // method call
			}
		});
		
		//This will drop down list suggestion keywords

		String [] array = {"a","bb","cc"};
		TextFields.bindAutoCompletion(searchField, array);
		

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

		bookmark.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			System.out.println("Bookmarks");
		});
		
		download.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			System.out.println("download click");
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