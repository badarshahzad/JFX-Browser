package downloader;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class maindownload {
	private String url ;
//	private  String title ;
	/* sample URL
	 * http://sound30.mp3slash.net/320/indian/raees/02%20-%20Zaalima%20[Songspk.GURU].mp3
	 * */
	
	/**
	 * @param url
	 */
	public maindownload(String url){
		this.url = url;
//		this.title = title;
		
		
	}
	/**
	 * 
	 */
	public void startDownload(){
		downloadThread thread = new downloadThread(url, downloadFolder());
		thread.start();
		
	}
	/*
	 *  *Method to add proxy configuration in downloader.
	 *  */
	private void setProxy(){
	    	Properties systemProperties = System.getProperties();
	    	systemProperties.setProperty("http.proxyHost","172.16.0.2");
	    	systemProperties.setProperty("http.proxyPort","8080");
	    }
	
	/*
	 * Method create the Folder Downloads in the home/ if does not exists .To store the Download Stuff
	 * */
	/**
	 * @return
	 */
	private String downloadFolder(){
		File home = new File(System.getProperty("user.home"));
		File folder = new File (home,"Downloads");
		if (!folder.exists()){
			folder.mkdir();
		}
		return folder.getAbsolutePath();
	}
	public static void main(String[] args) {
		/*This is just a demo to get url from the console and file name say "hello" .
		 * In actual we will be getting URL from our main browser and name of the file from
		 * document.gettitle() method .*/
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter URL for the download ..");
		maindownload download = new maindownload(sc.nextLine());
		download.setProxy();
		download.startDownload();
		

	}

}
