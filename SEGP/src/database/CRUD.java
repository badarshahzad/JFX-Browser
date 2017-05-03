package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.controlsfx.control.Notifications;

import javafx.util.Duration;

public class CRUD {

	/*public static void main(String args[]){

		//----------- Login database information ----------------
		Users bean = SqliteConnection.getLogin("naeemr2014@namal.edu.pk","123456");

		if(bean ==  null){
			System.err.println("No rows found");

		}else{
			System.out.println("User Id :"+ bean.getUser_id());
			System.out.println("Name: "+bean.getFirstName());
			System.out.println("Email: "+bean.getEmail());
			System.out.println("Password: "+bean.getPassword());
			System.out.println("Pin: "+bean.getPin());
		}

		//Downloads downBeans = SqliteConnection
	}
	 */
	
	private static String currentUserEmail = "Jfx Browser";  
	
	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}
	
	public static void setCurrentUserEmail(String currentUserEmail) {
		CRUD.currentUserEmail = currentUserEmail;
	}
	
	
	public static void createUserDataBase()
	{
		PreparedStatement perp = null;
		try {
			// Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:History.db");
			
			perp = SqliteConnection.Connector().prepareStatement(
					"CREATE TABLE if not exists users(name text ,email varchar(50) primary key,password varchar(30),"
							+ "pin integer);");
			perp.executeUpdate();
			System.out.println("user table created");
			perp.close();
			SqliteConnection.Connector().close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("issues");
		}
		
	}
	public static boolean isLogin(String email, String password) {
		PreparedStatement prepareStatment = null;
		ResultSet resultSet = null;
		String query = "Select * from users where email = ? and password = ?";
		try {
			prepareStatment = SqliteConnection.Connector().prepareStatement(query);
			prepareStatment.setString(1, email);
			prepareStatment.setString(2, password);

			resultSet = prepareStatment.executeQuery();
			if (resultSet.next()) {
				
				String email1 = resultSet.getString(2);
				String b = resultSet.getString(3);
				System.out.println("This is login values: "+email1 + "\n" + b);
				
				currentUserEmail = email1;
				
				System.out.println("Current User Email: "+currentUserEmail);
				
				return true;
				
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Exception in Login:" + e);
			return false;
			// TODO: handle exception
		} finally {
			try {
				prepareStatment.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * For the sake of test and devloping the pin will be String later according to team decesion this
	 * will be change
	 */

	public static boolean insertNewAccount(String firstName, String email, String password,String pin){
		PreparedStatement prepareStatment = null;
		String query = "INSERT INTO users(name,email,password,pin) VALUES (?,?,?,?)";
		try {
			prepareStatment = SqliteConnection.Connector().prepareStatement(query);
			prepareStatment.setString(1, firstName);
			prepareStatment.setString(2, email);
			prepareStatment.setString(3, password);
			prepareStatment.setString(4, pin);
			prepareStatment.executeUpdate();

			return true;
		}catch (Exception e) {
			

			Notifications.create()
			.title("Sign Up Failed")
			.text("Account already exists on this email.")
			.hideAfter(Duration.seconds(3))
			.showError();;
			
			//System.out.println("Exception in Login:" + e);
			// TODO: handle exception
		} finally {
			try {
				//connection.close();
				prepareStatment.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


}
