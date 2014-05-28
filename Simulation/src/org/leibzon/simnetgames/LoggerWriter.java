/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author william
 */
public class LoggerWriter {
    String log_filename = null;
    String detailed_results_filename = null;
    String short_results_filename = null;
    Writer log_writer = null;
    Writer results_writer_detailed = null;
    Writer results_writer_short = null;
    FixedSizeQueueStringWriter logger_fulllog = null;
    FixedSizeQueueStringWriter logger_shortresults = null;
    FixedSizeQueueStringWriter logger_fullresults = null;

    LoggerWriter(String log_filename_in, String results1_filename_in, String results2_filename_in) {
        log_filename = log_filename_in;
        detailed_results_filename = results1_filename_in;
        short_results_filename = results2_filename_in;
    }
    
    public boolean start() {
        if (log_filename!=null) {            
            log_writer = open_file(log_filename);
            if (log_writer==null) {
                return false;
            }
        }
        if (short_results_filename!=null) {
            results_writer_short = open_file(short_results_filename);
            if (results_writer_short==null) {
                return false;
            }
        }
        if (detailed_results_filename!=null) {
            results_writer_detailed = open_file(detailed_results_filename);
            if (results_writer_detailed==null) {
                return false;
            }
        }
        logger_fulllog = new FixedSizeQueueStringWriter();
        logger_shortresults = new FixedSizeQueueStringWriter();
        logger_fullresults = new FixedSizeQueueStringWriter();
        
        return true;
    }
    
    public void stop() {
        try {
            if (log_writer != null) {
                log_writer.close();
            }
            if (results_writer_short != null) {
                results_writer_short.close();
            }
            if (results_writer_detailed != null) {
                results_writer_detailed.close();
            }
        } catch (IOException e) {
            System.err.println("Problem closing files");
        }        
    }
    
    private Writer open_file(String filename) {
        try {
            File fl = new File(filename);
            FileOutputStream ofl = new FileOutputStream(fl, true);
            OutputStreamWriter osw = new OutputStreamWriter(ofl);    
            Writer w = new BufferedWriter(osw);
            return w;
        } catch (IOException e) {
            System.err.println("Problem opening file "+filename);
        }
        return null;
    }
    
    public void log(String text) {
        try {
            if (log_writer!=null) {
                log_writer.write(get_date_string()+" - "+text+"\n");
                logger_fulllog.write(text);
                logger_fulllog.newline();
            }
        } catch (IOException e) {
            System.err.println("Problem writing to file "+log_filename);
        }
    }
    
    public void output_results(String text, boolean detailed) {
        try {
            if (detailed && results_writer_detailed!=null) {
                results_writer_detailed.write(text);
                logger_fullresults.write(text);
            }
            if (!detailed && results_writer_short!=null) {
                results_writer_short.write(text);
                logger_shortresults.write(text);
            }
        } catch (IOException e) {
            if (detailed) {
                System.err.println("Problem writing to file "+detailed_results_filename);
            }
            else {
                System.err.println("Problem writing to file "+short_results_filename);                
            }
        }
    }
    
    public String get_date_string() {
       // Instantiate a Date object
       SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
       return ft.format(new Date());
    }
    
    public String get_date_longnum() {
       // Instantiate a Date object
       SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
       return ft.format(new Date());
    }
    
    public String getLastLines_log(int n) { 
        return logger_fulllog.getLastLines(n);
    }
    
    public String getLastLines_fullresults(int n) { 
        return logger_fullresults.getLastLines(n);
    }
    
    public String getLastLines_shortresults(int n) { 
        return logger_shortresults.getLastLines(n);
    }

}
