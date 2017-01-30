package DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class History
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
	
	public static void main(String [] args)
	{
		showHistory();
	}
	
//-------------------------------------------------------Creating SQLITE DATABASE and TABLE--------------------------------------//
	public static void CreateDataBase()
	{
		
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
		      System.out.println("Opened database successfully");
		      perp=c.prepareStatement("CREATE TABLE if not exists history(id integer primary key AUTOINCREMENT,	url text  ,Time  DEFAULT CURRENT_TIME,Date DEFAULT CURRENT_DATE );");
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
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
			  String insert="insert into history(url)"+"values(?)";
		      perp=c.prepareStatement(insert);
		      perp.setString(1, url);
		      perp.executeUpdate();
		      System.out.println("data has been inserted");
		}
		catch(Exception e)
		{
			System.out.println("There are issues while doing insertion url");
		}
	
	}
//---------------------------------VIEWING THE HISTORY--------------------------------------------------------------------------//

public static void showHistory()
{
	Connection c = null;
	PreparedStatement perp=null;
	try
	{
		 Class.forName("org.sqlite.JDBC");
	     c = DriverManager.getConnection("jdbc:sqlite:History.db");
		 String str="select * from history";
	     perp=c.prepareStatement(str);
	     ResultSet rs=perp.executeQuery();
	     System.out.println("ID		"+"URL		                                              "+"Time			   "+"Date");
	     while(rs.next())
	     {
	    	 System.out.print(rs.getInt(1)+"             ");
	    	 System.out.print(rs.getString(2)+"   	                                      ");
	    	 System.out.print(rs.getString(3)+"		    ");
	    	 System.out.print(rs.getString(4));
	    	 System.out.println();
	     }	
	}
	catch(Exception e)
	{
		System.out.println("There are issues while showing the history");
	}
	
	}
}


