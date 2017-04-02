package passwordVault;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;

public class DetectForm {

	private HTMLInputElement password = null;
	private HTMLInputElement username = null;
	private boolean isForm = false;
	public void detect(Document doc){


		if (doc!=null && doc.getElementsByTagName("form").getLength() > 0) {
			HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(0);
			NodeList nodes = form.getElementsByTagName("input");

			for (int i = 0; i < nodes.getLength(); i++) {
				if(nodes.item(i).hasAttributes()){
					NamedNodeMap attr = nodes.item(i).getAttributes();
					for (int j=0 ; j<attr.getLength();j++){
						Attr atribute = (Attr)attr.item(j);
						if(atribute.getValue().equals("password")){
//							System.out.println("Password detected");
							password = (HTMLInputElement) nodes.item(i);
							username = (HTMLInputElement) nodes.item(i-1);
							//             				password.setValue("helloword");
							//             				username.setValue("helloword");
							isForm = true;
						}
					}
				}

			}

			EventListener listener = new EventListener() {
				@Override
				public void handleEvent(Event evt) {
					System.out.println("action listener from DOM.");
					if(username!=null && password!=null){
						String user = username.getValue();
						String pass = password.getValue();
						System.out.println("want to save username password: "+user+"  "+pass);
					}


				}
			};
			if(isForm){
				Node button = form.getElementsByTagName("button").item(0);
				if(button!=null && button.hasAttributes()){
					NamedNodeMap attr = button.getAttributes();
					for(int j=0; j<attr.getLength(); j++){
						Attr atribute = (Attr)attr.item(j);
						if(atribute.getValue().equals("submit")){
//							System.out.println("submit button detected.");
							((EventTarget) button).addEventListener("click", listener, false);

						}

					}
				}
			}

		}




	}

}
