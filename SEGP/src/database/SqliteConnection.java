package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.controlsfx.control.Notifications;

import db.beans.Users;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.util.Duration;

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
				//Connection conn = DriverManager.getConnection("jdbc:sqlite:History.db");
				PreparedStatement stmt = Connector().prepareStatement(sql);
				) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				Users userBean = new Users();
				userBean.setFirstName(rs.getString("name"));
				userBean.setEmail(username);
				userBean.setPassword(password);
				userBean.setPin(rs.getString("pin"));
				return userBean;
			}else{
				
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	

}
