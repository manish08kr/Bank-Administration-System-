package myPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Util.DBConnection;

public class ViewAccounts {

	public static void main(String[] args) {

		Connection con = null;

		try {
			con = DBConnection.getConnection();
			if (con != null) {

				String query = "Select * from accounts";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);

				StringBuilder sb = new StringBuilder();
				sb.append("All Accounts Records : \n");

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String accNo = rs.getString("account_no");
					String bal = rs.getString("balance");

					sb.append("ID: ").append(id).append("\n").append("Name: ").append(name).append("\n")
							.append("Account No: ").append(accNo).append("\n").append("Balance: Rs ").append(bal)
							.append("\n").append("------------------------------------------\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "All Accounts", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "DB Connection failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		} finally {
			DBConnection.cleanUp(con, null, null);
		}

	}

}
