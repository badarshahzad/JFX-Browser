package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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
    private JFXTextField lastname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signUpBt;

    @FXML
    private JFXButton cancel;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		//----------Sign up button working
		signUpBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (e4)->{
			
		boolean flag = getFirstname().getText().isEmpty() | getEmail().getText().isEmpty() |
						getLastname().getText().isEmpty() | getPassword().getText().isEmpty();
			
		if(flag){
			
			Notifications noti= Notifications.create()
    				.title("Empty Field")
    				.text("Please fill the empty field!")
    				//.graphic(new ImageView(null))
    				.hideAfter(Duration.seconds(5))
    				.position(Pos.TOP_RIGHT);
			noti.showError();

		}
		
		});
		
		cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (e5)->{
			//To close the login pane
			controllers.SettingController.getLoginSignInStage().close();
			
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



	public JFXTextField getLastname() {
		return lastname;
	}



	public void setLastname(JFXTextField lastname) {
		this.lastname = lastname;
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
