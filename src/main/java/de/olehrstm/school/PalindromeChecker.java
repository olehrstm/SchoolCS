package de.olehrstm.school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Locale;

public class PalindromeChecker extends JFrame {

    public PalindromeChecker() {
        InterfaceFactory ui = new InterfaceFactory(this);
        ui.configureFrame("Palindrom pruefen", 320, 220);

        ui.addLabel("Wort oder Text", 10, 10, 300, 20);
        JTextField inputTextField = ui.addTextField(10, 30, 300, 24);

        ui.addLabel("Ergebnis", 10, 100, 300, 20);
        JTextField resultTextField = ui.addOutputField(10, 120, 300, 24);
        resultTextField.setText("Noch nicht geprueft");

        JButton checkButton = ui.addButton("Palindrom pruefen", 10, 64, 300, 24, _ -> processInput(inputTextField, resultTextField));
        inputTextField.addActionListener(_ -> processInput(inputTextField, resultTextField));
        getRootPane().setDefaultButton(checkButton);

        setVisible(true);
        inputTextField.requestFocusInWindow();
    }

    private void processInput(JTextField inputTextField, JTextField resultTextField) {
        String rawInput = inputTextField.getText();
        if (rawInput == null || rawInput.isBlank()) {
            resultTextField.setText("Bitte ein Wort eingeben.");
            return;
        }

        String normalizedText = rawInput.trim().toLowerCase(Locale.ROOT);
        boolean isPalindrome = normalizedText.contentEquals(new StringBuilder(normalizedText).reverse());
        resultTextField.setText(isPalindrome ? "Ist ein Palindrom" : "Kein Palindrom");
    }

    static void main() {
        new PalindromeChecker();
    }
}
