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
public class SimSeedGraph_GuaranteedRandom_WithPreference extends GraphSeederBase {
    private int centrality_preference_a = 1;
    private int centrality_preference_b = 1;
    
    public SimSeedGraph_GuaranteedRandom_WithPreference(int numA, int numB, int centrality_preference_a_in, int centrality_preference_b_in) {
        super(numA,numB);
        centrality_preference_a = centrality_preference_a_in;
        centrality_preference_b = centrality_preference_b_in;
    }
    
    @Override
    public void seedGraph_NoMessage() {
        Random random = new Random();
            int r;
            int r2;
            int r_degree;
            int r2_degree;
            Node[] nodesArray = graph.getNodes().toArray();
            done_set_A=0;
            done_set_B=0;
            
            for (int i=0; i<num_to_seed_A;i++) {
                int pcount=0;
                r=0;
                r_degree=0;
                do {
                    r2=random.nextInt(nodesArray.length);
                    r2_degree=getDegree(nodesArray[r2]);
                    if (!getCurrentBelieve(nodesArray[r2]).isBelieveSet() && r2_degree>=r_degree) {
                        r=r2;
                        r_degree=r2_degree;
                        pcount++;
                    }
                }
                while (pcount<centrality_preference_a);
                setBelieve(nodesArray[r],new Believe(Believe.believe_A));
                done_set_A++;
            }
            for (int i=0; i<num_to_seed_B;i++) {
                int pcount=0;
                r=0;
                r_degree=0;
                do {
                    r2=random.nextInt(nodesArray.length);
                    r2_degree=getDegree(nodesArray[r2]);
                    if (!getCurrentBelieve(nodesArray[r2]).isBelieveSet() && r2_degree>=r_degree) {
                        r=r2;
                        r_degree=r2_degree;
                        pcount++;
                    }
                }
                while (pcount<centrality_preference_b);
                setBelieve(nodesArray[r],new Believe(Believe.believe_B));
                done_set_B++;
            }
    }
        
}