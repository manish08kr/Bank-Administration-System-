package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Util.DBConnection;

public class DepositMoney {

	public static void main(String[] args) {

		try(Connection con = DBConnection.getConnection()){
			if(con != null) {
				String AccNo = JOptionPane.showInputDialog("Enter your Account No : ");
				String AmtStr = JOptionPane.showInputDialog("Enter Amount to Deposit");
				Double Amt = Double.parseDouble(AmtStr);
				
				String query = "Select balance from accounts where account_no =?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, AccNo);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					double currBal = rs.getDouble("balance");
					double newBal = currBal + Amt;
					
					String UpdateQuery = "Update accounts set balance =? where account_no =?";
					PreparedStatement Uptps = con.prepareStatement(UpdateQuery);
					
					Uptps.setDouble(1, newBal);
					Uptps.setString(2, AccNo);
					
					int rows = Uptps.executeUpdate();
					
					if(rows > 0) {
						JOptionPane.showMessageDialog(null, "Deposit successfully! \n new Balance: "+ newBal);
					} else {
						JOptionPane.showMessageDialog(null, "Deposit failed!");
					}
				} else {
                    JOptionPane.showMessageDialog(null, "❌ Account not found.");
				}
			} else {
                JOptionPane.showMessageDialog(null, "❌ DB Connection Failed");
			}
		} catch(Exception e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			
		}
		
	}

}
