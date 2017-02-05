package downloader;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

/*
 * This is the main class of downloader to download any file form the internet. In this class a new thread is being created to 
 * start a new connection for every new file. Another method is created to set proxy for using this application on different 
 * places. Next method is created to create new file and return path of the file to which the downloading file will be stored.
 */

public class MainDownload {
	private String url ;
	private  String title ;
	/* sample URL
	 * http://sound30.mp3slash.net/320/indian/raees/02%20-%20Zaalima%20[Songspk.GURU].mp3
	 * */
	
public MainDownload(String url , String title){
	this.url = url;
	this.title = title;
		
}

/*
 *THis method is creating new thread on every new request for downloading any file.
 *Thread is taking 3 arguments 1st is the URL of the file to be downloaded. Second, File where downloaded file would be saved.
 *THird, title which will be assigned to new downloading file. 
 */

public void startDownload(){
	DownloadThread thread = new DownloadThread(url, downloadFolder(), title);
	thread.start();
		
}

public MainDownload(){
	this.url = null;
}

/*
 * Method to add proxy configuration in downloader.
 * In this method proxyHost and proxyport are being set so that it can be used on any place.
 */

private void setProxy(){
	Properties systemProperties = System.getProperties();
	systemProperties.setProperty("http.proxyHost","172.16.0.2");
	systemProperties.setProperty("http.proxyPort","8080");
}
	
/*
 * Method create the Folder Downloads in the home/ if does not exists .To store the Downloaded Stuff. If the folder is already
 * created then it will save file to that folder.
 */
	
private String downloadFolder(){
	File home = new File(System.getProperty("user.home"));
	File folder = new File (home,"Downloads");
	if (!folder.exists()){
		folder.mkdir();
	}
	return folder.getAbsolutePath();
}

/*This is just a demo to get url from the console and file name say "hello" .
 * In actual we will be getting URL from our main browser and name of the file from
 * document.gettitle() method .
 */

public void DownloaderStart() {
		
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter URL for the download ..");
		
	MainDownload download = new MainDownload(sc.nextLine(), "hello");
	//download.setProxy();
	download.startDownload();	

}

}
