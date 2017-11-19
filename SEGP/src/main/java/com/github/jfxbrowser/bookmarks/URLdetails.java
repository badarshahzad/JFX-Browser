package main.java.com.github.jfxbrowser.bookmarks;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class URLdetails {
	private	StringProperty name;
	private	StringProperty time;
	private	StringProperty date;
	private	StringProperty location;
	public URLdetails(String name , String time,String date, String location){
		this.name = new SimpleStringProperty(name);
		this.time = new SimpleStringProperty(time);
		this.date = new SimpleStringProperty(date);
		this.location = new SimpleStringProperty(location);
	}
	public String getName() {
		return name.get();
	}
	public String getDate() {
		return date.get();
	}
	public String getLocation() {
		return location.get();
	}		
	public String getTime() {
		return time.get();
	}
	public void setName(String name){
		this.name = new SimpleStringProperty(name);
	}
	public void setTime(String time){
		this.time = new SimpleStringProperty(time);
	}
	public void setDate(String date){
		this.date = new SimpleStringProperty(date);
	}
	public void setLocation(String location){
		this.location =new SimpleStringProperty(location);
	}
	@Override
	public String toString(){
		return this.location.toString();
	}
}