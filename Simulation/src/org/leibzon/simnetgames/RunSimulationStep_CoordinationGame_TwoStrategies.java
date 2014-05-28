/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

/**
 *
 * @author william
 */
public class RunSimulationStep_CoordinationGame_TwoStrategies extends RunSimulationStep_TwoPlayerGame 
{
   RunSimulationStep_CoordinationGame_TwoStrategies() {
        super();
        int mygame[][] = {   // opponent strategy - Unknown, A, B, N
                             /* my strategy U */ { 0, 0, 0, 0 },
                             /* my strategy A */ { 1, 2, 1, 1 }, 
                             /* my strategy B */ { 1, 1, 2, 1 }, 
                             /* my strategy N */ { 1, 1, 1, 1 }
                         };
        game = mygame;
        prob_of_change = 0.8;    
    }
   
}