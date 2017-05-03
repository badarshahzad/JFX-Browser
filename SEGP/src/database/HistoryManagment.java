package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import controllers.HistoryController;
import javafx.collections.ObservableList;

public class HistoryManagment {
	
	// private static Connection c = SqliteConnection.Connector();
	
	private static PreparedStatement perp = null;
	private static java.util.Date dateTime;
	static SimpleDateFormat formateTime;
	private static SimpleDateFormat dateFormate;

	// ------------------------------------------------------Creating SQLITE
	// DATABASE and TABLE--------------------------------------//
	
	public static void CreateDataBase() {
		try {

			perp = SqliteConnection.Connector().prepareStatement(
					"CREATE TABLE if not exists history(email text primary key ,url text ,Time varchar(30),Date varchar(30),"
							+ "domain varchar(40),title varchar(50), user_id integer );");
			perp.executeUpdate();
			System.out.println("table created");
			perp.close();
			SqliteConnection.Connector().close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("issues");
			
		}
	}
	// ------------------------------------ULR INSERTION IN
	// DATABASE----------------------------------------------------------------//

	public static void insertUrl(String email,String url, String domain, String title) {
		dateTime = new java.util.Date();
		formateTime = new SimpleDateFormat("HH:mm:ss");
		dateFormate = new SimpleDateFormat("dd-MM-yyyy");

		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String insert = "insert or replace into history(email,url,Time,Date,domain,title,user_id)"
					+ "values(?,?,?,?,?,?,?)";
			String time = formateTime.format(dateTime);
			String date = dateFormate.format(dateTime);
			int user = 1;
			perp = SqliteConnection.Connector().prepareStatement(insert);
			perp.setString(1, email);
			perp.setString(2, url);
			perp.setString(3, time);
			perp.setString(4, date);
			perp.setString(5, domain);
			perp.setString(6, title);
			perp.setInt(7, user);
			perp.executeUpdate();
			
			System.out.println("data has been inserted");
			perp.close();
			SqliteConnection.Connector().close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	// --------------------------------- Delete
	// History------------------------------------------------------------------//
	public static void deleteFromDatabase() {
		ResultSet rs = null;
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String qeury = "delete  from history;";
			perp = SqliteConnection.Connector().prepareStatement(qeury);
			perp.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Issues in delete method");
		}

	}
	// ---------------------------------Full HISTORY
	// Show--------------------------------------------------------------------------//

	public static ObservableList fullHistoryShow(ObservableList fullHistory) {

		ResultSet rs = null;
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String str = "select * from(select * from history order by Time DESC) history order by Date DESC";
			perp = SqliteConnection.Connector().prepareStatement(str);
			rs = perp.executeQuery();

			while (rs.next()) // loop for data fetching and pass it to GUI table
				// view
			{
				String email1 = rs.getString(1);
				String link1 = rs.getString(2);
				String time1 = rs.getString(3);
				String date1 = rs.getString(4);
				String domain1 = rs.getString(5);
				String title1 = rs.getString(6);

				// System.out.println(link1, time1,date1,domain1,title1);
				fullHistory = HistoryController.addDataInList(email1,link1, time1, date1, domain1, title1, fullHistory);
			}
			rs.close();
			perp.close();
			SqliteConnection.Connector().close();
		} catch (Exception e) {
			System.out.println("Issues in fullHistoryShow method");
		}
		return fullHistory;
	}

	public static ObservableList getDomainNames(ObservableList domainNames) {

		ResultSet rs = null;
		try {
			String str = "select url from history";
			perp = SqliteConnection.Connector().prepareStatement(str);
			rs = perp.executeQuery();

			while (rs.next()) // loop for data fetching and pass it to GUI table
				// view
			{
				String link1 = rs.getString(1);

				// ObservableList<String> domainNamesList =
				// FXCollections.observableArrayList();
				domainNames.add(link1);

			}
			rs.close();
			perp.close();
			SqliteConnection.Connector().close();
		} catch (Exception e) {
			System.out.println("Issues in fullHistoryShow method");
		}
		return domainNames;
	}

	// ------------------------------------this method return user specified
	// histories--------------------------------------------------------------------//1
	public static ObservableList getHistory(ObservableList list, int dateRange) {
		ResultSet rs = null;
		dateTime = new java.util.Date();

		// past dates denpending upon the function parameter 'dateRange'
		dateFormate = new SimpleDateFormat("yy-MM-dd");
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dateRange);
		String pastDate = dateFormate.format(cal.getTime());
		pastDate = "'" + pastDate + "'";

		String qeury;
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			// user aske for today or yesterday history
			if (dateRange == 0 || dateRange == -1) {
				qeury = "select * from (select * from history order by Time DESC) history where Date like" + pastDate
						+ ";";
				perp = SqliteConnection.Connector().prepareStatement(qeury);
				rs = perp.executeQuery();
			}
			// if user asks for more two day history
			else {
				qeury = "select * from (select * from history order by Time DESC) history where Date>=" + pastDate
						+ " Order BY Date DESC;";
				perp = SqliteConnection.Connector().prepareStatement(qeury);
				rs = perp.executeQuery();
			}

			while (rs.next())// loop for data fetching and pass it to GUI table
				// view
			{
				String email1 = rs.getString(1);
				String link1 = rs.getString(2);
				String time1 = rs.getString(3);
				String date1 = rs.getString(4);
				String domain1 = rs.getString(5);
				String title1 = rs.getString(6);
				
				list = HistoryController.addDataInList(email1,link1, time1, date1, domain1, title1, list);
			}

			rs.close();
			perp.close();
			SqliteConnection.Connector().close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isseus in getHistory method ");
		}
		return list;
	}
	// end method

	// -------------------------------------Past Hour Histroy
	// Show-------------------------------------------------------------//
	public static ObservableList pastHoursHistory(ObservableList pastHour, int time) {
		ResultSet rs = null;
		dateTime = new java.util.Date();
		formateTime = new SimpleDateFormat("HH:mm:ss");
		dateFormate = new SimpleDateFormat("yy-MM-dd");

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, time);
		Date date = cal.getTime();

		// current and past time
		String pastHourTime = formateTime.format(date);
		pastHourTime = "'" + pastHourTime + "'";

		// current date
		String currentDate = dateFormate.format(dateTime);
		currentDate = "'" + currentDate + "'";
		System.out.println(currentDate);
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String qeury = "select * from history where Time>" + pastHourTime + "AND Date LIKE " + currentDate
					+ "order by Time DESC;";
			perp = SqliteConnection.Connector().prepareStatement(qeury);
			rs = perp.executeQuery();
			System.out.println(rs.next());
			while (rs.next()) // loop for data fetching and pass it to GUI table
				// view
			{
				String email1 = rs.getString(1);
				String link1 = rs.getString(2);
				String time1 = rs.getString(3);
				String date1 = rs.getString(4);
				String domain1 = rs.getString(5);
				String title1 = rs.getString(6);
				
				pastHour = HistoryController.addDataInList(email1,link1, time1, date1, domain1, title1, pastHour);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isseus in pastHours mehtod");
		}

		return pastHour;
	}
	// end method
}
// end Class