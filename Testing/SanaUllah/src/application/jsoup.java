package application;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class jsoup{
	public static void main(String [] args) throws IOException{
		File input=new File("C:/Users/SanaUllah/Desktop/j.htm");
		Document doc=Jsoup.parse(input,"UTF-8");
		System.out.println(doc.toString());
	}
	
}