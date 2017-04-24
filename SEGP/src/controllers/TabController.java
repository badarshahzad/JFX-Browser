package controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import database.BookMarksDataBase;
import database.HistoryManagment;
import downloader.MainDownload;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Segp-Group 3
 */
public class TabController implements Initializable

 {

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
	private Label htmlAsPdf;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private GridPane navigationBar;
	@FXML
	private JFXProgressBar progressbar;
	private String folder;
	String title;
	ObservableList<String> options;
	MainController mainController = new MainController();
	// Classes objects to get methods or set methods access
	private Hamburger ham = new Hamburger();
	public VBox drawerPane = new VBox();

	public WebView browser = new WebView();
	public WebEngine webEngine = browser.getEngine();

	public Worker<Void> worker;

	private TabPane tabPane = mainController.getTabPane();
/*
	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}*/
	
	// we made this webgine object to get access the current url of webpage
	static WebEngine engine;
	public void setWebEngine(WebEngine webEngine){
		engine = webEngine;
	}
	public static WebEngine getWebEngine(){
		return engine;
	}

	private DetectForm detectForm = new DetectForm();
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		setWebEngine(webEngine);
		
		back.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/backword1.png"))));
		forward.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/forward1.png"))));
		refresh.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/refresh.png"))));
		search.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/search.png"))));
		search.setOpacity(.5);
		download.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/download.png"))));
		download.setOpacity(.6);
		bookmark.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/bookmark.png"))));
		bookmark.setOpacity(.7);
		htmlAsPdf.setGraphic(new ImageView(new Image (getClass().getResourceAsStream("/pdfConverter.png"))));
		
		
		
		
		// Worker load the page
		worker = webEngine.getLoadWorker();
		worker.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				System.out.println("Loading state: " + newValue.toString());
				if (newValue == Worker.State.SUCCEEDED) {
					System.out.println("Finish!");
					org.w3c.dom.Document doc = webEngine.getDocument();
					detectForm.detect(doc);
//					String imgs = "";
//					 File f = new File(getClass().getResource("/screenshots").getFile());
//				        for (File fs : f.listFiles()) {
//				            imgs += "<img src=\""+fs.toURI()+"\" width='100' height = '100' />";
//				        }
//				        System.out.println("images:"+imgs);
//				        System.out.println("Hello \"" + imgs + "\"");
////				        webEngine.loadContent("<div>"+imgs+"</div>");
//					String div = "var body =  document.getElementsByTagName('body')[0];"
//							+ "var newElement = document.createElement('div');"
//							+ "var center = document.createElement('center');"
//							+ "newElement.append = "
//							+ "newElement.setAttribute('id', 'custom_div');"
//							+ "center.appendChild(newElement);"
//							+"body.appendChild(center);";
////					webEngine.executeScript("document.getElementsByTagName('body')[0].innerHTML =\"" + imgs + "\"");
//					webEngine.executeScript("if (!window.indexedDB) window.alert(\"Your browser doesn't support a stable version of IndexedDB.\")");			
					}
				
			}
		});
		/*location property to get the location of the webview.
		 * 
		 * 
		 * 
		 * */
		 
		webEngine.locationProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("location of engine: "+newValue);
				dwnlod.startDownload(newValue,webEngine.getTitle());
			
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
		
		//Tabpane listener for new tab or for security password:  @testing phase 
		tabPane.setOnKeyPressed(event->{
			/*
			switch (event.getCode() ) {
			case T :
			case CONTROL:
				System.out.println(event.getCode());
				break;
			}*/
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
			

			Label bookmarksLabel = new Label("Bookmarks");
			VBox popUpContent = new VBox();
			
			popUpContent.setMinSize(300, 250);
			popUpContent.setSpacing(5);
			popUpContent.setPadding(new Insets(5, 5, 5, 5));
			Label nameLabel = new Label("Name");
			JFXTextField markNameText = new JFXTextField();
			markNameText.setText(webEngine.getTitle());
			Label folderLabel = new Label("Folder");
			options =  BookMarksDataBase.folders();
			JFXComboBox<String> markFolderList = new JFXComboBox<>(options);
			markFolderList.setMinWidth(300);
			markFolderList.getSelectionModel().select(0);
			JFXButton cancelPopup = new JFXButton("Cancel");
			cancelPopup.setMinSize(100, 50);
			JFXButton newFolderMarkFolder = new JFXButton("New Folder");
			newFolderMarkFolder.setMinSize(100, 50);
			JFXButton saveMark = new JFXButton("Save");
			saveMark.setMinSize(100, 50);
			HBox hbox = new HBox();
			hbox.getChildren().addAll(cancelPopup, newFolderMarkFolder,saveMark);
			//markFolderList.setVisibleRowCount(0);
			
			VBox.setMargin(bookmarksLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(nameLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(markNameText, new Insets(5, 5, 5, 5));
			VBox.setMargin(folderLabel, new Insets(5, 5, 5, 5));
			VBox.setMargin(markFolderList, new Insets(5, 5, 5, 5));
			
			popUpContent.getChildren().addAll(bookmarksLabel,nameLabel,markNameText,folderLabel,markFolderList,hbox);
			
			PopOver popOver = new PopOver(new JFXButton("Yes"));

			//popOver.getRoot().getStylesheets().add("Yes");
			//popOver.setDetachable(true);
			popOver.setCornerRadius(4);
			popOver.setContentNode(popUpContent);
			//popOver.setMinSize(400, 400);
			popOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			popOver.show(bookmark);
			
			cancelPopup.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler ->{
				popOver.hide();
			});
			saveMark.addEventHandler(MouseEvent.MOUSE_CLICKED, (s)->{
				System.out.println(folder);
				if(folder==null){
					folder=markFolderList.getItems().get(0);
				}
				 title = markNameText.getText();
				if(title==null){
					title = webEngine.getTitle();
				}
				BookMarksDataBase.insertBookmarks(searchField.getText(), folder,title,1);
				popOver.hide();
			});
			newFolderMarkFolder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				
				TextInputDialog dialog = new TextInputDialog("walter");
				dialog.setTitle("Create New Folder");
				dialog.setHeaderText("Create New Folder");
				dialog.setContentText("Please enter folder name:");
				Optional<String> result = dialog.showAndWait();
				result.ifPresent(name ->{
					if(!name.equals("")){
						this.folder = name;
						options.add(folder);
					}
					if(title==null){
						title = webEngine.getTitle();
					}
					BookMarksDataBase.insertBookmarks(searchField.getText(), folder,title,1);
							});
				
				
			});
		});
		
		
		download.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			
		});

	}// end intializer method

	// mehtod to rendere page
	MainDownload dwnlod = new MainDownload();
	public void pageRender(String url) {
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
					URL domain = null;
					if (!(webEngine.getLocation().equals("about:blank")))
						try {
							domain  = new URL(webEngine.getLocation());
						} catch (MalformedURLException e) {
							System.err.println("Invalid domain.");
						}
						HistoryManagment.insertUrl(webEngine.getLocation(),domain.getHost(),webEngine.getTitle());
					
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