/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

import org.gephi.graph.api.Node;

/**
 *
 * @author william
 */
public class RunningSimulationBase extends SimBase implements RunableSimulation {
    protected double preference_a = 1.0;
    protected double preference_b = 1.0;

    @Override
    public void runSimulation(int num_iterations, double preference_a_in, double preference_b_in) {
            preference_a = preference_a_in;
            preference_b = preference_b_in;
            for (int i=1; i<= num_iterations; i++) {
                believeCurrentToLast();
                runIteration();
            }
            colorGraphByBelieve();
    }
    
    @Override
    public int runSimulationMaxIterations(int max_iterations, double preference_a_in, double preference_b_in) {
            preference_a = preference_a_in;
            preference_b = preference_b_in;
            int n=1;
            int m=0;
            for (int i=1; i<= max_iterations && n>0; i++) {
                believeCurrentToLast();
                n=runIteration();
                m++;
            }
            colorGraphByBelieve();
            return m;
    }
    
    @Override
    public int runIteration() {
        int n=0;
        for(Node node : graph.getNodes().toArray()) {
            Believe p_old = getCurrentBelieve(node);
            Believe p_new =  calculateNewBelieveForNode(node);
            if (!p_new.isBelieveUnknown() && !p_old.equals(p_new)) {
                setBelieve(node,p_new);
                n++;
            }
        }
        return n;
    }
    
    @Override
    public Believe calculateNewBelieveForNode(Node node) {
        return new Believe(Believe.believe_unknown);
    }
    
}