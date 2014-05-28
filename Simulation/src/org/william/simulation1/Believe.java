/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;
import java.awt.Color;

/**
 *
 * @author william
 */
public class Believe extends Object {
    
    public final static int believe_unknown = 0;
    public final static int believe_unset = 1;
    public final static int believe_neutral = 15;
    public final static int believe_A = 10;
    public final static int believe_B = 20;

    public final static Believe Believe_A = new Believe(believe_A);
    public final static Believe Believe_B = new Believe(believe_B);
    public final static Believe Believe_Neutral = new Believe(believe_neutral);
    public final static Believe Believe_Unset = new Believe(believe_unset);
    
    public final Color believe_A_color = Color.RED;
    public final Color believe_B_color = Color.BLUE;
    public final Color believe_neutral_color = Color.GREEN;
    public final Color believe_unset_color = Color.GRAY;
    public final Color believe_unknown_color = Color.BLACK;
    
    // The actual position variabble
    private int believe = believe_unknown;
            
    public Believe() {
          believe = believe_unset;
    }
    
    public Believe(Believe pin) {
          believe = pin.getNumericValue();
    }

    public Believe(Integer in) {
          if (isValidNumericValue(in.intValue())) {
              believe = in.intValue();
          }
          else {
              believe = believe_unset;
          }
    }

    public final boolean isValidNumericValue(int value) {
         return (value == believe_unset || value == believe_neutral ||
                 value == believe_A || value == believe_B);
    }
    
    public int getNumericValue() {
          if (isValidNumericValue(believe)) {
                return believe;
          }
          return believe_unknown;
    }
   
    public Integer getIntegerValue() {
        return new Integer(getNumericValue());
    }
    
    public boolean isBelieveA() {
        return believe == believe_A;
    }
    
    public boolean isBelieveB() {
        return believe == believe_B;
    }
    
    public boolean isBelieveSet() {
         return (believe == believe_neutral || 
                 believe == believe_A || 
                 believe == believe_B);
    }
    
    public boolean isBelieveUnknown() {
         return !isValidNumericValue(believe);
    }

    public String getStringValue() {
          String ret = null;
          switch (believe) {
              case believe_A: ret = "A"; break;
              case believe_B: ret = "B"; break;
              case believe_neutral: ret="Neutral"; break;
              case believe_unset: ret="Unset"; break;
              default: ret = "Unknown";
          }
          return ret;
    }
    
    public Color getColor() {
          Color ret = null;
          switch (believe) {
              case believe_A: ret = believe_A_color; break;
              case believe_B: ret = believe_B_color; break;
              case believe_neutral: ret = believe_neutral_color; break;
              case believe_unset: ret = believe_unset_color; break;
              default: ret = believe_unknown_color;
          }
          return ret;
    }
    
    @Override
    public String toString() {
          return (new Integer(believe)).toString();
    }
    
    @Override
    public boolean equals(Object obj) {
          if (obj instanceof Believe) {
               Believe p = (Believe)obj;
               int p_numeric = p.getNumericValue();
               return p_numeric == this.getNumericValue() &&
                      p_numeric != believe_unknown;
          }
          return false;
    }

    @Override
    public int hashCode() {
        return believe;
    }
    
    public boolean equals(Believe obj) {
          return obj.getNumericValue() == this.getNumericValue();
    }
 }