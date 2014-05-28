/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

import org.gephi.graph.api.Node;
import java.util.Random;

/**
 *
 * @author william
 */
public class SimSeedGraph_ProbablisticRandom extends GraphSeederBase {
            
    public SimSeedGraph_ProbablisticRandom(int numA, int numB) {
        super(numA,numB);
    }
    
    @Override
    public void seedGraph_NoMessage() {
            Random random = new Random();
            int total_nodes = graph.getNodeCount();
            double prob_seed_A = (new Integer(num_to_seed_A)).doubleValue() / (new Integer(total_nodes)).doubleValue();
            double prob_seed_B = (new Integer(num_to_seed_B)).doubleValue() / (new Integer(total_nodes)).doubleValue();
            double one_minus_prob_seed_B = 1.0 - prob_seed_B;
            done_set_A=0;
            done_set_B=0;
            
            for(Node node : graph.getNodes().toArray()) {
                  double frand = random.nextDouble();
                  if (!getCurrentBelieve(node).isBelieveSet()) {
                        if (frand<=prob_seed_A) {
                               setBelieve(node,new Believe(Believe.believe_A));
                               done_set_A++;
                        }
                        else {
                            if (frand>=one_minus_prob_seed_B) {
                               setBelieve(node,new Believe(Believe.believe_B));
                               done_set_B++;
                            }
                        }
                  }
            }
    }
    
}