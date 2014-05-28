/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;

import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Attributes;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author william
 */
public class SimFinalize_CalculateReputation extends SimBase {
    
    private Double reputation_change_coefficient_negative = 0.9;
    private Double reputation_change_coefficient_positive = 1.1;
    
    public void endSimulation_CalculateReputation(Believe trueBelieve) {
        int num_reputation_updates = 
                updateReputation(trueBelieve);
        clearAllBelievesToUnset();
        colorGraphByBelieve();
        String display_text = "End of Simulation Processing complete\n\n"+
                    "Nodes Links with updates to reputation: "+num_reputation_updates+"\n";
        NotifyDescriptor d = new NotifyDescriptor.Message(display_text, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

    public void endSimulation() {
        clearAllBelievesToUnset();
        colorGraphByBelieve();
        String display_text = "End of Simulation Processing complete\nAll nodes reset\n";
        NotifyDescriptor d = new NotifyDescriptor.Message(display_text, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

    private int updateReputation(Believe trueBelieve) {
        int num_reputation_updates = 0;
        for(Node node : graph.getNodes().toArray()) {
            Believe p_node = getCurrentBelieve(node);
            for (Edge edge : graph.getEdges(node).toArray()) {
                 Node neighbor = edge.getTarget();
                 if (neighbor != node && wasEdgeUsed(edge)) {
                    Believe p_neighbor = getLastBelieve(neighbor);
                    // Positive reputation if node's last believe is trueBelieve
                    // and neighbor had the same believe at last interaction and
                    // this connection was used for calculating node's believe
                    if ((p_node.isBelieveA() || p_node.isBelieveB()) &&
                            p_node.equals(p_neighbor)) {
                        Double reputation = getReputationValue(edge);                    
                        if (p_node.equals(trueBelieve)) {
                            reputation = reputation * reputation_change_coefficient_positive;
                        }
                        // Negative reputation if node's last believe is not trueBelieve
                        // and neighbor had the same believe at last interaction and
                        // this connection was used for calculating node's believe
                        else {
                            reputation = reputation * reputation_change_coefficient_negative;
                        }
                        setReputationValue(edge,reputation);
                        num_reputation_updates++;
                    }
                 }
            }
        }
        return num_reputation_updates;
    }

    public void clearAllBelievesToUnset() {
        if (lock()) {
            for(Node node : graph.getNodes().toArray()) {
                Attributes a = node.getAttributes();
                a.setValue(believeAttribute, new Integer(Believe.believe_unset));
                a.setValue(believeAttributeLastTime, new Integer(Believe.believe_unset));
            }
            unlock();
        }
    }
}