/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_tbd1;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class MainScreen extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form MainScree
     */
    public MainScreen() {
        initComponents();
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setResizable(false);
        show();
        setLocationRelativeTo(null);
        this.setTitle("Teoría de Base de Datos I");
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pics/Tiger.jpg"));
        this.setIconImage(icono);

        Thread pb = new Thread(this);
        pb.start();

        Add.setVisible(false);
        List.setVisible(false);
        Search.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        OptionPanel = new javax.swing.JPanel();
        Options = new javax.swing.JLabel();
        AddPanel = new javax.swing.JPanel();
        Add = new javax.swing.JLabel();
        List_Panel = new javax.swing.JPanel();
        List = new javax.swing.JLabel();
        SearchPanel = new javax.swing.JPanel();
        Search = new javax.swing.JLabel();
        ExitPanel = new javax.swing.JPanel();
        Exit = new javax.swing.JLabel();
        Real = new javax.swing.JLabel();
        State = new javax.swing.JLabel();
        ListPanel = new javax.swing.JLabel();
        SmallHouse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 370, 120));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OptionPanel.setBackground(new java.awt.Color(255, 0, 0));
        OptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionPanelMouseExited(evt);
            }
        });

        Options.setBackground(new java.awt.Color(255, 255, 255));
        Options.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Option_Menu.png"))); // NOI18N
        Options.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Options.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OptionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionPanelLayout = new javax.swing.GroupLayout(OptionPanel);
        OptionPanel.setLayout(OptionPanelLayout);
        OptionPanelLayout.setHorizontalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Options, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OptionPanelLayout.setVerticalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Options, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(OptionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 90));

        AddPanel.setBackground(new java.awt.Color(255, 0, 0));
        AddPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AddPanelMouseExited(evt);
            }
        });

        Add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/rebautizar.png"))); // NOI18N
        Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout AddPanelLayout = new javax.swing.GroupLayout(AddPanel);
        AddPanel.setLayout(AddPanelLayout);
        AddPanelLayout.setHorizontalGroup(
            AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        AddPanelLayout.setVerticalGroup(
            AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel3.add(AddPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 110, 80));

        List_Panel.setBackground(new java.awt.Color(255, 0, 0));
        List_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                List_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                List_PanelMouseExited(evt);
            }
        });

        List.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        List.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/portapapeles.png"))); // NOI18N
        List.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout List_PanelLayout = new javax.swing.GroupLayout(List_Panel);
        List_Panel.setLayout(List_PanelLayout);
        List_PanelLayout.setHorizontalGroup(
            List_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(List_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(List_PanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        List_PanelLayout.setVerticalGroup(
            List_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(List_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(List_PanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(List_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, 100));

        SearchPanel.setBackground(new java.awt.Color(255, 0, 0));
        SearchPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SearchPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SearchPanelMouseExited(evt);
            }
        });

        Search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        Search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
            .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SearchPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SearchPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(SearchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 110, 90));

        ExitPanel.setBackground(new java.awt.Color(255, 0, 0));

        Exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cerrar-sesion.png"))); // NOI18N
        Exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitPanelLayout = new javax.swing.GroupLayout(ExitPanel);
        ExitPanel.setLayout(ExitPanelLayout);
        ExitPanelLayout.setHorizontalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ExitPanelLayout.setVerticalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(ExitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 110, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 110, 780));

        Real.setFont(new java.awt.Font("British Shorthair", 1, 90)); // NOI18N
        Real.setForeground(new java.awt.Color(0, 0, 0));
        Real.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real.setText("Real");
        jPanel1.add(Real, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 260, 160));

        State.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        State.setForeground(new java.awt.Color(0, 0, 0));
        State.setText("Estate");
        jPanel1.add(State, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 300, 110));

        ListPanel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ListPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2.png"))); // NOI18N
        ListPanel.setLabelFor(ListPanel);
        jPanel1.add(ListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 650, 700));

        SmallHouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2(Small).png"))); // NOI18N
        jPanel1.add(SmallHouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 110, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Desea salir del Programa?", "WARNING!", JOptionPane.WARNING_MESSAGE);

        if (showConfirmDialog == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_ExitMouseClicked

    private void OptionPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionPanelMouseExited
        //OptionPanel.setBackground(null);
    }//GEN-LAST:event_OptionPanelMouseExited

    private void OptionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseExited
        OptionPanel.setBackground(null);
    }//GEN-LAST:event_OptionsMouseExited

    private void OptionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseEntered
        OptionPanel.setBackground(new Color(139, 0, 0));
    }//GEN-LAST:event_OptionsMouseEntered

    private void OptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseClicked
        boolean isVisible = Add.isVisible() || List.isVisible() || Search.isVisible();

        Add.setVisible(!isVisible);
        List.setVisible(!isVisible);
        Search.setVisible(!isVisible);
    }//GEN-LAST:event_OptionsMouseClicked

    private void ExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseEntered
        ExitPanel.setBackground(new Color(139, 0, 0));
    }//GEN-LAST:event_ExitMouseEntered

    private void ExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseExited
        ExitPanel.setBackground(null);
    }//GEN-LAST:event_ExitMouseExited

    private void AddPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPanelMouseExited

    private void AddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMouseEntered
        AddPanel.setBackground(new Color(139, 0, 0));
    }//GEN-LAST:event_AddMouseEntered

    private void AddPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPanelMouseEntered
        AddPanel.setBackground(null);
    }//GEN-LAST:event_AddPanelMouseEntered

    private void List_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_List_PanelMouseExited
        List_Panel.setBackground(null);
    }//GEN-LAST:event_List_PanelMouseExited

    private void SearchPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchPanelMouseExited
        SearchPanel.setBackground(null);
    }//GEN-LAST:event_SearchPanelMouseExited

    private void List_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_List_PanelMouseEntered
        List_Panel.setBackground(new Color(139, 0, 0));
    }//GEN-LAST:event_List_PanelMouseEntered

    private void SearchPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchPanelMouseEntered
        SearchPanel.setBackground(new Color(139, 0, 0));
    }//GEN-LAST:event_SearchPanelMouseEntered

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add;
    private javax.swing.JPanel AddPanel;
    private javax.swing.JLabel Exit;
    private javax.swing.JPanel ExitPanel;
    private javax.swing.JLabel List;
    private javax.swing.JLabel ListPanel;
    private javax.swing.JPanel List_Panel;
    private javax.swing.JPanel OptionPanel;
    private javax.swing.JLabel Options;
    private javax.swing.JLabel Real;
    private javax.swing.JLabel Search;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLabel SmallHouse;
    private javax.swing.JLabel State;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
