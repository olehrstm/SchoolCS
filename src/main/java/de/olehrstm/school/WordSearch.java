package de.olehrstm.school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Locale;

public class WordSearch extends JFrame {

    public WordSearch() {
        InterfaceFactory ui = new InterfaceFactory(this);
        ui.configureFrame("Zeichenkette suchen", 340, 320);

        ui.addLabel("Text", 10, 2, 300, 20);
        JTextArea inputTextArea = ui.addTextArea(10, 20, 300, 120);

        ui.addLabel("Suchzeichenkette", 10, 145, 300, 20);
        JTextField searchTextField = ui.addTextField(10, 163, 300, 20);
        searchTextField.setText("der");

        ui.addLabel("Ergebnis", 10, 220, 300, 20);
        JTextField resultTextField = ui.addOutputField(10, 238, 300, 20);
        resultTextField.setText("Noch nicht gesucht");

        JButton searchButton = ui.addButton("Suchen", 10, 190, 300, 20, _ -> processInput(inputTextArea, searchTextField, resultTextField));
        searchTextField.addActionListener(_ -> processInput(inputTextArea, searchTextField, resultTextField));
        getRootPane().setDefaultButton(searchButton);

        setVisible(true);
        inputTextArea.requestFocusInWindow();
    }

    private void processInput(JTextArea inputTextArea, JTextField searchTextField, JTextField resultTextField) {
        String text = inputTextArea.getText();
        String searchString = searchTextField.getText();

        if (searchString == null || searchString.isBlank()) {
            resultTextField.setText("Bitte Suchbegriff eingeben.");
            return;
        }

        int firstMatchIndex = findFirstMatchIndexIgnoreCase(text, searchString);
        resultTextField.setText(firstMatchIndex >= 0 ? "Gefunden an Position " + (firstMatchIndex + 1) : "Nicht gefunden");
    }

    private int findFirstMatchIndexIgnoreCase(String text, String searchString) {
        if (text == null || text.isBlank() || searchString == null || searchString.isBlank()) {
            return -1;
        }

        String lowerText = text.toLowerCase(Locale.ROOT);
        String lowerSearchString = searchString.toLowerCase(Locale.ROOT);
        return lowerText.indexOf(lowerSearchString);
    }

    static void main() {
        new WordSearch();
    }
}
