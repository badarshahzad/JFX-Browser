package downloader;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class MainDownload {
//	private  String title ;
	/* sample URL
	 * http://sound30.mp3slash.net/320/indian/raees/02%20-%20Zaalima%20[Songspk.GURU].mp3
	 * */

	public void startDownload(String url){

		DownloadThread thread = new DownloadThread(url, downloadFolder());
		System.setProperty("java.net.preferIPv4Stack" , "true");
//		this.setProxy();
		thread.start();
		
	}

		
	/*
	 *  *Method to add proxy configuration in downloader.
	 *  */
	private void setProxy(){
	    	Properties systemProperties = System.getProperties();
	    	systemProperties.setProperty("http.proxyHost","172.16.0.2");
	    	systemProperties.setProperty("http.proxyPort","8080");
	    	systemProperties.setProperty("https.proxyHost","172.16.0.2");
			systemProperties.setProperty("https.proxyPort","8080");
			
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
	public static void main (String[] args){
//		new MainDownload().startDownload("https://examples.javacodegeeks.com/core-java/net/sockettimeoutexception/java-net-sockettimeoutexception-how-to-solve-sockettimeoutexception/");
//		
		new MainDownload().startDownload("https://www.namal.edu.pk/wp-content/uploads/2017/01/Cloud-Gaming.pdf");
//		new MainDownload().startDownload("https://www.google.com.pk/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwiE2ObT1vrRAhUBtRQKHShJDvYQFggXMAA&url=http%3A%2F%2Fwww.pdf995.com%2Fsamples%2Fpdf.pdf&usg=AFQjCNFUjjZAJw2DPPGOy7FpbX0wEqSnzA&bvm=bv.146094739,d.d24");
		
	}
}
