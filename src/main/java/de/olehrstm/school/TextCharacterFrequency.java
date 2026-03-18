package de.olehrstm.school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextCharacterFrequency extends JFrame {

    public TextCharacterFrequency() {
        InterfaceFactory ui = new InterfaceFactory(this);
        ui.configureFrame("Text", 320, 500);

        ui.addLabel("Text", 10, 2, 300, 20);
        JTextArea inputTextArea = ui.addTextArea(10, 20, 300, 130);

        ui.addLabel("Ergebnis", 10, 190, 300, 20);
        JTextArea resultTextArea = ui.addOutputArea(10, 208, 300, 250);
        resultTextArea.setText("Noch nicht berechnet");

        JButton processButton = ui.addButton("Verarbeiten", 10, 155, 300, 20, _ -> processInput(inputTextArea, resultTextArea));
        getRootPane().setDefaultButton(processButton);

        setVisible(true);
        inputTextArea.requestFocusInWindow();
    }

    private void processInput(JTextArea inputTextArea, JTextArea resultTextArea) {
        String text = inputTextArea.getText();
        int[] letterCounts = countCharacters(text);

        StringBuilder result = formatFrequencies(letterCounts);
        appendMostUsedLetter(result, letterCounts);

        resultTextArea.setText(result.toString());
    }

    private int[] countCharacters(String text) {
        int[] letterCounts = new int[26];
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            int index = toAlphabetIndex(character);
            if (index < 0) {
                continue;
            }

            letterCounts[index]++;
        }
        return letterCounts;
    }

    private StringBuilder formatFrequencies(int[] letterCounts) {
        StringBuilder result = new StringBuilder();

        int[] counts = letterCounts.clone();
        for (int i = 0; i < 26; i++) {
            int maxIdx = findMaxIndex(counts);

            if (maxIdx != -1 && counts[maxIdx] > 0) {
                result.append((char) ('a' + maxIdx)).append(": ").append(counts[maxIdx]).append("\n");
                counts[maxIdx] = 0;
            }
        }
        return result;
    }

    private int findMaxIndex(int[] counts) {
        int maxIdx = -1;
        int maxC = 0;
        for (int j = 0; j < 26; j++) {
            if (counts[j] > maxC) {
                maxC = counts[j];
                maxIdx = j;
            }
        }
        return maxIdx;
    }

    private void appendMostUsedLetter(StringBuilder result, int[] letterCounts) {
        int maxIndex = -1;
        int maxCount = 0;
        for (int i = 0; i < letterCounts.length; i++) {
            if (letterCounts[i] > maxCount) {
                maxCount = letterCounts[i];
                maxIndex = i;
            }
        }

        if (maxIndex != -1) {
            result.append("\nMeistgenutzter Buchstabe: ").append((char) ('a' + maxIndex)).append(" (").append(maxCount).append(")");
        }
    }

    private int toAlphabetIndex(char character) {
        character = switch (character) {
            case 'ä', 'Ä' -> 'a';
            case 'ö', 'Ö' -> 'o';
            case 'ü', 'Ü' -> 'u';
            case 'ß' -> 's';
            default -> character;
        };

        if (character >= 'A' && character <= 'Z') {
            return character - 'A';
        }

        if (character < 'a' || character > 'z') {
            return -1;
        }

        return character - 'a';
    }

    static void main() {
        new TextCharacterFrequency();
    }
}
