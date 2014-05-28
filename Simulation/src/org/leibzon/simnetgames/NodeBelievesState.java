/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import org.gephi.graph.api.Node;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author william
 */
public class NodeBelievesState extends SimBase {
    private Map<Node, Believe> map;
    
    public NodeBelievesState() {
        map = new HashMap<Node, Believe>();
        for(Node node : graph.getNodes().toArray()) {
            Believe b = getCurrentBelieve(node);
            map.put(node, b);
        }    
    }
    
    public int CompareStates_NumDifferences(NodeBelievesState other) {
        int n = 0;
        for(Map.Entry<Node,Believe> e : map.entrySet()) {
            if (other.map.containsKey(e.getKey()) &&
                    !e.getValue().equals(other.map.get(e.getKey()))) {
                n++;
            }
        }        
        return n;        
    }
    
    public int DifferencesWithCurrentGraph() {
        int n = 0;
        for(Map.Entry<Node,Believe> e : map.entrySet()) {
            if (graph.contains(e.getKey()) &&
                    !e.getValue().equals(getCurrentBelieve(e.getKey()))) {
                n++;
            }
        }        
        return n;        
    }
    
    public void UpdateState() {
        for(Node node : graph.getNodes().toArray()) {
            Believe b = getCurrentBelieve(node);
            if (!map.containsKey(node) ||
                    !map.get(node).equals(b)) {
                map.put(node, b);
            }
        }    
    }
}