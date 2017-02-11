package htmlToPdf;

import pdfcrowd.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JFileChooser;

import javafx.stage.FileChooser;


public class HTMLtoPDF {
	
	@SuppressWarnings("deprecation")
	public void convertHtmlToPdf()
	{
		try {
			   Properties p=System.getProperties();
			   p.setProperty("http.proxyHost", "172.16.0.2");
			   p.setProperty("http.proxyPort", "8080");

			   p.setProperty("https.proxyHost", "172.16.0.2");
			   p.setProperty("https.proxyPort", "8080");
				
			   Client client = new Client("Hassan_Iqbal", "962ca71a838132f00947f2ec13984587");
			   FileOutputStream fileStream = new FileOutputStream(new File(System.getProperty("user.home"), "/Desktop/test1.pdf"));
			   
			   
			   //JFileChooser file = new JFileChooser();
			   //file.showSaveDialog();
			   //client.convertURI("https://en.wikipedia.org/wiki/Main_Page", fileStream);
			   //client.convertURI("https://blackboard.brad.ac.uk/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_14_1", fileStream);
			   client.convertURI("http://docs.oracle.com/javafx/2/css_tutorial/jfxpub-css_tutorial.htm", fileStream);
			  
			   fileStream.close();
			  
			   System.out.println("File Download:check");
//			   
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
