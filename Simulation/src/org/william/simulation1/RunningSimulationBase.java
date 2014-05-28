/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

import org.gephi.graph.api.Node;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author william
 */
public class RunningSimulationBase extends SimBase implements RunableSimulation {
    protected double preference_a;
    protected double preference_b;
 
    RunningSimulationBase() {
        super();
        preference_a = 1.0;
        preference_b = 1.0;        
    }
    
    @Override
    public void runSimulation(int num_iterations, double preference_a_in, double preference_b_in) {
            preference_a = preference_a_in;
            preference_b = preference_b_in;
            for (int i=1; i<= num_iterations; i++) {
                runIteration();
            }
            colorGraphByBelieve();
    }

    @Override
    public int runIteration() {
            believeCurrentToLast();
            payoffCurrentToLast();
            updatePayoffs(); // used only by some models
            return updateBeliefs();// updates beiefs for all nodes
    }
    
    @Override
    public int runSimulationMaxIterations(int max_iterations, double preference_a_in, double preference_b_in) {
            preference_a = preference_a_in;
            preference_b = preference_b_in;
            NodeBelievesState evenStates = null;
            NodeBelievesState oddStates = null;
            int n=1;
            int m=0;
            for (int i=1; i<= max_iterations && n>0; i++) {
                n=runIteration();
                m++;
                if (i==1) {
                    oddStates = new NodeBelievesState();
                }
                else if (i==2) {
                    evenStates = new NodeBelievesState();
                }
                else if ((i % 2)==1) {
                    if (oddStates.DifferencesWithCurrentGraph()==0) {
                        n=0;
                    }
                }
                else if ((i % 2)==0) {
                    if (evenStates.DifferencesWithCurrentGraph()==0) {
                        n=0;
                    }                    
                }
            }
            colorGraphByBelieve();
            return m;
    }
    
    @Override
    public int updateBeliefs() {
        int n=0;
        for(Node node : graph.getNodes().toArray()) {
            Believe p_old = getCurrentBelieve(node);
            Believe p_new =  calculateNewBeliefForNode(node);
            if (!p_new.isBelieveUnknown() && !p_old.equals(p_new)) {
                setBelieve(node,p_new);
                n++;
            }
        }
        return n;
    }

    // This makes changes such that all nodes change together based on previous state
    // However this is not really needed as changes are usually computed based on
    // previous turn belives which are also part of the graph structure
    public int runIteration_AllNodesChange() {
        int n=0;
        Map<Node, Believe> map = new HashMap<Node, Believe>();
        for(Node node : graph.getNodes().toArray()) {
            Believe p_old = getCurrentBelieve(node);
            Believe p_new =  calculateNewBeliefForNode(node);
            if (!p_new.isBelieveUnknown() && !p_old.equals(p_new)) {
                map.put(node, p_new);
            }
        }
        for(Map.Entry<Node,Believe> e : map.entrySet()) {
            setBelieve(e.getKey(),e.getValue());
            n++;
        }
        return n;
    }
    
    @Override
    public void updatePayoffs() {
        for(Node node : graph.getNodes().toArray()) {
            setPayoff(node, calculatePayoffForNode(node));
        }
    }

    @Override
    public Believe calculateNewBeliefForNode(Node node) {
        return new Believe(Believe.believe_unknown);
    }

    @Override
    public double calculatePayoffForNode(Node node) {
        return 0.0;
    }

}