<<<<<<< HEAD
package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.nio.file.Paths;

import javax.net.ssl.HttpsURLConnection;

import org.omg.PortableInterceptor.RequestInfo;

public class DownloadThread extends Thread{
	private String url ;
	private String filePath ;
	private static final int BUFFER_SIZE = 4096;
	public DownloadThread(String url ,String path){
		this.url = url;
		this.filePath = path;
	}
	/**
	 * @param dispose
	 * @param fileURL
	 * @return
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	private File createFile(String contentType , String url) throws URISyntaxException, MalformedURLException{
		String fileTitle = "[UNKNOWN]";
		if((url.length()-url.lastIndexOf('.'))==4){
			System.out.println("plain url.");
			fileTitle = url.substring(url.lastIndexOf("/") + 1,url.length());
		}else{
			
			String[] ext = contentType.split("/");
			System.out.println(ext[1]);
			fileTitle = fileTitle+"."+ext[1];
			System.out.println(fileTitle);
		}
		File downloadFile = new File(filePath+File.separator+fileTitle);		
		if(!downloadFile.exists()){
			try {
				downloadFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Cannot create File to store.");
//				System.exit(0);
			}
		}
		return downloadFile;
	}
	public boolean isDownloadable(String content){
		System.out.println(content);
		if(content.contains("application")){
			return true;
		}else if (content.contains("video")){
			return true;
		}else if (content.contains("audio")){
			return true;
		}
		return false;
		
	}//
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run(){
		try{
			URL obj = new URL(url); // create url object for the given string
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			if(url.startsWith("https")){
				System.out.println("Establishing https URL connection. . .");
				 connection = (HttpsURLConnection) obj.openConnection();
			}
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.connect(); // connect to the server with in the specified url
//			System.out.println(FilenameUtils.getBaseName(obj.getPath())); // -> file
//	        System.out.println(FilenameUtils.getExtension(obj.getPath())); // -> xml
//	        System.out.println(FilenameUtils.getName(obj.getPath())); // -> file.xml
//			FileNameMap file=connection.getFileNameMap();
//			 System.out.println(FilenameUtils.getBaseName(url.getPath())); // -> file
//		        System.out.println(FilenameUtils.getExtension(url.getPath())); // -> xml
//		        System.out.println(FileNameMap.getName(url.getPath())); // -> file.xml
//			System.out.println(file.toString());
//			try {
//				System.out.println(Paths.get(new URI(url).getPath()).getFileName().toString());
//				System.out.println(connection.getContentType());
//			} catch (URISyntaxException e) {
//				// TODO Auto-generated catch block
//				System.out.println("hwllo");
//			}
			int requestinfo = connection.getResponseCode(); // get the responce code from the server which might be helpful in understanding the server response for the download request.
//			String dispose = connection.getHeaderField("Content-Disposition");
//			if(connection.getContentType().contains("Application")){
//				System.out.println("This is a downloadable link.");
//			}
//			if(dispose!=null){
//				System.out.println("no header filed.");
//				System.out.println("Content-Disposition :  "+dispose );
//			}else{
//				System.out.println("no dispose content.");
			String  contentType = connection.getContentType();
//			}
			if (requestinfo == connection.HTTP_OK && isDownloadable(contentType)) {
				System.out.println("Download started on link  "+url);
				BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); // open the input stream on the established tcp connection.
				FileOutputStream out = new FileOutputStream(createFile(contentType,url)); // create a file and open the output stream to write on file.
				int size = connection.getContentLength(); // to get the total size of the file being downloaded it will be helpful making the GUI like progress bar.
				int len = -1; 
				int progress = 0 ; // to update the GUI progress bar.
				byte[] buffer = new byte[BUFFER_SIZE]; // byte array to get the content from the input stream.
				while((len = in.read(buffer,0,BUFFER_SIZE)) != -1){ // getting content from the input stream and saving into the buffer byte array.
					out.write(buffer,0,len); // writing the bytes to the file.
					//			out.write(buffer);
					progress+=len; // update progress variable 
					System.out.println("Downloded bytes "+progress/1024+" KB"+ " Remaining  bytes  "+(size-progress)/1024+" KB");
				} 
				out.flush(); // empty the buffer.
				in.close(); // close opened streams
				out.close();
				System.out.println("Download Complete . ");
			}else{
				System.out.println(isDownloadable(contentType));
				System.out.println("Cannot download File response code from server: " +requestinfo);
				connection.disconnect();
			}

		}catch(FileNotFoundException e){
			System.err.println("Error While Downloading : file not found . ");
			e.printStackTrace();
		}catch(ProtocolException e){
			System.err.println("Error While Downloading : protocol exception . ");
			e.printStackTrace();
		}catch(MalformedURLException e){
			System.err.println("invalid url.");

		}catch(IOException e){
			System.err.println("input output exception .");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occured in filTitle URL.");
			e.printStackTrace();
		}


	}

}
=======
package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DownloadThread extends Thread{
	//http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3
	private String url ;
	//	private String fileTitle ;
	private String filePath ;
	private static final int BUFFER_SIZE = 4096;

	public DownloadThread(String url ,String path){

		this.url = url;
		//		this.fileTitle = title;
		this.filePath = path;

	}
	/**
	 * @param dispose
	 * @param fileURL
	 * @return
	 */
	private File createFile(String dispose , String fileURL){
		String fileTitle = null;
		if (dispose != null) {
			// extracts file name from header field
			int index = dispose.indexOf("filename=");
			if (index > 0) {
				fileTitle = dispose.substring(index + 10,
						dispose.length() - 1);
				System.out.println("filename" + fileTitle);
			}
		} else {
			// extracts file name from URL
			fileTitle = fileURL.substring(fileURL.lastIndexOf("/") + 1,
					fileURL.length());
		}
		File downlodedFile = new File(filePath+"/"+fileTitle);
		if(!downlodedFile.exists()){
			try {
				downlodedFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Cannot create File to store.");
				e.printStackTrace();
				System.exit(0);
			}
		}
		return downlodedFile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run(){
		try{
			URL obj = new URL(url); // create url object for the given string
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			if(url.startsWith("https")){
				System.out.println("Establishing https URL connection. . .");
				 connection = (HttpsURLConnection) obj.openConnection();
			}
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.connect(); // connect to the server with in the specified url
			
			int requestinfo = connection.getResponseCode(); // get the responce code from the server which might be helpful in understanding the server response for the download request.
			String dispose = connection.getHeaderField("Content-Disposition");
			if (requestinfo == connection.HTTP_OK) {
				BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); // open the input stream on the established tcp connection.
				FileOutputStream out = new FileOutputStream(createFile(dispose,url)); // create a file and open the output stream to write on file.
				int size = connection.getContentLength(); // to get the total size of the file being downloaded it will be helpful making the GUI like progress bar.
				int len = -1; 
				int progress = 0 ; // to update the GUI progress bar.

				byte[] buffer = new byte[BUFFER_SIZE]; // byte array to get the content from the input stream.
				while((len = in.read(buffer,0,BUFFER_SIZE)) != -1){ // getting content from the input stream and saving into the buffer byte array.
					out.write(buffer,0,len); // writing the bytes to the file.
					//			out.write(buffer);
					progress+=len; // update progress variable 
					System.out.println("Downloded bytes "+progress+ " Remaining  bytes  "+(size-progress));
				} 
				out.flush(); // empty the buffer.
				in.close(); // close opened streams
				out.close();
				System.out.println("Download Complete . ");
			}else{
				System.out.println("Cannot download File response code from server: " +requestinfo);
			}

		}catch(FileNotFoundException e){
			System.err.println("Error While Downloading : file not found . ");
			e.printStackTrace();
		}catch(ProtocolException e){
			System.err.println("Error While Downloading : protocol exception . ");
			e.printStackTrace();
		}catch(MalformedURLException e){
			System.err.println("invalid url.");

		}catch(IOException e){
			System.err.println("input output exception .");
			e.printStackTrace();
		}
		}

}
>>>>>>> 5e52cfb48be6a20f5a9073c870c2fa4f6f4ec81d
