package userInterface;

import java.io.FileOutputStream;
import java.util.Properties;

import com.pdfcrowd.Client;

public class SavePdf {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		try {
			   Properties p=System.getProperties();
			   p.setProperty("http.proxyHost", "172.16.0.2");
			   p.setProperty("http.proxyPort", "8080");
				
			   Client client = new Client("Hassan_Iqbal", "962ca71a838132f00947f2ec13984587");
			   FileOutputStream fileStream = new FileOutputStream("client.pdf");
			   client.convertURI("https://pdfcrowd.com/static/clients/java/Client.java.html", fileStream);
			   fileStream.close();
			   System.out.println("file Created ");
//			   
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
