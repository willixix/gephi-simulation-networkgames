/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

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
    
    @Override
    public void seedGraph_NoMessage() {
    }
    
    @Override
    public void seedGraph() {
            seedGraph_NoMessage();
            colorGraphByBelieve();
            String display_text = "Seeding complete\n\n"+
                    "Nodes set to believe A: "+done_set_A+"\n"+
                    "Nodes set to believe B: "+done_set_B+"\n";
            NotifyDescriptor d = new NotifyDescriptor.Message(display_text, NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
    }
    
}