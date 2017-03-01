package userInterface;


import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.TabPane;

public class TabPaneView {
	
	public TabPane getTabPane(TabPane tabpane,Tab addNewTab){
		tabpane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {
			        	if(newSelectedTab==addNewTab){
			           /*
			        		@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--Problem---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			        		 * Problem is when If I will put previous navigation bar in new pane then
			        		 * it works for previous listeners? Here his a debate start how we will do it?
			        	*/	
			        		//New Tab new rowser pane
			        		WebView browser = new WebView();
			        		WebEngine webEngine1 = browser.getEngine();
			        		webEngine1.load("http://www.google.com");
			        		
			        		
			        		//---------------New tab is created --------------------
			        		Tab tab = new Tab("New Tab");
			        		
			        		try {
								tab.setContent(FXMLLoader.load(getClass().getResource("Tab.fxml")));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("Exception: New tab click but not working in TabPaneView Class");
								e.printStackTrace();
							}
			    			
			    			
			    			tab.getStyleClass().addAll("tab-pane");
			    			
			    			
			    			final ObservableList<Tab> tabs = tabpane.getTabs();
			    			
			    			//System.out.println("Now Size"+tabs.size());
			    			
			    			tabs.add(tabs.size()-1,tab);
			    			SingleSelectionModel<Tab> selectedTab = tabpane.getSelectionModel();
			    			selectedTab.select(tab);
			    			
			    			System.out.println("Tabpane view changing listener:"+tabs.size());
			    			
			    			//###That was a bug tabpane.getSelectionModel().select(tab);
			    			//System.out.println("Now Size"+tabs.size());
			    			
			    			
			        		
			        		
			        	}
			        }
			        
				});
		return tabpane;
	}
	


}
