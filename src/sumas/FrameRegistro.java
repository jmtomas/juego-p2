/* Juan Manuel Tomás - 232063 */
package sumas;

import java.awt.Color;
import javax.swing.JOptionPane;

public class FrameRegistro extends javax.swing.JFrame {

    private Sistema sistema;

    public FrameRegistro() {
        initComponents();
        this.setTitle("Frame Registro");
        this.getContentPane().setBackground(new Color(0x222222));
    }

    public FrameRegistro(Sistema sistema) {
        this();
        this.sistema = sistema;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        fieldNombre = new javax.swing.JTextField();
        fieldAlias = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        fieldEdad = new javax.swing.JSpinner();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().add(fieldNombre);
        fieldNombre.setBounds(140, 60, 180, 19);
        getContentPane().add(fieldAlias);
        fieldAlias.setBounds(140, 110, 180, 19);

        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Registrar Jugador");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 10, 140, 15);

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 60, 70, 15);

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Alias:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 110, 39, 15);

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Edad:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 160, 40, 15);

        botonRegistrar.setBackground(java.awt.Color.gray);
        botonRegistrar.setForeground(java.awt.Color.white);
        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(botonRegistrar);
        botonRegistrar.setBounds(224, 230, 100, 25);

        botonSalir.setBackground(java.awt.Color.gray);
        botonSalir.setForeground(java.awt.Color.white);
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir);
        botonSalir.setBounds(60, 230, 80, 25);

        fieldEdad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        getContentPane().add(fieldEdad);
        fieldEdad.setBounds(140, 160, 180, 20);

        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        String nombre = this.fieldNombre.getText();
        String alias = this.fieldAlias.getText();
        int edad = (Integer)this.fieldEdad.getValue();
        Sonido s;
        if (this.fieldNombre.getText().equals("")) {
            s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Ingrese un nombre.");
        } else if (this.fieldAlias.getText().equals("")) {
            s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Ingrese un alias.");
        } else if (!this.sistema.aliasUnico(alias)) {
            s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Ingrese un alias único.");
        } else {
            this.sistema.registroJugador(nombre, alias, edad);
            this.dispose();
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrameRegistro().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField fieldAlias;
    private javax.swing.JSpinner fieldEdad;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
