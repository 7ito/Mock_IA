/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock.ia;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tito
 */
public class Blue extends javax.swing.JFrame {

    private Parser parser;
    private GUIForm gui;
    
    /**
     * Creates new form Blue
     */
    public Blue(Parser parser) throws Exception{
        this.parser = parser;
        initComponents();
    }

    private Blue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    /**
     * Setter method to set the gui used to return to on back button
     * @param gui 
     */
    public void setGUI(GUIForm gui) throws Exception {
        this.gui = gui;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws Exception {

        BlueLogo = new javax.swing.JLabel();
        TeamsText = new javax.swing.JLabel();
        players = new javax.swing.JLabel();
        advanced = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        String output = "";
        Team team = parser.getTeam(1);
        System.out.println(team.getName());
        String[][] data = new String[team.getPlayers().size() + 1][12];
        String[] columns = {"Name", "Position", "#", "PPG", "APG", "RPG", "OREB", "DREB", "MPG", "STLPG", "BLKPG", "TOPG"};
        data[0] = columns;

        for (int p = 1; p < team.getPlayers().size() + 1; p++)
        {
            data[p] = team.getPlayers().get(p-1).averages();
        }

        for (int row = 0; row<data.length; row++)
        {
            for (int column = 0; column<data[row].length; column++)
            {
                output = output + data[row][column] + "\t       ";
            }
            output = output + "\n";
        }
        stats = new javax.swing.JTextArea(output);
        jScrollPane3 = new javax.swing.JScrollPane();
        String result = "";
        String[][] dataset = new String[2][5];

        dataset[1] = team.average();

        String[] categories = {"PPG", "Opp PPG", "Pace", "OffRtg", "DefRtg"};
        dataset[0] = categories;

        for (int row = 0; row<dataset.length; row++)
        {
            for (int column = 0; column<dataset[row].length; column++)
            {
                result = result + dataset[row][column] + "\t       ";
            }
            result = result + "\n";
        }
        advancedStats = new javax.swing.JTextArea(result);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BlueLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mock/1535466171.png"))); // NOI18N
        BlueLogo.setText("jLabel2");

        TeamsText.setFont(new java.awt.Font("Trebuchet MS", 0, 28)); // NOI18N
        TeamsText.setText("Blue Army U17");

        players.setText("Players");

        advanced.setText("Advanced");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        stats.setColumns(20);
        stats.setRows(5);
        jScrollPane1.setViewportView(stats);

        advancedStats.setEditable(false);
        advancedStats.setColumns(20);
        advancedStats.setRows(5);
        jScrollPane3.setViewportView(advancedStats);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(players, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(advanced))
                        .addContainerGap(586, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BlueLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(TeamsText))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton)
                                .addGap(15, 15, 15))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BlueLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TeamsText)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton)))
                .addGap(18, 18, 18)
                .addComponent(players, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(advanced)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Performs the switching of JFrames from this to the GUI
     * @param evt 
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        
        try {
            gui.setVisible(true);
            close();
        } catch (Exception ex) {
            Logger.getLogger(Blue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Used to close the JFrame on use of the backButton
     */
    public void close()
    {
        this.setVisible(false);
    }
    
    
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Blue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Blue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Blue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Blue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Blue().setVisible(true);
                    
                } catch (Exception ex) {
                    Logger.getLogger(Blue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BlueLogo;
    private javax.swing.JLabel TeamsText;
    private javax.swing.JLabel advanced;
    private javax.swing.JTextArea advancedStats;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel players;
    private javax.swing.JTextArea stats;
    // End of variables declaration//GEN-END:variables
}
