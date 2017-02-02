package userInterface;


import java.awt.Desktop.Action;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import downloader.MainDownload;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MenuView {

	/*
	 * Below we are going to use different listener to learn the taste of different listners 
	 * used in java,javafx and for jfoenix librarys.
	 */

	private Tab tab = new Tab();
	public BorderPane settingBorderPane = new BorderPane();
	HamburgerSlideCloseTransition transition;
	JFXDrawersStack drawersStack;
	JFXDrawer rightDrawer;
	
	public void setMenuViewListener( 
			JFXButton home,JFXButton history,JFXButton downloads,JFXButton bookmarks,
			JFXButton saveAsPdf, JFXButton setting,TabPane tabPane,HamburgerSlideCloseTransition transition,
			JFXDrawersStack drawersStack,JFXDrawer rightDrawer){
		
		this.transition = transition;
		this.drawersStack = drawersStack;
		this.rightDrawer = rightDrawer;
		
		final ObservableList<Tab> tabs = tabPane.getTabs();
		SingleSelectionModel<Tab> selectedTab = tabPane.getSelectionModel();
			
		
		//-------------------------------------------------------Home listener----------------------------------------------------------
		home.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
				
				
				System.out.println("Home");
				tab.setText("Home");
				tab.setId("home");
				
				
			}
			
		});
		
		
		//-------------------------------------------------------History listener-------------------------------------------------------
		history.setOnAction((ActionEvent)->{
			
				//When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
			
				System.out.println("History");

				//tab name and id for accessing
				tab.setText("History");
				tab.setId("history");
				
				if(tabs.get(tabs.size()-2).getId()!=("history")){
				
				History ob = new History();
				tab = ob.getHistoryView(tab, settingBorderPane);
				tabs.add(tabs.size()-1,tab);

				//System.out.println(tab.getId());
				//System.out.println(tabs.size());
				
				//The below is just select the current tab 
				selectedTab.select(tab);
				}
				
				if(tabs.get(tabs.size()-2).getId()==("history")){
					System.out.println("Adfa");
					selectedTab.select(tabs.size()-2);
					return;
				}
				
		});
		
		
		//-------------------------------------------------------Downloads listener-----------------------------------------------------
		downloads.setOnAction((e)->{
			
			//When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
			
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					download.startDownload();
					
				MainDownload object = new MainDownload();
					object.DownloaderStart();
				}
			});
			th.start();
			
			//System.out.println("Downloads");
			tab.setText("Downloads");
			tab.setId("downloads");
		});
		
		
		//-------------------------------------------------------Bookmarks listener-----------------------------------------------------
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//When the menu click Hamburger and DrawerStack will hide
				onClickHideHamburger();
			
				System.out.println("Bookmarks");
				tab.setText("Bookmarks");
				tab.setId("bookmarks");
				
			}
		});
		
		
		//-------------------------------------------------------SaveAsPdf listener-----------------------------------------------------
		saveAsPdf.addEventHandler(MouseEvent.MOUSE_CLICKED, (ActionEvent)->{
		
			//When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
		
			
			System.out.println("Save As PDF");
			tab.setText("Save As Pdf");
			tab.setId("saveAsPdf");
			
		});
		
		
		//-------------------------------------------------------Setting listener-------------------------------------------------------
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			
			//When the menu click Hamburger and DrawerStack will hide
			onClickHideHamburger();
		
			System.out.println("Setting");
			
				//tab name and id for accessing
				tab.setText("Setting");
				tab.setId("setting");
				
				if(tabs.get(tabs.size()-2).getId()!=("setting")){
						
				/*
				 * Here we developed a tab and its borderpane for setting we made setting class that 
				 *		design the layout of setting then a single tab for all menus set a Tab and only in one
				 *		tab all menu can be seen Is this cool? or not give me constructive feedback!*/
				//----------Bug of New Tab open then click setting and if index is not less than -2 then 
				//----------it will not find setting and again same setting tab will open ! issue can be resolved but need time!
				
					
				/*
				 * Setting is class that will only desinge the layout of setting tab and we are just calling
				 * its method getSettingView and give two arguments that is tab and setting pane
				 */	
				Setting ob = new Setting();		
				tab = ob.getSettingView(tab, settingBorderPane);
				
				//System.out.println(tabs.get(tabs.size()-2).getId());
				//This is just selecitng the just now opened tab
				tabs.add(tabs.size()-1,tab);

				//System.out.println(tab.getId());
				//System.out.println(tabs.size());
				
				//The below is just select the current tab 
				selectedTab.select(tab);
				}
				
				if(tabs.get(tabs.size()-2).getId()==("setting")){
					System.out.println("Adfa");
					selectedTab.select(tabs.size()-2);
					return;
				}
		 
		});
		
	}
	
	public void onClickHideHamburger(){
		transition.setRate(transition.getRate()*-1);
		transition.play();
		drawersStack.toggle(rightDrawer);
	}
	
}
