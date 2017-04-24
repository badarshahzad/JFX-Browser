package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class BookMarksDataBase
{
	private static Connection c = SqliteConnection.Connector();
	private static PreparedStatement perp=null;
	public static void createBookMarksDataBase(){
		  try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
	      perp=c.prepareStatement("CREATE TABLE if not exists bookmark(url text ,folder_name varchar(20),"
	      		+ "title varchar (30), user_id integer );");
		      perp.executeUpdate();
		      System.out.println("table created");
		} catch (ClassNotFoundException | SQLException e) {
		}
	      
	}
	public static void insertBookmarks(String url,String folder,String title,int user_id){
		
		try
		{
			
			String insert="insert or replace into bookmark(url,folder_name,title,user_id)"+"values(?,?,?,?)";
			perp=c.prepareStatement(insert);
			perp.setString(1, url);
			perp.setString(2,folder);
			perp.setString(3, title);
			perp.setInt(4,user_id);
			perp.executeUpdate();
			System.out.println("Bookmark Entry added.");
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}

	}
	//	-------------------------------------show bookmarks--------------------------------------------
	public static ResultSet showBookmarks(String folder)
	{
		ResultSet rs=null;
		try
		{
			String query="select title, time ,date,url "
					+ "from history where url in (select url from bookmark where folder_name = ?);";
			perp=c.prepareStatement(query);
			perp.setString(1, folder);
			rs=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("Exception showing bookmarks:");
			e.printStackTrace();
		}
		return rs;
	}
	public static ObservableList<String> folders(){
		ResultSet rs;
		String query=null;
		ObservableList<String> list = FXCollections.observableArrayList();
			query ="select distinct folder_name from bookmark;";			
		try {
			rs = c.prepareStatement(query).executeQuery();
			while(rs.next()){
				String folder = rs.getString(1);
				if(folder!=null){
					list.add(folder);	
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving folder names.");
		}
		return list;
		
	}
}