package de.olehrstm.school.calendar;

import lombok.extern.slf4j.Slf4j;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

@Slf4j
public class GoToDateApplication extends JFrame {

    public GoToDateApplication() {
        // Frame init
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Kalender");
        setLayout(new BorderLayout());
        setResizable(false);

        // components
        Byte[] days = new Byte[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = (byte) i;
        }
        JComboBox<Byte> dayComboBox = new JComboBox<>(days);

        Byte[] months = new Byte[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = (byte) i;
        }
        JComboBox<Byte> monthComboBox = new JComboBox<>(months);

        int iteration = 0;
        Integer[] years = new Integer[201];
        for (int i = 1900; i <= 2100; i++) {
            years[iteration] = i;
            iteration++;
        }
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);
        yearComboBox.setSelectedIndex(125); // year 2025

        JButton goButton = new JButton("Go");
        goButton.addActionListener(e -> {

        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(dayComboBox, BorderLayout.WEST);
        inputPanel.add(monthComboBox, BorderLayout.CENTER);
        inputPanel.add(yearComboBox, BorderLayout.EAST);
        inputPanel.add(goButton, BorderLayout.NORTH);

        add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GoToDateApplication();
    }
}
