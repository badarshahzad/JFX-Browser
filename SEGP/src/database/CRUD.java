package database;

import db.beans.Users;

public class CRUD {
	
	public static void main(String args[]){
		Users bean = SqliteConnection.getRow("rasheed@gmail.com");
		
		if(bean ==  null){
			System.err.println("No rows found");
		}else{
			System.out.println("Name: "+bean.getFirstName());
			System.out.println("Email: "+bean.getEmail());
			System.out.println("Password: "+bean.getPassword());
			System.out.println("Pin: "+bean.getPin());
		}
	}

}
