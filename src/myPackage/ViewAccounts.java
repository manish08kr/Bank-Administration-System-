package myPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.DBConnection;

public class ViewAccounts {

	public static void main(String[] args) {

		try(Connection con = DBConnection.getConnection()){
			if(con != null) {
				
				String query = "Select * from accounts";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				StringBuilder sb = new StringBuilder();
				sb.append("ID\tName\tAccount No\tBalance\n");
				sb.append("------------------------------------------------------------------------");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					
				}
				
				
			}
		} catch() {
			
		}
		
	}

}
