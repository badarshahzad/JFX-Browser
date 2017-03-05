package userInterface;

import java.util.Properties;

<<<<<<< HEAD
/**
 * @author SEGP-Group3
 *
 */
=======
>>>>>>> upstream/master
public class Proxy {
	private String pHost=null;
	private String pPort=null;
	
	
	//-----------------------setProxy of the Systtem-------------------------------
<<<<<<< HEAD
	/**
	 * @param type Proxy type e.g http,https.
	 */
=======
<<<<<<< HEAD
	/**
	 * @param type of proxy e.g http/https, socks as a string 
	 */
=======
>>>>>>> upstream/master
>>>>>>> 8995dbeff95d15d59f84b530de9dc39ed3f4a7b6
	private void setProxy(String type){
		Properties systemProperties = System.getProperties();
		try{
			
		systemProperties.setProperty(type+".proxyHost",pHost);
		systemProperties.setProperty(type+".proxyPort",pPort);
		}catch(Exception e){
			System.err.println("Invalid Proxy");
		}
	}
	//-----------------------http proxy--------------------------------------------
<<<<<<< HEAD
	/**
	 * @param pHost Http proxy host
	 * @param pPort Http proxy host
	 */
=======
<<<<<<< HEAD
	/**
	 * @param pHost  http proxy host 
	 * @param pPort  http proxy port
	 */
=======
>>>>>>> upstream/master
>>>>>>> 8995dbeff95d15d59f84b530de9dc39ed3f4a7b6
	public void setHttpProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("http");
	}
	//--------------------------https proxy---------------------------------------
<<<<<<< HEAD
	/**
	 * @param pHost Https proxy host
	 * @param pPort Https proxy host
	 */
=======
<<<<<<< HEAD
	/**
	 * @param pHost https proxy host
	 * @param pPort https proxy port
	 */
=======
>>>>>>> upstream/master
>>>>>>> 8995dbeff95d15d59f84b530de9dc39ed3f4a7b6
	public void setHttpsProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("https");
		
	}
	//----------------------------Socks proxy-------------------------------------
<<<<<<< HEAD
	/**
	 * @param pHost Socks proxy host
	 * @param pPort Socks proxy host
	 */
=======
<<<<<<< HEAD
	/**
	 * @param pHost socks proxy host
	 * @param pPort socks proxy port
	 */
=======
>>>>>>> upstream/master
>>>>>>> 8995dbeff95d15d59f84b530de9dc39ed3f4a7b6
	public void setSocksProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("socks");
	}

}
