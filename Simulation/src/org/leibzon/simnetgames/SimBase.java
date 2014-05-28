/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

/**
 *
 * @author william
 */

import org.gephi.graph.api.MixedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Attributes;
import org.gephi.graph.api.NodeData;
import org.openide.util.Lookup;
import java.awt.Color;

public class SimBase {
    protected MixedGraph graph = null;
    private int graph_locked = 0;

    protected final static String believeAttribute = "believe_value";
    protected final static String believeAttributeLastTime = "believe_value_last";
    protected final static String reputationAttribute = "reputation_value";
    protected final static String reputationAttributeLastTime = "reputation_value_last";
    protected final static String reputationUsedAttribute = "has_influence";
    protected final static String payoffAttribute = "payoff_value";
    protected final static String payoffAttributeLastTime = "paoff_value_last";
   
    // Consructor. it gets graph object ready
    public SimBase() {
        GraphController gc = Lookup.getDefault().lookup(GraphController.class);
        GraphModel graphModel = gc.getModel();
        
        if (graphModel != null) {
            graph = graphModel.getMixedGraph();
        }        
    }
        
    protected boolean lock() {
        if (graph != null && graph_locked != 1) {
            graph.writeLock();
            graph_locked = 1;
            return true;
        }
        return false;
    }
    
    protected void unlock() {
        if (graph != null && graph_locked == 1) {
            graph.writeUnlock();
            graph_locked = 0;
        }
    }
    
    protected void believeCurrentToLast() {
        if (lock()) {
            for(Node node : graph.getNodes().toArray()) {
                  Attributes a = node.getAttributes();
                  if (a.getValue(believeAttribute)!= null) {
                       a.setValue(believeAttributeLastTime, a.getValue(believeAttribute));
                  }
            }
            unlock();
        }        
    }
    
    public void setBelieve(Node node, Believe pos) {
        Attributes a = node.getAttributes();
        if (a!=null && lock()) {
            a.setValue(believeAttribute, pos.getIntegerValue());
            unlock();
        }
    }

    public Believe getCurrentBelieve(Node node) {
        Attributes a = node.getAttributes();
        if (a!=null && a.getValue(believeAttribute)!=null) {
            return new Believe((Integer)a.getValue(believeAttribute));
        }
        return Believe.Believe_Unset;
    }

    public Believe getLastBelieve(Node node) {
        Attributes a = node.getAttributes();
        if (a!=null && a.getValue(believeAttributeLastTime)!=null) {
            return new Believe((Integer)a.getValue(believeAttributeLastTime));
        }
        return Believe.Believe_Unset;
    }
    
    public Double getReputationValue(Edge edge) {
        Attributes a = edge.getAttributes();
        if (a!=null && a.getValue(reputationAttribute)!=null) {
            return (Double)a.getValue(reputationAttribute);
        }
        return null;     
    }

    public void setReputationValue(Edge edge, Double new_reputation) {
        Attributes a = edge.getAttributes();
        boolean ret = false;
        if (a!=null && lock()) {
            Double old_reputation = (Double)a.getValue(reputationAttribute);
            if (old_reputation != null) {
                a.setValue(reputationAttributeLastTime, old_reputation);
            }
            a.setValue(reputationAttribute, new_reputation);
            unlock();
        }
    }

    public boolean wasEdgeUsed(Edge edge) {
        Attributes a = edge.getAttributes();
        if (a!=null && a.getValue(reputationUsedAttribute)!=null) {
            Integer yn = (Integer)a.getValue(reputationUsedAttribute);
            if (yn!=null && yn.intValue()==1) {
                return true;
            }
        }
        return false;
    }

    public void setReputationUsed(Edge edge, boolean yn) {
        Attributes a = edge.getAttributes();
        if (a!=null && lock()) {
            if (yn) {
                a.setValue(reputationUsedAttribute, new Integer(1));
            }
            else {
                a.setValue(reputationUsedAttribute, new Integer(0));
            }
            unlock();
        }
    }
    
