package bookmarks;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.History_Managment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PopulateTable {
	private ObservableList<URLdetails> list = FXCollections.observableArrayList();
	
	public ObservableList<URLdetails> PopulateTable(String folder){
		ResultSet bookmarks = History_Managment.showBookmarks(folder);
		try {
			while(bookmarks.next()){
				URLdetails bookmark = new URLdetails(bookmarks.getString(4), bookmarks.getString(2), bookmarks.getString(3), bookmarks.getString(1));
				System.out.println(bookmarks.toString());
				list.add(bookmark);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("SQLITE Exception in populate table.");
			System.out.println(e.getMessage());
		}
		return null;
	}

}
