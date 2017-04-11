package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.beans.Users;

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

	public static Users getRow(String username) {
		String sql = "Select * from login where username = ?";
		ResultSet rs = null;

		try (
				//Connection conn = DriverManager.getConnection("jdbc:sqlite:History.db");
				PreparedStatement stmt = Connector().prepareStatement(sql);
				) {
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				Users userBean = new Users();
				userBean.setFirstName(rs.getString("name"));
				userBean.setEmail(username);
				userBean.setPassword(rs.getString("password"));
				userBean.setPin(rs.getString("pin"));
				return userBean;
			}else{
				System.out.println("No row finds");
				return null;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	

}
