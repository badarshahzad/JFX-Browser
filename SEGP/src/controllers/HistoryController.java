package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import database.HistoryManagment;

import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import userInterface.HistoryTree;

public class HistoryController implements Initializable {

	// This is a Javafx Table view
	@FXML
	private JFXTreeTableView<HistoryStoreView> historyTable;

	// private static TabPane tabPane;

	private ArrayList<TreeItem> storeItems = HistoryTree.getStoreItems();
	private TreeView treeView = new TreeView();
	private TreeItem rootItem = new TreeItem("History");

	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
		handleMouseClicked(event);
	};

	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		// Accept clicks only on node cells, and not on empty spaces of the
		// TreeView
		if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
			String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
			
			System.out.println("Node click: " + name);
		}
	}

	public Tab getHistoryView(Tab historyTab, BorderPane borderPaneHistory) {

		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

		rootItem.getChildren().addAll(storeItems);
		treeView.setRoot(rootItem);
		treeView.setMinWidth(150);
		treeView.setMaxWidth(150);
		borderPaneHistory.setLeft(treeView);

		try {
			borderPaneHistory.setCenter(FXMLLoader.load(getClass().getResource("/ui/History.fxml")));
		} catch (Exception e1) {
			System.out.println("File is not find for setting! " + " \n " + e1);
			e1.printStackTrace();
		}
		// settingTab.setContent(borderpane);

		historyTab.setContent(borderPaneHistory);

		return historyTab;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		System.out.println("Ghind maza");
		JFXTreeTableColumn<HistoryStoreView, String> date = new JFXTreeTableColumn<>("Date");
		date.setPrefWidth(150);
		date.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().getDate();
					}
				});

		JFXTreeTableColumn<HistoryStoreView, String> link = new JFXTreeTableColumn<>("Links");
		link.setPrefWidth(250);
		link.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().getLink();
					}
				});

		JFXTreeTableColumn<HistoryStoreView, String> time = new JFXTreeTableColumn<>("Time");
		time.setPrefWidth(150);
		time.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().getTime();
					}
				});
		
		//Database fetching data and showing in table
		ObservableList<HistoryStoreView> historyStoreView = FXCollections.observableArrayList();
		ResultSet rs = HistoryManagment.showHistory(); // method call to fetch
														// the data from history
														// table.
		try {
			while (rs.next()) // loop for data fetching and pass it to GUI table
								// view
			{
				String link1 = rs.getString(1);
				String time1 = rs.getString(2);
				String date1 = rs.getString(3);
				
				historyStoreView.add(new HistoryStoreView(date1, link1, time1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		final TreeItem<HistoryStoreView> root = new RecursiveTreeItem<HistoryStoreView>(historyStoreView,
				RecursiveTreeObject::getChildren);
		historyTable.getColumns().setAll(date, link, time);
		historyTable.setRoot(root);
		historyTable.setShowRoot(false);
	}

	class HistoryStoreView extends RecursiveTreeObject<HistoryStoreView> {
		StringProperty date;
		StringProperty link;
		StringProperty time;

		public HistoryStoreView(String date, String link, String time) {
			this.date = new SimpleStringProperty(date);
			this.link = new SimpleStringProperty(link);
			this.time = new SimpleStringProperty(time);
		}

		public StringProperty getDate() {
			return date;
		}

		public StringProperty getLink() {
			return link;
		}

		public StringProperty getTime() {
			return time;
		}
	}
}
