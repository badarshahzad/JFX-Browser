package userInterface;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.TabPane;

public class TabPaneView {
	
	public TabPane getTabPane(TabPane tabpane,Tab addNewTab,GridPane navigationBar){
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
			        		BorderPane pane = new BorderPane();
			        		pane.setTop(navigationBar);
			        		//pane.setCenter(browser);
			        		
			        		Tab tab = new Tab();
			    			tab.setText("Taab");;
			        		tab.setContent(pane);
			    			
			    			
			    			tab.getStyleClass().addAll("tab-pane");
			    			final ObservableList<Tab> tabs = tabpane.getTabs();
			    			
			    			//System.out.println("Now Size"+tabs.size());
			    			
			    			tabs.add(tabs.size()-1,tab);
			    			SingleSelectionModel<Tab> selectedTab = tabpane.getSelectionModel();
			    			selectedTab.select(tab);
			    			
			    			//###That was a bug tabpane.getSelectionModel().select(tab);
			    			//System.out.println("Now Size"+tabs.size());
			        	}
			        }
			        
				});
		return tabpane;
	}

}
