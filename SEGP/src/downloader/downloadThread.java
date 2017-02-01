package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import org.omg.PortableInterceptor.RequestInfo;

public class downloadThread extends Thread{
	//http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3
	private String url ;
	//	private String fileTitle ;
	private String filePath ;
	private static final int BUFFER_SIZE = 4096;

	public downloadThread(String url ,String path){
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
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection(); // open http connection on the url 
			//		connection.connect(); // connect to the server with in the specified url
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			int requestinfo = connection.getResponseCode(); // get the responce code from the server which might be helpful in understanding the server response for the download request.
			String dispose = connection.getHeaderField("Content-Disposition");
			if (requestinfo == HttpURLConnection.HTTP_OK) {
				BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); // open the input stream on the established tcp connection.
				FileOutputStream out = new FileOutputStream(createFile(dispose,url)); // create a file and open the output stream to write on file.
				int size = connection.getContentLength(); // to get the total size of the file being downloaded it will be helpful making the GUI like progress bar.
				int len = -1; 
				int progress = 0 ; // to update the GUI progress bar.

				byte[] buffer = new byte[BUFFER_SIZE]; // byte array to get the content from the input stream.
				while((len = in.read(buffer,0,BUFFER_SIZE)) != -1){ // getting content from the input stream and saving into the buffer byte array.
					out.write(buffer,0,BUFFER_SIZE); // writing the bytes to the file.
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
		}catch(MalformedURLException e){
			System.err.println("invalid url.");

		}catch(MalformedInputException e){
			System.err.println("invalid input.");
		}
		catch(Exception e){
			System.err.println("Error While Downloading ::");
			e.printStackTrace();
		}

	}

}
