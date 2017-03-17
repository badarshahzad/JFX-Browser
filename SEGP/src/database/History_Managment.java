package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
public class History_Managment
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
	private static java.util.Date date = new java.util.Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

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
			c.prepareStatement("CREATE TABLE if not exists bookmarks(url text primary key ,"
					+ "Time text,"
					+ "Date DEFAULT CURRENT_DATE ,"
					+ "name varchar(30),"
					+ "folder varchar(30));").executeUpdate();
			System.out.println("table created");
			perp.close();
			c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("issues");
			e.printStackTrace();
		}
	}
	//------------------------------------ULR INSERTION IN DATABASE----------------------------------------------------------------//

	public static void insertUrl(String url)
	{
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formate_time = new SimpleDateFormat("HH:mm:ss");

		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String insert="insert or replace into history(url,Time)"+"values(?,?)";
			String time=formate_time.format(date);
			perp=c.prepareStatement(insert);
			perp.setString(1, url);
			perp.setString(2,time);
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
		ResultSet rs=null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String str="select * from history order by date DESC";
			perp=c.prepareStatement(str);
			rs=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("There are issues while showing the history");
		}
		return rs;
	}

	/*Modifying the history database 
	 * if you guys don't mind :P
	 * */


	
//	------------------------------------insert bookmarks----------------------------------------------
	public static void insertBookmarks(String url,String name,String folder)
	{
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formate_time = new SimpleDateFormat("HH:mm:ss");

		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String insert="insert or replace into bookmarks(url,Time,name,folder)"+"values(?,?,?,?)";
			String time=formate_time.format(date);
			perp=c.prepareStatement(insert);
			perp.setString(1, url);
			perp.setString(2,time);
			perp.setString(3,name);
			perp.setString(4, folder);
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
//	-------------------------------------show bookmarks--------------------------------------------
	public static ResultSet showBookmarks(String folder)
	{
		ResultSet rs=null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String str="select * from bookmarks where folder = ?";
			perp=c.prepareStatement(str);
			perp.setString(1, folder);
			rs=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("There are issues while showing the history");
		}
		return rs;
	}
	public static void main(String[] args) throws SQLException{
		History_Managment.CreateDataBase();
		History_Managment.insertBookmarks("www.google.com.pk", "google","Entertainment");
		History_Managment.insertBookmarks("www.facebook.com.", "facebook","Entertainment");
		History_Managment.insertBookmarks("www.twitter.com", "twitter","Entertainment");
		History_Managment.insertBookmarks("www.youtube.com", "youtube","Entertainment");
		History_Managment.insertBookmarks("www.github.com", "github","Entertainment");
		History_Managment.insertBookmarks("www.softonic.com", "softonic softwares","Programming");
		History_Managment.insertBookmarks("www.ytpak.com", "ytpak videos","Entertainment");
		History_Managment.insertBookmarks("www.stackoverflow.com", "stackoverflow","Programming");
		History_Managment.insertBookmarks("www.tutorialspoint.com", "tutorialspoint","Programming");
		History_Managment.insertBookmarks("http://www.cplusplus.com/doc/tutorial/", "Learn C++","C++");
		History_Managment.insertBookmarks("https://www.tutorialspoint.com/cplusplus/", "Learn C++ tuttorialpoint","C++");
		History_Managment.insertBookmarks("http://www.cprogramming.com/tutorial/c++-tutorial.html", "cprogramming.com","C++");
		History_Managment.insertBookmarks("https://www.udemy.com/free-learn-c-tutorial-beginners/", "udemy","C++");
		History_Managment.insertBookmarks("https://www.programiz.com/cpp-programming", "programiz","C++");
		History_Managment.insertBookmarks("https://www.tutorialcup.com/cplusplus", "totorialzcup","C++");
		History_Managment.insertBookmarks("https://www.tutorialcup.com/java", "totorialzcup","Java");
		History_Managment.insertBookmarks("https://www.tutorialspoint.com/java/", "tpoint","Java");
		History_Managment.insertBookmarks("https://www.udemy.com/java-tutorial/", "udemy","Java");
		History_Managment.insertBookmarks("http://learnyouahaskell.com/", "haskellpro","Haskell");
		History_Managment.insertBookmarks("http://learnyouahaskell.com/chapters", "chapters","Haskell");
		History_Managment.insertBookmarks("https://github.com/bitemyapp/learnhaskell", "github","Haskell");
		History_Managment.insertBookmarks("http://www.happylearnhaskelltutorial.com/", "happylearnhaskell","Haskell");
		History_Managment.insertBookmarks("https://youtube.com", "youtube.com","Youtube");
		History_Managment.insertBookmarks("https://facebook.com", "facebook.com","facebook");
		History_Managment.insertBookmarks("https://twitter.com", "twitter.com","twitter");
		ResultSet result = History_Managment.showBookmarks("Entertainment");
		while(result.next()){
			System.out.println(result.getString(1)+result.getString(2)+result.getString(5));
		}
	}
}