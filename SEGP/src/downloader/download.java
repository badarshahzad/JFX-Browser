package downloader;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class download{
	private	StringProperty url;
	private	StringProperty fileName;
	private	StringProperty fileStatus;
	public download(String url , String fileName,String fileStatus){
		this.url = new SimpleStringProperty(url);
		this.fileName = new SimpleStringProperty(fileName);
		this.fileStatus = new SimpleStringProperty(fileStatus);
	}
	public String getUrl() {
		return url.get();
	}
	public String getFileName() {
		return fileName.get();
	}
	public String getFileStatus() {
		return fileStatus.get();
	}		
	
	public void setUrl(String url){
		this.url = new SimpleStringProperty(url);
	}
	public void setFileName(String fileName){
		this.fileName = new SimpleStringProperty(fileName);
	}
	public void setFileStatus(String fileStatus){
		this.fileStatus = new SimpleStringProperty(fileStatus);
	}
}
