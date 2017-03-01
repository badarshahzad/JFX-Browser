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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SignUpController implements Initializable {

	@FXML
    private BorderPane borderpane;
	
	@FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXButton createacc;

    @FXML
    private JFXButton cancel;




    @FXML
    public void createaccount(ActionEvent event) {
    	String f=firstname.getText();
    	String l=lastname.getText();
    	String e=email.getText();
    	String p=pass.getText();
    	System.out.println(f+" "+l+"\n"+e+"\nSuccesfully created Account");
    	
    	
    }
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
	}
/*
	public void setSignUpPane(BorderPane borderpane2) {
		try {
			borderpane2.setCenter(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/



	

}
