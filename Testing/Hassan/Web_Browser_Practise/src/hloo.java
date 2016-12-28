import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import javax.lang.model.util.Elements;
//import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;

public class hloo {

	public static void main(String[] args) throws IOException {
		//Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		//Elements newsHeadlines = doc.select("#mp-itn b a");
        
		//System.out.println(System.getProperties());
		File input = new File("E:\\New folder (2)\\Data.Strings.html");
		Document doc1 = Jsoup.parse(input, "UTF-8", "http://example.com/");
		System.out.println(doc1.toString());
	

		//org.jsoup.nodes.Document doms = Jsoup.parseBodyFragment(null);
		//doc1.outputSettings().escapeMode(EscapeMode.xhtml); // avoid transformin the characters '<' and '>' as its html characters codes like &gt; and &lt;
		//String STYLE= "<style type=\"text/css\">body {line-height:19px}</style";
		//doc1.head().append(STYLE);
		//http://jsoup.org/cookbook/input/load-document-from-url
		//Document doc = Jsoup.connect("http://example.com/").get();

//		Element content = doc1.getElementById("content");
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//		  String linkHref = link.attr("href");
//		  String linkText = link.text();
//		}
		}

//	public static Document createDocumentFromXMLString(String xml) throws Exception
//	{
//	DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
	//fac.setNamespaceAware(false);
	//fac.setValidating(false);
//	fac.setFeature("http://xml.org/sax/features/namespaces", false);
//	fac.setFeature("http://xml.org/sax/features/validation", false);
//	fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
//	fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	//DocumentBuilder builder = fac.newDocumentBuilder();
//	InputSource is = new InputSource(new StringReader(xml));
//	return (Document) builder.parse(is);
//	return null;
//	}
	
}
