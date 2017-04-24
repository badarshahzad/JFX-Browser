package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadDatabase {
	private static Connection c = SqliteConnection.Connector();
	private static PreparedStatement perp=null;
	public static void createDownloadDataBase(){
		  try {
			c = SqliteConnection.Connector();
	      perp=c.prepareStatement("CREATE TABLE if not exists download(url text ,file_name varchar(20),"
	      		+ "status varchar (30), user_id integer );");
		      perp.executeUpdate();
		      System.out.println("table created");
		} catch (SQLException e) {
		}
	      
	}
	public static void insertDownload(String url, String fileName,String status ,int userId){
		c= SqliteConnection.Connector();
		try
		{
			
			String insert="insert or replace into download(url,file_name,status,user_id)"+"values(?,?,?,?)";
			perp=c.prepareStatement(insert);
			perp.setString(1, url);
			perp.setString(2,fileName);
			perp.setString(3, status);
			perp.setInt(4,userId);
			perp.executeUpdate();
			System.out.println("Download Entry added.");
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	public static ResultSet getDownload(int userId){
		
		ResultSet result = null;
		c= SqliteConnection.Connector();
		try
		{
			String query="select url, file_name,status "
					+ "from download where user_id = ?;";
			perp=c.prepareStatement(query);
			perp.setInt(1, userId);
			result=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("Exception showing bookmarks:");
			e.printStackTrace();
		}
		return result;
	}
	
}
