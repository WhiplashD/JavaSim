/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Whiplash
 */
public class ConsoleLogger {

    private static final File simlog = new File("simlog.log");
    private static Date date = new Date();
    private static Timestamp stamp = new Timestamp(date.getTime());

    public static void Log(Object input) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(simlog, true)))) {
            out.println(stamp);
            out.println(input);
        } catch (IOException ex) {
            System.out.println("Logging error: " + ex.getMessage());
        }

    }

}
