
package controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import database.BookMarksDataBase;
import database.CRUD;
import database.HistoryManagment;
import downloader.MainDownload;
import htmlToPdf.HTMLtoPDF;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import passwordVault.DetectForm;
import userInterface.Hamburger;
import userInterface.MenuView;

/**
 *
 * @author Segp-Group 3
 */

public class TabController implements Initializable {


	public TabController() {
		// TODO Auto-generated constructor stub
	}


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
	private String title;
	private ObservableList<String> options;

	// Classes objects to get methods or set methods access

	private MainController mainController = new MainController();
	private Hamburger ham = new Hamburger();
	private MainDownload dwnlod = new MainDownload();
	public VBox drawerPane = new VBox();

	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();
	private WebHistory history = webEngine.getHistory();
	
	public Worker<Void> worker;

	private TabPane tabPane = mainController.getTabPane();

	// we made this webgine object to get access the current url of webpage

	static WebEngine engine;

	public void setWebEngine(WebEngine webEngine) {
		engine = webEngine;
	}

	public static WebEngine getWebEngine() {
		return engine;
	}


	public Label getBookmark() {
		return bookmark;
	}

	public void setBookmark(Label bookmark) {
		this.bookmark = bookmark;
	}


	public Label getHtmlAsPdf() {
		return htmlAsPdf;
	}

	public void setHtmlAsPdf(Label htmlAsPdf) {
		this.htmlAsPdf = htmlAsPdf;
	}

	public void setHamburger(JFXHamburger hamburger) {
		this.hamburger = hamburger;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {


		//htmlAsPdf.setDisable(true);
		//bookmark.setDisable(true);
		//hamburger.setDisable(true);
		
		browser.setOnKeyPressed(event->{

			if (event.getCode() == KeyCode.X && event.isControlDown()) {

				JFXButton button = new JFXButton("Ok");
				JFXTextField textfield = new JFXTextField();

				button.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6) -> {
					System.out.println("Pin is :" + textfield.getText());

					Pattern ipAddress = Pattern.compile("[0-9]{4}");
					Matcher m1 = ipAddress.matcher(textfield.getText());
					boolean b1 = m1.matches();


					if (b1) {

					//	getHamburger().setDisable(false);

					//	getHtmlAsPdf().setDisable(false);

					//	getBookmark().setDisable(false);

					} else {

						Notifications.create().title("Wronge Pin")
						.text("Your pin is exceeding limit or your pin is consists\n" + "of invalid characters")
						.hideAfter(Duration.seconds(5)).showError();

					}

				});

				Main.setDialouge(button, "Pin", "Please type your pin", textfield);

				Notifications.create().title("Pin Activation").text("You are going to access Pro-Verion.")
				.hideAfter(Duration.seconds(3)).showInformation();
			}
		});

		// untill user valid pin these will be disabled 

		setWebEngine(webEngine);
		searchField.setText("https://www.google.com");

		// Home Page Link
		pageRender(searchField.getText());

		back.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/backword1.png"))));
		back.setDisable(true);

		forward.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/forward1.png"))));
		forward.setDisable(true);

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

					// The current url title set in current tab
					tabPane.getSelectionModel().getSelectedItem().setText(webEngine.getTitle());

					org.w3c.dom.Document doc = webEngine.getDocument();
					
					DetectForm detectForm = new DetectForm();
					
					detectForm.detect(doc);
					
					detectForm.insert(doc);
								
					}
				
			}
		});

		webEngine.locationProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("location of engine: " + newValue);
				dwnlod.startDownload(newValue, webEngine.getTitle());

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

			File file = fileChooser.showSaveDialog(Main.getStage());
			HTMLtoPDF object = new HTMLtoPDF(file);
			object.setDaemon(true);
			object.start();

		});

		// Search Field Listener

		searchField.setOnKeyPressed(event -> {

			Pattern p = Pattern.compile("[a-z]*[ ]*[A-Z]*[ ]*[0-9]*[ ]");
			Matcher m = p.matcher(searchField.getText());
			boolean b = m.matches();

			if (event.getCode() == KeyCode.ENTER) {

				if (b) {
					pageRender("https://www.google.com.pk/search?q=" + searchField.getText()); // method
					// call
				} else {
					pageRender(searchField.getText()); // method call
				}
			}

			ObservableList<String> domainNames = FXCollections.observableArrayList();
			domainNames = HistoryManagment.getDomainNames(domainNames);
			String[] array = new String[domainNames.size()];

			for (int a = 0; a < domainNames.size(); a++) {
				array[a] = domainNames.get(a);
				// System.out.println("Ghin Value"+array[a]);
			}

			TextFields.bindAutoCompletion(searchField, array);

		});


		back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {

				System.out.println("Max size :" + history.getEntries().size());
				System.out.println("Current index backword: " + history.getCurrentIndex());
				history.go(-1);

			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});

		forward.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				System.out.println("Max size :" + history.getEntries().size());
				System.out.println("Current index forward: " + history.getCurrentIndex());
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

			options =  BookMarksDataBase.folders(1);

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

			saveMark.addEventHandler(MouseEvent.MOUSE_CLICKED, (s)->{

				if(folder==null){
					folder=markFolderList.getItems().get(0);

				}
				title = markNameText.getText();
				if (title == null) {
					title = webEngine.getTitle();
				}
				BookMarksDataBase.insertBookmarks(searchField.getText(), folder, title, 1);
				popOver.hide();
			});
			newFolderMarkFolder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

				
				TextInputDialog dialog = new TextInputDialog("All Bookmarks");

				dialog.setTitle("Create New Folder");
				dialog.setHeaderText("Create New Folder");
				dialog.setContentText("Please enter folder name:");
				Optional<String> result = dialog.showAndWait();

				result.ifPresent(name ->{
					if(title==null){
						System.out.println("null title");
						title = webEngine.getTitle();
					}
					if(name!=null && !name.isEmpty()){
						this.folder = name;
						options.add(folder);
						BookMarksDataBase.insertBookmarks(searchField.getText(), folder,title,1);
					}else{
						Notifications notify = Notifications.create().title("BookMark Folder")
								.text("No Folder specified.")
								.hideAfter(javafx.util.Duration.seconds(1))
								.position(Pos.BOTTOM_RIGHT);
						notify.darkStyle();
						notify.showInformation();
					}
							});
				
				
			});
		});

		download.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

		});

	}// end intializer method



	public void pageRender(String url) {

		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {

					searchField.setText(webEngine.getLocation());

					// The name of current tab set in Window
					//MainClass.getStage().setTitle(webEngine.getTitle());
					
					//Here According to the client we are setting the name of browse window
					Main.getStage().setTitle("Jfx Browser");


					// The backword & forward tab disabled and enable when index
					// 0 disabled and when > 0 then enabled

					if (history.getCurrentIndex() == 0) {
						back.setDisable(true);
						forward.setDisable(true);
						if(history.getEntries().size()>1){
							forward.setDisable(false);	
						}
					}

					if (history.getCurrentIndex() > 0) {
						back.setDisable(false);
						forward.setDisable(false);
					}

					if ((history.getCurrentIndex()+1) == history.getEntries().size()) {
						forward.setDisable(true);
					}

					URL domain = null;
					if (!(webEngine.getLocation().equals("about:blank")))
						try {
							domain = new URL(webEngine.getLocation());
						} catch (MalformedURLException e) {
							System.err.println("Invalid domain.");
						}

					String userEmail  = CRUD.getCurrentUserEmail();

					HistoryManagment.insertUrl(userEmail,webEngine.getLocation(), domain.getHost(), webEngine.getTitle());


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