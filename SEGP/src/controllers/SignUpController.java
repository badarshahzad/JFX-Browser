package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import database.CRUD;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController implements Initializable {

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
	
	@FXML
	private JFXButton backLoginView;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		backLoginView.setText("");
		backLoginView.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Main.IMAGES+"backword1.png"))));

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
		this.pin = pin;
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
	
	public JFXButton getBackLoginView() {
		return backLoginView;
	}

	public void setBackLoginView(JFXButton backLoginView) {
		this.backLoginView = backLoginView;
	}


	/*
	 * public static void main(String args[]){ System.out.println("Yes");
	 * launch(args); }
	 * 
	 * @Override public void start(Stage primaryStage) throws Exception { //
	 * TODO Auto-generated method stub Parent root =
	 * FXMLLoader.load(getClass().getResource("/ui/SignUp.fxml")); Scene scene =
	 * new Scene(root); primaryStage.setScene(scene); primaryStage.show();
	 * 
	 * }
	 */

}
