package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
	public static Connection Connector(){
		
		try {
			Class.forName("org.sqlite.JDBC");
				Connection conn =  DriverManager.getConnection("jdbc:sqlite:History.db");
				return conn;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
