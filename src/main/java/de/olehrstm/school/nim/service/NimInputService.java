package de.olehrstm.school.nim.service;

import javax.swing.JOptionPane;
import java.util.Optional;
import java.util.function.Predicate;

public class NimInputService {

    @SuppressWarnings("unchecked")
    public <T> Optional<T> input(String message, Class<T> type, Predicate<T> predicate) {
        String resultString = JOptionPane.showInputDialog(null, message);

        if (resultString.isEmpty()) {
            return Optional.empty();
        }

        T result;
        if (type == String.class) {
            result = (T) resultString;
        } else if (type == Integer.class) {
            result = (T) Integer.valueOf(resultString);
        } else {
            throw new IllegalArgumentException(type + " is not a valid input type.");
        }

        if (!predicate.test(result)) {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
