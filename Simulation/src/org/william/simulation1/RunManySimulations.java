/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.william.simulation1;
import java.util.Date;

/**
 *
 * @author william
 */

// TODO: support doing simulation over range of preference and seed_centrality parameters

public class RunManySimulations extends SimBase {
    private final int win_ratio = 10; // When there are 10x more A than B its same as "only A" 
    private double run_preference_a = 1.0;
    private double run_preference_b = 1.0;
    private int seed_pref_a = 1;
    private int seed_pref_b = 1;
    private int max_iterations = 100;
    private RunableSimulation runner = null;
    private GraphSeeder seeder = null;

    public RunManySimulations(GraphSeeder seeder_in, RunableSimulation runner_in, int max_iterations_in, double believe_pref_a, double believe_pref_b) {
        runner = runner_in;
        seeder = seeder_in;
        max_iterations = max_iterations_in;
        run_preference_a = believe_pref_a;
        run_preference_b = believe_pref_b;
        }
        
    public RunManySimulations(RunableSimulation runner_in, int seed_numA, int seed_numB, int seed_prefA, int seed_prefB, int max_iterations_in, double believe_pref_a, double believe_pref_b) {
        run_preference_a = believe_pref_a;
        run_preference_b = believe_pref_b;
        seed_pref_a = seed_prefA;
        seed_pref_b = seed_prefB;
        max_iterations = max_iterations_in;
        runner = runner_in;
        seeder = new SimSeedGraph_GuaranteedRandom_WithPreference(seed_numA, seed_numB, seed_pref_a, seed_pref_b);
    }
    
    public void execute(int num_trials, LoggerWriter logwriter) {
        int iterations_completed;
        int i;
        int alltrials_end_a=0;
        int alltrials_end_b=0;
        int alltrials_end_both_ab=0;
        int alltrials_start_avgdegree_a=0;
        int alltrials_start_avgdegree_b=0;
        int alltrials_avg_nodecount = 0;
        int alltrials_avg_edgecount = 0;
        int alltrials_avg_start_a = 0;
        int alltrials_avg_start_b = 0;
        int alltrials_avg_numiterations_a = 0;
        int alltrials_avg_numiterations_b = 0;
        int alltrials_avg_numiterations_both_ab = 0;
        
        if (max_iterations<10) {
            max_iterations = 200;
        }
        for (i=1; i<=num_trials; i++) {
            Date dt = new Date();
            logwriter.log("");
            seeder.seedGraph_NoMessage();            

            int start_num_a = countBelieveNodes(Believe.Believe_A);
            int start_num_b = countBelieveNodes(Believe.Believe_B);
            int start_avgdegree_a = getAverageDegree_ByBelieve(Believe.Believe_A);
            int start_avgdegree_b = getAverageDegree_ByBelieve(Believe.Believe_B);
            alltrials_start_avgdegree_a += start_avgdegree_a;
            alltrials_start_avgdegree_b += start_avgdegree_b;
            alltrials_avg_nodecount +=graph.getNodeCount();
            alltrials_avg_edgecount +=graph.getEdgeCount();
            alltrials_avg_start_a += start_num_a;
            alltrials_avg_start_b += start_num_b;

            logwriter.log("trial "+i+": seeding complete, seed_pref_a="+seed_pref_a+
                    ", sim_pref_b="+seed_pref_b+getString_NodesDegreesByBelieve());
            iterations_completed = runner.runSimulationMaxIterations(max_iterations, run_preference_a, run_preference_b);
            logwriter.log("trial "+i+": simulation complete with "+iterations_completed+" iterations"+
                    ", sim_pref_a="+run_preference_a+", sim_pref_b="+run_preference_b);
            logwriter.log("trial "+i+": simulation end "+getString_CountNodesByBelieve());
            
            int end_num_a = countBelieveNodes(Believe.Believe_A);
            int end_num_b = countBelieveNodes(Believe.Believe_B);
            if (end_num_b == 0 || (end_num_a/end_num_b) >= win_ratio) {
                alltrials_end_a++;
                alltrials_avg_numiterations_a += iterations_completed;
            }
            else if (end_num_a == 0 || (end_num_b/end_num_a) >= win_ratio) {
                alltrials_end_b++;
                alltrials_avg_numiterations_b += iterations_completed;
            }
            else {
                alltrials_end_both_ab++;
                alltrials_avg_numiterations_both_ab += iterations_completed;
            }
                    
            String results = logwriter.get_date_longnum()+","+
                             graph.getNodeCount()+","+graph.getEdgeCount()+","+
                             start_num_a+","+start_num_b+","+
                             seed_pref_a+","+seed_pref_b+","+
                             start_avgdegree_a+","+start_avgdegree_b+","+
                             String.format("%.2f, %.2f, ", run_preference_a, run_preference_b)+
                             iterations_completed+","+
                             end_num_a+","+end_num_b+"\n";
            logwriter.output_results(results, true);
            
            SimFinalize_CalculateReputation finalizer = new SimFinalize_CalculateReputation();
            finalizer.clearAllBelievesToUnset();
            finalizer.colorGraphByBelieve();
        }
        alltrials_start_avgdegree_a = alltrials_start_avgdegree_a / num_trials;
        alltrials_start_avgdegree_b = alltrials_start_avgdegree_b / num_trials;
        alltrials_avg_nodecount = alltrials_avg_nodecount / num_trials;
        alltrials_avg_edgecount = alltrials_avg_edgecount / num_trials;
        alltrials_avg_start_a = alltrials_avg_start_a / num_trials;
        alltrials_avg_start_b = alltrials_avg_start_b / num_trials;
        alltrials_avg_numiterations_a = alltrials_avg_numiterations_a / alltrials_end_a;
        alltrials_avg_numiterations_b = alltrials_avg_numiterations_b / alltrials_end_b;
        alltrials_avg_numiterations_both_ab = alltrials_avg_numiterations_both_ab / alltrials_end_both_ab;
        String results = logwriter.get_date_longnum()+","+
                             alltrials_avg_nodecount+","+alltrials_avg_edgecount+","+
                             alltrials_avg_start_a+","+alltrials_avg_start_b+","+
                             seed_pref_a+","+seed_pref_b+","+
                             alltrials_start_avgdegree_a+","+alltrials_start_avgdegree_b+","+
                             String.format("%.2f,%.2f,", run_preference_a, run_preference_b)+
                             alltrials_end_a+","+alltrials_end_b+","+alltrials_end_both_ab+","+
                             alltrials_avg_numiterations_a+","+alltrials_avg_numiterations_b+","+
                             alltrials_avg_numiterations_both_ab+"\n";
        logwriter.output_results(results, false);
    }
    
}
