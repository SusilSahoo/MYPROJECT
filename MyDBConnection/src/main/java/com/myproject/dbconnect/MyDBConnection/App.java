package com.myproject.dbconnect.MyDBConnection;

import java.sql.Connection;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "DB Connection Initiated !" );
        
        String dbName = "empldir";
        
        Connection con = new DBConnect().DBConnection(dbName);
        
        if (con != null) {
			System.out.println("Database connection successfully");
		} else {
			System.out.println("Error in DB Connection");
		}
    }
}
