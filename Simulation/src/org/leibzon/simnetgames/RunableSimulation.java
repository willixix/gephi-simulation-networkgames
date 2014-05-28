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
public interface RunableSimulation {
    public void runSimulation(int num_iterations, double preference_a, double preference_b);
    public int runSimulationMaxIterations(int max_iterations, double preference_a_in, double preference_b_in);
    public void updatePayoffs();
    public int updateBeliefs();
    public int runIteration();
    public Believe calculateNewBeliefForNode(Node node);
    public double calculatePayoffForNode(Node node);
}