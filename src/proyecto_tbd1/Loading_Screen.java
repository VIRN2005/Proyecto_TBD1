/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_tbd1;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Victor
 */
public class Loading_Screen extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Main_Screen
     */
    public Loading_Screen() {
        initComponents();

        // Loaction settings
        this.setLocationRelativeTo(null);
        this.setTitle("Teoría de Base de Datos I");
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pics/Tiger.jpg"));
        this.setIconImage(icono);
        this.pack();

        // Threads & all Stuff
        Thread pb = new Thread(this);
        pb.start();

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
        Loading_Icon = new javax.swing.JLabel();
        Loading = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        Real = new javax.swing.JLabel();
        Estate = new javax.swing.JLabel();
        Image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Loading_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/loading-gif.gif"))); // NOI18N
        Loading_Icon.setText("jLabel1");
        jPanel1.add(Loading_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 190, 180));

        Loading.setBackground(new java.awt.Color(0, 0, 0));
        Loading.setFont(new java.awt.Font("Neo Sans Std", 1, 30)); // NOI18N
        Loading.setForeground(new java.awt.Color(0, 0, 0));
        Loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Loading.setText("Loading...");
        jPanel1.add(Loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 260, 70));
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 500, 40));

        Real.setFont(new java.awt.Font("British Shorthair", 1, 90)); // NOI18N
        Real.setForeground(new java.awt.Color(0, 0, 0));
        Real.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real.setText("Real");
        jPanel1.add(Real, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 210, -1));

        Estate.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        Estate.setForeground(new java.awt.Color(255, 255, 255));
        Estate.setText("Estate");
        jPanel1.add(Estate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Loading_House.jpg"))); // NOI18N
        Image.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jPanel1.add(Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 740, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Loading_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loading_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loading_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loading_Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loading_Screen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Estate;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel Loading;
    private javax.swing.JLabel Loading_Icon;
    private javax.swing.JLabel Real;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(40);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Loading.setText(i + "%");
            jProgressBar1.setValue(i);
        }

//        Login login = new Login();
//        login.setVisible(true);
        SignUp signup = new SignUp();
        signup.setVisible(true);
        dispose();

    }
}
