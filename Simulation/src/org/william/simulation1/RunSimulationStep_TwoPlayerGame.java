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
public class RunSimulationStep_TwoPlayerGame extends RunningSimulationBase {
    
    protected int game[][];
    protected double prob_of_change=1.0; // probability node will look for better strateg from one of its neighbors

    RunSimulationStep_TwoPlayerGame() {
        super();
        int mygame[][] = { // opponent strategy - Unknown, A, B, N
                           /* my strategy U */ { 0, 0, 0, 0 },
                           /* my strategy A */ { 1, 5, 1, 3 }, 
                           /* my strategy B */ { 1, 1, 5, 3 }, 
                           /* my strategy N */ { 1, 3, 3, 3 }
                         };
        game = mygame;
        prob_of_change = 1.0;    
    }
    
    @Override
    public double calculatePayoffForNode(Node node) {
        int num_believes_set=0;
        Believe my_belief = getLastBelieve(node);
        int my_strategy = my_belief.getGameStrategyNumber();
        double my_total_payoff = 0.0;
        int num_neighbors = 0;
        if (node!=null) {
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 if (neighbor!=node) {
                     int neighbor_strategy = getLastBelieve(neighbor).getGameStrategyNumber();
                     int this_game_payoff = game[my_strategy][neighbor_strategy];
                     double my_strategy_strength = 1.0;
                     if (my_belief.isBelieveA()) {
                         my_strategy_strength = preference_a;
                     }
                     if (my_belief.isBelieveB()) {
                         my_strategy_strength = preference_b;
                     }
                     my_total_payoff += this_game_payoff * my_strategy_strength * getReputationValue(edge);
                     num_neighbors ++;
                 }
            }
        }
        if (num_neighbors>0) {
            my_total_payoff = my_total_payoff / num_neighbors;
        }
        return my_total_payoff;
    }

    // Finds a node with largest payoff value among node's neighbors
    // Note: there maybe more than 1 such node, how to account for it?
    public Node[] bestPayoffNeighborNode(Node node) {
        final int maxbest = 50;
        double bestpayoff = 0.0;
        Node[] bestneighbor;
        int numbest = 0;
        
        bestneighbor = new Node[maxbest];
        bestneighbor[0]=null;
        if (node!=null) {
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 if (neighbor!=node && getLastBelieve(neighbor).isBelieveSet()) {
                     double payoff = getCurrentPayoff(neighbor);
                     if (payoff > bestpayoff) {
                         bestpayoff = payoff;
                         bestneighbor[0] = neighbor;
                         bestneighbor[1] = null;
                         numbest = 1;
                     }
                     else if (payoff > 0.0 && payoff == bestpayoff) {
                         if (numbest < maxbest) {
                            bestneighbor[numbest] = neighbor;
                            numbest++;
                            bestneighbor[numbest] = null;
                         }
                     }
                 }
            }
        }
        return bestneighbor;
    }

    // Finds largest payoff value among node's neighbors
    public double bestPayoffNeighbor(Node node) {
        double best = 0.0;
        if (node!=null) {
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 if (neighbor!=node) {
                     double payoff = getCurrentPayoff(neighbor);
                     if (payoff > best) {
                         best = payoff;
                     }
                 }
            }
        }
        return best;
    }

    // Return strategy (belief) of the neighboring node that got at least payoff min_payoff
    // for some reason this funtion did not work
    public Believe betterStrategyNeighbor(Node node, double min_payoff) {
        if (node!=null) {
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 if (neighbor!=node) {
                     double payoff = getCurrentPayoff(neighbor);
                     if (payoff > min_payoff) {
                         return getLastBelieve(neighbor);
                     }
                 }
            }
        }
        return new Believe(Believe.believe_unknown);
    }

    @Override
    public Believe calculateNewBeliefForNode(Node node) {
        if (node!=null) {
            Node neighbor[] = bestPayoffNeighborNode(node);
            if (neighbor!=null && neighbor[0]!=null &&
                    getCurrentPayoff(neighbor[0])>getCurrentPayoff(node) &&
                    java.lang.Math.random() < prob_of_change) {
                int n = 0;
                if (neighbor[1]!=null) {
                    int n_neighbors = 0;
                    for (n_neighbors=0;neighbor[n_neighbors]!=null;n_neighbors++) {}
                    n = new Double(java.lang.Math.floor(java.lang.Math.random()*n_neighbors)).intValue();                
                }
                return getLastBelieve(neighbor[n]);
            }
        }
        return getCurrentBelieve(node);
    }
}