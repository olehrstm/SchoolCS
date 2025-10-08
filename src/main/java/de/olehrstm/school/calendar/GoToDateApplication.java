package de.olehrstm.school.calendar;

import de.olehrstm.school.calendar.services.DateService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

@Slf4j
public class GoToDateApplication extends JFrame {

    private final DateService dateService;

    public GoToDateApplication(DateService dateService) {
        this.dateService = dateService;

        // Frame init
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = (d.width - getSize().width) / 2;
        int startY = (d.height - getSize().height) / 2;
        setLocation(startX, startY);
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

        int startYear = 2000;
        int endYear = 2100;
        Integer[] years = new Integer[endYear - startYear + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = startYear + i;
        }
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);
        yearComboBox.setSelectedIndex(25); // year 2025

        JLabel resultLabel = new JLabel("Bitte wähle ein Datum aus.", SwingConstants.CENTER);

        JButton goButton = new JButton("Los");
        goButton.addActionListener(e -> {
            byte day = (byte) dayComboBox.getSelectedItem();
            byte month = (byte) monthComboBox.getSelectedItem();
            int year = (int) yearComboBox.getSelectedItem();

            if (!this.dateService.isValidDate(day, month, year)) {
                resultLabel.setText("Ungültiges Datum!");
                return;
            }

            String weekDay = this.dateService.getWeekDay(day, month, year);
            resultLabel.setText(String.format("%d.%d.%d is a %s", day, month, year, weekDay));
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(dayComboBox);
        inputPanel.add(monthComboBox);
        inputPanel.add(yearComboBox);

        add(inputPanel, BorderLayout.NORTH);
        add(goButton, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        setVisible(true);
    }


    public static void main(String[] args) {
        new GoToDateApplication(new DateService());
    }
}
