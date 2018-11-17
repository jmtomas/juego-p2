package sumas;

import java.awt.Color;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrameReplica extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public FrameReplica() {
        initComponents();
    }

    public FrameReplica(Sistema sistema) {
        initComponents();
        this.sistema = sistema;
        this.generarTabla();
        this.setTitle("Frame Replica");
        this.getContentPane().setBackground(new Color(0x222222));
    }

    private void generarTabla() {
        UneditableModel terminadas = new UneditableModel();
        terminadas.addColumn("Rojo");
        terminadas.addColumn("Azul");
        terminadas.addColumn("Ganador");
        terminadas.addColumn("Hora");
        for (Partida p : this.sistema.getPartidas()) {
            String hora = p.getCal().get(Calendar.HOUR_OF_DAY) + ":"
                    + p.getCal().get(Calendar.MINUTE) + ":"
                    + p.getCal().get(Calendar.SECOND);
            Object[] data = {p.getRojo().getAlias(), p.getAzul().getAlias(), p.getActual(), hora};
            terminadas.addRow(data);
        }
        this.tablaPartidas.setModel(terminadas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panePartidas = new javax.swing.JScrollPane();
        tablaPartidas = new javax.swing.JTable();
        botonReplicar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        panePartidas.setBackground(java.awt.Color.darkGray);
        panePartidas.setForeground(java.awt.Color.white);

        tablaPartidas.setForeground(java.awt.Color.darkGray);
        tablaPartidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaPartidas.setGridColor(java.awt.Color.darkGray);
        tablaPartidas.setSelectionBackground(new java.awt.Color(174, 209, 243));
        tablaPartidas.setSelectionForeground(java.awt.Color.darkGray);
        panePartidas.setViewportView(tablaPartidas);

        getContentPane().add(panePartidas);
        panePartidas.setBounds(23, 40, 350, 180);

        botonReplicar.setBackground(java.awt.Color.gray);
        botonReplicar.setForeground(java.awt.Color.white);
        botonReplicar.setText("Replicar");
        botonReplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReplicarActionPerformed(evt);
            }
        });
        getContentPane().add(botonReplicar);
        botonReplicar.setBounds(268, 245, 100, 25);

        botonSalir.setBackground(java.awt.Color.gray);
        botonSalir.setForeground(java.awt.Color.white);
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir);
        botonSalir.setBounds(20, 240, 70, 25);

        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Replicar partida");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(123, 8, 178, 15);

        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonReplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReplicarActionPerformed
        if (this.tablaPartidas.getSelectedRow() == -1) {
            Sonido s = new Sonido("Exclamation");
            JOptionPane.showMessageDialog(this, "Seleccione una partida.");
        } else {
            Partida partida = this.sistema.nuevaReplica(this.tablaPartidas.getSelectedRow());
            FrameTablero frame = new FrameTablero(this.sistema, partida);
            partida.addObserver(frame);
            partida.ejecutarComando(partida.pop());
            frame.setVisible(true);
            this.sistema.deleteObserver(this);
            this.dispose();
        }
    }//GEN-LAST:event_botonReplicarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.sistema.deleteObserver(this);
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
            java.util.logging.Logger.getLogger(FrameReplica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrameReplica().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonReplicar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane panePartidas;
    private javax.swing.JTable tablaPartidas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        this.generarTabla();
    }

    private class UneditableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
