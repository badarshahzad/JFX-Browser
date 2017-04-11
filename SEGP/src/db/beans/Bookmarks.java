package db.beans;

public class Bookmarks {
	
	private int url_Id;
	private String folder;
	private int user_id;
	
	public int getUrl_Id() {
		return url_Id;
	}
	public void setUrl_Id(int url_Id) {
		this.url_Id = url_Id;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
