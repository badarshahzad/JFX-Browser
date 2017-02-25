package userInterface;

import java.util.Properties;

public class Proxy {
	private String pHost=null;
	private String pPort=null;
	
	
	//-----------------------setProxy of the Systtem-------------------------------
	/**
	 * @param type Proxy type e.g http,https.
	 */
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
	/**
	 * @param pHost Http proxy host
	 * @param pPort Http proxy host
	 */
	public void setHttpProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("http");
	}
	//--------------------------https proxy---------------------------------------
	/**
	 * @param pHost Https proxy host
	 * @param pPort Https proxy host
	 */
	public void setHttpsProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("https");
		
	}
	//----------------------------Socks proxy-------------------------------------
	/**
	 * @param pHost Socks proxy host
	 * @param pPort Socks proxy host
	 */
	public void setSocksProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("socks");
	}

}
