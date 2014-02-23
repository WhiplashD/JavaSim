
package javasim;

/**
 *
 * @author Whiplash
 */
public class SimDate {

    private int epoch;

    public int Epoch() {
        epoch++;
        return epoch;
    }

    public int getHour() {
        int hour = epoch % 24;
        ConsoleLogger.Log("Hour:" + hour);
        return hour;
    }

    public int getDay() {
        int day = epoch / 24 % 31 + 1;
        ConsoleLogger.Log("Day: " + day);
        return day;
    }

    public int getMonth() {
        int month = epoch / 744 % 12 + 1;
        ConsoleLogger.Log("Month: " + month);
        return month;
    }

    public int getYear() {
        int year = epoch / 8928;
        ConsoleLogger.Log("Year: " + year);
        return year;
    }

}
