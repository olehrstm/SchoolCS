package de.olehrstm.school.calendar;

import de.olehrstm.school.calendar.services.DateService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

@Slf4j
public class CalendarApplication extends JFrame {

    private final JLabel monthLabel;
    private final JPanel calendarPanel;
    private final DateService dateService;
    private YearMonth currentMonth;

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

        this.currentMonth = YearMonth.now();

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

        String monthName = this.currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.GERMAN);
        int year = this.currentMonth.getYear();
        this.monthLabel.setText(monthName + " " + year);

        String[] headers = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
        for (String header : headers) {
            JLabel label = new JLabel(header, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            this.calendarPanel.add(label);
        }

        LocalDate firstDayOfMonth = this.currentMonth.atDay(1);
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        int emptySlots = dayOfWeek.getValue() - 1; // Monday is 1, so we need 0 empty slots for monday.

        for (int i = 0; i < emptySlots; i++) {
            this.calendarPanel.add(new JLabel(""));
        }

        int daysInMonth = this.dateService.getDaysInMonth(this.currentMonth.getMonthValue(), year);

        LocalDate today = LocalDate.now();
        int currentDay = -1;
        if (this.currentMonth.equals(YearMonth.from(today))) {
            currentDay = today.getDayOfMonth();
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
        this.currentMonth = this.currentMonth.plusMonths(amount);
        updateCalendar();
    }

    public static void main(String[] args) {
        String lookAndFeel = "Nimbus";
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception exception) {
            log.error("Error setting look and feel", exception);
        }

        new CalendarApplication();
    }
}
