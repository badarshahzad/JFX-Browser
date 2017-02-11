package userInterface;


import java.awt.Desktop.Action;
import java.util.Scanner;

import javax.swing.text.html.HTML;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import downloader.MainDownload;
import htmlToPdf.HTMLtoPDF;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import main.Renderer;

public class MenuView {
	
	

	/*
	 * Below we are going to use different listener to learn the taste of different listners 
	 * used in java,javafx and for jfoenix librarys.
	 * 
	 */

	private Tab tab = new Tab();
	private BorderPane settingBorderPane = new BorderPane();
	private HamburgerSlideCloseTransition transition;
	private JFXDrawersStack drawersStack;
	private JFXDrawer rightDrawer;

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
				System.out.println("@BorderPane is set@");
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
		/*	
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					download.startDownload();
					
				MainDownload object = new MainDownload();
				//new MainDownload().startDownload("");
					//object.startDownload("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ");new MainDownload().startDownload("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ");
					object.startDownload(getUrl());
				}
			});
			th.start();*/
			
			System.out.println("Downloads");

			//tab.setText("Downloads");
			//tab.setId("downloads");
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
			Thread th = new Thread(new Runnable() {
				public void run() {
					HTMLtoPDF object= new HTMLtoPDF();
					object.convertHtmlToPdf();
					
				}
			});
			th.start();
			
			System.out.println("Save As PDF");
			//tab.setText("Save As Pdf");
			//tab.setId("saveAsPdf");
			
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
