package de.olehrstm.school.calendar.services;

public class DateService {

    private static final int[] FIRST_DAYS = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
    private static final int[] FIRST_DAYS_LEAP = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int getDaysInMonth(int month, int year) {
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return daysInMonth[month - 1];
    }

    public int getDayOfYear(int day, int month, int year) {
        return (isLeapYear(year)
                ? FIRST_DAYS[month]
                : FIRST_DAYS_LEAP[month]
               ) + day;
    }
}
