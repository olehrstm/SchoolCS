package de.olehrstm.school.calendar;

import de.olehrstm.school.calendar.services.DateService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Locale;

public class CalendarApplication extends JFrame {

    private final JLabel monthLabel;
    private final JPanel calendarPanel;
    private final Calendar calendar;
    private final DateService dateService;

    public CalendarApplication() {
        this.dateService = new DateService();

        // Frame init
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Kalender");
        setLayout(new BorderLayout());

        this.calendar = Calendar.getInstance();

        // components
        JPanel controlPanel = new JPanel();
        JButton previousButton = new JButton("<");
        JButton nextButton = new JButton(">");
        this.monthLabel = new JLabel("", SwingConstants.CENTER);

        previousButton.addActionListener(e -> changeMonth(-1));
        nextButton.addActionListener(e -> changeMonth(1));

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(previousButton, BorderLayout.WEST);
        controlPanel.add(this.monthLabel, BorderLayout.CENTER);
        controlPanel.add(nextButton, BorderLayout.EAST);

        add(controlPanel, BorderLayout.NORTH);

        this.calendarPanel = new JPanel();
        this.calendarPanel.setLayout(new GridLayout(0, 7));
        this.calendarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(this.calendarPanel, BorderLayout.CENTER);

        updateCalendar();

        setVisible(true);
    }

    private void updateCalendar() {
        this.calendarPanel.removeAll();

        String monthName = String.format(Locale.GERMAN, "%tB", this.calendar);
        int year = this.calendar.get(Calendar.YEAR);
        this.monthLabel.setText(monthName + " " + year);

        String[] headers = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
        for (String header : headers) {
            JLabel label = new JLabel(header, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            this.calendarPanel.add(label);
        }

        Calendar tempCal = (Calendar) this.calendar.clone();
        tempCal.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfWeek = tempCal.get(Calendar.DAY_OF_WEEK);

        int emptySlots = (firstDayOfWeek + 5) % 7;

        for (int i = 0; i < emptySlots; i++) {
            this.calendarPanel.add(new JLabel(""));
        }

        int daysInMonth = this.dateService.getDaysInMonth(this.calendar.get(Calendar.MONTH) + 1, year);

        Calendar today = Calendar.getInstance();
        int currentDay = -1;
        if (this.calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
            && this.calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
        ) {
            currentDay = today.get(Calendar.DAY_OF_MONTH);
        }

        for (int i = 1; i <= daysInMonth; i++) {
            JLabel dayLabel = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            dayLabel.setOpaque(true);

            if (i == currentDay) {
                dayLabel.setBackground(Color.LIGHT_GRAY);
                dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            this.calendarPanel.add(dayLabel);
        }

        this.calendarPanel.revalidate();
        this.calendarPanel.repaint();
    }

    private void changeMonth(int amount) {
        this.calendar.add(Calendar.MONTH, amount);
        updateCalendar();
    }

    public static void main(String[] args) {
        new CalendarApplication();
    }
}
