package main.java.com.github.jfxbrowser.bookmarks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.github.jfxbrowser.database.BookMarksDataBase;

public class PopulateTable {
	private ObservableList<URLdetails> list = FXCollections.observableArrayList();
	private BookMarksDataBase db = new BookMarksDataBase();

	public ObservableList<URLdetails> PopulateTable(String folder) {

		ResultSet bookmarks = BookMarksDataBase.showBookmarks(folder, 1);
		try {
			while (bookmarks.next()) {
				URLdetails bookmark = new URLdetails(bookmarks.getString(1), bookmarks.getString(2),
						bookmarks.getString(3), bookmarks.getString(4));
				list.add(bookmark);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("SQLITE Exception in populate table.");
		}
		return null;
	}

	public ArrayList<String> folderNames() {
		ArrayList<String> folderNames = new ArrayList<>();
		return folderNames;
	}
}
