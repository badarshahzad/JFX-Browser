package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class BookMarksDataBase
{
	private static Connection c = SqliteConnection.Connector();
	private static PreparedStatement perp=null;
	private static java.util.Date date = new java.util.Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public BookMarksDataBase(){
	}
	public static void insertBookmarks(String url,String folder){
		
		try
		{
			int urlId=0;
			String query = "select url_id from history where url = ? and user_id = ?";
			perp=c.prepareStatement(query);
			perp.setString(1,url);
			perp.setInt(2,1);
			ResultSet rs = perp.executeQuery();
			while(rs.next()){
				urlId = rs.getInt(1);
			}
			String insert="insert or replace into bookmark(url_id,folder,user_id)"+"values(?,?,?)";
			perp=c.prepareStatement(insert);
			perp.setInt(1, urlId);
			perp.setString(2,folder);
			perp.setInt(3,1);
			perp.executeUpdate();
			perp.close();
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
			String query="select history.title, history.time ,history.date, history.url "
					+ "from history where url_id in (select url_id from bookmark where folder = ?);";
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
			query ="select distinct folder from bookmark;";			
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