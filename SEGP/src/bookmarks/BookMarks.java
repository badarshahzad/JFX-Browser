package bookmarks;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import database.BookMarksDataBase;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BookMarks {

	private JFXTextField searchBar;
	private JFXButton searchButton;
	private TableColumn<URLdetails, String> nameCol =  new TableColumn<>("name");
	private TableColumn<URLdetails, String> timeCol =  new TableColumn<>("time");
	private TableColumn<URLdetails, String> dateCol =  new TableColumn<>("date");
	private TableColumn<URLdetails, String> locationCol =  new TableColumn<>("location");
	private TableView<URLdetails> table = new TableView<>();	
	public static ObservableList<URLdetails> list = FXCollections.observableArrayList();
	private Image folderImage = new Image(getClass().getResourceAsStream("folder.png"));
	private TreeTableView<String> treeView  = new TreeTableView<>();
	private TreeTableColumn<String, String> bookMarkCol = new TreeTableColumn<>("BookMarks");
	TreeItem parentFolder = new TreeItem<>("All Bookmarks",new ImageView(folderImage));
	private ObservableList<String> folders = BookMarksDataBase.folders();
 	
	public BookMarks(){
		for(int i=0 ; i< folders.size();i++){
			System.out.println(i);
			parentFolder.getChildren().add(new TreeItem<>(folders.get(i),new ImageView(folderImage)));
		}
		bookMarkCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<String, String> param) {
				return new SimpleStringProperty(param.getValue().getValue());
			}
		});		
		nameCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("Title"));
		locationCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("Url"));
		dateCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("time"));
		treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue,
					Object newValue) {

				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				System.out.println("Selected Text : " + selectedItem.getValue());
				ObservableList<URLdetails> list = new PopulateTable().PopulateTable(selectedItem.getValue());
				table.setItems(list);
			}

		});
		bookMarkCol.setPrefWidth(150);
		nameCol.setPrefWidth(200);
		timeCol.setPrefWidth(150);
		dateCol.setPrefWidth(150);
		locationCol.setPrefWidth(300);

		treeView.getColumns().add(bookMarkCol);
		treeView.setRoot(parentFolder);
		table.getColumns().addAll(nameCol,locationCol,dateCol,timeCol);
		table.setItems(list);
	}
	public Tab getBookmarkView(Tab bookmarkTab, BorderPane borderPaneBookmark) {
		treeView.setMinWidth(150);
		treeView.setMaxWidth(150);
		borderPaneBookmark.setLeft(treeView);
		borderPaneBookmark.setCenter(table);
		bookmarkTab.setContent(borderPaneBookmark);
		return bookmarkTab;
	}


}
