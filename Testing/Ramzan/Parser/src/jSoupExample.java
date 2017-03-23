import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class jSoupExample {

    public static void main(String[] args) {

	StringBuffer html = new StringBuffer();

	html.append("<html lang=\"en\">");
	html.append("<head>");
	html.append("<link rel=\"icon\" href=\"http://example.com/image.ico\" />");
	//html.append("<meta content=\"/images/google_favicon_128.png\" itemprop=\"image\">");
	html.append("</head>");
	html.append("<body>");
	html.append("something");
	html.append("</body>");
	html.append("</html>");

	Document doc = Jsoup.parse(html.toString());

	String fav = "";

	Element element = doc.head().select("link[href~=.*\\.(ico|png)]").first();
	if(element==null){

		element = doc.head().select("meta[itemprop=image]").first();
		if(element!=null){
			fav = element.attr("content");
		}
	}else{
		fav = element.attr("href");
	}
	System.out.println(fav);
  }

}