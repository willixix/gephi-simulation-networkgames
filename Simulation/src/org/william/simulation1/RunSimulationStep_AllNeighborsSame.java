/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;

/**
 *
 * @author william
 */
public class RunSimulationStep_AllNeighborsSame extends RunningSimulationBase {
    
    @Override
    public Believe calculateNewBelieveForNode(Node node) {
        double believeA_count=0;
        double believeB_count=0;
        int num_believes_set=0;
        if (node!=null) {
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 Believe p = getLastBelieve(neighbor);
                 // Edge edge_back = graph.getEdge(neighbor, node);
                 if (p!=null && neighbor!=node) {
                     if (p.isBelieveSet()) {
                        num_believes_set++;
                        // below we add how many neighbors have believe A and B
                        // neighors are added taking into account reputation they have
                        if (p.isBelieveA()) {
                            believeA_count += getReputationValue(edge).doubleValue();
                        }
                        if (p.isBelieveB()) {
                            believeB_count += getReputationValue(edge).doubleValue();
                        }
                        setReputationUsed(edge, true);
                     }
                 }
            }
            if (num_believes_set>0) {
                 if (believeA_count*preference_a>believeB_count*preference_b) {
                     return new Believe(Believe.believe_A);
                 }
                 if (believeB_count*preference_b>believeA_count*preference_a) {
                     return new Believe(Believe.believe_B);
                 }
                 return new Believe(Believe.believe_neutral);
            }
        }
        return getCurrentBelieve(node);
    }
}
