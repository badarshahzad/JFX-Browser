package downloader;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

import userInterface.Proxy;

public class MainDownload {
//	private  String title ;
	/* sample URL
	 * http://sound30.mp3slash.net/320/indian/raees/02%20-%20Zaalima%20[Songspk.GURU].mp3
	 * */

	public void startDownload(String url){

		DownloadThread thread = new DownloadThread(url, downloadFolder());
		System.setProperty("java.net.preferIPv4Stack" , "true");
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.16.0.2", "8080");
		proxy.setHttpsProxy("172.16.0.2", "8080");
//		thread.start();
		thread.start();
		
		
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
		MainDownload download = new MainDownload();
		
//		download.startDownload("https://examples.javacodegeeks.com/core-java/net/sockettimeoutexception/java-net-sockettimeoutexception-how-to-solve-sockettimeoutexception/");
//		download.startDownload("https://www.namal.edu.pk/wp-content/uploads/2017/01/Cloud-Gaming.pdf");
//		new MainDownload().startDownload("https://github-windows.s3.amazonaws.com/GitHubSetup.exe");
		//http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3
//		download.startDownload("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ");
		/*https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ*/
//		download.startDownload("https://www.google.com.pk/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwiE2ObT1vrRAhUBtRQKHShJDvYQFggXMAA&url=http%3A%2F%2Fwww.pdf995.com%2Fsamples%2Fpdf.pdf&usg=AFQjCNFUjjZAJw2DPPGOy7FpbX0wEqSnzA&bvm=bv.146094739,d.d24");
	
		/*	download.startDownload("http://dldir1.qq.com/qqfile/qq/QQ2013/QQ2013Beta2.exe");
		download.startDownload("http://down.myapp.com/android/45592/881859/qq2013_4.0.2.1550_android.apk");
		download.startDownload( "http://dictionary.b0.upaiyun.com/phrase.zip");
		download.startDownload("http://212.187.212.73/bt/58179a08a7873dc2c624c38abfc3f3bebef91d79/data/2012-12-16-wheezy-raspbian.zip");
		download.startDownload("http://mac.eltima.com/download/elmediaplayer.dmg");
		download.startDownload("http://res.yyets.com/ftp/2013/0419/YYeTs_2199a2019776bdc256fc3b127b2b93b3.zip");
		download.startDownload("http://down11.zol.com.cn/suyan/ggpy2.1.0.apk");
	*/
	}
}
