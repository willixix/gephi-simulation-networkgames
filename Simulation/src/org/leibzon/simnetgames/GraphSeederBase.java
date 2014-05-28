/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author william
 */
public class GraphSeederBase extends SimBase implements GraphSeeder {
    protected int num_to_seed_A = 0;
    protected int num_to_seed_B = 0;
    protected int done_set_A=0;
    protected int done_set_B=0;
            
    public GraphSeederBase(int numA, int numB) {
        num_to_seed_A = numA;
        num_to_seed_B = numB;
    }
    
    // This is where actual seeding will be done
    @Override
    public boolean seedGraph_NoMessage() {
        return true;
    }
    
    @Override
    public void seedGraph() {
            String display_text = "";
            boolean seededOk = seedGraph_NoMessage();
            if (seededOk) {
                colorGraphByBelieve();
                display_text = "Seeding complete\n\n"+
                    "Nodes set to believe A: "+done_set_A+"\n"+
                    "Nodes set to believe B: "+done_set_B+"\n";
            }
            else {
                display_text = "Failed to seed the graph.\n\nDid you prepare it first?\n";
            }
            NotifyDescriptor d = new NotifyDescriptor.Message(display_text, NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
    }
    
}