package com.myproject.dataTransfer.DataTransfer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.myproject.dbconnect.MyDBConnection.DBConnect;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

public class App 
{
    public static void main( String[] args )
    {
    	App app = new App();
    	
        System.out.println( "DB Connection initiated ! ");
        
        String dbName = "empldir";
        
        Connection con = (Connection) new DBConnect().DBConnection(dbName);
        
        Collection<String> result = new ArrayList<String>();
        Collection<String> columns = new ArrayList<String>();
        
        result = app.accessTableFromDB(con, result);        
        System.out.println("Table details from '" + dbName + "' DataBase : " + result);        
        columns = app.generateCreateTable(con, columns);        
        System.out.println("Column details from table 'emps'"  + columns);        
        System.out.println("Colulmn count -" + columns.size());        
        app.copyData(con , columns);
        
    }

	
	private void copyData(Connection con, Collection<String> columns) {
		
		StringBuffer selectSQL = new StringBuffer();
		StringBuffer insertSQL = new StringBuffer();
		StringBuffer values = new StringBuffer();
		
		selectSQL.append(" SELECT ");
		insertSQL.append(" INSERT INTO ");
		insertSQL.append(" empsd ");
		insertSQL.append(" ( ");
		
		boolean last = false; 
		int col = 0;
		for (String column : columns) {
			col = col + 1;
			if (col == columns.size()) {
				last = true;
			}
			
			if (!last) {
				selectSQL.append(column + " , ");
				insertSQL.append(column + " , ");
				values.append(" ? " + " , ");
			} else {
				selectSQL.append(column);
				insertSQL.append(column);
				values.append(" ? ");
			}
			
		}
		
		
		selectSQL.append(" FROM ");
		selectSQL.append(" emps ");
		
		insertSQL.append(") VALUES (");
		insertSQL.append(values);
		insertSQL.append(")");
		
		System.out.println("SELECT SQL: " + selectSQL);
		System.out.println("INSERT SQL: " + insertSQL);
		
		try {
			Statement selectQuery = con.createStatement();
			PreparedStatement insertQuery = (PreparedStatement) con.prepareStatement(insertSQL.toString());
			
			ResultSet rs = selectQuery.executeQuery(selectSQL.toString());
			
			while (rs.next()) {
				for (int i = 1; i <= columns.size(); i++) {
					System.out.println("Column Name : " + columns.toArray()[i-1] + " value :" + rs.getString(i));
					insertQuery.setString(i, rs.getString(i));
				}
				insertQuery.execute();
				System.out.println("\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}


	private Collection<String> generateCreateTable(Connection con, Collection<String> columns) {
		
		StringBuffer result = new StringBuffer();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM ");
			sql.append("emps");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			ResultSetMetaData rsmtb = (ResultSetMetaData) rs.getMetaData();
			
			System.out.println("ResultSetMetaData column count: " + rsmtb.getColumnCount());
			
			result.append("CREATE TABLE");
			result.append(" emps "); result.append(" ( ");
			
			for (int i = 1; i <= rsmtb.getColumnCount(); i++) {

				columns.add(rsmtb.getColumnName(i));
				result.append(rsmtb.getColumnName(i));
				result.append(',');
			}
			
			result.append(')');
			
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return columns;
	}

	private Collection<String> accessTableFromDB(Connection con, Collection<String> result) {
		
		ResultSet rs = null;
		
		try{
			DatabaseMetaData dbm = (DatabaseMetaData) con.getMetaData();
			String types[] = { "TABLE" };
			rs = dbm.getTables(null, null, "", types);
			
			while (rs.next()) {
				String str = rs.getString("TABLE_NAME");
				result.add(str);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}
		
		return result;
	}
}
