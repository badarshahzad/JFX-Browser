package bookmarks;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.History_Managment;

public class PopulateTable {
	
	public PopulateTable(){
		ResultSet bookmarks = History_Managment.showBookmarks();
		try {
			while(bookmarks.next()){
				
				
			}
		} catch (SQLException e) {
			System.out.println("SQLITE Exception in populate table.");
			System.out.println(e.getMessage());
		}
	}

}
