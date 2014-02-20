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
        System.out.println("The hour is: " + hour);
ConsoleLogger.Log(hour);
        return hour;
    }

     int day() {
        if (day >= 31) {
            day = 1;
            month++;
        }
        System.out.println("The day is: " + day);
        ConsoleLogger.Log(day);
        return day;
    }

     int month() {
        if (month >= 12) {
            month = 1;
            year++;
        }
        System.out.println("The month is: " + month);
        return month;
    }

     int year() {
        System.out.println("The year is: " + year);
        return year;
    }
}
