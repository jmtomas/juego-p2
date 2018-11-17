/* Juan Manuel Tomás - 232063 */
package sumas;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class FramePartida extends javax.swing.JFrame implements Observer {

    private Sistema sistema;
    private Partida partida;
    private int terminacion;

    public FramePartida() {
        initComponents();
        this.setTitle("Frame Partida");
        this.getContentPane().setBackground(new Color(0x222222));
    }

    public FramePartida(Sistema sistema) {
        this();
        this.sistema = sistema;
        this.partida = null;
        this.terminacion = 1;
        this.fieldTurnos.setEnabled(false);
        this.generarComboBoxes();
    }
    
    public FramePartida(Sistema sistema, Partida partida) {
        this();
        this.sistema = sistema;
        this.partida = partida;
        this.terminacion = partida.getTerminacion();
        this.fieldTurnos.setEnabled(false);
        this.radioPrimera.setEnabled(false);
        this.radioTodas.setEnabled(false);
        this.radioTurnos.setEnabled(false);
        this.generarComboBoxes();
    }
    
    private void generarComboBoxes() {
        DefaultComboBoxModel aliasRojo = new DefaultComboBoxModel();
        DefaultComboBoxModel aliasAzul = new DefaultComboBoxModel();
        this.sistema.getJugadores().stream().forEach((j) -> {
            aliasRojo.addElement(j.getAlias());
            aliasAzul.addElement(j.getAlias());
        });
        this.fieldAzul.setModel(aliasRojo);
        this.fieldRojo.setModel(aliasAzul);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonJugar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        radioTodas = new javax.swing.JRadioButton();
        radioPrimera = new javax.swing.JRadioButton();
        radioTurnos = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        labelNoTurnos = new javax.swing.JLabel();
        fieldTurnos = new javax.swing.JSpinner();
        fieldRojo = new javax.swing.JComboBox<>();
        fieldAzul = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Nueva Partida");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 20, 110, 15);

        jLabel2.setBackground(java.awt.Color.darkGray);
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Alias Rojo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 80, 80, 15);

        jLabel3.setBackground(java.awt.Color.darkGray);
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Alias Azul");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 130, 80, 15);

        botonJugar.setBackground(java.awt.Color.gray);
        botonJugar.setForeground(java.awt.Color.white);
        botonJugar.setText("Jugar");
        botonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJugarActionPerformed(evt);
            }
        });
        getContentPane().add(botonJugar);
        botonJugar.setBounds(240, 240, 80, 25);

        botonSalir.setBackground(java.awt.Color.gray);
        botonSalir.setForeground(java.awt.Color.white);
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir);
        botonSalir.setBounds(50, 240, 70, 25);

        radioTodas.setBackground(new java.awt.Color(34, 34, 34));
        buttonGroup1.add(radioTodas);
        radioTodas.setForeground(java.awt.Color.white);
        radioTodas.setSelected(true);
        radioTodas.setText("Todas");
        radioTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodasActionPerformed(evt);
            }
        });
        getContentPane().add(radioTodas);
        radioTodas.setBounds(240, 100, 68, 23);

        radioPrimera.setBackground(new java.awt.Color(34, 34, 34));
        buttonGroup1.add(radioPrimera);
        radioPrimera.setForeground(java.awt.Color.white);
        radioPrimera.setText("Primera");
        radioPrimera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPrimeraActionPerformed(evt);
            }
        });
        getContentPane().add(radioPrimera);
        radioPrimera.setBounds(240, 140, 80, 23);

        radioTurnos.setBackground(new java.awt.Color(34, 34, 34));
        buttonGroup1.add(radioTurnos);
        radioTurnos.setForeground(java.awt.Color.white);
        radioTurnos.setText("Turnos");
        radioTurnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTurnosActionPerformed(evt);
            }
        });
        getContentPane().add(radioTurnos);
        radioTurnos.setBounds(240, 180, 74, 23);

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Terminación");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(240, 80, 100, 15);

        labelNoTurnos.setBackground(java.awt.Color.darkGray);
        labelNoTurnos.setForeground(java.awt.Color.white);
        labelNoTurnos.setText("No. turnos");
        getContentPane().add(labelNoTurnos);
        labelNoTurnos.setBounds(30, 180, 80, 15);

        fieldTurnos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        getContentPane().add(fieldTurnos);
        fieldTurnos.setBounds(120, 180, 100, 20);

        getContentPane().add(fieldRojo);
        fieldRojo.setBounds(120, 80, 100, 24);

        getContentPane().add(fieldAzul);
        fieldAzul.setBounds(120, 130, 100, 24);

        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJugarActionPerformed
        String aliasRojo = (String)this.fieldRojo.getSelectedItem();
        String aliasAzul = (String)this.fieldAzul.getSelectedItem();
        int turnos = (Integer) this.fieldTurnos.getValue();
        Sonido s;
        if (aliasRojo.equals(aliasAzul)) {
            s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Los alias no deben ser iguales.");
        } else if (this.radioTurnos.isSelected() && turnos < 1) {
            s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad valida de turnos.");
        } else {
            Partida nueva;
            if (this.partida == null) {
                nueva = this.sistema.nuevaPartida(this.terminacion, aliasRojo, aliasAzul);
            } else {
                nueva = this.sistema.nuevaPartida(this.partida, aliasRojo, aliasAzul);
            }
            if (this.radioTurnos.isSelected()) {
                nueva.setTurnos(turnos);
                nueva.setRestantes(turnos);
            }
            FrameTablero frame = new FrameTablero(this.sistema, nueva);
            nueva.addObserver(frame);
            nueva.ejecutarComando(new Comando());
            frame.setVisible(true);
            this.sistema.deleteObserver(this);
            this.dispose();
        }
    }//GEN-LAST:event_botonJugarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.sistema.deleteObserver(this);
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void radioTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodasActionPerformed
        this.terminacion = 1;
        this.fieldTurnos.setEnabled(false);
    }//GEN-LAST:event_radioTodasActionPerformed

    private void radioPrimeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPrimeraActionPerformed
        this.terminacion = 2;
        this.fieldTurnos.setEnabled(false);
    }//GEN-LAST:event_radioPrimeraActionPerformed

    private void radioTurnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTurnosActionPerformed
        this.terminacion = 3;
        this.fieldTurnos.setEnabled(true);
    }//GEN-LAST:event_radioTurnosActionPerformed

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
            java.util.logging.Logger.getLogger(FramePartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FramePartida().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonJugar;
    private javax.swing.JButton botonSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> fieldAzul;
    private javax.swing.JComboBox<String> fieldRojo;
    private javax.swing.JSpinner fieldTurnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelNoTurnos;
    private javax.swing.JRadioButton radioPrimera;
    private javax.swing.JRadioButton radioTodas;
    private javax.swing.JRadioButton radioTurnos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        this.generarComboBoxes();
    }
}