package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.omg.PortableInterceptor.RequestInfo;

/*
 * This is the class for downloading any file audio or video from the Internet. In this class URL is given to the method
 * and a path to download the file. Another method is created which is used to create file of the name specified in the URL.
 * Another method is create which is used to overwrite the run method. In this method a connection is created and GET request
 * is sent to receive the or download that file. 
 */
//http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3

public class DownloadThread extends Thread{
	private String url ;
	private String fileTitle ;
	private String filePath ;

/*
 * The constructor of the class is created to assign values to the strings we have created.
 * One string is for getting url of the file to be downloaded.
 * Second string is for getting the title of the file to be downloaded.
 * Third string is for getting the filepath where the downloaded file will be placed.
 * 
 */
	
public DownloadThread(String url ,String path, String title){
	this.url = url;
	this.fileTitle = title;
	this.filePath = path;

}

/*
 *A createFile method is created. In this method first it is being checked that whether the file is already present in the 
 *directory or not, if the file is not present it will create new file and place all the downloaded data into that file. If
 *will is already present it will just return that file. 
 */

private File createFile(){
	String ext = url.substring(url.lastIndexOf("."), url.length()); // extension of the file like .zip , .mp3 , .mp4 etc.
	 //create file with title and extension
	File downlodedFile = new File(filePath+"/"+fileTitle+ext);
	if(!downlodedFile.exists()){
		try {
			downlodedFile.createNewFile();
		} catch (IOException e) {
			System.err.println("Cannot create File to store.");
			e.printStackTrace();
		}
	}
	
	return downlodedFile;
}

/*
 * (non-Javadoc)
 * @see java.lang.Thread#run()
 * 
 * In this method we are overirding the builtin run method. URL object is created to get autodetect the URL from the browser and
 * then create connection to that link and send the GET request to get the required file. If the connection is established the 
 * file starts downloading to the place we jsut created in the above given method.
 */
	
@Override
public void run(){
	try{
		URL obj = new URL(url); // create url object for the given string
		
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection(); // open http connection on the url 
//		connection.connect(); // connect to the server with in the specified url
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		
		int requestinfo = connection.getResponseCode(); // get the responce code from the server which might be helpful in understanding the server response for the download request.
		
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); // open the input stream on the established tcp connection.
		
		FileOutputStream out = new FileOutputStream(createFile()); // create a file and open the output stream to write on file.
		
		int size = connection.getContentLength(); // to get the total size of the file being downloaded it will be helpful making the GUI like progress bar.
		int len = -1; 
		int progress = 0 ; // to update the GUI progress bar.
		
		byte[] buffer = new byte[1024]; // byte array to get the content from the input stream.
		
		while((len = in.read(buffer,0,1024)) != -1){ // getting content from the input stream and saving into the buffer byte array.
			out.write(buffer,0,1024); // writing the bytes to the file.
//			out.write(buffer);
			progress+=len; // update progress variable 
			System.out.println("Downloded bytes "+progress+ " Remaining  bytes  "+(size-progress));
		} 
		out.flush(); // empty the buffer.
		in.close(); // close opened streams
		out.close();
		
		System.out.println("Download Complete . ..   tan tan tan :) ");
	
	}catch(Exception e){
		System.err.println("Error While Downloading ::");
		e.printStackTrace();
	}
}

}
