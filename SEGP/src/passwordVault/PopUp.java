package passwordVault;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import controllers.TabController;
import database.BookMarksDataBase;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUp{

	public PopUp(Node download){
		VBox popUpContent = new VBox();
		popUpContent.setMinSize(100, 100);
		popUpContent.setSpacing(5);
		popUpContent.setPadding(new Insets(5, 5, 5, 5));
		Label label = new Label("would you like to save password for this account? \n ******");
		JFXButton cancelPopup = new JFXButton("Cancel");
		cancelPopup.setMinSize(150, 30);
		JFXButton save = new JFXButton("Save");
		save.setMinSize(150, 30);
		HBox hbox = new HBox();
		hbox.getChildren().addAll(cancelPopup,save);
		VBox.setMargin(label, new Insets(5, 5, 5, 5));
		popUpContent.getChildren().addAll(label,hbox);
		PopOver popOver = new PopOver(new JFXButton("Yes"));
		popOver.setCornerRadius(4);
		popOver.setContentNode(popUpContent);
		popOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
		popOver.show(download);
		popOver.detach();	
		cancelPopup.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler ->{
			popOver.hide();
		});
		save.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			//database save.
		});
		
		//		
	}

	
	

}
