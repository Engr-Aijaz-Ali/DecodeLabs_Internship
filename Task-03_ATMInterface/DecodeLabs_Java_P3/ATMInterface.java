import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMInterface extends JFrame implements ActionListener {

    // Account Details
    private String name = "Aijaz Ali";
    private int accountNumber = 1001;
    private int pin = 1234;
    private double balance = 10000;

    // Components
    private JTextField nameField, accountField;
    private JPasswordField pinField;

    private JButton loginBtn, depositBtn, withdrawBtn,
            balanceBtn, statementBtn, exitBtn;

    private JPanel loginPanel, menuPanel;

    public ATMInterface() {

        setTitle("Digital ATM System");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLoginScreen();

        setVisible(true);
    }

    // LOGIN SCREEN
    private void showLoginScreen() {

        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(25, 42, 86));
        loginPanel.setLayout(null);

        JLabel title = new JLabel("DIGITAL ATM SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 40, 350, 40);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(140, 120, 100, 25);

        nameField = new JTextField();
        nameField.setBounds(250, 120, 180, 25);

        JLabel accLabel = new JLabel("Account No:");
        accLabel.setForeground(Color.WHITE);
        accLabel.setBounds(140, 170, 100, 25);

        accountField = new JTextField();
        accountField.setBounds(250, 170, 180, 25);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setBounds(140, 220, 100, 25);

        pinField = new JPasswordField();
        pinField.setBounds(250, 220, 180, 25);

        loginBtn = new JButton("LOGIN");
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setBounds(240, 290, 120, 40);
        loginBtn.addActionListener(this);

        loginPanel.add(title);
        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(accLabel);
        loginPanel.add(accountField);
        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(loginBtn);

        add(loginPanel);
    }

    // MENU SCREEN
    private void showMenu() {

        getContentPane().removeAll();

        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(44, 62, 80));
        menuPanel.setLayout(new BorderLayout());

        JLabel heading = new JLabel("WELCOME " + name, SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Center panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(44, 62, 80));
        buttonPanel.setLayout(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                20,   // top
                140,  // left margin
                20,   // bottom
                140   // right margin
        ));

        depositBtn = createRoundedButton("Deposit", new Color(46, 204, 113));
        withdrawBtn = createRoundedButton("Withdraw", new Color(243, 156, 18));
        balanceBtn = createRoundedButton("Check Balance", new Color(52, 152, 219));
        statementBtn = createRoundedButton("Mini Statement", new Color(155, 89, 182));
        exitBtn = createRoundedButton("Exit", new Color(231, 76, 60));

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        statementBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(statementBtn);
        buttonPanel.add(exitBtn);

        menuPanel.add(heading, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        add(menuPanel);

        revalidate();
        repaint();
    }

    private JButton createRoundedButton(String text, Color color) {

        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    // RECEIPT
    private void showReceipt(String type, double amount, double previousBalance) {

        DateTimeFormatter dtf =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        String receipt =
                "========== TRANSACTION RECEIPT ==========\n\n" +
                        "Customer Name : " + name + "\n" +
                        "Account Number: " + accountNumber + "\n" +
                        "Transaction   : " + type + "\n" +
                        "Amount        : Rs. " + amount + "\n" +
                        "Previous Bal. : Rs. " + previousBalance + "\n" +
                        "Current Bal.  : Rs. " + balance + "\n" +
                        "Date & Time   : " + dtf.format(LocalDateTime.now()) + "\n\n" +
                        "Status        : Successful\n\n" +
                        "========================================";

        JOptionPane.showMessageDialog(this, receipt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String enteredName = nameField.getText();
            int enteredAcc = Integer.parseInt(accountField.getText());
            int enteredPin = Integer.parseInt(pinField.getText());

            if (enteredName.equalsIgnoreCase(name)
                    && enteredAcc == accountNumber
                    && enteredPin == pin) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful!");

                showMenu();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Credentials!");
            }
        }

        else if (e.getSource() == depositBtn) {

            double previousBalance = balance;

            String amountStr =
                    JOptionPane.showInputDialog("Enter Deposit Amount:");

            double amount = Double.parseDouble(amountStr);

            balance += amount;

            showReceipt("Deposit", amount, previousBalance);
        }

        else if (e.getSource() == withdrawBtn) {

            double previousBalance = balance;

            String amountStr =
                    JOptionPane.showInputDialog("Enter Withdraw Amount:");

            double amount = Double.parseDouble(amountStr);

            if (amount > balance) {

                JOptionPane.showMessageDialog(this,
                        "Insufficient Balance!");

            } else {

                balance -= amount;

                showReceipt("Withdrawal", amount, previousBalance);
            }
        }

        else if (e.getSource() == balanceBtn) {

            JOptionPane.showMessageDialog(this,
                    "Current Balance = Rs. " + balance);
        }

        else if (e.getSource() == statementBtn) {

            JOptionPane.showMessageDialog(this,
                    "Customer Name : " + name +
                            "\nAccount Number : " + accountNumber +
                            "\nAvailable Balance : Rs. " + balance);
        }

        else if (e.getSource() == exitBtn) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new ATMInterface();
    }
}