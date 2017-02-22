
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
		File folder = new File (home,"Downloads/PaluDownloads");
		if (!folder.exists()){
			folder.mkdir();
		}
		return folder.getAbsolutePath();
	}
	public static void main (String[] args){
		System.out.println("hello");
		MainDownload download = new MainDownload();
		
//		download.startDownload("https://examples.javacodegeeks.com/core-java/net/sockettimeoutexception/java-net-sockettimeoutexception-how-to-solve-sockettimeoutexception/");
//		download.startDownload("https://www.namal.edu.pk/wp-content/uploads/2017/01/Cloud-Gaming.pdf");
//		new MainDownload().startDownload("https://github-windows.s3.amazonaws.com/GitHubSetup.exe");
//		download.startDownload("http://320net.songspk.onl/singles/Bloody%20Hell%20-%20320Kbps%20-%20Rangoon%20[Songspk.DESI].mp3");
//		download.startDownload("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ");
		/*https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ*/
//		download.startDownload("https://www.google.com.pk/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwiE2ObT1vrRAhUBtRQKHShJDvYQFggXMAA&url=http%3A%2F%2Fwww.pdf995.com%2Fsamples%2Fpdf.pdf&usg=AFQjCNFUjjZAJw2DPPGOy7FpbX0wEqSnzA&bvm=bv.146094739,d.d24");
//	
//		download.startDownload("http://dldir1.qq.com/qqfile/qq/QQ2013/QQ2013Beta2.exe");
//		download.startDownload("http://down.myapp.com/android/45592/881859/qq2013_4.0.2.1550_android.apk");
//		download.startDownload( "http://dictionary.b0.upaiyun.com/phrase.zip");
//		download.startDownload("http://212.187.212.73/bt/58179a08a7873dc2c624c38abfc3f3bebef91d79/data/2012-12-16-wheezy-raspbian.zip");
//		download.startDownload("https://r2---sn-hpa7zne6.googlevideo.com/videoplayback?key=cms1&sparams=clen,dur,ei,expire,gir,id,initcwndbps,ip,ipbits,itag,lmt,mime,mm,mn,ms,mv,pl,ratebypass,requiressl,source,upn&pl=24&ipbits=0&source=youtube&mime=video/mp4&requiressl=yes&ei=U4alWJbtAYrioAOuvoCQCg&itag=18&dur=176.518&id=o-AOQBCY_R8aoKfzijudm86B8iMFkIgfTscEpfPe70xQeT&gir=yes&ip=121.52.153.75&clen=17869581&expire=1487264435&lmt=1486754454737303&ratebypass=yes&upn=TQ0agBnI5PU&signature=5BFAB399E58E56612BC84F5A61FB02D78A07CCE9.5D7BC7F303ACDB73C248FB28A3BBDF539B71EC1D&cms_redirect=yes&mm=31&mn=sn-hpa7zne6&ms=au&mt=1487242825&mv=m");
		//		download.startDownload("http://mac.eltima.com/download/elmediaplayer.dmg");
//		download.startDownload("http://res.yyets.com/ftp/2013/0419/YYeTs_2199a2019776bdc256fc3b127b2b93b3.zip");
//		download.startDownload("http://down11.zol.com.cn/suyan/ggpy2.1.0.apk");
		download.startDownload("https://r1---sn-hpa7zn7d.googlevideo.com/videoplayback?source=youtube&beids=%5B9452307%5D&pl=24&id=o-AHl4C3JYlh9Iz31A8gwu5Wm1ajBgsQkVt8xgGJzM26Lh&requiressl=yes&dur=137.555&ipbits=0&expire=1487595633&sparams=clen,dur,ei,expire,gir,id,initcwndbps,ip,ipbits,itag,lmt,mime,mm,mn,ms,mv,pl,ratebypass,requiressl,source,upn&lmt=1487318235648466&upn=l6jtqYNTjyA&mime=video/mp4&itag=18&key=cms1&gir=yes&clen=9427852&ratebypass=yes&ip=121.52.153.75&ei=EZSqWP2mKsP1oAPAkIbIDg&signature=2FB09D331196899E1638448A48BB96F2327125B2.0E730C0801BE8149704B91E6AAC2AFB778DC9A8C&downloadvideo=1&title=PhillauriDUMDUM-Download-From-YTPak.com&filename=PhillauriDUMDUM-Download-From&extension=mp4&cms_redirect=yes&mm=31&mn=sn-hpa7zn7d&ms=au&mt=1487580388&mv=m");
	}
}
