/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segp3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Segp-Group 3
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private TabPane tabpane;
    
   
    @FXML
    private JFXButton back = new JFXButton();
    

    @FXML
    private JFXButton farward;

    @FXML
    private JFXTextField searchField;
    
    @FXML
    private JFXButton search;
   
    @FXML
    private Tab addNewTab;
    
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
    		System.out.println("ajdda");
    	});
    	
    }    
    
}
