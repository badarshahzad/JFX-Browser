package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
public class History_Managment
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
<<<<<<< HEAD:SEGP/src/database/History.java
	private static java.util.Date date = new java.util.Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public static void sleep(int mSec){
		for (int i =0 ; i<mSec*mSec*1000 ; i++){
			
		}
	}
	
	public static void main(String [] args)
	{
		CreateDataBase();
//		insertUrl("https://www.google.com/");
//		insertUrl("https://www.facebook.com/");
//		insertUrl("https://www.cnn.com/");
//		insertUrl("https://www.wikipedia.com/");
//		insertUrl("https://www.youtube.com/");
//		insertUrl("https://www.stackOverflow.com/");
		showHistory();
=======
	//private static java.util.Date date = new java.util.Date();
	//private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public static void main(String [] args)
	{
		showHistory();
		
>>>>>>> 5e52cfb48be6a20f5a9073c870c2fa4f6f4ec81d:SEGP/src/database/History_Managment.java
	}
//------------------------------------------------------Creating SQLITE DATABASE and TABLE--------------------------------------//
	public static void CreateDataBase()
	{		
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
		      System.out.println("Opened database successfully");
		      perp=c.prepareStatement("CREATE TABLE if not exists history(url text primary key ,Time text,Date DEFAULT CURRENT_DATE );");
		      perp.executeUpdate();
		      System.out.println("table created");
		      perp.close();
		      c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
	    	System.out.println("issues");
	    }
		}
//------------------------------------ULR INSERTION IN DATABASE----------------------------------------------------------------//
	
	public static void insertUrl(String url)
	{
		 
		Connection c = null;
		PreparedStatement perp=null;
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
			  String insert="insert or replace into history(url,Time)"+"values(?,?)";
			  String str=sdf.format(date);
		      perp=c.prepareStatement(insert);
		      perp.setString(1, url);
		      perp.setString(2,str);
		      perp.executeUpdate();
		      System.out.println("data has been inserted");
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	
	}
//---------------------------------VIEWING THE HISTORY--------------------------------------------------------------------------//

public static ResultSet showHistory()
{
	
	
	Connection c = null;
	PreparedStatement perp=null;
	ResultSet rs=null;
	try
	{
		 Class.forName("org.sqlite.JDBC");
	     c = DriverManager.getConnection("jdbc:sqlite:History.db");
<<<<<<< HEAD:SEGP/src/database/History.java
		 String str="select * from history order by time DESC ,date DESC";
=======
		 String str="select * from history order by time DESC,date DESC";
>>>>>>> 5e52cfb48be6a20f5a9073c870c2fa4f6f4ec81d:SEGP/src/database/History_Managment.java
	     perp=c.prepareStatement(str);
	     rs=perp.executeQuery();
	    /* while(rs.next())
		 {
			 String link1 =rs.getString(1);
			 String time1=rs.getString(2);
			 String date1=rs.getString(3);
			 System.out.println(link1);
			 System.out.println(time1);
			 System.out.println(date1);
			 
		 }*/
	}
	catch(Exception e)
	{
		System.out.println("There are issues while showing the history");
	}
	return rs;
	}
}


