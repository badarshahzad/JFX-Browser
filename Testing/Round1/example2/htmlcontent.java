package example2;

import javax.print.DocFlavor.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class htmlcontent extends JFrame{

	public static void main(String args[]){
		new htmlcontent().start();
	}
	
	public void start(){
		
		try{
			String html;
			html="<html><head><title>Simple Page</title></head>";
			html+="<body bgcolor='#777779'><hr/><font size=50>This is Html content</font><hr/>";
			html+="</body></html>";
			
			Document doc1 = Jsoup.connect("http://www.google.com").get();
			Document doc = Jsoup.parse("abc.html", "UTF-8"); 
			String e =doc1.toString(); 
			//System.out.println(e);
			
			//JEditorPane e1 = new JEditorPane(a);
			String url = "http://www.google.com";
			
			JEditorPane e1 = new JEditorPane(url);
			add(e1);
			
			JScrollPane jsp = new JScrollPane(e1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(jsp);
			setVisible(true);
			setSize(400, 500);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		}catch(Exception e){
			
		}
		
	}
}
