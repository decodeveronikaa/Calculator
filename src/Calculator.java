import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(220, 320);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JTextField displayField = new JTextField();
        container.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        container.add(buttonPanel, BorderLayout.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonPanel.add(button);
        }

        ActionListener buttonListener = new ActionListener() {
            private String input = "";
            private String operator = "";
            private double storedValue = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String text = source.getText();

                if (Character.isDigit(text.charAt(0)) || text.equals(".")) {
                    input += text;
                    displayField.setText(input);
                } else if (text.equals("=")) {
                    if (!operator.isEmpty() && !input.isEmpty()) {
                        double currentValue = Double.parseDouble(input);
                        performOperation(currentValue);
                        displayField.setText(String.valueOf(storedValue));
                        input = "";
                        operator = "";
                    }
                } else {
                    if (!input.isEmpty()) {
                        double currentValue = Double.parseDouble(input);
                        if (operator.isEmpty()) {
                            storedValue = currentValue;
                        } else {
                            performOperation(currentValue);
                        }
                        input = "";
                        displayField.setText(String.valueOf(storedValue));
                    }
                    operator = text;
                }
            }

            private void performOperation(double currentValue) {
                switch (operator) {
                    case "+":
                        storedValue += currentValue;
                        break;
                    case "-":
                        storedValue -= currentValue;
                        break;
                    case "*":
                        storedValue *= currentValue;
                        break;
                    case "/":
                        storedValue /= currentValue;
                        break;
                }
            }
        };

        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).addActionListener(buttonListener);
            }
        }

        frame.setVisible(true);
    }
}
