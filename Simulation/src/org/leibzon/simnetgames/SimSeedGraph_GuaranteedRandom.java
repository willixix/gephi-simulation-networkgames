/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import org.gephi.graph.api.Node;
import java.util.Random;

/**
 *
 * @author william
 */
public class SimSeedGraph_GuaranteedRandom extends GraphSeederBase {

    public SimSeedGraph_GuaranteedRandom(int numA, int numB) {
      super(numA,numB);
    }

    @Override
    public boolean seedGraph_NoMessage() {
            Double preseed = new Double(java.lang.Math.random()*32768.0*32768.0);
            Random random = new Random(preseed.longValue());
            int r;
            Node[] nodesArray = graph.getNodes().toArray();
            done_set_A=0;
            done_set_B=0;
            
            for (int i=0; i<num_to_seed_A;i++) {
                do {
                    r=random.nextInt(nodesArray.length);
                }
                while (getCurrentBelieve(nodesArray[r]).isBelieveSet());
                setBelieve(nodesArray[r],new Believe(Believe.believe_A));
                done_set_A++;
            }
            for (int i=0; i<num_to_seed_B;i++) {
                do {
                    r=random.nextInt(nodesArray.length);
                }
                while (getCurrentBelieve(nodesArray[r]).isBelieveSet());
                setBelieve(nodesArray[r],new Believe(Believe.believe_B));
                done_set_B++;
            }
            return true;
    }
    
}