package userInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.History_Managment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class History
{
	@FXML
	private TreeView Tree_View=new TreeView();
	@FXML
	private TreeTableColumn Date=new TreeTableColumn();
	@FXML
	private TreeTableColumn Links=new TreeTableColumn();
	@FXML
	private TreeTableColumn Time=new TreeTableColumn();
	
	HistoryTree hs=new HistoryTree();
	ArrayList children=hs.getStoreItems();
	TreeItem rootItem = new TreeItem("History");
	
	
	public Tab getHistoryView(Tab settingTab, BorderPane borderpane)
	{
		
		rootItem.getChildren().addAll(children);
		Tree_View.setRoot(rootItem);
		borderpane.setLeft(Tree_View);
		
		try{
		borderpane.setCenter(FXMLLoader.load(getClass().getResource("History.fxml")));
		}	
		catch(Exception e1)
		{
		System.out.println("History NOT FOUND..!"+ " \n "+e1);
		e1.printStackTrace();
		}	
		settingTab.setContent(borderpane);
		Tree_View.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
		return settingTab;
	}
	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
    handleMouseClicked(event);
	};
	


	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
	    // Accept clicks only on node cells, and not on empty spaces of the TreeView
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
	        String name = (String) ((TreeItem)Tree_View.getSelectionModel().getSelectedItem()).getValue();
	        if(name.equals("History"))
	        {
	        	ResultSet rs=History_Managment.showHistory();
	        	showAllHistory(rs);
	        }
	    }
	   
		
	}
	public void showAllHistory(ResultSet rs)
	{
		try {
			while(rs.next()) //loop for data fetching and pass it to GUI table view
			 {
				 String link1 =rs.getString(1);
				 String time1=rs.getString(2);
				 String date1=rs.getString(3);
				 System.out.println(link1);
				 System.out.println(time1);
				 System.out.println(date1);
			 } 
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}

	
	/*@FXML
	private TreeView Tree_View;
	HistoryTree hs=new HistoryTree();
	TreeItem rootItem = new TreeItem("History");
	ArrayList children=hs.getStoreItems();
	
	
	/*
	
	private ObservableList<HistoryStoreView> historyStoreView =  FXCollections.observableArrayList();
	HistoryTree hs=new HistoryTree();
	TreeView treeView = new TreeView();
	TreeItem rootItem = new TreeItem("History");
	
	public Tab getHistoryView(Tab settingTab, BorderPane borderpane) {
		ArrayList children=hs.getStoreItems();
		rootItem.getChildren().addAll(children);
		treeView.setRoot(rootItem);
		treeView.setPrefSize(100,100);
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
		borderpane.setLeft(treeView);
		
		try{
			//borderpane.setCenter(table);
			borderpane.setCenter(FXMLLoader.load(getClass().getResource("History.fxml")));
			borderpane.setCenter(treeTableView);
			borderpane.setMinSize(600, 400);
			borderpane.setMaxSize(1024, 800);
		}catch(Exception e1){
			System.out.println("History NOT FOUND..!"+ " \n "+e1);
			e1.printStackTrace();
		}
		
		settingTab.setContent(borderpane);
		return settingTab;
	}

	//This is a Javafx Table view
	@FXML
    private JFXTreeTableView<HistoryStoreView> treeTableView;
	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
	    handleMouseClicked(event);
	};
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
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
		link.setMinWidth(600);
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
		
		
		//ObservableList<HistoryStoreView> historyStoreView =  FXCollections.observableArrayList();
		//Give data to table as an example to checking its working fin
		final TreeItem <HistoryStoreView> root = new RecursiveTreeItem<HistoryStoreView>(historyStoreView,RecursiveTreeObject::getChildren);
		treeTableView.getColumns().setAll(date,link,time);
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
		
	}
	
	//There is class for data entry in table 
	
	class HistoryStoreView extends RecursiveTreeObject<HistoryStoreView>{
		StringProperty date;
		StringProperty link;
		StringProperty time;
		
		public HistoryStoreView(String date,String link, String time ){
			this.date = new SimpleStringProperty(date);
			this.link = new SimpleStringProperty(link);
			this.time = new SimpleStringProperty(time);
		}
	}
	
	
	
	
	private void handleMouseClicked(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    // Accept clicks only on node cells, and not on empty spaces of the TreeView
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
	        String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
	        if(name.equals("History"))
	        {
	        	ResultSet rs=History_Managment.showHistory(); //method call to fetch the data from history table.
	    		try {
	    			while(rs.next()) //loop for data fetching and pass it to GUI table view
	    			 {
	    				 String link1 =rs.getString(1);
	    				 String time1=rs.getString(2);
	    				 String date1=rs.getString(3);
	    				 System.out.println(link1);
	    				 System.out.println(time1);
	    				 System.out.println(date1);
	    				 historyStoreView.add(new HistoryStoreView(date1,link1,time1));
	    				 
	    			 }
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	        	
	        }
	
	    }
	}*/
	//}



