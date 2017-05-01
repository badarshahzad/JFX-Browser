package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import database.HistoryManagment;
import database.SqliteConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import main.MainClass;
import userInterface.HistoryTree;

public class HistoryController implements Initializable {

	@FXML
	private BorderPane borderPaneHistory;
	@FXML
	private JFXTreeTableView<HistoryStoreView> table;

	@FXML
	private Label selectedDeleteEntries;

	@FXML
	private JFXTextField search;

	@FXML
	private VBox paneForChart;

	@FXML
	private PieChart historyPieChart;

	@FXML
	private VBox vBox;

	@FXML
	private TreeView treeView;
	@FXML
	private HBox hBox;

	@FXML
	private JFXButton deleteSingle;

	private ArrayList<TreeItem> storeItems;

	private TreeItem rootItem;

	JFXTreeTableColumn<HistoryStoreView, String> dateCol = new JFXTreeTableColumn<HistoryStoreView, String>("Date");

	JFXTreeTableColumn<HistoryStoreView, String> linkCol = new JFXTreeTableColumn<HistoryStoreView, String>("Links");

	JFXTreeTableColumn<HistoryStoreView, String> timeCol = new JFXTreeTableColumn<HistoryStoreView, String>("Time");
	JFXTreeTableColumn<HistoryStoreView, String> domainCol = new JFXTreeTableColumn<HistoryStoreView, String>(
			"Domain Name");
	JFXTreeTableColumn<HistoryStoreView, String> titleCol = new JFXTreeTableColumn<HistoryStoreView, String>("Title");

	// private static TabPane tabPane;
	// Tab addTab;

	// Lists for maintaining different Histories
	private static ObservableList<HistoryStoreView> fullHistory = FXCollections.observableArrayList();
	private ObservableList<HistoryStoreView> pastHours = FXCollections.observableArrayList();
	private ObservableList<HistoryStoreView> todayHistory = FXCollections.observableArrayList();
	private ObservableList<HistoryStoreView> yesterdayHistory = FXCollections.observableArrayList();
	private ObservableList<HistoryStoreView> pastWeekHistory = FXCollections.observableArrayList();
	private ObservableList<HistoryStoreView> pastMonthHistory = FXCollections.observableArrayList();
	
	private Image folderImage = new Image(getClass().getResourceAsStream("/folder.png"));

