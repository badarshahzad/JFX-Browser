package bookmarks;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BookMarksController implements Initializable{
	
	  @FXML
	    private JFXTextField searchBar;

	    @FXML
	    private JFXButton searchButton;
	    @FXML
	    private TableColumn<URLdetails, String> nameCol;

	    @FXML
	    private TableColumn<URLdetails, String> timeCol;

	    @FXML
	    private TableColumn<URLdetails, String> dateCol;

	    @FXML
	    private TableColumn<URLdetails, String> locationCol;
	    @FXML
	    private TableView<URLdetails> table;	
	public static ObservableList<URLdetails> list = FXCollections.observableArrayList();
	
		private Image folderImage = new Image(getClass().getResourceAsStream("folder.png"));
		 private Image searchImage = new Image(getClass().getResourceAsStream("search.png"));
	 @FXML
	    private TreeTableView<String> treeView;
	    @FXML
	    private TreeTableColumn<String, String> bookMarkCol;
	    TreeItem parentFolder = new TreeItem<>("All Bookmarks",new ImageView(folderImage));
	    TreeItem child1 = new TreeItem<>("Programming",new ImageView(folderImage));
	    TreeItem child2 = new TreeItem<>("Entertainment",new ImageView(folderImage));
	    TreeItem child3 = new TreeItem<>("SEGP",new ImageView(folderImage));
	    TreeItem child11 = new TreeItem<>("Java",new ImageView(folderImage));
	    TreeItem child12 = new TreeItem<>("C++",new ImageView(folderImage));
	    TreeItem child13 = new TreeItem<>("Haskell",new ImageView(folderImage));
	    TreeItem child21 = new TreeItem<>("Youtube",new ImageView(folderImage));
	    TreeItem child22 = new TreeItem<>("facebook",new ImageView(folderImage));
	    TreeItem child23 = new TreeItem<>("Twitter",new ImageView(folderImage));
	    TreeItem child31 = new TreeItem<>("Allah Khair",new ImageView(folderImage));
	    TreeItem child32 = new TreeItem<>("Aaa Haan",new ImageView(folderImage));
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchButton.setGraphic(new ImageView(searchImage));
		child1.getChildren().setAll(child11,child12,child13);
		child2.getChildren().setAll(child21,child22,child23);
		child3.getChildren().setAll(child31,child32);
		parentFolder.getChildren().setAll(child1,child2,child3);
		bookMarkCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<String, String> param) {
				return new SimpleStringProperty(param.getValue().getValue());
			}
		});		
		nameCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("name"));
		locationCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("location"));
		dateCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("time"));
		table.setItems(list);
		treeView.setRoot(parentFolder);
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
			mouseClick(e);
		});
		
		
	}
		

	public void mouseClick(MouseEvent event){
		TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
		if(item!=null && item.getValue().equals("Entertainment")){
		System.out.println(item.getValue());
		ObservableList<URLdetails> list = new PopulateTable().PopulateTable(item.getValue());
		System.out.println("entered");
		table.setItems(list);
		}
	}
}
