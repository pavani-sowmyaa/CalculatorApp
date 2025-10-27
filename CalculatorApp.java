import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public ModernCalculator() {
        setTitle("Modern Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Segoe UI", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2, true));
            button.setBackground(new Color(255, 255, 255));
            button.setForeground(new Color(30, 30, 30));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Highlight operator buttons
            if (text.matches("[/*\\-+=]")) {
                button.setBackground(new Color(72, 133, 237));
                button.setForeground(Color.WHITE);
            } 
            else if (text.equals("C")) {
                button.setBackground(new Color(219, 68, 55));
                button.setForeground(Color.WHITE);
            }

            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        // Add some padding
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            display.setText(display.getText() + command);
        } 
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } 
        else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 != 0) result = num1 / num2;
                        else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } 
        else {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernCalculator());
    }
}