	private StringProperty selectedItem ;

	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
		handleMouseClicked(event);
	};

	private boolean check = false;

	private ArrayList<String> selectedValues  = new ArrayList<>();;

	boolean daikh = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		search.setPromptText("Search History");

		table.setEditable(false);
		table.setId("treetable");

		selectedDeleteEntries.setVisible(false);


		historyPieChart.setTitle("Most Visited Sites");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Facebook", 30)
				,new PieChart.Data("Google", 60)
				,new PieChart.Data("Twitter", 20)
				,new PieChart.Data("Gmail", 30));

		historyPieChart.setData(pieChartData);

		table.setOnKeyPressed((event)->{

			if (event.isControlDown() ) {


				table.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
					daikh = true;
				});

				table.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
					if(daikh){
						try{
							selectedItem = table.getSelectionModel().getSelectedItem().getValue().link1;

							boolean entery = true;

							for (int a = 0; a < selectedValues.size(); a++) {
								if (selectedValues.get(a).equals(selectedItem.getValue())) {
									entery = false;

								}
							}

							if (entery==true) {
								selectedDeleteEntries.setVisible(true);
								selectedValues.add(selectedItem.getValue());
								selectedDeleteEntries.setText("Selected "+selectedValues.size());
								entery = false;
							}



						}catch (Exception tableEmpty){

							Notifications.create()
							.title("History ")
							.text("No row has been selected or you have selected \n"
									+ "same row again in history table.")
							.hideAfter(Duration.seconds(3))
							.showWarning();
							return;
						}
					}
				});

			}

		});

		table.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{

			try{
				selectedItem = table.getSelectionModel().getSelectedItem().getValue().link1;

				boolean entery = true;

				for (int a = 0; a < selectedValues.size(); a++) {
					if (selectedValues.get(a).equals(selectedItem.getValue())) {
						entery = false;

					}
				}

				if (entery==true) {
					selectedDeleteEntries.setVisible(true);
					selectedValues.add(selectedItem.getValue());
					selectedDeleteEntries.setText("Selected "+selectedValues.size());

					entery = false;
				}



			}catch (Exception tableEmpty){

				Notifications.create()
				.title("History ")
				.text("No row has been selected or you have selected \n"
						+ "same row again in history table.")
				.hideAfter(Duration.seconds(3))
				.showWarning();
				return;
			}

		});

		deleteSingle.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			if (selectedValues.size()<=0) {
				System.out.println("Empty not selected");
				return;
			} else {
				for (int a = 0; a < selectedValues.size(); a++) {
					System.out.println(selectedValues.get(a));
					SqliteConnection.excuteQuery(selectedValues.get(a));
					selectedValues.remove(a);

					// Update the history table
					ObservableList<HistoryStoreView> fullHistory = FXCollections.observableArrayList();
					fullHistory = SqliteConnection.getFullHistory(fullHistory);
					addListInTable(fullHistory);
					table.refresh();
				}
			}

			selectedDeleteEntries.setVisible(false);

			/*			
			if(check){
				try{
				selectedItem = table.getSelectionModel().getSelectedItem().getValue().link1;
				}catch (Exception tableEmpty){
					System.out.println("Check table empty");
					Notifications.create()
					.title("History ")
					.text("You do not have data in your history.")
					.hideAfter(Duration.seconds(3))
					.showInformation();
					return;
				}


					SqliteConnection.excuteQuery(selectedItem.getValue());

					// Update the history table
					ObservableList<HistoryStoreView> fullHistory = FXCollections.observableArrayList();
					fullHistory = SqliteConnection.getFullHistory(fullHistory);
					addListInTable(fullHistory);
					table.refresh();

					Notifications.create()
					.title("Successfull")
					.text("Your selected entry has been removed from history.")
					.hideAfter(Duration.seconds(3))
					.showInformation();

					check = false;
			}else{

					Notifications.create()
					.title("Unsuccessfull")
					.text("You did not select any row. Please select \n any row  then delete.")
					.hideAfter(Duration.seconds(3))
					.showWarning();
				return;
			}
			 */
		});



		initializingView();
		initializeListsWithData();

	}
	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		// Accept clicks only on node cells, and not on empty spaces of the
		// TreeView
		if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
			String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
			//System.out.println(name);
			if (name.equals("History")) {
				addListInTable(fullHistory);

			}
			if (name.equals("Past hour")) {
				addListInTable(pastHours);
			}
			if (name.equals("Today")) {
				addListInTable(todayHistory);
			}

			if (name.equals("Yesterday")) {
				addListInTable(yesterdayHistory);
			}

			if (name.equals("Past Week")) {
				addListInTable(pastWeekHistory);
			}

			if (name.equals("Past Month")) {
				addListInTable(pastMonthHistory);
			}

			// table.setMinWidth(600);
			// table.setMaxWidth(800);
			// borderPaneHistory.setCenter(table);

		}
	}

	public void initializeListsWithData() {
		pastHours = HistoryManagment.pastHoursHistory(pastHours, -1);
		todayHistory = HistoryManagment.getHistory(todayHistory, 0);
		yesterdayHistory = HistoryManagment.getHistory(yesterdayHistory, -1);
		pastWeekHistory = HistoryManagment.getHistory(pastWeekHistory, -7);
		pastMonthHistory = HistoryManagment.getHistory(pastMonthHistory, -30);

	}

	public void initializingView() {

		// Border pane left View
		storeItems = HistoryTree.getStoreItems();
		rootItem = new TreeItem("History",new ImageView(folderImage));
		rootItem.getChildren().addAll(storeItems);
		rootItem.setExpanded(true);
		treeView.setRoot(rootItem);
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

		// Border pane Middle view
		fullHistory = HistoryManagment.fullHistoryShow(fullHistory);
		addListInTable(fullHistory);

	}


	public void addListInTable(ObservableList<HistoryStoreView> list) {
		dateCol.setPrefWidth(150);
		dateCol.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().date1;
					}
				});
		linkCol.setPrefWidth(400);
		linkCol.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().link1;
					}
				});
		timeCol.setPrefWidth(150);
		timeCol.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().time1;
					}
				});
		domainCol.setPrefWidth(200);
		domainCol.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().domain1;
					}
				});
		titleCol.setPrefWidth(150);
		titleCol.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryStoreView, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryStoreView, String> param) {
						return param.getValue().getValue().title1;
					}
				});

		final TreeItem<HistoryStoreView> root = new RecursiveTreeItem<HistoryStoreView>(list,
				RecursiveTreeObject::getChildren);

		table.getColumns().setAll(dateCol, linkCol, timeCol, domainCol, titleCol);
		table.setRoot(root);
		table.setShowRoot(false);

	}

	@FXML
	void deleteHistory(MouseEvent event) {

		// confirmation dialogue
		/*	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("History");
		alert.setContentText("Are you want to delete all History ?");
		Optional<ButtonType> result = alert.showAndWait();*/

		JFXButton okBt = new JFXButton("Ok");
		JFXButton cancelBt = new JFXButton("Cancel");

		okBt.setId("mybutton");
		cancelBt.setId("mybutton");

		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Clear History"));
		content.setBody(new Text(" You are going to delete your complete history. After that\n"
				+ "you will not have any single url in your history. Click Ok \n"
				+ "to confirm or click cancel to main window."));
		JFXDialog historyWarn= new JFXDialog(MainClass.getPane(),content,JFXDialog.DialogTransition.CENTER);
		content.setActions(okBt,cancelBt);
		historyWarn.show();	

		okBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6) -> {

			historyWarn.close();

			JFXButton yesBt = new JFXButton("Yes");
			JFXButton noBt = new JFXButton("No");

			JFXDialogLayout confirmContent = new JFXDialogLayout();
			confirmContent.setHeading(new Text("Clear History"));
			confirmContent.setBody(new Text("Do you want to delete your complete history?"));
			JFXDialog historyConfirm = new JFXDialog(MainClass.getPane(), confirmContent, JFXDialog.DialogTransition.CENTER);
			confirmContent.setActions(yesBt, noBt);
			historyConfirm.show();

			yesBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e7) -> {

				table.setRoot(null);
				table.refresh();
				fullHistory.clear();
				pastHours.clear();
				pastMonthHistory.clear();
				pastWeekHistory.clear();
				yesterdayHistory.clear();
				todayHistory.clear();
				historyConfirm.close();

				HistoryManagment.deleteFromDatabase();

			});

			noBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e7) -> {
				historyConfirm.close();
			});

		});
		cancelBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e6) -> {
			historyWarn.close();
		});


		//To show overlay dialougge box

		/*
		if (alert.getResult() == ButtonType.OK) {


			// information dialogue
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("History");
			alert.setHeaderText(null);
			String s = "History has been deleted!";
			alert.setContentText(s);
			alert.show();
		}*/


	}

	@FXML
	public void SearchDataInTable() {
		System.out.println("lalala");
		search.textProperty().addListener((o, oldVal, newVal) -> {
			table.setPredicate(HistoryStoreView -> HistoryStoreView.getValue().time1.get().contains(newVal)
					|| HistoryStoreView.getValue().date1.get().contains(newVal)
					|| HistoryStoreView.getValue().link1.get().contains(newVal));
		});

	}


	public static ObservableList addDataInList(String link, String time, String date, String domain, String title,
			ObservableList list) {
		list.add(new HistoryStoreView(date, link, time, domain, title));
		return list;
	}

}

class HistoryStoreView extends RecursiveTreeObject<HistoryStoreView> {
	StringProperty date1;
	StringProperty link1;
	StringProperty time1;
	StringProperty domain1;
	StringProperty title1;

	public HistoryStoreView(String date, String link, String time, String domain, String title) {
		this.date1 = new SimpleStringProperty(date);
		this.link1 = new SimpleStringProperty(link);
		this.time1 = new SimpleStringProperty(time);
		this.domain1 = new SimpleStringProperty(domain);
		this.title1 = new SimpleStringProperty(title);
	}
}

// method call to fetch the data from history table.

// ----------------------------------------plugging different Views in the
// GUI-------------------------------------------//
