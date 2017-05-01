package controllers;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.DownloadDatabase;
import database.SqliteConnection;
import downloader.download;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DownloadController  extends Application implements Initializable{

	@FXML
	private BorderPane borderPane;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton downloadMenu;

	@FXML
	private JFXButton search;

	@FXML
	private JFXButton clean;

	@FXML
	private JFXButton cancel;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableView<download> table;

	private ObservableList<download>  list = FXCollections.observableArrayList();

	Connection connection;
	public DownloadController(){
		connection = SqliteConnection.Connector();
		if(connection==null)
			System.exit(1);


	}

	public void getDownloadHistory(int userId) throws SQLException{
		ResultSet result = DownloadDatabase.getDownload(userId);
		while(result.next()){
			list.add(new download(result.getString(1),result.getString(2), result.getString(3)));
		}

	}

	public ResultSet downloadHistory() {

		PreparedStatement prepareStatment = null;
		ResultSet resultSet = null;
		String query = "Select * from download";
		try {
			prepareStatment = connection.prepareStatement(query);
			resultSet = prepareStatment.executeQuery();

			return resultSet;
		} catch (Exception e) {
			System.out.println("Exception in Login:" + e);
			// TODO: handle exception
		} finally {
			try {
				prepareStatment.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultSet;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override

	public void initialize(URL location, ResourceBundle resources) {

		table.setEditable(false);

		downloadMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			System.out.println("hellow");
			String url=null,filename=null,status=null ;
		});
		table.setOpacity(.8);
		TableColumn<download, String> url =  new TableColumn<>(" Url ");
		TableColumn<download, String> fileName =  new TableColumn<>(" Name ");
		TableColumn<download, String> fileStatus =  new TableColumn<>(" Status ");

		url.setCellValueFactory(new PropertyValueFactory<download,String>("url"));
		fileName.setCellValueFactory(new PropertyValueFactory<download,String>("fileName"));
		fileStatus.setCellValueFactory(new PropertyValueFactory<download,String>("fileStatus"));
		url.setPrefWidth(300);
		fileName.setPrefWidth(250);
		fileStatus.setPrefWidth(250);
		try {
			getDownloadHistory(1);
		} catch (SQLException e1) {
			System.out.println("unable to fetch data from download database.");
		}
		table.getColumns().addAll(fileName,fileStatus,url);
		table.setItems(list);
	}


	/*public Tab getDownloadView(Tab downloadTab, BorderPane borderPaneDownload) {


		try {
			borderPaneDownload.setCenter(FXMLLoader.load(getClass().getResource("/ui/Downloads.fxml")));
		} catch (Exception e1) {
			System.out.println("File is not find for setting downloads ! " + " \n " + e1);
			e1.printStackTrace();
		}
		// settingTab.setContent(borderpane);

		downloadTab.setContent(borderPaneDownload);

		return downloadTab;
	}
	 */	
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/Downloads.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
