package de.olehrstm.school.calendar.services;

import javax.swing.JTextField;
import java.util.Optional;

public class InputService {

    @SuppressWarnings("unchecked")
    public <T> Optional<T> input(JTextField textField, Class<T> type) {
        String resultString = textField.getText();

        if (resultString.isEmpty()) {
            return Optional.empty();
        }

        try {
            T result;

            if (type == String.class) {
                result = (T) resultString;
            } else if (type == Integer.class) {
                result = (T) Integer.valueOf(resultString);
            } else {
                throw new IllegalArgumentException(type + " is not a valid input type.");
            }

            return Optional.of(result);
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }
}
