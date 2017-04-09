package com.myproject.dbconnect.MyDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
	
	public Connection DBConnection(String dbName) {
		
		Connection con = null;
		String url = DBConnectionDetails.CONNECTION_URL + dbName;
		
		try {
			Class.forName(DBConnectionDetails.DRIVER_CLASS).newInstance();
			con = DriverManager.getConnection(url, DBConnectionDetails.user, DBConnectionDetails.pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return con;
	}
}
