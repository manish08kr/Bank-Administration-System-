package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnection {

	public static Connection getConnection() {

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "2486");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void cleanUp(Connection cn, Statement st, ResultSet rs) {
		try {
			if(cn != null) {
				cn.close();
			}
			if(st != null) {
				((Connection) st).close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

}
