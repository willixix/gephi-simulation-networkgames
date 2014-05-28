/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

/**
 *
 * @author william
 */

import java.io.Writer;
import java.util.Iterator;

public class FixedSizeQueueStringWriter extends Writer {
    LimitedQueue<String> buffer = null;
    final int default_limit = 1000;

    public FixedSizeQueueStringWriter(int limit) {
        buffer = new LimitedQueue<String>(limit);
    }
    
    public FixedSizeQueueStringWriter() {
        buffer = new LimitedQueue<String>(default_limit);
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) {
        String str = new String(cbuf, off, len);
        buffer.add(str);
    }
    
    @Override
    public void write(String str) {
        buffer.add(str);
    }
    
    @Override
    public void close() {
        flush();
        buffer = null;
    }
    
    @Override
    public void flush() {
        buffer.clear();
    }

    // if last string ended with newline, add it by itself, otherwise append to last
    public void newline() {
        String str = buffer.getLast();
        if (str.endsWith("\n")) {
            buffer.add("\n");
        }
        else {
            buffer.removeLast();
            buffer.add(str+"\n");
        }
    }
    
    public String getLastLines(int n) {
        String ret = "";
        Iterator<String> iter = buffer.descendingIterator();
        for (int i=0; i<n && iter.hasNext(); i++) {
           ret = iter.next() + ret;
        }
        return ret;
    }
    
    @Override
    public String toString() {
        String ret = "";
        Iterator<String> iter = buffer.iterator();
        while(iter.hasNext()) {
            ret = ret + iter.next();
        }
        return ret;
    }
}