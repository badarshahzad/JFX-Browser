package controllers;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import database.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController  extends Application implements Initializable {

	

    @FXML
    private Pane signUpPane;

	@FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;
    
    @FXML
    private JFXTextField pin;

    @FXML
    private JFXButton signUpBt;

    @FXML
    private JFXButton cancel;

    Connection connection;
    public SignUpController() {
		// TODO Auto-generated constructor stub
    	connection = SqliteConnection.Connector();
    	if(connection==null)
    		System.exit(1);
	}
    
    /*
     * For the sake of test and devloping the pin will be String later according to team decesion this
     * will be change
     */
	public boolean insertNewAccount(String firstName, String username, String password,String pin){
		PreparedStatement prepareStatment = null;
		String query = "INSERT INTO login(username,password,name,pin) VALUES (?,?,?,?)";
		try {
			prepareStatment = connection.prepareStatement(query);
			prepareStatment.setString(1, username);
			prepareStatment.setString(2, password);
			prepareStatment.setString(3, firstName);
			prepareStatment.setString(4, pin);
			prepareStatment.executeUpdate();

			return true;
		}catch (Exception e) {
			System.out.println("Exception in Login:" + e);
			// TODO: handle exception
		} finally {
			try {
				//connection.close();
				prepareStatment.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
		//----------Sign up button working
		signUpBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e4)->{
			
		boolean flag = getFirstname().getText().isEmpty() | getEmail().getText().isEmpty() |
						getPin().getText().isEmpty() | getPassword().getText().isEmpty();
			
		if(flag){
			
			Notifications noti= Notifications.create()
    				.title("Empty Field")
    				.text("Please fill the empty field!")
    				//.graphic(new ImageView(null))
    				.hideAfter(Duration.seconds(5))
    				.position(Pos.TOP_RIGHT);
			noti.showError();

		}else{
			if(insertNewAccount(getFirstname().getText(),getEmail().getText(), getPassword().getText(),getPin().getText())){
			Notifications noti= Notifications.create()
    				.title("Successfull")
    				.text("Congratulation! You successfully Create an Account.")
    				//.graphic(new ImageView(null))
    				.hideAfter(Duration.seconds(5))
    				.position(Pos.TOP_RIGHT);
			noti.showInformation();
			}
		}
		
		});
		
		cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (e5)->{
			//To close the login pane
			controllers.SettingController.getLoginSignInStage().close();
			System.exit(0);
			
		});
		
	}
	
	public Pane getSignUpPane() {
		return signUpPane;
	}



	public void setSignUpPane(Pane signUpPane) {
		this.signUpPane = signUpPane;
	}



	public JFXTextField getFirstname() {
		return firstname;
	}



	public void setFirstname(JFXTextField firstname) {
		this.firstname = firstname;
	}



	public JFXTextField getPin() {
		return pin;
	}



	public void setPin(JFXTextField pin) {
		this.pin= pin;
	}



	public JFXTextField getEmail() {
		return email;
	}



	public void setEmail(JFXTextField email) {
		this.email = email;
	}



	public JFXPasswordField getPassword() {
		return password;
	}



	public void setPass(JFXPasswordField pass) {
		this.password = pass;
	}



	public JFXButton getSignUpBt() {
		return signUpBt;
	}



	public void setSignUpBt(JFXButton createacc) {
		this.signUpBt = createacc;
	}



	public JFXButton getCancel() {
		return cancel;
	}



	public void setCancel(JFXButton cancel) {
		this.cancel = cancel;
	}

	
	public static void main(String args[]){
		System.out.println("Yes");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/ui/SignUp.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


}
