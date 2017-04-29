package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import database.BookMarksDataBase;
import database.HistoryManagment;
import downloader.MainDownload;

import java.util.regex.*;  

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.stage.FileChooser;

import htmlToPdf.HTMLtoPDF;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import passwordVault.DetectForm;
import userInterface.Hamburger;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;

import main.MainClass;
import userInterface.MenuView;

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
	private GridPane navigationBar;

	@FXML
	private Label back;

	@FXML
	private Label forward;

	@FXML
	private Label refresh;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label search;

	@FXML
	private Label download;

	@FXML
	private Label bookmark;

	@FXML
	private Label htmlAsPdf;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXProgressBar progressbar;

	private String folder;
	String title;
	ObservableList<String> options;

	MainController mainController = new MainController();

	// Classes objects to get methods or set methods access

	private MenuView menviewObject = new MenuView();

	private Hamburger ham = new Hamburger();
	public VBox drawerPane = new VBox();

	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	public Worker<Void> worker;

	private TabPane tabPane = mainController.getTabPane();
	/*
	 * public TabPane getTabPane() { return tabPane; }
	 * 
	 * public void setTabPane(TabPane tabPane) { this.tabPane = tabPane; }
	 */

	// we made this webgine object to get access the current url of webpage

	// we made this webgine object to get access the current url of webpage
	static WebEngine engine;

	public void setWebEngine(WebEngine webEngine) {
		engine = webEngine;
	}

	public static WebEngine getWebEngine() {
		return engine;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		setWebEngine(webEngine);
		searchField.setText("https://www.google.com");
		
		//Home Page Link
		pageRender(searchField.getText());

		back.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/backword1.png"))));
		back.setStyle("");
		forward.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/forward1.png"))));
		refresh.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/refresh.png"))));
		search.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/search.png"))));
		search.setId("search");
		download.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/download.png"))));
		bookmark.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/bookmark.png"))));
		htmlAsPdf.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pdfConverter.png"))));
		
		// Worker load the page
		worker = webEngine.getLoadWorker();
		worker.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				System.out.println("Loading state: " + newValue.toString());
				if (newValue == Worker.State.SUCCEEDED) {
					System.out.println("Finish!");
					
					tabPane.getSelectionModel().getSelectedItem().setText(webEngine.getTitle());

					org.w3c.dom.Document doc = webEngine.getDocument();
					DetectForm detectForm = new DetectForm();
					detectForm.detect(doc);
					
					// String imgs = "";
					// File f = new
					// File(getClass().getResource("/screenshots").getFile());
					// for (File fs : f.listFiles()) {
					// imgs += "<img src=\""+fs.toURI()+"\" width='100' height =
					// '100' />";
					// }
					// System.out.println("images:"+imgs);
					// System.out.println("Hello \"" + imgs + "\"");
					//// webEngine.loadContent("<div>"+imgs+"</div>");
					// String div = "var body =
					// document.getElementsByTagName('body')[0];"
					// + "var newElement = document.createElement('div');"
					// + "var center = document.createElement('center');"
					// + "newElement.append = "
					// + "newElement.setAttribute('id', 'custom_div');"
					// + "center.appendChild(newElement);"
					// +"body.appendChild(center);";
					//// webEngine.executeScript("document.getElementsByTagName('body')[0].innerHTML
					// =\"" + imgs + "\"");
					// webEngine.executeScript("if (!window.indexedDB)
					// window.alert(\"Your browser doesn't support a stable
					// version of IndexedDB.\")");
					
				}

			}
		});

		webEngine.locationProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("location of engine: " + newValue);
				dwnlod.startDownload(newValue, webEngine.getTitle());

				// if (!webEngine.getTitle().equals(null)) {
				// System.out.println("Title: " + webEngine.getTitle());

				// }

			}
		});

		progressbar.progressProperty().bind(worker.progressProperty());

	

		ham.getHamburger(hamburger, borderpane, tabPane);

		// Search Button Listener

		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			pageRender(searchField.getText());

		});

		htmlAsPdf.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			FileChooser fileChooser = new FileChooser();
			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF File (*.pdf)", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			// The stage for show dialouge is get from MainClass stage

			File file = fileChooser.showSaveDialog(MainClass.getStage());
			HTMLtoPDF object = new HTMLtoPDF(file);
			object.setDaemon(true);
			object.start();

		});

		// Search Field Listener

		searchField.setOnKeyPressed(event -> {
			
			Pattern p = Pattern.compile("[a-z]*[ ]*[A-Z]*[ ]*[0-9]*[ ]");//. represents single character  
			Matcher m = p.matcher(searchField.getText());  
			boolean b = m.matches();
			
			if (event.getCode() == KeyCode.ENTER) {
				
				if(b){
					pageRender("https://www.google.com.pk/search?q="+searchField.getText()); // method call
				}else {
					pageRender(searchField.getText()); // method call
				}
			}
			
		
				
				ObservableList<String> domainNames = FXCollections.observableArrayList();
				domainNames = HistoryManagment.getDomainNames(domainNames);
				String[]array = new String[domainNames.size()];
				
				for(int a = 0; a< domainNames.size();a++){
					array[a] = domainNames.get(a);
					//System.out.println("Ghin Value"+array[a]);
				}
				
				TextFields.bindAutoCompletion(searchField,array);
				
			
		});
		
		//System.out.println("Full history index 0: +"+fullHistory.get(0));

		// Tabpane listener for new tab or for security password: @testing phase
		tabPane.setOnKeyPressed(event -> {
			/*
			 * switch (event.getCode() ) { case T : case CONTROL:
			 * System.out.println(event.getCode()); break; }
			 */
		});
		// This will drop down list suggestion keywords
		
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

			Label bookmarksLabel = new Label("Bookmarks");
			VBox popUpContent = new VBox();

			popUpContent.setMinSize(300, 250);
			popUpContent.setSpacing(5);
			popUpContent.setPadding(new Insets(5, 5, 5, 5));
			Label nameLabel = new Label("Name");
			JFXTextField markNameText = new JFXTextField();

			markNameText.setText(webEngine.getTitle());
			Label folderLabel = new Label("Folder");
			options = BookMarksDataBase.folders();
			JFXComboBox<String> markFolderList = new JFXComboBox<>(options);
			markFolderList.setMinWidth(300);
			markFolderList.getSelectionModel().select(0);

			JFXButton cancelPopup = new JFXButton("Cancel");
			cancelPopup.setMinSize(100, 30);
			
			JFXButton newFolderMarkFolder = new JFXButton("New Folder");
			newFolderMarkFolder.setMinSize(100, 30);
			
			JFXButton saveMark = new JFXButton("Save");
			saveMark.setMinSize(100, 30);

			HBox hbox = new HBox();
			hbox.setSpacing(5);
			hbox.getChildren().addAll(cancelPopup, newFolderMarkFolder, saveMark);
			// markFolderList.setVisibleRowCount(0);

			bookmarksLabel.setId("bookmarkLabel");
			nameLabel.setId("bookmarkLabel");
			markFolderList.setId("bookmarkLabel");
			folderLabel.setId("bookmarkLabel");
			markFolderList.setId("bookmarkLabel");
			
			VBox.setMargin(bookmarksLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(nameLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(markNameText, new Insets(5, 5, 5, 5));
			VBox.setMargin(folderLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(markFolderList, new Insets(5, 5, 5, 5));

			popUpContent.getChildren().addAll(bookmarksLabel, nameLabel, markNameText, folderLabel, markFolderList,
					hbox);

			PopOver popOver = new PopOver(new JFXButton("Yes"));

			// popOver.getRoot().getStylesheets().add("Yes");
			// popOver.setDetachable(true);
			popOver.setCornerRadius(4);
			popOver.setContentNode(popUpContent);
			// popOver.setMinSize(400, 400);
			popOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			popOver.show(bookmark);

			cancelPopup.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler -> {
				popOver.hide();
			});
			saveMark.addEventHandler(MouseEvent.MOUSE_CLICKED, (s) -> {
				System.out.println(folder);
				if (folder == null) {
					folder = markFolderList.getItems().get(0);
				}
				title = markNameText.getText();
				if (title == null) {
					title = webEngine.getTitle();
				}
				BookMarksDataBase.insertBookmarks(searchField.getText(), folder, title, 1);
				popOver.hide();
			});
			newFolderMarkFolder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

				TextInputDialog dialog = new TextInputDialog("walter");
				dialog.setTitle("Create New Folder");
				dialog.setHeaderText("Create New Folder");
				dialog.setContentText("Please enter folder name:");
				Optional<String> result = dialog.showAndWait();
				result.ifPresent(name -> {
					if (!name.equals("")) {
						this.folder = name;
						options.add(folder);
					}
					if (title == null) {
						title = webEngine.getTitle();
					}
					BookMarksDataBase.insertBookmarks(searchField.getText(), folder, title, 1);
				});

			});
		});

		download.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

		});

	}// end intializer method

	// mehtod to rendere page

	MainDownload dwnlod = new MainDownload();
/*	
	String titleOfPage;
	
	public String  getTitleOfPage(){
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {
					
				titleOfPage = webEngine.getTitle();
			}
		});
		
		return titleOfPage;
				
	}*/

	public void pageRender(String url) {
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					
					searchField.setText(webEngine.getLocation());

					MainClass.getStage().setTitle(webEngine.getTitle());
					//
					//MainController.setSelectedTabTitle(webEngine.getTitle());
					// Tab tab = MainController.getFirstTab();
					// tab.setText(webEngine.getTitle());
					// MainController.setFirstTab(tab);

					System.out.println("Sudo title of tab" + webEngine.getTitle());

					URL domain = null;
					if (!(webEngine.getLocation().equals("about:blank")))
						try {
							domain = new URL(webEngine.getLocation());
						} catch (MalformedURLException e) {
							System.err.println("Invalid domain.");
						}
					HistoryManagment.insertUrl(webEngine.getLocation(), domain.getHost(), webEngine.getTitle());

				}

			}

		});
		webEngine.load(url);

		borderpane.setCenter(browser);
	}

	public JFXHamburger getHamburger() {
		return hamburger;
	}

}