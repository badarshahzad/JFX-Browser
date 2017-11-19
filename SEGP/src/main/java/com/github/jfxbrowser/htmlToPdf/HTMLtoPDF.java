package main.java.com.github.jfxbrowser.htmlToPdf;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.util.Duration;
import main.java.com.github.jfxbrowser.controllers.TabController;
import main.java.com.github.jfxbrowser.pdfcrowd.Client;

public class HTMLtoPDF extends Thread {
	private File file = null;

	public HTMLtoPDF(File file) {
		this.file = file;
	}

	@Override
	public void run() {

		System.out.println("File name I think:" + file);
		if (file == null) {
			return;
		} else {
			Client client = new Client("Hassan_Iqbal", "962ca71a838132f00947f2ec13984587");
			// if(file.isFile()){
			FileOutputStream fileStream = null;
			try {
				fileStream = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String currentUrl = TabController.getWebEngine().getLocation().toString();
			client.convertURI(currentUrl, fileStream);

			try {
				fileStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * FileChooser fileChooser = new FileChooser();
		 * FileChooser.ExtensionFilter extFilter = new
		 * FileChooser.ExtensionFilter("PDF File (*.pdf)", "*.pdf");
		 * fileChooser.getExtensionFilters().add(extFilter); file =
		 * fileChooser.showSaveDialog(MainClass.getStage());
		 * 
		 * 
		 * 
		 * try { if(!file.exists()){ file.createNewFile(); }
		 * 
		 * if(file==null){ return;
		 * 
		 * }else{
		 * 
		 * Client client = new Client("Hassan_Iqbal",
		 * "962ca71a838132f00947f2ec13984587"); String currentUrl =
		 * TabController.getWebEngine().getLocation().toString();
		 * FileOutputStream fileStream; try { fileStream = new
		 * FileOutputStream(file); client.convertURI(currentUrl, fileStream);
		 * fileStream.close(); } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * }
		 */
		Platform.runLater(new Runnable() {
			public void run() {
				Notifications.create().title("File Downloaded")
				.text("Your HTML to PDF file Downloaded \n Path: " + file).darkStyle()
				.hideAfter(Duration.seconds(10)).showInformation();
			}
		});
		System.out.println("File Download:check");

		// }
		//
	}

}
