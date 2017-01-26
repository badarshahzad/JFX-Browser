package ui;


import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Tab;
import segp3.FXMLDocumentController;

public class MenuView extends FXMLDocumentController{

	public Tab menuTabView = new Tab();
	
	public void setMenuTabView(Tab tab){
		
		//this.menuTabView =  tab;
	}
	
	public Tab getMenuTabView(){
		return menuTabView;
	}
	
	public void setMenuViewListener( JFXButton home,JFXButton history,JFXButton downloads,JFXButton bookmarks,JFXButton saveAsPdf, JFXButton setting){
		
		
	}
	
}
