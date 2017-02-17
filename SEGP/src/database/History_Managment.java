package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class History_Managment
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
	//private static java.util.Date date = new java.util.Date();
	//private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public static void main(String [] args)
	{
		showHistory();
		
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
		 String str="select * from history order by time DESC,date DESC";
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


