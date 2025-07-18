package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Util.DBConnection;

public class CheckBalance {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String accNo = JOptionPane.showInputDialog("Enter Account no");

		try {

			con = DBConnection.getConnection();

			String Query = "Select balance from accounts where account_no = ?";
			ps = con.prepareStatement(Query);
			ps.setString(1, accNo);

			rs = ps.executeQuery();

			if (rs.next()) {

				String name = rs.getString("name");
				Double balance = rs.getDouble("balance");

				String msg = "Account No : " + accNo + "\n Name : " + name + "\n Cuurent Balance : " + balance;
				JOptionPane.showMessageDialog(null, msg, "Account Balance", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Account not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
		} finally {
			DBConnection.cleanUp(con, ps, rs);
		}
	}
}
