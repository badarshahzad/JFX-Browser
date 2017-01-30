package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lowagie.text.DocumentException; 
import com.lowagie.text.pdf.PdfWriter; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Map; 
import java.util.HashMap; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;
//import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
public class SavePdf {

	public static void main(String[] args) throws MalformedURLException, FileNotFoundException, IOException, DocumentException
	
		{
			FileInputStream fis=null;
			try   
			{  
			   fis = new FileInputStream("C:/Users/student/Desktop/Wikipedia_the.html");  
			}  
			catch (java.io.FileNotFoundException e)   
			{  
			    System.out.println("File not found: ");  
			}  
			Tidy tidy=new Tidy();
			 tidy.setShowWarnings(false);
		     tidy.setXmlTags(false);
		     tidy.setInputEncoding("UTF-8");
		     tidy.setOutputEncoding("UTF-8");
		     tidy.setXHTML(true);// 
		     tidy.setMakeClean(true);
		     org.w3c.dom.Document xmldoc=tidy.parseDOM(fis, null);
		     try  
		     {  
			    tidy.pprint(xmldoc,new FileOutputStream("C:/Users/student/Desktop/Wikipedia_the.xhtml"));  
		     }  
		     catch(Exception e)  
		     {  
		    	 System.out.println(e);
		     }
		     
		     String inputFile = "C:/Users/student/Desktop/Wikipedia_the.xhtml";
			  String url = new File(inputFile).toURI().toURL().toString();
			  String outputFile = "maygodhelpme1.pdf";
			  OutputStream os = new FileOutputStream(outputFile);
//
			  ITextRenderer renderer = new ITextRenderer();
			  renderer.setDocument(url);
			  renderer.layout();
			  renderer.createPDF(os);
			  os.close();	
		}	
}
