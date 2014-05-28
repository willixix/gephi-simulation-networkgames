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
public class RunSimulationStep_CoordinationGame_ThreeStrategies extends RunSimulationStep_TwoPlayerGame 
{
   RunSimulationStep_CoordinationGame_ThreeStrategies() {
        super();
        int mygame[][] = {   // opponent strategy - Unknown, A, B, N
                             /* my strategy U */ { 0, 0, 0, 0 },
                             /* my strategy A */ { 1, 5, 1, 3 }, 
                             /* my strategy B */ { 1, 1, 5, 3 }, 
                             /* my strategy N */ { 1, 3, 3, 3 }
                         };
        game = mygame;
        prob_of_change = 0.8;    
    }

    @Override
    public Believe calculateNewBeliefForNode(Node node) {
        if (node!=null) {
            Node neighbor[] = bestPayoffNeighborNode(node);
            if (neighbor!=null && neighbor[0]!=null && java.lang.Math.random() < prob_of_change) {
                if (getCurrentPayoff(neighbor[0])>getCurrentPayoff(node)) {
                    int n = 0;
                    if (neighbor[1]!=null) {
                        int n_neighbors = 0;
                        for (n_neighbors=0;neighbor[n_neighbors]!=null;n_neighbors++) {}
                        n = new Double(java.lang.Math.floor(java.lang.Math.random()*n_neighbors)).intValue();                
                    }
                    return getLastBelieve(neighbor[n]);
                }
                // Neutral if one of the neighbors has the same payoff and opposite strategy
                else if (getCurrentPayoff(neighbor[0])==getCurrentPayoff(node)) {
                        Believe my_believe = getLastBelieve(node);
                        for (int ni=0;neighbor[ni]!=null;ni++) {
                              if ((my_believe.isBelieveA() && 
                                   getLastBelieve(neighbor[ni]).isBelieveB()) ||
                                  (my_believe.isBelieveA() && 
                                   getLastBelieve(neighbor[ni]).isBelieveA())) {
                                  return new Believe(Believe.believe_neutral);
                              }
                        }
                }
            }
        }
        return getCurrentBelieve(node);
    }
   
}