package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.controlsfx.control.Notifications;

import db.beans.Users;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
	public static boolean isLogin(String username, String password) {
	PreparedStatement prepareStatment = null;
	ResultSet resultSet = null;
	String query = "Select * from users where username = ? and password = ?";
	try {
		prepareStatment = SqliteConnection.Connector().prepareStatement(query);
		prepareStatment.setString(1, username);
		prepareStatment.setString(2, password);
		
		resultSet = prepareStatment.executeQuery();
		if (resultSet.next()) {
			String a = resultSet.getString(1);
			String b = resultSet.getString(2);
			System.out.println(a + "\n" + b);

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
    
	public static boolean insertNewAccount(String firstName, String username, String password,String pin){
		PreparedStatement prepareStatment = null;
		String query = "INSERT INTO users(username,password,name,pin) VALUES (?,?,?,?)";
		try {
			prepareStatment = SqliteConnection.Connector().prepareStatement(query);
			prepareStatment.setString(1, username);
			prepareStatment.setString(2, password);
			prepareStatment.setString(3, firstName);
			prepareStatment.setString(4, pin);
			prepareStatment.executeUpdate();

			return true;
		}catch (Exception e) {
			System.out.println("Exception in Login:" + e);
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
