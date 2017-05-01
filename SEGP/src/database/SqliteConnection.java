package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.HistoryController;
import db.beans.Users;
import javafx.collections.ObservableList;

public class SqliteConnection {
	public static Connection Connector() {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:History.db");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Users getLogin(String username, String password) {
		String sql = "Select * from users where username = ? and password = ?";
		ResultSet rs = null;
		try (
				// Connection conn =
				// DriverManager.getConnection("jdbc:sqlite:History.db");
				PreparedStatement stmt = Connector().prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Users userBean = new Users();
				userBean.setFirstName(rs.getString("name"));
				userBean.setEmail(username);
				userBean.setPassword(password);
				userBean.setPin(rs.getString("pin"));
				return userBean;
			} else {

				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void excuteQuery(String deleteItem) {
		String sql = "delete from history where url = ?;";

		// Connection conn =
		// DriverManager.getConnection("jdbc:sqlite:History.db");
		PreparedStatement stmt;
		try {
			stmt = Connector().prepareStatement(sql);
			stmt.setString(1, deleteItem);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ObservableList getFullHistory(ObservableList fullHistory) {

		ResultSet rs = null;
		PreparedStatement perp;
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			String str = "select * from history";
			perp = Connector().prepareStatement(str);
			rs = perp.executeQuery();

			while (rs.next()) // loop for data fetching and pass it to GUI table
				// view
			{
				String link1 = rs.getString(1);
				String time1 = rs.getString(2);
				String date1 = rs.getString(3);
				String domain1 = rs.getString(4);
				String title1 = rs.getString(5);

				fullHistory = HistoryController.addDataInList(link1, time1, date1, domain1, title1, fullHistory);
			}
			rs.close();
			perp.close();
			SqliteConnection.Connector().close();
		} catch (Exception e) {
			System.out.println("Issues in fullHistoryShow method");
		}
		return fullHistory;
	}

}
