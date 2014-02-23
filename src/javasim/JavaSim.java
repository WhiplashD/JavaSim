
package javasim;

import java.io.PrintStream;

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
        PrintStream printStream = new PrintStream(new ConsoleOutputStream(f.ConsoleOutputWindow));
        System.setOut(printStream);
        System.setErr(printStream);
        ConsoleLogger.StartLog();
        ConsoleLogger.Verbosity(1);
        SimDate sd = new SimDate();
        while (isRunning) {
            if (isUpdating) {
                sd.Epoch();
                f.setHour(sd.getHour());
                f.setDay(sd.getDay());
                f.setMonth(sd.getMonth());
                f.setYear(sd.getYear());
            }
            Thread.sleep(1000);
        }
        
    }
    
}
