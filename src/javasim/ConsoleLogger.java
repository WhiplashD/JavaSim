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

/**
 *
 * @author Whiplash
 */
public class ConsoleLogger {

    private static final File simlog = new File("simlog.log");
    private static Date date;
    private static Timestamp stamp;

    public static void Log(Object input) {

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(simlog, true)))) {
            date = new Date();
            stamp = new Timestamp(date.getTime());
            out.println(stamp);
            out.println(input);
            System.out.println(stamp);
            System.out.println(input);
        } catch (IOException ex) {
            System.out.println("Logging error: " + ex.getMessage());
        }

    }

}
