import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame {

    // Fixed exchange rates (example rates)
    private final double USD_TO_PKR = 280.0;
    private final double EUR_TO_PKR = 300.0;
    private final double GBP_TO_PKR = 350.0;

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    public CurrencyConverter() {

        setTitle("💱 Currency Converter");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 60));
        setContentPane(panel);

        // Title
        JLabel title = new JLabel("Currency Converter");
        title.setBounds(120, 20, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Amount label
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50, 80, 150, 25);
        amountLabel.setForeground(Color.WHITE);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(200, 80, 200, 25);
        panel.add(amountField);

        // From currency
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(50, 130, 100, 25);
        fromLabel.setForeground(Color.WHITE);
        panel.add(fromLabel);

        String[] currencies = {"USD", "EUR", "GBP", "PKR"};
        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(200, 130, 200, 25);
        panel.add(fromCurrency);

        // To currency
        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(50, 180, 100, 25);
        toLabel.setForeground(Color.WHITE);
        panel.add(toLabel);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(200, 180, 200, 25);
        panel.add(toCurrency);

        // Convert button
        JButton convertBtn = new JButton("Convert 💰");
        convertBtn.setBounds(170, 230, 150, 35);
        convertBtn.setBackground(new Color(0, 200, 120));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFocusPainted(false);
        panel.add(convertBtn);

        // Result label
        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(50, 280, 400, 25);
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);

        // Button action
        convertBtn.addActionListener(e -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double inPKR = toPKR(amount, from);
            double result = fromPKR(inPKR, to);

            resultLabel.setText("Result: " + amount + " " + from + " = " + result + " " + to);

        } catch (Exception e) {
            resultLabel.setText("❌ Please enter a valid number!");
        }
    }

    private double toPKR(double amount, String currency) {
        switch (currency) {
            case "USD": return amount * USD_TO_PKR;
            case "EUR": return amount * EUR_TO_PKR;
            case "GBP": return amount * GBP_TO_PKR;
            default: return amount;
        }
    }

    private double fromPKR(double amount, String currency) {
        switch (currency) {
            case "USD": return amount / USD_TO_PKR;
            case "EUR": return amount / EUR_TO_PKR;
            case "GBP": return amount / GBP_TO_PKR;
            default: return amount;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}