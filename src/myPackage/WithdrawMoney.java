package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.DBConnection;

public class WithdrawMoney {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String accNo = JOptionPane.showInputDialog("Enter Account Number");
		String amt = JOptionPane.showInputDialog("Enter Amount to Deposit");

		try {
			con = DBConnection.getConnection();

			Double wdAmt = Double.parseDouble(amt);

			String query = "Select balance from accounts where account_no =?";

			ps = con.prepareStatement(query);
			ps.setString(1, accNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				double currBal = rs.getDouble("balance");

				if (wdAmt > currBal) {
					JOptionPane.showMessageDialog(null, "Insufficient Balance! \n Current Balance: " + currBal);
				} else {
					double newBal = currBal - wdAmt;

					String updtQuery = "Update accounts set balance =? where account_no =?";
					
					PreparedStatement updtps = con.prepareStatement(updtQuery);
					updtps.setDouble(1, newBal);
					updtps.setString(2, accNo);

					int value = updtps.executeUpdate();

					if (value > 0) {
						JOptionPane.showMessageDialog(null, "Withdrawal successful ! \n Current Balance: " + newBal);
					} else {
						JOptionPane.showMessageDialog(null, "Withdrawal failed");
					}
					updtps.close();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Account doesn't exist");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please Enter a valid Number");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database errror: " + e.getMessage());
		} finally {
			DBConnection.cleanUp(con, ps, rs);
		}
	}
}
