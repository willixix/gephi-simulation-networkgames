/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Attributes;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author william
 */
public class SimPrepareGraph extends SimBase {
    
    public void prepareGraph() {
        if (lock()) {
            int edges_removed_selfloop=0;
            int edges_added=0;
            int edges_converted_directed=0;
            int edges_added_attributes=0;
            int nodes_added_attributes=0;
            // Make sure the graph is is directed-like with every edge 
            // having one going back, however these edges may have different
            // attributes so officially it must be undirected graph
            for(Edge edge : graph.getEdges().toArray()) {
                // First remove self-edges
                if(edge.isSelfLoop()) {
                    graph.removeEdge(edge);
                    edges_removed_selfloop++;
                }
                else {
                    Node sourceNode = edge.getSource();
                    Node targetNode = edge.getTarget();
                    // Convert to directed edge if it is undirected
                    if (edge.isDirected()==false) {
                        graph.removeEdge(edge);
                        graph.addEdge(sourceNode, targetNode, true);
                        graph.addEdge(targetNode, sourceNode, true);
                        edges_converted_directed++;
                    }
                    // Set the mutual edge if not there
                    Edge mutualEdge = findMutualEdge(sourceNode, targetNode);
                    if (mutualEdge == null) {
                        graph.addEdge(targetNode, sourceNode, true);
                        edges_added++;
                    }
                }
            }
            // Now need to add proper attributes if they are not there
            for(Node node : graph.getNodes().toArray()) {
                  Attributes a = node.getAttributes();
                  if (a.getValue(believeAttribute)== null) {
                       a.setValue(believeAttribute, new Integer(Believe.believe_unset));
                       nodes_added_attributes++;
                  }
                  if (a.getValue(believeAttributeLastTime)== null) {
                       a.setValue(believeAttributeLastTime, new Integer(Believe.believe_unset));
                  }
                  if (a.getValue(payoffAttribute)== null) {
                       a.setValue(payoffAttribute, new Double(0.0));
                  }
                  if (a.getValue(payoffAttributeLastTime)== null) {
                       a.setValue(payoffAttributeLastTime, new Double(0.0));
                  }
            }
            // Now need to add proper attributes if they are not there
            for(Edge edge : graph.getEdges().toArray()) {
                  Attributes a = edge.getAttributes();
                  if (a.getValue(reputationAttribute)== null) {
                       a.setValue(reputationAttribute, new Double(1));
                       edges_added_attributes++;
                  }
                  if (a.getValue(reputationAttributeLastTime)== null) {
                       a.setValue(reputationAttributeLastTime, new Double(1));
                  }
                  if (a.getValue(reputationUsedAttribute)== null) {
                       a.setValue(reputationUsedAttribute, new Integer(0));
                  }
            }
            unlock();
            String display_text = "Processing complete\n\n"+
                    "Self-Loops Removed: "+edges_removed_selfloop+"\n"+
                    "Mutual-Edges Added: "+edges_added+"\n"+
                    "Edges Reset to Directed: "+edges_converted_directed+"\n"+
                    "Belief & Payoff Value added to: "+nodes_added_attributes+" nodes\n"+
                    "Reputation Default Value added to: "+edges_added_attributes+" edges\n\n"+
                    "Total Nodes: "+graph.getNodeCount()+"\n"+
                    "Total Edges: "+graph.getEdgeCount()+"\n";
            NotifyDescriptor d = new NotifyDescriptor.Message(display_text, NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
        }
    }
    
    // finds edge from destination to source
    private Edge findMutualEdge(Node source, Node destination) {
        Edge ret = null;
        if (source!=null && destination != null) {
            for (Edge edge : graph.getEdges(destination).toArray()) {
                  if (edge.getTarget().equals(source)) {
                      ret = edge;
                  }
            }
        }
        return ret;
    }
}