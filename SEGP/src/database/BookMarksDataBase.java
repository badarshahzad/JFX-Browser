package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
public class BookMarksDataBase
{
	private static Connection c = null;
	private static PreparedStatement perp=null;
	private static java.util.Date date = new java.util.Date();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public BookMarksDataBase(){
		CreateDataBase();
	}
	public static void CreateDataBase()
	{		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:History.db");
			perp = c.prepareStatement("CREATE TABLE if not exists bookmarks(url text primary key ,"
					+ "Time text,"
					+ "Date DEFAULT CURRENT_DATE ,"
					+ "name varchar(30),"
					+ "folder varchar(30));");
			perp.executeUpdate();
			perp.close();
			c.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception while creating Table Bookmarks");
			e.printStackTrace();
		}
	}
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
//	public static void main(String[] args){
//		DataBase db = new DataBase();
//		db.insertBookmarks("www.google.com.pk", "google","Entertainment");
//		db.insertBookmarks("www.facebook.com.", "facebook","Entertainment");
//		db.insertBookmarks("www.twitter.com", "twitter","Entertainment");
//		db.insertBookmarks("www.youtube.com", "youtube","Entertainment");
//		db.insertBookmarks("www.github.com", "github","Entertainment");
//		db.insertBookmarks("www.softonic.com", "softonic softwares","Programming");
//		db.insertBookmarks("www.ytpak.com", "ytpak videos","Entertainment");
//		db.insertBookmarks("www.stackoverflow.com", "stackoverflow","Programming");
//		db.insertBookmarks("www.tutorialspoint.com", "tutorialspoint","Programming");
//		db.insertBookmarks("http://www.cplusplus.com/doc/tutorial/", "Learn C++","C++");
//		db.insertBookmarks("https://www.tutorialspoint.com/cplusplus/", "Learn C++ tuttorialpoint","C++");
//		db.insertBookmarks("http://www.cprogramming.com/tutorial/c++-tutorial.html", "cprogramming.com","C++");
//		db.insertBookmarks("https://www.udemy.com/free-learn-c-tutorial-beginners/", "udemy","C++");
//		db.insertBookmarks("https://www.programiz.com/cpp-programming", "programiz","C++");
//		db.insertBookmarks("https://www.tutorialcup.com/cplusplus", "totorialzcup","C++");
//		db.insertBookmarks("https://www.tutorialcup.com/java", "totorialzcup","Java");
//		db.insertBookmarks("https://www.tutorialspoint.com/java/", "tpoint","Java");
//		db.insertBookmarks("https://www.udemy.com/java-tutorial/", "udemy","Java");
//		db.insertBookmarks("http://learnyouahaskell.com/", "haskellpro","Haskell");
//		db.insertBookmarks("http://learnyouahaskell.com/chapters", "chapters","Haskell");
//		db.insertBookmarks("https://github.com/bitemyapp/learnhaskell", "github","Haskell");
//		db.insertBookmarks("http://www.happylearnhaskelltutorial.com/", "happylearnhaskell","Haskell");
//		db.insertBookmarks("https://youtube.com", "youtube.com","Youtube");
//		db.insertBookmarks("https://facebook.com", "facebook.com","facebook");
//		db.insertBookmarks("https://twitter.com", "twitter.com","Twitter");
//		ResultSet result = db.showBookmarks("Programming");
//		try {
//			while(result.next()){
//				System.out.println(result.getString(1)+result.getString(2)+result.getString(5));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
