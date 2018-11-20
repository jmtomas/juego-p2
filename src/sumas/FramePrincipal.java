/* Juan Manuel Tomás - 232063 */
package sumas;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;

public class FramePrincipal extends javax.swing.JFrame {

    private Sistema sistema;

    public FramePrincipal() {
        initComponents();
        this.setTitle("Frame Principal");
        this.getContentPane().setBackground(new Color(0x222222));
        Sonido.reproducir("Startup");
    }

    public FramePrincipal(Sistema sistema) {
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

        TituloMenuPrincipal = new javax.swing.JLabel();
        botonRegistrarJugador = new javax.swing.JButton();
        botonNuevaPartida = new javax.swing.JButton();
        botonReplicarPartida = new javax.swing.JButton();
        botonVerRanking = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(null);

        TituloMenuPrincipal.setForeground(java.awt.Color.white);
        TituloMenuPrincipal.setText("Menu Principal");
        getContentPane().add(TituloMenuPrincipal);
        TituloMenuPrincipal.setBounds(155, 27, 104, 15);

        botonRegistrarJugador.setBackground(java.awt.Color.gray);
        botonRegistrarJugador.setForeground(java.awt.Color.white);
        botonRegistrarJugador.setText("Registrar Jugador");
        botonRegistrarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarJugadorActionPerformed(evt);
            }
        });
        getContentPane().add(botonRegistrarJugador);
        botonRegistrarJugador.setBounds(146, 60, 159, 25);

        botonNuevaPartida.setBackground(java.awt.Color.gray);
        botonNuevaPartida.setForeground(java.awt.Color.white);
        botonNuevaPartida.setText("Nueva Partida");
        botonNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(botonNuevaPartida);
        botonNuevaPartida.setBounds(146, 103, 134, 25);

        botonReplicarPartida.setBackground(java.awt.Color.gray);
        botonReplicarPartida.setForeground(java.awt.Color.white);
        botonReplicarPartida.setText("Replicar Partidas");
        botonReplicarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReplicarPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(botonReplicarPartida);
        botonReplicarPartida.setBounds(146, 146, 155, 25);

        botonVerRanking.setBackground(java.awt.Color.gray);
        botonVerRanking.setForeground(java.awt.Color.white);
        botonVerRanking.setText("Ver Ranking");
        botonVerRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerRankingActionPerformed(evt);
            }
        });
        getContentPane().add(botonVerRanking);
        botonVerRanking.setBounds(146, 189, 119, 25);

        botonSalir.setBackground(java.awt.Color.gray);
        botonSalir.setForeground(java.awt.Color.white);
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir);
        botonSalir.setBounds(146, 232, 66, 25);

        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarJugadorActionPerformed
        FrameRegistro frame = new FrameRegistro(this.sistema);
        frame.setVisible(true);
    }//GEN-LAST:event_botonRegistrarJugadorActionPerformed

    private void botonNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaPartidaActionPerformed
        if (this.sistema.getJugadores().isEmpty()) {
            Sonido.reproducir("Exclamation");
            JOptionPane.showMessageDialog(this, "No hay jugadores registrados.");
        } else {
            FramePartida frame = new FramePartida(this.sistema);
            this.sistema.addObserver(frame);
            frame.setVisible(true);
        }
    }//GEN-LAST:event_botonNuevaPartidaActionPerformed

    private void botonReplicarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReplicarPartidaActionPerformed
        if (this.sistema.getPartidas().isEmpty()) {
            Sonido.reproducir("Exclamation");
            JOptionPane.showMessageDialog(this, "No hay partidas terminadas.");
        } else {
            FrameReplica frame = new FrameReplica(this.sistema);
            this.sistema.addObserver(frame);
            frame.setVisible(true);
        }
    }//GEN-LAST:event_botonReplicarPartidaActionPerformed

    private void botonVerRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerRankingActionPerformed
        if (this.sistema.getJugadores().isEmpty()) {
            Sonido.reproducir("Exclamation");
            JOptionPane.showMessageDialog(this, "No hay jugadores registrados.");
        } else {
            FrameRanking frame = new FrameRanking(this.sistema);
            this.sistema.addObserver(frame);
            frame.setVisible(true);
        }
    }//GEN-LAST:event_botonVerRankingActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        try {
            this.sistema.guardar();
        } catch (IOException ex) {
            Sonido.reproducir("Error");
            JOptionPane.showMessageDialog(this, "No se pudo guardar el sistema.");
        }
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            new FramePrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TituloMenuPrincipal;
    private javax.swing.JButton botonNuevaPartida;
    private javax.swing.JButton botonRegistrarJugador;
    private javax.swing.JButton botonReplicarPartida;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonVerRanking;
    // End of variables declaration//GEN-END:variables
}
