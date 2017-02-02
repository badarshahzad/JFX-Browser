package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.omg.PortableInterceptor.RequestInfo;

public class DownloadThread extends Thread{
	//http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3
	private String url ;
	private String fileTitle ;
	private String filePath ;
	
	public DownloadThread(String url ,String path, String title){
		this.url = url;
		this.fileTitle = title;
		this.filePath = path;
		
	}
	
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
