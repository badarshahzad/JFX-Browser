package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import userInterface.Setting;

/**
 *
 * @author Segp-Group 3
 */
public class LoginController implements Initializable {



    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton signup;


    
    @FXML
    void makelogin(ActionEvent event) {
    	String username=user.getText();
    	String pass=password.getText();
    	if(username.equalsIgnoreCase("hassan") && pass.equals("khan"))
    	{
    		System.out.println("Welcome");
    	}
    	else 
    		System.out.println("Wrong password or username");
    }
    
	@Override
	public void initialize(URL url, ResourceBundle res) {
		// TODO Auto-generated method stub
		
		signup.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			try {
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}


}
