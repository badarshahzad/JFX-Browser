package passwordVault;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;

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

import controllers.TabController;
import database.UserAccounts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class DetectForm {




	private HTMLInputElement password = null;
	private HTMLInputElement username = null;
	private boolean isForm = false;
	//	public void notifcation(javafx.scene.Node node){
	//		VBox popUpContent = new VBox();
	//		popUpContent.setMinSize(100, 100);
	//		popUpContent.setSpacing(5);
	//		popUpContent.setPadding(new Insets(5, 5, 5, 5));
	//		Label label = new Label("would you like to save password for this account? \n ******");
	//		JFXButton cancelPopup = new JFXButton("Cancel");
	//		cancelPopup.setMinSize(150, 30);
	//		JFXButton save = new JFXButton("Save");
	//		save.setMinSize(150, 30);
	//		HBox hbox = new HBox();
	//		hbox.getChildren().addAll(cancelPopup,save);
	//		VBox.setMargin(label, new Insets(5, 5, 5, 5));
	//		popUpContent.getChildren().addAll(label,hbox);
	//		PopOver popOver = new PopOver(new JFXButton("Yes"));
	//		popOver.setCornerRadius(4);
	//		popOver.setContentNode(popUpContent);
	//		popOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
	//		popOver.show(node);
	//		popOver.detach();	
	//		cancelPopup.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler ->{
	//			popOver.hide();
	//		});
	//		save.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
	//			//database save.
	//		});
	//	}
	public void detect(Document doc){
		EventListener listener = new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				System.out.println("action listener from DOM.");
				String user = username.getValue();
				String pass = password.getValue();
				if(!(user.isEmpty()&&pass.isEmpty())){
					Alert alert = new Alert(AlertType.CONFIRMATION);
					//				alert.setX(50);
					alert.setTitle("Confirmation Dialog");
					alert.setHeaderText("Confirmation");
					alert.setContentText("Would you like to save password for this Account ?");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						try {
							UserAccounts.insertAccount(new URL(TabController.getWebEngine().getLocation()).getHost(), user, pass, 1);
						} catch (MalformedURLException e) {
							System.err.println("Url exception in detect form.");
							e.printStackTrace();
						}
					} else {
						// ... user chose CANCEL or closed the dialog
					}

				}
				System.err.println("want to save username:  "+user+" password: "+"  "+pass);
			}
		};

		if (doc!=null && doc.getElementsByTagName("form").getLength() > 0) {
			for(int k=0;k<doc.getElementsByTagName("form").getLength();k++){
				HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(k);
				NodeList nodes = form.getElementsByTagName("input");
				for (int i = 0; i < nodes.getLength(); i++) {
					if(nodes.item(i).hasAttributes()){
						NamedNodeMap attr = nodes.item(i).getAttributes();
						for (int j=0 ; j<attr.getLength();j++){
							Attr atribute = (Attr)attr.item(j);
							if(atribute.getValue().equals("text")){
								username = (HTMLInputElement) nodes.item(i);
							}
							if(atribute.getValue().equals("password")){
								password = (HTMLInputElement) nodes.item(i);
								isForm=true;
							}else if(atribute.getValue().equals("submit")){
								System.out.println("DETECTION");
								((EventTarget) nodes.item(i)).addEventListener("click", listener, false);

							}

						}
					}

				}
				if(isForm){
					Node button = form.getElementsByTagName("button").item(0);
					if(button!=null && button.hasAttributes()){
						NamedNodeMap attr = button.getAttributes();
						for(int j=0; j<attr.getLength(); j++){
							Attr atribute = (Attr)attr.item(j);
							if(atribute.getValue().equals("submit")){
								System.out.println("DETECTION");
								((EventTarget) button).addEventListener("click", listener, false);

							}

						}
					}
				}
			}

		}




	}
	public void insert(Document doc){
		HTMLInputElement username =null,password = null;
		if (doc!=null && doc.getElementsByTagName("form").getLength() > 0) {
			for(int k=0;k<doc.getElementsByTagName("form").getLength();k++){
				HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(k);
				NodeList nodes = form.getElementsByTagName("input");
				for (int i = 0; i < nodes.getLength(); i++) {
					if(nodes.item(i).hasAttributes()){
						NamedNodeMap attr = nodes.item(i).getAttributes();
						for (int j=0 ; j<attr.getLength();j++){
							Attr atribute = (Attr)attr.item(j);
							if(atribute.getValue().equals("text")){
								username = (HTMLInputElement) nodes.item(i);
							}
							if(atribute.getValue().equals("password")){
								password = (HTMLInputElement) nodes.item(i);
								try{
									String domain = new URL(TabController.getWebEngine().getLocation()).getHost() ;
									ResultSet resultSet = UserAccounts.getAccounts(1,domain);
									while(resultSet.next()){
										username.setValue(resultSet.getString(1));
										password.setValue(resultSet.getString(2));

									}

								}catch(Exception e){
									System.out.println("Exception while getting domain name.");
								}

							}
						}
					}

				}
			}

		}

	}
}
