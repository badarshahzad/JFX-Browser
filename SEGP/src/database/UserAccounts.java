package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserAccounts {
	
	private static Connection c = SqliteConnection.Connector();
	private static PreparedStatement perp=null;
	public static void createUserAccountsDataBase(){
		  try {
			c = SqliteConnection.Connector();
	      perp=c.prepareStatement("CREATE TABLE if not exists accounts(domain text primary key,username varchar(20),"
	      		+ "password varchar (30), user_id integer );");
		      perp.executeUpdate();
		      System.out.println("table created");
		} catch (SQLException e) {
		}
	      
	}
	public static void insertAccount(String domain, String userName,String password ,int userId){
		c= SqliteConnection.Connector();
		try
		{
			
			String insert="insert or replace into accounts(domain,username,password,user_id)"+"values(?,?,?,?)";
			perp=c.prepareStatement(insert);
			perp.setString(1, domain);
			perp.setString(2,userName);
			perp.setString(3, password);
			perp.setInt(4,userId);
			perp.executeUpdate();
			System.out.println("Accounts Entry added.");
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	public static ResultSet getAccounts(int userId,String domian){
		
		ResultSet result = null;
		c= SqliteConnection.Connector();
		try
		{
			String query="select username,password "
					+ "from accounts where user_id = ? and domain = ?;";
			perp=c.prepareStatement(query);
			perp.setInt(1, userId);
			perp.setString(2,domian);
			result=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.err.println("Exception showing Accounts DataBase:");
			e.printStackTrace();
		}
		return result;
	}
	

}
