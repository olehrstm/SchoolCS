package de.olehrstm.school.calendar.services;

public class DateService {

    private static final int[] FIRST_DAYS = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
    private static final int[] FIRST_DAYS_LEAP = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int getDayOfYear(int day, int month, int year) {
        return (isLeapYear(year)
                ? FIRST_DAYS_LEAP[month - 1]
                : FIRST_DAYS[month - 1]
               ) + day;
    }

    public int getDaysInMonth(int month, int year) {
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return daysInMonth[month - 1];
    }

    public boolean isValidDate(int day, int month, int year) {
        if (year < 2000 || year > 2100) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        return day >= 1 && day <= getDaysInMonth(month, year);
    }

    public String getWeekDay(int day, int month, int year) {
        // Reference: January 1, 2000 was a Saturday (index 5 in the array)
        int referenceYear = 2000;
        int referenceDayOfWeek = 5; // Saturday

        String[] weekdays = { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag" };

        int totalDays = 0;

        for (int y = referenceYear; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }

        totalDays += getDayOfYear(day, month, year) - 1; // -1 because Jan 1 = day 0

        int dayIndex = (referenceDayOfWeek + totalDays) % 7;
        return weekdays[dayIndex];
    }
}
