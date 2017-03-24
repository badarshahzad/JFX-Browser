<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> phase5
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class listeners{
	
	@FXML 
	 private TextField text;
	@FXML
	 private ScrollPane pane=null;
	
	@FXML
	public void ButtonClicked(ActionEvent event)
	{  
		
		try {
				String str=text.getText();
				File file=new File(str);
				Document doc=Jsoup.parse(file,"UTF-8","");
				String str1=doc.toString();
				
				Element image=doc.select("img").first();
				String imageLocation=image.attr("src");
				System.out.println(imageLocation);
				//URL url = getClass().getResource(imageLocation);
				
				//System.out.println(url);
						
				
				final WebView browser = new WebView();
		        final WebEngine webEngine = browser.getEngine();
		        webEngine.loadContent(str1);
		        
		        webEngine.loadContent(imageLocation);
		        pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		        pane.setContent(browser);
		        
				}
			
			catch (IOException e) 
			{
			e.printStackTrace();
			}
			//btn=new Button("lala");
			//hb.getChildren().add(btn);
			
	}
	
	
}

























































/*WebView lala=new WebView();
WebEngine engine=new WebEngine();
engine=lala.getEngine();
engine.load("http://docs.oracle.com/javafx/scenebuilder/1/use_java_ides/sb-with-eclipse.htm");


File file=new File("file.html"); 
Document doc1;
try 
{
	doc1 = Jsoup.parse(file,"UTF-8"," ");
	//System.out.println(doc1);
	Elements media = doc1.select("[src]");
	//System.out.println(media);
	//Elements links = doc1.select("a[href]");
	//System.out.println(links);
	Elements link = doc1.getElementsByTag("a");
	for (Element links : link) {
	  String linkHref = link.attr("href");
	  String linkText = link.text();
	  System.out.println(linkHref);
	  System.out.println(linkText);
	  
	}
	
} 
catch (IOException e) 
{
	e.printStackTrace();
}
*/
<<<<<<< HEAD
=======
=======
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class listeners{
	
	@FXML 
	 private TextField text;
	@FXML
	 private ScrollPane pane=null;
	
	@FXML
	public void ButtonClicked(ActionEvent event)
	{  
		
		try {
				String str=text.getText();
				File file=new File(str);
				Document doc=Jsoup.parse(file,"UTF-8","");
				String str1=doc.toString();
				
				Element image=doc.select("img").first();
				String imageLocation=image.attr("src");
				System.out.println(imageLocation);
				//URL url = getClass().getResource(imageLocation);
				
				//System.out.println(url);
						
				
				final WebView browser = new WebView();
		        final WebEngine webEngine = browser.getEngine();
		        webEngine.loadContent(str1);
		        
		        webEngine.loadContent(imageLocation);
		        pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		        pane.setContent(browser);
		        
				}
			
			catch (IOException e) 
			{
			e.printStackTrace();
			}
			//btn=new Button("lala");
			//hb.getChildren().add(btn);
			
	}
	
	
}

























































/*WebView lala=new WebView();
WebEngine engine=new WebEngine();
engine=lala.getEngine();
engine.load("http://docs.oracle.com/javafx/scenebuilder/1/use_java_ides/sb-with-eclipse.htm");


File file=new File("file.html"); 
Document doc1;
try 
{
	doc1 = Jsoup.parse(file,"UTF-8"," ");
	//System.out.println(doc1);
	Elements media = doc1.select("[src]");
	//System.out.println(media);
	//Elements links = doc1.select("a[href]");
	//System.out.println(links);
	Elements link = doc1.getElementsByTag("a");
	for (Element links : link) {
	  String linkHref = link.attr("href");
	  String linkText = link.text();
	  System.out.println(linkHref);
	  System.out.println(linkText);
	  
	}
	
} 
catch (IOException e) 
{
	e.printStackTrace();
}
*/
>>>>>>> upstream/master
>>>>>>> phase5
