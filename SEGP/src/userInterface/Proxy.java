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
	 * @param type of proxy e.g http/https, socks as a string 
	 */
=======
>>>>>>> upstream/master
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
	 * @param pHost  http proxy host 
	 * @param pPort  http proxy port
	 */
=======
>>>>>>> upstream/master
	public void setHttpProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("http");
	}
	//--------------------------https proxy---------------------------------------
<<<<<<< HEAD
	/**
	 * @param pHost https proxy host
	 * @param pPort https proxy port
	 */
=======
>>>>>>> upstream/master
	public void setHttpsProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("https");
		
	}
	//----------------------------Socks proxy-------------------------------------
<<<<<<< HEAD
	/**
	 * @param pHost socks proxy host
	 * @param pPort socks proxy port
	 */
=======
>>>>>>> upstream/master
	public void setSocksProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("socks");
	}

}
