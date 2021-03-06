/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leibzon.simnetgames;

import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.william.simulation1//progress//EN",
autostore = false)
@TopComponent.Description(preferredID = "progressTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "Expression MODE is undefined on line 28, column 38 in Templates/NetBeansModuleDevelopment-files/templateTopComponent637.java.", openAtStartup = false)
@ActionID(category = "Window", id = "org.william.simulation1.progressTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_progressAction",
preferredID = "progressTopComponent")

public final class progressMessageWindow extends TopComponent {
    
    private boolean paused_pressed = false;
    private boolean end_pressed = false;

    public progressMessageWindow() {
        initComponents();
        setName(NbBundle.getMessage(progressMessageWindow.class, "CTL_progressTopComponent"));
        setToolTipText(NbBundle.getMessage(progressMessageWindow.class, "HINT_progressTopComponent"));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);

    }
    
    public void setNumTrials(int totalNumTrials, int totalTrialSamePrefs) {
        NumTrials_SamePrefs.setText((new Integer(totalTrialSamePrefs)).toString());
        NumTrials_Total.setText((new Integer(totalNumTrials)).toString());
    }
    
    public void setSeedPrefs(int seed_pref_a, int seed_pref_b) {
        Seed_Pref_A.setText((new Integer(seed_pref_a)).toString());
        Seed_Pref_B.setText((new Integer(seed_pref_b)).toString());        
    }
    
    public void setRunPrefs(double sim_pref_a, double sim_pref_b) {
        Sim_Pref_A.setText((new Double(sim_pref_a)).toString());
        Sim_Pref_B.setText((new Double(sim_pref_b)).toString());        
    }
    
    public void setResults(String str) {
        jTextArea_ResultsData.setText(str);
    }
    
    public void setLog(String str) {
        jTextArea_LogData.setText(str);
    }
    
    public boolean isPaused() {
        return paused_pressed;
    }

    public boolean endRequested() {
        return end_pressed;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JLabel0 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NumTrials_Total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Sim_Pref_B = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Seed_Pref_A = new javax.swing.JLabel();
        Sim_Pref_A = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Seed_Pref_B = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NumTrials_SamePrefs = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton_Pause = new javax.swing.JButton();
        jButton_End = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_ResultsData = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_LogData = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getSize()+8f));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(NumTrials_Total, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.NumTrials_Total.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(Sim_Pref_B, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.Sim_Pref_B.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(Seed_Pref_A, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.Seed_Pref_A.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(Sim_Pref_A, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.Sim_Pref_A.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(Seed_Pref_B, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.Seed_Pref_B.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(NumTrials_SamePrefs, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.NumTrials_SamePrefs.text")); // NOI18N

        javax.swing.GroupLayout JLabel0Layout = new javax.swing.GroupLayout(JLabel0);
        JLabel0.setLayout(JLabel0Layout);
        JLabel0Layout.setHorizontalGroup(
            JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLabel0Layout.createSequentialGroup()
                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JLabel0Layout.createSequentialGroup()
                        .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JLabel0Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(JLabel0Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Sim_Pref_A, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JLabel0Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Seed_Pref_A, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sim_Pref_B, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Seed_Pref_B, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NumTrials_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NumTrials_SamePrefs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        JLabel0Layout.setVerticalGroup(
            JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLabel0Layout.createSequentialGroup()
                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumTrials_Total)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(NumTrials_SamePrefs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(Sim_Pref_A)
                    .addComponent(Sim_Pref_B))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JLabel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Seed_Pref_A)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(Seed_Pref_B))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton_Pause, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jButton_Pause.text")); // NOI18N
        jButton_Pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PauseActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton_End, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jButton_End.text")); // NOI18N
        jButton_End.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Pause)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton_End)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Pause)
                    .addComponent(jButton_End))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea_ResultsData.setColumns(20);
        jTextArea_ResultsData.setRows(5);
        jScrollPane1.setViewportView(jTextArea_ResultsData);

        jTextArea_LogData.setColumns(20);
        jTextArea_LogData.setRows(5);
        jScrollPane2.setViewportView(jTextArea_LogData);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(progressMessageWindow.class, "progressMessageWindow.jLabel9.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(JLabel0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(JLabel0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton_PauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PauseActionPerformed
    paused_pressed = true;
}//GEN-LAST:event_jButton_PauseActionPerformed

private void jButton_EndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EndActionPerformed
    end_pressed = true;
}//GEN-LAST:event_jButton_EndActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JLabel0;
    private javax.swing.JLabel NumTrials_SamePrefs;
    private javax.swing.JLabel NumTrials_Total;
    private javax.swing.JLabel Seed_Pref_A;
    private javax.swing.JLabel Seed_Pref_B;
    private javax.swing.JLabel Sim_Pref_A;
    private javax.swing.JLabel Sim_Pref_B;
    private javax.swing.JButton jButton_End;
    private javax.swing.JButton jButton_Pause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_LogData;
    private javax.swing.JTextArea jTextArea_ResultsData;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