    public void colorGraphByBelieve() {
        for(Node node : graph.getNodes().toArray()) {
            Object believe_obj = node.getAttributes().getValue(believeAttribute);
            if (believe_obj!= null) {
                Believe p = new Believe((Integer)believe_obj);
                Color c = p.getColor();
                NodeData n = node.getNodeData();
                n.setR(Float.valueOf(c.getRed())/255);
                n.setG(Float.valueOf(c.getGreen())/255);
                n.setB(Float.valueOf(c.getBlue())/255);
            }
        }
    }
    
    public int countBelieveNodes(Believe bc) {
        int c = 0;
        for(Node node : graph.getNodes().toArray()) {
            Object believe_obj = node.getAttributes().getValue(believeAttribute);
            if (believe_obj!= null) {
                Believe p = new Believe((Integer)believe_obj);
                if (p.equals(bc)) {
                    c++;
                }
            }
        }
        return c;
    }
    
    protected void payoffCurrentToLast() {
        if (lock()) {
            for(Node node : graph.getNodes().toArray()) {
                  Attributes a = node.getAttributes();
                  if (a.getValue(payoffAttribute)!= null) {
                       a.setValue(payoffAttributeLastTime, a.getValue(payoffAttribute));
                  }
            }
            unlock();
        }        
    }
    
    public void setPayoff(Node node, double payoff_in) {
        Double payoff = new Double(payoff_in);
        Attributes a = node.getAttributes();
        if (a!=null && lock()) {
            a.setValue(payoffAttribute, payoff);
            unlock();
        }
    }

    public double getCurrentPayoff(Node node) {
        Attributes a = node.getAttributes();
        if (a!=null && a.getValue(payoffAttribute)!=null) {
            Double payoff = (Double)a.getValue(payoffAttribute);
            return payoff.doubleValue();
        }
        return 0.0;
    }

    public double getLastPayoff(Node node) {
        Attributes a = node.getAttributes();
        if (a!=null && a.getValue(payoffAttributeLastTime)!=null) {
            Double payoff = (Double)a.getValue(payoffAttributeLastTime);
            return payoff.doubleValue();
        }
        return 0.0;
    }

    public int getDegree(Node n) {
        if (graph!=null && n!=null) {
            return graph.getEdges(n).toArray().length;
        }
        return 0;
    }

    public int getAverageDegree_ByBelieve(Believe b) {
        int sum_degree=0;
        int num_believe=0;
        for(Node node : graph.getNodes().toArray()) {
            Object believe_obj = node.getAttributes().getValue(believeAttribute);
            if (believe_obj!= null) {
                Believe p = new Believe((Integer)believe_obj);
                if (p.equals(b)) {
                    sum_degree += getDegree(node);
                    num_believe++;
                }
            }
        }
        if (num_believe!=0) {
            return sum_degree/num_believe;
        }
        return 0;
    }

    public String getString_NodesDegreesByBelieve() {
        String a_node_degrees="";
        String b_node_degrees="";
        for(Node node : graph.getNodes().toArray()) {
            Object believe_obj = node.getAttributes().getValue(believeAttribute);
            if (believe_obj!= null) {
                Believe p = new Believe((Integer)believe_obj);
                if (p.isBelieveA()) {
                    a_node_degrees = a_node_degrees + " " + getDegree(node);
                }
                if (p.isBelieveB()) {
                    b_node_degrees = b_node_degrees + " " + getDegree(node);
                }
            }
        }
        return "- Believe A degrees:"+a_node_degrees + 
                " (avg " + getAverageDegree_ByBelieve(Believe.Believe_A)+")" +
                "- Believe B degrees:"+b_node_degrees +
                "(avg " + getAverageDegree_ByBelieve(Believe.Believe_B)+")";
    }

    public String getString_CountNodesByBelieve() {
        return " - # Belief A nodes: " +
                countBelieveNodes(Believe.Believe_A) +
               " - # Belief B nodes: " +
                countBelieveNodes(Believe.Believe_B) +
               " - # Neutral belief nodes: " +
                countBelieveNodes(Believe.Believe_Neutral) +
               " - # Unset belief nodes: " +
               countBelieveNodes(Believe.Believe_Unset);
    }
        
}