/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasim;

import java.util.Date;
import sun.misc.PerformanceLogger;

/**
 *
 * @author Whiplash
 */
public class SimDate {
    /*
     Setting up calender variables.
     */

    private static int hour;
    private static int day = 1;
    private static int month = 1;
    private static int year = 1;

    int hour() {
        hour++;
        if (hour >= 24) {
            hour = 1;
            day++;
        }
        ConsoleLogger.Log("Hours: " + hour);
        return hour;
    }

    int day() {
        if (day >= 31) {
            day = 1;
            month++;
        }
        ConsoleLogger.Log("Days: " + day);
        return day;
    }

    int month() {
        if (month >= 12) {
            month = 1;
            year++;
        }
        ConsoleLogger.Log("Months: " + month);
        return month;
    }

    int year() {
        ConsoleLogger.Log("Years: " + year);
        return year;
    }
}
