package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import Util.DBConnection;

public class AddAccount {

	public static void main(String[] args) {

		String name = JOptionPane.showInputDialog("Enter account holder Name : ");
		String accNo = JOptionPane.showInputDialog("Enter Account Number");
		String balInput = JOptionPane.showInputDialog("Enter Inital balance");
		
		double bal = 0.0;
		try {
			bal = Double.parseDouble(balInput);
		} catch(NumberFormatException e) {
			e.printStackTrace();			
			JOptionPane.showMessageDialog(null, "Invalid balance Entered");
			return;
		}
		
		try (Connection con = DBConnection.getConnection()){
			
			if(con != null) {
				String sql = "Insert into accounts (name, account_no, balance) values(?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, accNo);
				ps.setDouble(3, bal);
				
				int rows = ps.executeUpdate();
				
				if(rows > 0){
					JOptionPane.showMessageDialog(null, "Account created Successfully");
				} else {
					JOptionPane.showMessageDialog(null, "Account created failed");
				}
			} else {
				JOptionPane.showMessageDialog(null, "DB connection failed");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		
		
	}

}
