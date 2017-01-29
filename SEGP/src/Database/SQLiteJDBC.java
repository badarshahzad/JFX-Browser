package Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SQLiteJDBC
{
	private java.util.Date today = new java.util.Date();
  public static void main( String args[] )
  {
    Connection c = null;
    String url="www.google.com";
    PreparedStatement perp=null;
    try 
    {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Muhmmmad Khan/Desktop/SEGP/src/kaka.db");
      System.out.println("Opened database successfully");
      perp=c.prepareStatement("CREATE TABLE if not exists history(id integer primary key AUTOINCREMENT,	url text  ,Time  DEFAULT CURRENT_TIME,Date DEFAULT CURRENT_DATE );");
      perp.executeUpdate();
      System.out.println("table created");
      String insert="insert into history(url)"+"values(?)";
      perp=c.prepareStatement(insert);
      perp.setString(1, url);
      perp.executeUpdate();
      System.out.println("data has been inserted");
      String str="select * from history";
     perp=c.prepareStatement(str);
     ResultSet rs=perp.executeQuery();
     System.out.println("ID		"+"URL		"+"Time			"+"Date");
     while(rs.next())
     {
    	 System.out.print(rs.getInt(1)+"             ");
    	 System.out.print(rs.getString(2)+"   	");
    	 System.out.print(rs.getString(3)+"		");
    	 System.out.print(rs.getString(4));
    	 System.out.println();
    	 //Timestamp time=rs.getTimestamp("time");
    	 //System.out.println(time);
     }
      perp.close();
      c.close();
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	System.out.println("issues");
    }
  }
}