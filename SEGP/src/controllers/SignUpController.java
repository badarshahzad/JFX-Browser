package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SignUpController implements Initializable {

	

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



	public JFXPasswordField getPass() {
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


}
