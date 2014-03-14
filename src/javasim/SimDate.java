package javasim;

/**
 *
 * @author Whiplash
 */
public class SimDate implements Updateable {

    private int epoch; // Time (in game steps; 1 game step = 1 simulation hour, which has passed since the simulation started.

    public SimDate() {

    }

    @Override
    public void Update() {
        Epoch();
    }

    public int Epoch() {
        epoch++;
        return epoch;
    }

    public int getHour() {
        int hour = epoch % 24; // Hours are on a 24 hour clock, 0 being 24, or 12 AM.
        ConsoleLogger.Log("Hour: " + hour, 1);
        return hour;
    }

    public int getDay() {
        int day = epoch / 24 % 31 + 1; // All months have 31 days in this simulation.
        ConsoleLogger.Log("Day: " + day, 1);
        return day;
    }

    public int getMonth() {
        int month = epoch / 744 % 12 + 1;
        ConsoleLogger.Log("Month: " + month, 1);
        return month;
    }

    public int getYear() {
        int year = epoch / 8928 + 1;
        ConsoleLogger.Log("Year: " + year, 1);
        return year;
    }

}
