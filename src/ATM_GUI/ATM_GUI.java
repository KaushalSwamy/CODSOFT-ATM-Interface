package ATM_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_GUI extends JFrame implements ActionListener {
    private final JButton WithdrawButton;
    private final JButton DepositButton;
    private final JButton balanceButton;
    private final JButton ExitButton;
    private double accountBalance = 2000.0; // Initial-balance

    public ATM_GUI() {
        setTitle("ATM");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        WithdrawButton = new JButton("Withdraw");
        DepositButton = new JButton("Deposit");
        balanceButton = new JButton("Check Balance");
        ExitButton = new JButton("Exit");

        WithdrawButton.addActionListener(this);
        DepositButton.addActionListener(this);
        balanceButton.addActionListener(this);
        ExitButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(WithdrawButton);
        panel.add(DepositButton);
        panel.add(balanceButton);
        panel.add(ExitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATM_GUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == WithdrawButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                withdraw(amount);
            }
        } else if (e.getSource() == DepositButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                deposit(amount);
            }
        } else if (e.getSource() == balanceButton) {
            checkBalance();
        } else if (e.getSource() == ExitButton) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }



    private void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance: $" + accountBalance);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid withdrawal amount or insufficient funds.");
        }
    }

    private void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            JOptionPane.showMessageDialog(this, "Deposit successful. New balance: $" + accountBalance);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid deposit amount.");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Current balance: $" + accountBalance);
    }
}
