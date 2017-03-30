package controllers;

import java.awt.Desktop.Action;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
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
    private TableView table;

    Connection connection;
	public DownloadController(){
    	connection = SqliteConnection.Connector();
    	if(connection==null)
    		System.exit(1);
		
	    
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
		
		downloadMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			System.out.println("hellow");
			String url=null,filename=null,status=null ;
			ResultSet resultSet = downloadHistory();
			
			try {
				while(resultSet.next()){
					url = resultSet.getString(1);
					status = resultSet.getString(2);
					filename = resultSet.getString(3);
					
					System.out.println(url);
					System.out.println(status);
					System.out.println(filename);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(url+filename+status);
		});
		
		
		// TODO Auto-generated method stub
		table.setOpacity(.8);
		TableColumn col1 = new TableColumn("File Name");
		col1.setMinWidth(150);
		
		//col1.setStyle("-fx-background-color: #2959cb");
		
		
		TableColumn col2 = new TableColumn("Status");
		col2.setMinWidth(150);
		//col2.setStyle("-fx-background-color:#2959ea");
		TableColumn col3 = new TableColumn("Url");
		col3.setMinWidth(800);
		//col3.setStyle("-fx-background-color:#2959ea");
		table.getColumns().addAll(col1,col2,col3);
				
	}
	public Tab getDownloadView(Tab downloadTab, BorderPane borderPaneDownload) {


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
