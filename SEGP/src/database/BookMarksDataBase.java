package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookMarksDataBase {
	private static Connection c = SqliteConnection.Connector();
	private static PreparedStatement perp = null;
	private static java.util.Date dateTime= new java.util.Date();;
	static SimpleDateFormat formateTime = new SimpleDateFormat("HH:mm:ss");;
	private static SimpleDateFormat dateFormate = new SimpleDateFormat("yy-MM-dd");
	
	public static void createBookMarksDataBase() {
		try {
			
			//Class.forName("org.sqlite.JDBC");
			///c = DriverManager.getConnection("jdbc:sqlite:History.db");
			perp = SqliteConnection.Connector().prepareStatement("CREATE TABLE if not exists bookmark(url text ,folder_name varchar(20),"
					+ "title varchar (30),"
					+ "time varchar(20),"
					+ "date varchar(20),"
					+ " user_id integer );");
			perp.executeUpdate();
		} catch (SQLException e) {
			
		}

	}

	public static void insertBookmarks(String url, String folder, String title, int user_id) {

		try {
			 String time=formateTime.format(dateTime);
			  String date=dateFormate.format(dateTime);

			String insert = "insert or replace into bookmark(url,folder_name,title,time,date,user_id)" + "values(?,?,?,?,?,?)";
			perp = c.prepareStatement(insert);
			
			perp.setString(1, url);
			perp.setString(2, folder);
			perp.setString(3, title);
			perp.setString(4, time);
			perp.setString(5, date);
			perp.setInt(6, user_id);
			perp.executeUpdate();
			System.out.println("Bookmark Entry added.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------show
	// bookmarks--------------------------------------------
	public static ResultSet showBookmarks(String folder , int userId) {
		ResultSet rs = null;
		try {
			String query = "select title, time ,date,url "

					+ "from bookmark where url in (select url from bookmark where folder_name = ? and user_id = ?);";

			perp = c.prepareStatement(query);
			perp.setString(1, folder);
			perp.setInt(2, userId);
			rs = perp.executeQuery();
		} catch (Exception e) {
			System.out.println("Exception showing bookmarks:");
			e.printStackTrace();
		}
		return rs;
	}

	public static ObservableList<String> folders(int userId) {
		ResultSet rs;
		String query = null;
		ObservableList<String> list = FXCollections.observableArrayList();
		query = "select distinct folder_name from bookmark where user_id = ?;";
		try {
			perp = c.prepareStatement(query);
			perp.setInt(1, userId);
			rs = perp.executeQuery();
			while (rs.next()) {
				String folder = rs.getString(1);
				if (folder != null) {
					list.add(folder);
				}

			}
		} catch (SQLException e) {
			System.out.println("Error retrieving folder names.");
		}
		return list;

	}
	
}