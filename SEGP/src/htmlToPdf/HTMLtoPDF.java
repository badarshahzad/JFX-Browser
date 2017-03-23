package htmlToPdf;

import pdfcrowd.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import controllers.MainController;
import controllers.TabController;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import main.MainClass;

public class HTMLtoPDF {

	@SuppressWarnings("deprecation")
	public void convertHtmlToPdf() {
		try {

			FileChooser fileChooser = new FileChooser();
			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF File (*.pdf)", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);
			// Show save file dialog
			//The stage for show dialouge is get from MainClass stage
			File file = fileChooser.showSaveDialog(MainClass.getStage());
			

			Client client = new Client("Hassan_Iqbal", "962ca71a838132f00947f2ec13984587");
			if(file.isFile()){
			FileOutputStream fileStream = new FileOutputStream(file);
			
			
			String currentUrl = TabController.getWebEngine().getLocation().toString();
			client.convertURI(currentUrl, fileStream);

			fileStream.close();
			}else{
			System.out.println("File Download:check");
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
