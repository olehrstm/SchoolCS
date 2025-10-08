package de.olehrstm.school.calendar;

import de.olehrstm.school.calendar.services.DateService;
import de.olehrstm.school.calendar.services.InputService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

@Slf4j
public class GoToDateApplication extends JFrame {

    private final DateService dateService;
    private final InputService inputService;

    public GoToDateApplication(DateService dateService, InputService inputService) {
        this.dateService = dateService;
        this.inputService = inputService;

        // Frame init
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = (d.width - getSize().width) / 2;
        int startY = (d.height - getSize().height) / 2;
        setLocation(startX, startY);
        setTitle("Kalender");
        setLayout(null);
        setResizable(true);

        // components
        JLabel dayLabel = new JLabel("Tag");
        dayLabel.setBounds(16, 30, 80, 24);
        add(dayLabel);
        JTextField dayTextField = new JTextField("1");
        dayTextField.setBounds(16, 56, 80, 24);
        add(dayTextField);

        JLabel monthLabel = new JLabel("Monat");
        monthLabel.setBounds(104, 30, 80, 24);
        add(monthLabel);
        JTextField monthTextField = new JTextField("1");
        monthTextField.setBounds(104, 56, 80, 24);
        add(monthTextField);

        JLabel yearLabel = new JLabel("Jahr");
        yearLabel.setBounds(192, 30, 80, 24);
        add(yearLabel);
        JTextField yearTextField = new JTextField("2000");
        yearTextField.setBounds(192, 56, 80, 24);
        add(yearTextField);

        JLabel resultLabel = new JLabel("Bitte wähle ein Datum aus.", SwingConstants.CENTER);
        resultLabel.setBounds(16, 96, 256, 24);
        add(resultLabel);

        JButton goButton = new JButton("Los");
        goButton.addActionListener(e -> {
            int day = this.inputService.input(dayTextField, Integer.class).orElse(-1);
            int month = this.inputService.input(monthTextField, Integer.class).orElse(-1);
            int year = this.inputService.input(yearTextField, Integer.class).orElse(-1);

            if (!this.dateService.isValidDate(day, month, year)) {
                resultLabel.setText("Ungültiges Datum!");
                return;
            }

            String weekDay = this.dateService.getWeekDay(day, month, year);
            resultLabel.setText(String.format("%d.%d.%d ist ein %s", day, month, year, weekDay));
        });
        goButton.setBounds(56, 144, 165, 24);
        add(goButton);

        setVisible(true);
    }


    public static void main(String[] args) {
        new GoToDateApplication(new DateService(), new InputService());
    }
}
