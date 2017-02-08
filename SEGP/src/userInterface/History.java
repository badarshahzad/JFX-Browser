package userInterface;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class History implements Initializable{
	
	public Tab getHistoryView(Tab settingTab, BorderPane borderpane) {

		JFXButton setting = new JFXButton("Setting");setting.setMinSize(100, 50);
		JFXButton history = new JFXButton("History");history.setMinSize(100, 50);

		/*
		 * Add two buttons in gridpane that will be put in
		 * drawer->drawerstack(container) -> left side of border to come ount
		 * whenever user click the setting button
		 */
		GridPane gridPane = new GridPane();
		gridPane.add(setting, 0, 0);
		gridPane.add(history, 0, 1);

		// ------------------------------------------------------Right----DrawerStack--------------------------------

		// Alredy detialed mention in Hamburger class about JFx DrawerStack and
		// JFXDrawer
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		JFXDrawer leftDrawer = new JFXDrawer();
		leftDrawer.setDirection(DrawerDirection.LEFT);
		leftDrawer.setDefaultDrawerSize(80);
		leftDrawer.setSidePane(gridPane);
		leftDrawer.setResizableOnDrag(true);

		borderpane.setLeft(drawersStack);
		try{
			
			borderpane.setCenter(FXMLLoader.load(getClass().getResource("History.fxml")));
			//borderpane.setCenter(treeTableView);
			//borderpane.setMinSize(600, 400);
			//borderpane.setMaxSize(1024, 800);
		}catch(Exception e1){
			System.out.println("File is not find for setting! "+ " \n "+e1);
			e1.printStackTrace();
		}
		
		
		drawersStack.toggle(leftDrawer);
		settingTab.setContent(borderpane);
		return settingTab;
	}

	//This is a Javafx Table view
	@FXML
    private JFXTreeTableView<HistoryStoreView> treeTableView;
	
	//JFXTreeTableView<HistoryStoreView> treeTableView = new JFXTreeTableView<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//For every column we have to create a new Treetable column as we did below and we are getting the
		//the value of cloumn from below method that is ObservableValue method declare below
		
		
		//Date Colun Header: column Header name is set in parametters, widht of column and we
		//are getting the values by constructor method as return value from HistoryStorageViewClass
		//setCellVallueFactory method is to just get the Name of class in Callback method parameters
		//to get value from class constructor 
		//Furthur detials is hardly to explain please look at API.  
		JFXTreeTableColumn<HistoryStoreView, String> date =  new JFXTreeTableColumn<>("Date ");
		date.setMinWidth(120);
		date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
				return param.getValue().getValue().date;
			}
		});
		
		//link column
		JFXTreeTableColumn<HistoryStoreView, String> link =  new JFXTreeTableColumn<>("Link");
		link.setMinWidth(400);
		link.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
				return param.getValue().getValue().link;
			}
		});
		
		//Time column
		JFXTreeTableColumn<HistoryStoreView, String> time =  new JFXTreeTableColumn<>("Time");
		time.setMinWidth(150);
		time.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
				return param.getValue().getValue().time;
			}
		});
		
		
		
		//Give data to table as an example to checking its working fine
		ObservableList<HistoryStoreView> historyStoreView =  FXCollections.observableArrayList();
		//historyStoreView.add(getHistory());
		historyStoreView.add(new HistoryStoreView("1", "http://www.google.com", "2:00pm"));
		
		/*historyStoreView.add(new HistoryStoreView("1", "http://www.google.com", "1:00pm"));
		historyStoreView.add(new HistoryStoreView("1", "http://www.google.com", "2:00pm"));
		historyStoreView.add(new HistoryStoreView("1", "http://www.google.com", "1:00pm"));*/
		
		final TreeItem <HistoryStoreView> root = new RecursiveTreeItem<HistoryStoreView>(historyStoreView,RecursiveTreeObject::getChildren);
		treeTableView.getColumns().setAll(date,link,time);
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
		
	}
	
	//Set History method is set by main browser class and can easily set the history 
	String dateTxt,linkTxt,timeTxt;
	public void setHistory(String date, String link, String time){
		this.dateTxt = date ;
		this.linkTxt = link ;
		this.timeTxt = time ;
		System.out.println("Time: "+ this.timeTxt+ "\n"+ "Date :" + this.dateTxt +"\n"+"Link: "+this.linkTxt);
		
	}
	
	//get History method will return the date, link, time
	public HistoryStoreView getHistory(){
		System.out.println("Get date time");
		return new HistoryStoreView(dateTxt, linkTxt, timeTxt);
	}
	
	
	//There is class for data entry in table 
	
	class HistoryStoreView extends RecursiveTreeObject<HistoryStoreView>{
		
		// below these are the colums for tables and values can be enter useing the constructor
		// method o fHistoryStoreView that is mention below!
		StringProperty date;
		StringProperty link;
		StringProperty time;
		
		public HistoryStoreView(String date,String link, String time ){
			this.date = new SimpleStringProperty( date);
			this.link = new SimpleStringProperty(link);
			this.time = new SimpleStringProperty(time);
		}
	}

}
