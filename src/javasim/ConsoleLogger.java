package javasim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Whiplash
 */
public class ConsoleLogger {

    private static final File simlog = new File("simlog.log"); // Creates simlog.log relative to the java package.
    private static Date date;
    private static Timestamp stamp;
    private static int verbosity;

    public static void Verbosity(int vlevel) {
        verbosity = vlevel;
        if (verbosity == 0) {
            System.out.println("Logging Verbosity is: " + verbosity);
        }
    }

    public static void Log(Object input, int level) { // Takes the object to be logged, with its relevant logging level (1 being minor, 3 being major).
        if (level >= verbosity && verbosity != 0) { // If the log call level is greater than or equal to verbosity, and verbosity is NOT 0
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(simlog, true)))) { // create a filewriter to simlog, appending all new data
                date = new Date(); // get the date at the time of the call
                stamp = new Timestamp(date.getTime()); // turn the date into a timestamp
                out.println(stamp); // print the timestamp on its own line
                out.println(input); // then print the object of the logging call to the file
                System.out.println(stamp); // oh yeah also do the same to the internal console.
                System.out.println(input);
            } catch (IOException ex) {
                System.out.println("Logging error: " + ex.getMessage());
            }
        }
        else { // Do nothing if level is less than verbosity and verbosity is 0.

        }
    }

    public static void StartLog() { // This method is called to create the initial timestamp and line seperator in simlog.log to tell when a new session of the program has begun.
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(simlog, true)))) {
            date = new Date();
            stamp = new Timestamp(date.getTime());
            out.println("-----------PROGRAM START " + stamp + "-----------");
            System.out.println("-----------PROGRAM START " + stamp + "-----------");
        } catch (IOException ex) {
            System.out.println("Logging error: " + ex.getMessage());
        }

    }
}
