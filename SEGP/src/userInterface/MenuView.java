package userInterface;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;

import bookmarks.BookMarks;
import controllers.MainController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;



public class MenuView {

	/*
	 * Below we are going to use different listener to learn the taste of
	 * different listners used in java,javafx and for jfoenix librarys.
	 * 
	 */

	private Tab tab = new Tab();

	private JFXTabPane fxTabpane = new JFXTabPane();
	private Tab historyTab = new Tab("History");

	private Tab downloadsTab = new Tab("Downloads");
	private Tab bookmarksTab = new Tab("Bookmarks");
	private Tab settingTab = new Tab("Setting");

	//private MainController mainCtrl = new MainController();
	
	//private BorderPane settingBorderPane = new BorderPane();
	private JFXDrawersStack drawersStack;
	private JFXDrawer rightDrawer;
	

	private ObservableList<Tab> tabs ;
	private ObservableList<Tab> fxTabs ; 

	SingleSelectionModel<Tab> selectedTab ; 
	SingleSelectionModel<Tab> fxSelectedTab ;

	public void setMenuViewListener(JFXButton history, JFXButton downloads, JFXButton bookmarks, JFXButton setting,
			TabPane tabPane, JFXDrawersStack drawersStack, JFXDrawer rightDrawer) {

		// BorderPane settingTabBPane = new BorderPane();
		// tab.setContent(settingTabBPane);
		tabs = tabPane.getTabs();
		selectedTab  = tabPane.getSelectionModel();
		
		fxTabs = fxTabpane.getTabs();
		fxSelectedTab = fxTabpane.getSelectionModel();
		
		try {
			fxTabpane.setTabMinWidth(150);
			fxTabpane.setTabMinHeight(30);
			fxTabpane.setId("settingTabPane");

			historyTab.setText("History");
			historyTab.setContent(FXMLLoader.load(getClass().getResource("/ui/History.fxml")));

			downloadsTab.setText("Downloads");
			downloadsTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Downloads.fxml")));

			bookmarksTab.setText("Bookmarks");
			// bookmarksTab.setContent(FXMLLoader.load(getClass().getResource("/ui/bookmarks.fxml")));

			settingTab.setText("Setting");
			settingTab.setContent(FXMLLoader.load(getClass().getResource("/ui/Setting.fxml")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Setting tabs fxml files not loading");
		}

		this.drawersStack = drawersStack;
		this.rightDrawer = rightDrawer;


		/*
		 * Here we developed a tab and its borderpane for setting we made
		 * setting class that design the layout of setting then a single tab for
		 * all menus set a Tab and only in one tab all menu can be seen Is this
		 * cool? or not give me constructive feedback! Setting is class that
		 * will only desinge the layout of setting tab and we are just calling
		 * its method getSettingView and give two arguments that is tab and
		 * setting pane
		 */

		// tab.setText("Settig");
		// -------------------------------------------------------Historylistener-------------------------------------------------------
		history.setOnAction(this::historyHandleBt);
		// ------------------------------------------------------Downloads
		// listener-----------------------------------------------------

		downloads.setOnAction((e) -> {

			onClickHideHamburger();
			
			addAndSelectNewTab(tabs,tab,selectedTab,fxSelectedTab);
			
			tab.setText("Downloads");

		});

		// -------------------------------------------------------Bookmarks
		// listener-----------------------------------------------------
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				onClickHideHamburger();

				addAndSelectNewTab(tabs,tab,selectedTab,fxSelectedTab);

				tab.setText("Bookmarks");

			}

		});

		// -------------------------------------------------------Setting
		// listener-------------------------------------------------------
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			onClickHideHamburger();

			addAndSelectNewTab(tabs,tab,selectedTab,fxSelectedTab);
			
			tab.setText("Setting");

		});

		//As the bookmarks is not designed in fxml so: When the bookmarks tab 
		//access to show the view of bookmark 
		//if(fxTabpane.getSelectionModel().selectedIndexProperty().getName().equals("Bookmarks")){

		//}

		tab.setContent(fxTabpane);
		fxTabpane.getTabs().addAll(historyTab, downloadsTab, bookmarksTab, settingTab);

		fxTabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override

			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab newSelectedTab) {

				if (newSelectedTab == historyTab) {
					tab.setText("History");

					/*ObservableList<HistoryStoreView> fullHistory = FXCollections.observableArrayList();
					fullHistory = SqliteConnection.getFullHistory(fullHistory);
					addListInTable(fullHistory);
					table.refresh();
					 */
				}
				if (newSelectedTab == bookmarksTab) {
					tab.setText("Bookarks");
				}
				if (newSelectedTab == downloadsTab) {
					tab.setText("Downloads");
				}
				if (newSelectedTab == settingTab) {
					tab.setText("Setting");
				}
			}
		});
	}


	private void addAndSelectNewTab(ObservableList<Tab> tabs, Tab tab2, SingleSelectionModel<Tab> selectedTab, SingleSelectionModel<Tab> fxSelectedTab) {
		// TODO Auto-generated method stub


		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				
				

				for(int a=0; a<tabs.size();a++){
					
					String openTabName = tabs.get(a).getText();
					
					if(openTabName.equals("History") 		|| 
							openTabName.equals("Bookmarks") ||
							openTabName.equals("Downloads") ||
							openTabName.equals("Setting"))
						{
							
							return;
						
						}
					
				}
				tabs.add(tabs.size() - 1, tab);
				selectedTab.select(tab);
				getBookMarkView();

			}
		});	
	}
	
	private void historyHandleBt(ActionEvent event) {
	
		// When the menu click Hamburger and DrawerStack will hide
		onClickHideHamburger();

		addAndSelectNewTab(tabs,tab,selectedTab,fxSelectedTab);
		
		tab.setText("History");
	}

	public void getBookMarkView(){
		BookMarks ob = new BookMarks();
		bookmarksTab = ob.getBookmarkView(bookmarksTab);
	}

	public void onClickHideHamburger() {

		// TODO Auto-generated method stub
		// transition.setRate(transition.getRate() * -1);
		// transition.play();
		drawersStack.toggle(rightDrawer);
	}

}
