package de.olehrstm.school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

final class InterfaceFactory {

    private final JFrame targetFrame;

    InterfaceFactory(JFrame targetFrame) {
        this.targetFrame = targetFrame;
    }

    void configureFrame(String title, int width, int height) {
        this.targetFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.targetFrame.setSize(width, height);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = (screen.width - this.targetFrame.getSize().width) / 2;
        int startY = (screen.height - this.targetFrame.getSize().height) / 2;

        this.targetFrame.setLocation(startX, startY);
        this.targetFrame.setTitle(title);
        this.targetFrame.setLayout(null);
        this.targetFrame.setResizable(false);
    }

    JLabel addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        this.targetFrame.add(label);
        return label;
    }

    JTextArea addTextArea(int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, width, height);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        this.targetFrame.add(textArea);
        return textArea;
    }

    JTextField addTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        this.targetFrame.add(textField);
        return textField;
    }

    JTextField addOutputField(int x, int y, int width, int height) {
        JTextField textField = addTextField(x, y, width, height);
        textField.setEditable(false);
        textField.setFocusable(false);
        return textField;
    }

    JButton addButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        this.targetFrame.add(button);
        return button;
    }
}
