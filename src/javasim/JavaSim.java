/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        SimDate sd = new SimDate();
        while (isRunning) {
            if (isUpdating) {
                sd.Epoch();
                sd.getHour();
                sd.getDay();
                sd.getMonth();
            }
            Thread.sleep(10);
        }

    }

}
