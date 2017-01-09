

import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.Timestamp;
import java.util.ArrayList;

import javax.swing.text.html.parser.Element;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class browser extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		File html = new File("D:/Study/Offline websites/Tutorialspoint Offline 2015 -ARSDK/Tutorialspoint Offline 2015 -ARSDK/index.html");
		File bookmark = new File ("bookmarks.txt");
		PrintWriter write = new PrintWriter(bookmark);
		WebView renderer = new WebView();
		VBox root = new VBox();
		WebEngine engine = renderer.getEngine();
		
		Button button = new Button("Load local File:");
		Button url = new Button("Load URL");
		Button bookmarks = new Button("BookMark :P");
		Button History = new Button("History");
		Button back = new Button("Back");
		Button forward = new Button("Forward");
		Button txtFile = new Button("txtFile");
		
		url.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				engine.load("https://www.google.com");
			}
			
		});
		button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					engine.load(html.toURI().toURL().toString());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		bookmarks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String url = engine.getLocation();
				System.out.println(url);
//				engine.get
//				write.println(url);
			}
			
		});
		History.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 WebHistory	history = engine.getHistory();
				 ObservableList<Entry> list = history.getEntries();
				 for(int i=0 ; i< list.size();i++){
					 System.out.println(list.get(i));
				 }
				 
			
			}
			
		});
	
		back.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				 WebHistory	history = engine.getHistory();
				 try{
					 history.go(-1);
				 }catch(IndexOutOfBoundsException  e){
					 
					 System.err.println("No Furthur Entries:");
				 }
			
			}
			
		});
		forward.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				 WebHistory	history = engine.getHistory();
				 try{
					 history.go(1);
				 }catch(IndexOutOfBoundsException  e){ 
					 System.err.println("No Furthur Entries:");
				 }
			
			}
			
		});
		txtFile.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				Document doc = engine.getDocument();
				NodeList elm= doc.getElementsByTagName("p");
			
				for (int i =0 ; i<elm.getLength(); i++){
					System.out.println(elm.item(i).getTextContent());
				}
		}
			}
		);

	
	
		
		root.getChildren().addAll(renderer,button,bookmarks,History,back,forward,txtFile,url);
		
		Scene scene = new Scene(root,1200,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args ){
		Application.launch(args);
	}

}
