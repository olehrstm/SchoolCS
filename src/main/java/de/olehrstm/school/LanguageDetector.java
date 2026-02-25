package de.olehrstm.school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Locale;

public class LanguageDetector extends JFrame {

    private static final int ALPHABET_SIZE = 26;
    private static final int MINIMUM_LETTERS_FOR_DETECTION = 40;

    private static final double[] ENGLISH_FREQUENCIES = {
            8.04, 1.54, 3.06, 3.99, 12.51, 2.30, 1.96, 5.49, 7.26, 0.16, 0.67, 4.14, 2.53,
            7.09, 7.60, 2.00, 0.11, 6.12, 6.54, 9.25, 2.71, 0.99, 1.92, 0.19, 1.73, 0.09
    };

    private static final double[] GERMAN_FREQUENCIES = {
            6.47, 1.93, 2.68, 4.83, 17.48, 1.65, 3.06, 4.23, 7.73, 0.27, 1.46, 3.49, 2.58,
            9.84, 2.98, 0.96, 0.02, 7.54, 6.83, 6.13, 4.17, 0.94, 1.48, 0.04, 0.08, 1.14
    };

    public LanguageDetector() {
        InterfaceFactory ui = new InterfaceFactory(this);
        ui.configureFrame("Sprache erkennen", 320, 300);

        ui.addLabel("Text (mind. 40 Buchstaben)", 10, 2, 300, 20);
        JTextArea inputTextArea = ui.addTextArea(10, 20, 300, 130);

        ui.addLabel("Ergebnis", 10, 190, 300, 20);
        JTextField resultTextField = ui.addOutputField(10, 208, 300, 20);
        resultTextField.setText("Noch nicht berechnet");

        JButton processButton = ui.addButton("Sprache erkennen", 10, 155, 300, 20, _ -> processInput(inputTextArea, resultTextField));
        getRootPane().setDefaultButton(processButton);

        setVisible(true);
        inputTextArea.requestFocusInWindow();
    }

    private void processInput(JTextArea inputTextArea, JTextField resultTextField) {
        String text = inputTextArea.getText();
        resultTextField.setText(detectLanguage(text));
    }

    private String detectLanguage(String text) {
        int[] letterCounts = new int[ALPHABET_SIZE];
        int totalLetterCount = countAlphabetLetters(text, letterCounts);

        if (totalLetterCount == 0) {
            return "Bitte Text mit Buchstaben eingeben.";
        }
        if (totalLetterCount < MINIMUM_LETTERS_FOR_DETECTION) {
            return "Text zu kurz (mind. " + MINIMUM_LETTERS_FOR_DETECTION + " Buchstaben).";
        }

        int score = calculateLanguageScore(letterCounts, totalLetterCount);

        if (score > 0) {
            return "Englisch";
        }
        if (score < 0) {
            return "Deutsch";
        }
        return "Unklar";
    }

    private int countAlphabetLetters(String text, int[] letterCounts) {
        int totalLetterCount = 0;
        String lowerCaseText = text.toLowerCase(Locale.ROOT);

        for (char character : lowerCaseText.toCharArray()) {
            int index = toAlphabetIndex(character);
            if (index < 0) {
                continue;
            }

            letterCounts[index]++;
            totalLetterCount++;
        }

        return totalLetterCount;
    }

    private int calculateLanguageScore(int[] letterCounts, int totalLetterCount) {
        // positive = english
        // negative = german
        // 0 = unclear
        int score = 0;

        for (int i = 0; i < letterCounts.length; i++) {
            double observedFrequencyPercent = letterCounts[i] * 100.0 / totalLetterCount;
            double englishDifference = Math.abs(observedFrequencyPercent - ENGLISH_FREQUENCIES[i]);
            double germanDifference = Math.abs(observedFrequencyPercent - GERMAN_FREQUENCIES[i]);

            if (englishDifference < germanDifference) {
                score++;
            } else if (germanDifference < englishDifference) {
                score--;
            }
        }

        return score;
    }

    private int toAlphabetIndex(char character) {
        character = switch (character) {
            case 'ä' -> 'a';
            case 'ö' -> 'o';
            case 'ü' -> 'u';
            case 'ß' -> 's';
            default -> character;
        };

        if (character < 'a' || character > 'z') {
            return -1;
        }

        return character - 'a';
    }

    static void main() {
        new LanguageDetector();
    }
}
