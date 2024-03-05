/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_tbd1;

/**
 *
 * @author Victo
 */
public class SignUp extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public SignUp() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Teoría de Base de Datos I");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Lines = new javax.swing.JLabel();
        Fondo = new javax.swing.JPanel();
        Real_Estate = new javax.swing.JPanel();
        Dreams = new javax.swing.JLabel();
        Discover = new javax.swing.JLabel();
        Real = new javax.swing.JLabel();
        Estate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        DeNuevo = new javax.swing.JLabel();
        Bienvenido = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        Pass = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        SignIN = new javax.swing.JLabel();
        Sign = new javax.swing.JButton();
        LogIN = new javax.swing.JLabel();
        Log = new javax.swing.JButton();
        Pass1 = new javax.swing.JLabel();
        Login_Background = new javax.swing.JLabel();
        Lines1 = new javax.swing.JLabel();
        Lines2 = new javax.swing.JLabel();
        Lines3 = new javax.swing.JLabel();
        Lines4 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        Lines.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Real_Estate.setBackground(new java.awt.Color(255, 255, 255));
        Real_Estate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dreams.setFont(new java.awt.Font("British Shorthair", 0, 80)); // NOI18N
        Dreams.setForeground(new java.awt.Color(0, 0, 0));
        Dreams.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dreams.setText("dreams");
        Real_Estate.add(Dreams, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 280, -1));

        Discover.setFont(new java.awt.Font("Neo Sans Std", 0, 30)); // NOI18N
        Discover.setForeground(new java.awt.Color(0, 153, 51));
        Discover.setText("Discover the House of your...");
        Real_Estate.add(Discover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        Real.setFont(new java.awt.Font("British Shorthair", 1, 110)); // NOI18N
        Real.setForeground(new java.awt.Color(0, 0, 0));
        Real.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real.setText("Real");
        Real_Estate.add(Real, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 240, -1));

        Estate.setFont(new java.awt.Font("Neo Sans Std", 1, 100)); // NOI18N
        Estate.setForeground(new java.awt.Color(102, 204, 0));
        Estate.setText("Estate");
        Real_Estate.add(Estate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        Real_Estate.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 550, 20));

        Fondo.add(Real_Estate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 590, 380));

        DeNuevo.setFont(new java.awt.Font("British Shorthair", 0, 70)); // NOI18N
        DeNuevo.setForeground(new java.awt.Color(51, 153, 0));
        DeNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeNuevo.setText("de nuevo!");
        Fondo.add(DeNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 380, 120));

        Bienvenido.setBackground(new java.awt.Color(0, 0, 0));
        Bienvenido.setFont(new java.awt.Font("Neo Sans Std", 1, 48)); // NOI18N
        Bienvenido.setForeground(new java.awt.Color(51, 51, 51));
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("BIENVENIDO");
        Fondo.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 70));

        User.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        User.setForeground(new java.awt.Color(51, 51, 51));
        User.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User.setText("USERNAME");
        Fondo.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 140, 40));

        Username.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        Username.setText("Ingrese su Usuario");
        Username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsernameMouseClicked(evt);
            }
        });
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        Fondo.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 330, 40));

        Pass.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        Pass.setForeground(new java.awt.Color(51, 51, 51));
        Pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pass.setText("PASSWORD");
        Fondo.add(Pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 140, 40));

        jPasswordField1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        Fondo.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 330, 40));

        SignIN.setBackground(new java.awt.Color(0, 0, 0));
        SignIN.setFont(new java.awt.Font("Neo Sans Std", 1, 28)); // NOI18N
        SignIN.setForeground(new java.awt.Color(0, 0, 0));
        SignIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignIN.setText("Sign In");
        SignIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fondo.add(SignIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 100, 50));

        Sign.setBackground(new java.awt.Color(102, 204, 0));
        Sign.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        Sign.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Sign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignActionPerformed(evt);
            }
        });
        Fondo.add(Sign, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, 330, 40));

        LogIN.setBackground(new java.awt.Color(0, 0, 0));
        LogIN.setFont(new java.awt.Font("Neo Sans Std", 1, 28)); // NOI18N
        LogIN.setForeground(new java.awt.Color(0, 0, 0));
        LogIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogIN.setText("Log In");
        LogIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fondo.add(LogIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 100, 50));

        Log.setBackground(new java.awt.Color(204, 204, 204));
        Log.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        Log.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Log.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogMouseClicked(evt);
            }
        });
        Log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogActionPerformed(evt);
            }
        });
        Fondo.add(Log, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 330, 40));

        Pass1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        Pass1.setForeground(new java.awt.Color(153, 153, 153));
        Pass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pass1.setText("No tienes cuenta? Entonces dale Log In");
        Fondo.add(Pass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 330, 40));

        Login_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Login_Back.png"))); // NOI18N
        Login_Background.setOpaque(true);
        Fondo.add(Login_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 390, 380));

        Lines1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 660, 100));

        Lines2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 660, 100));

        Lines3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-230, 480, 660, 100));

        Lines4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-280, 0, 720, 100));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Fondo.png"))); // NOI18N
        Fondo.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 580));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignActionPerformed

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void UsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameMouseClicked
        Username.setText("");
    }//GEN-LAST:event_UsernameMouseClicked

    private void LogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogActionPerformed

    private void LogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogMouseClicked
        SignUp sign = new SignUp();
        Login login = new Login();
        sign.setVisible(false);
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_LogMouseClicked

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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JLabel DeNuevo;
    private javax.swing.JLabel Discover;
    private javax.swing.JLabel Dreams;
    private javax.swing.JLabel Estate;
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel Lines;
    private javax.swing.JLabel Lines1;
    private javax.swing.JLabel Lines2;
    private javax.swing.JLabel Lines3;
    private javax.swing.JLabel Lines4;
    private javax.swing.JButton Log;
    private javax.swing.JLabel LogIN;
    private javax.swing.JLabel Login_Background;
    private javax.swing.JLabel Pass;
    private javax.swing.JLabel Pass1;
    private javax.swing.JLabel Real;
    private javax.swing.JPanel Real_Estate;
    private javax.swing.JButton Sign;
    private javax.swing.JLabel SignIN;
    private javax.swing.JLabel User;
    private javax.swing.JTextField Username;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
