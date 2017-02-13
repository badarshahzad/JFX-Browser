package userInterface;

import java.util.Properties;

public class Proxy {
	private String pHost=null;
	private String pPort=null;
	
	
	//-----------------------setProxy of the Systtem-------------------------------
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
	public void setHttpProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("http");
	}
	//--------------------------https proxy---------------------------------------
	public void setHttpsProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("https");
		
	}
	//----------------------------Socks proxy-------------------------------------
	public void setSocksProxy(String pHost ,String pPort){
		this.pHost = pHost;
		this.pPort = pPort;
		setProxy("socks");
	}

}
