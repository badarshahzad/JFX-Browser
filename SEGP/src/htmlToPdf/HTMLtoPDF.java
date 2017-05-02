
package htmlToPdf;
import pdfcrowd.*;
import java.io.File;
import java.io.FileOutputStream;

import org.controlsfx.control.Notifications;

import controllers.TabController;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import main.MainClass;

public class HTMLtoPDF extends Thread{
	public HTMLtoPDF(){
		Platform.runLater(new  Runnable() {
			public void run() {
				convertHtmlToPdf();
			}
		});
		
	}

	public void convertHtmlToPdf() {
		try {

			FileChooser fileChooser = new FileChooser();
			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF File (*.pdf)", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);
			// Show save file dialog
			//The stage for show dialouge is get from MainClass stage
			File file = fileChooser.showSaveDialog(MainClass.getStage());
			System.out.println("File name I think:" + file);
			if(file==null){
			return;	
			}else{
			Client client = new Client("Hassan_Iqbal", "962ca71a838132f00947f2ec13984587");
		//	if(file.isFile()){
			FileOutputStream fileStream = new FileOutputStream(file);
			
			
			String currentUrl = TabController.getWebEngine().getLocation().toString();
			client.convertURI(currentUrl, fileStream);

			fileStream.close();
			}
			//}else{
			
			Notifications.create()
						.title("File Downloaded")
						.text("Your HTML to PDF file Downloaded \n Path: "+file)
						.darkStyle()
						.hideAfter(Duration
								.seconds(10))
						.showInformation();
			System.out.println("File Download:check");
			//}
			//
		} catch (Exception e) {
			System.err.println("Exception as you didn't select any file : ");
			e.printStackTrace();
		}
	}

}
