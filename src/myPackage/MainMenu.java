package myPackage;

import javax.swing.JOptionPane;

public class MainMenu {

	public static void main(String[] args) {

		while (true) {
			String menu = """
					Bank Administration System

					1. Add Account
					2. view All Accounts
					3. Deposit Money
					4. Withdraw Money
					5. Check Balance
					6. Exit

					Enter your choice (1-6):
					""";

			String input = JOptionPane.showInputDialog(menu);

			if (input == null) {
				JOptionPane.showMessageDialog(null, "Thank You! 👋 , Visit Again");
				break;
			}

			int choice = Integer.parseInt(input);

			switch (choice) {
			case 1 -> AddAccount.main(null);
			case 2 -> ViewAccounts.main(null);
			case 3 -> DepositMoney.main(null);
			case 4 -> WithdrawMoney.main(null);
			case 5 -> CheckBalance.main(null);
			case 6 -> {
				JOptionPane.showMessageDialog(null, "Thank You! 👋 , Visit Again");
				return;
			}
			default -> JOptionPane.showMessageDialog(null, "Please Enter correct choice");
			}
		}
	}
}
