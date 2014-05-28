/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import java.util.LinkedList;

/**
 *
 * @author william
 */
public class LimitedQueue<E> extends LinkedList<E> {

    private final int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) { super.remove(); }
        return true;
    }
}