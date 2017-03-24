package bookmarks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.BookMarksDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PopulateTable {
	private ObservableList<URLdetails> list = FXCollections.observableArrayList();
	private BookMarksDataBase db = new BookMarksDataBase();
	public ObservableList<URLdetails> PopulateTable(String folder){
				ResultSet bookmarks = db.showBookmarks(folder);
		try {
			while(bookmarks.next()){
				URLdetails bookmark = new URLdetails(bookmarks.getString(4), bookmarks.getString(2), bookmarks.getString(3), bookmarks.getString(1));
				list.add(bookmark);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("SQLITE Exception in populate table.");
			System.out.println(e.getMessage());
		}
		return null;
	}
public ArrayList<String> folderNames(){
	ArrayList<String> folderNames = new ArrayList<>();
	return folderNames;
}
}
