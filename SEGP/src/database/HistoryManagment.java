package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.ObservableList;
import userInterface.History;
public class HistoryManagment
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
	private static java.util.Date dateTime;
	static SimpleDateFormat formateTime;
	private static SimpleDateFormat dateFormate;
//------------------------------------------------------Creating SQLITE DATABASE and TABLE--------------------------------------//
	public static void CreateDataBase()
	{		
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
		      System.out.println("Open'ed database successfully");
		      perp=c.prepareStatement("CREATE TABLE if not exists history(url text primary key ,Time text,Date text );");
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
		dateTime = new java.util.Date();
		formateTime = new SimpleDateFormat("HH:mm:ss");
		dateFormate = new SimpleDateFormat("yy-MM-dd");
		
		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:History.db");
			  String insert="insert or replace into history(url,Time,Date)"+"values(?,?,?)";
			  String time=formateTime.format(dateTime);
			  String date=dateFormate.format(dateTime);
		      perp=c.prepareStatement(insert);
		      perp.setString(1, url);
		      perp.setString(2,time);
		      perp.setString(3,date);
		      perp.executeUpdate();
		      System.out.println("data has been inserted");
		      perp.close();
		      c.close();
		      
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	
	}
//---------------------------------	Delete History------------------------------------------------------------------//
	public static void deleteFromDatabase()
	{
		ResultSet rs=null;
		try
		{
		Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:History.db");
	    String qeury="delete  from history;";
	    perp=c.prepareStatement(qeury);
	    perp.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		System.out.println("Issues in delete method");
		}

	}
//---------------------------------Full HISTORY Show--------------------------------------------------------------------------//

public static  ObservableList fullHistoryShow(ObservableList fullHistory)
{
	
	ResultSet rs=null;
	try
	{
	Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection("jdbc:sqlite:History.db");
    String str="select * from(select * from history order by Time DESC) history order by Date DESC";
    perp=c.prepareStatement(str);
    rs=perp.executeQuery();
  
    	while(rs.next()) //loop for data fetching and pass it to GUI table view
    	{
    	String link1 =rs.getString(1);
    	String time1=rs.getString(2);
    	String date1=rs.getString(3);
    	fullHistory=History.addDataInList(link1, time1,date1,fullHistory);
    	}
    	rs.close();
    	perp.close();
    	c.close();
	}
	catch(Exception e)
	{
	System.out.println("Issues in fullHistoryShow method");
	}
return fullHistory;
}

//------------------------------------this method return user specified histories--------------------------------------------------------------------//1
public static ObservableList getHistory(ObservableList list,int dateRange)
{
	ResultSet rs=null;
	dateTime=new java.util.Date();
	
	//past dates denpending upon the function parameter 'dateRange' 
	dateFormate= new SimpleDateFormat("yy-MM-dd");
	final Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, dateRange);
    String pastDate=dateFormate.format(cal.getTime());
    pastDate="'"+pastDate+"'";
    
    String qeury;
    try
	{
	Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:History.db");
	//user aske for today or yesterday history
	if(dateRange==0||dateRange==-1)
	{
	qeury="select * from (select * from history order by Time DESC) history where Date like"+pastDate+";";
	perp=c.prepareStatement(qeury);
	rs=perp.executeQuery();
	}
	//if user asks for more two day history
	else
	{
	qeury="select * from (select * from history order by Time DESC) history where Date>="+pastDate+" Order BY Date DESC;";
	perp=c.prepareStatement(qeury);
	rs=perp.executeQuery();
	}

	while(rs.next())//loop for data fetching and pass it to GUI table view
	{
		 String link1 =rs.getString(1);
		 String time1=rs.getString(2);
		 String date1=rs.getString(3);
		 list=History.addDataInList(link1,time1,date1,list);
	}
		
		rs.close();
		perp.close();
		c.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("isseus in getHistory method ");
	}
return list;    
}
//end method

//-------------------------------------Past Hour Histroy Show-------------------------------------------------------------//
public static ObservableList pastHoursHistory(ObservableList pastHour,int time)
{
	ResultSet rs=null;
	dateTime = new java.util.Date();
	formateTime = new SimpleDateFormat("HH:mm:ss");
	dateFormate = new SimpleDateFormat("yy-MM-dd");
	
	final Calendar cal = Calendar.getInstance();
    cal.add(Calendar.HOUR_OF_DAY,time);
    Date date=cal.getTime();
    
   //current and past time
    String pastHourTime=formateTime.format(date);
	pastHourTime="'"+pastHourTime+"'";
	
	
	//current date
	String currentDate=dateFormate.format(dateTime);
	currentDate="'"+currentDate+"'";
	System.out.println(currentDate);
	try
	{
	Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:History.db");
	String qeury="select * from history where Time>"+pastHourTime+"AND Date LIKE "+currentDate+"order by Time DESC;";
	perp=c.prepareStatement(qeury);
	rs=perp.executeQuery();
	System.out.println(rs.next());
	while(rs.next()) //loop for data fetching and pass it to GUI table view
	 {
		String link1 =rs.getString(1);
		String time1=rs.getString(2);
		String date1=rs.getString(3);
		pastHour=History.addDataInList(link1,time1,date1,pastHour);
	 }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	System.out.println("isseus in pastHours mehtod");
	}
    
return pastHour;
}
//end method
}
//end Class