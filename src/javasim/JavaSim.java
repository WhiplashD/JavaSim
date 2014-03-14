package javasim;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class JavaSim {

    public static boolean isRunning = true;
    public static boolean isUpdating = true;
    private final static String newline = "\n";

    /**
     *
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        SimUI f = new SimUI();
        f.setVisible(true);
        PrintStream printStream = new PrintStream(new ConsoleOutputStream(f.ConsoleOutputWindow)); // Initializes ConsoleOutputStream 
        System.setOut(printStream); //which sends System messages and errors
        System.setErr(printStream); //  to the UI's ConsoleOutputWindow.
        ConsoleLogger.StartLog(); // Creates program startup timestamp in simlog.log

        try { // This must be wrapped in a try-catch incase we are supplied with no or an invalid first argument.
            int varg = Integer.parseInt(args[0]); // Takes an optional integer argument for verbosity level.
            if (varg > 0 && varg < 4) {
                ConsoleLogger.Verbosity(varg); // If the argument integer is greater than 0 and less than 4, sets the logger verbosity to that level
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ve) {
            ConsoleLogger.Verbosity(1); // else it defaults to 1.
            ConsoleLogger.Log("No or invalid parameters supplied as verbosity argument, setting default. " + newline + ve, 3);
        }

        SimDate sd = new SimDate();
        SimBiome sb = new SimBiome();
        sb.initializeBiome();
        List<Updateable> UpdateList = new ArrayList<>();
        UpdateList.add(sd);
        UpdateList.add(sb);
        while (isRunning) {
            if (isUpdating) {
                for (Updateable u : UpdateList) {
                    u.Update();
                }
                f.displayTime(sd);
            }
            Thread.sleep(1000);
        }

    }

}
