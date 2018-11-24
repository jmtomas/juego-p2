/* Juan Manuel Tomás - 232063 */
package sumas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class FrameTablero extends javax.swing.JFrame implements Observer {

    private JButton[][] celdas;
    private Sistema sistema;
    private Partida partida;

    public FrameTablero() {
        initComponents();
        this.setTitle("Frame Tablero");
        this.getContentPane().setBackground(new Color(0x222222));
    }

    public FrameTablero(Sistema sistema, Partida partida) {
        this();
        this.sistema = sistema;
        this.partida = partida;
        this.panelJuego.setLayout(new GridLayout(8, 9));
        this.celdas = new JButton[9][10];
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 9; j++) {
                JButton jButton = new JButton();
                jButton.setMargin(new Insets(-5, -5, -5, -5));
                jButton.setForeground(Color.white);
                jButton.addActionListener(new ListenerCelda(i, j, this.partida));
                this.panelJuego.add(jButton);
                this.celdas[i][j] = jButton;
            }
        }
        this.panelBotones.setLayout(new FlowLayout());
        if (!partida.isRepeticion()) {
            this.agregarBoton("Saltar turno", 0);
            this.agregarBoton("Terminar partida", 1);
        } else {
            this.agregarBoton("Replicar jugada", 2);
            this.agregarBoton("Nueva partida", 3);
        }
    }

    private void agregarBoton(String texto, int tipo) {
        JButton boton = new JButton();
        boton.addActionListener(new ListenerBoton(this.sistema, this.partida, tipo));
        boton.setText(texto);
        boton.setForeground(Color.white);
        boton.setBackground(Color.gray);
        this.panelBotones.add(boton);
    }

    @Override
    public void update(Observable obs, Object obj) {
        this.partida = (Partida) obs;
        Tablero tablero = this.partida.getTablero();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 9; j++) {
                Pieza pieza = tablero.piezaCoord(i, j);
                switch (pieza.getColor()) {
                    case "rojo":
                        this.celdas[i][j].setBackground(new Color(0xAA5555));
                        break;
                    case "azul":
                        this.celdas[i][j].setBackground(new Color(0x5555AA));
                        break;
                    default:
                        this.celdas[i][j].setBackground(Color.LIGHT_GRAY);
                        break;
                }
                if (pieza.getValor() != 0) {
                    this.celdas[i][j].setText(Integer.toString(pieza.getValor()));
                } else {
                    this.celdas[i][j].setText("");
                }
                if (!this.partida.isFinPartida() && !this.partida.isRepeticion() && (tablero.getPosibles().contains(pieza)
                        || (tablero.getPosibles().isEmpty() && pieza.getColor().equals(this.partida.getActual())))) {
                    this.celdas[i][j].setBorder(new LineBorder(Color.YELLOW, 2));
                } else {
                    this.celdas[i][j].setBorder(new LineBorder(Color.BLACK, 1));
                }
            }
        }
        if (!tablero.getPosibles().isEmpty()) {
            StringBuilder s = new StringBuilder();
            s.append("Piezas que se pueden mover:");
            tablero.getPosibles().stream().forEach((pieza) -> {
                s.append(" ").append(pieza.getValor());
            });
            this.statusBar.setText(s.toString());
        } else {
            this.statusBar.setText("");
        }
        this.labelJugadorActual.setText(partida.getActual());
        if (partida.getTerminacion() == 3) {
            this.labelTurnos.setText("Turnos restantes: " + partida.getRestantes());
        } else {
            this.labelTurnos.setText("");
        }
        if (this.partida.getComando().getTipo() == 0) {
            this.cambiarBordes();
        }
        if (partida.isFinPartida()) {
            Sonido.reproducir("Tada");
            if (!partida.getActual().equals("gris")) {
                JOptionPane.showMessageDialog(this, partida.getActual() + " ganó.");
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un empate.");
            }
            partida.deleteObserver(this);
            this.dispose();
        }
    }

    public void cambiarBordes() {
        Pieza pieza = this.partida.getComando().getTarget();
        Tablero tablero = this.partida.getTablero();
        int fila = pieza.getFila();
        if (pieza.getColor().equals("rojo")) {
            fila--;
        } else {
            fila++;
        }
        if (tablero.despejado(pieza, -1)) {
            this.celdas[fila][pieza.getColumna() - 1].setBorder(new LineBorder(Color.YELLOW, 2));
        }
        if (tablero.despejado(pieza, 0)) {
            this.celdas[fila][pieza.getColumna()].setBorder(new LineBorder(Color.YELLOW, 2));
        }
        if (tablero.despejado(pieza, 1)) {
            this.celdas[fila][pieza.getColumna() + 1].setBorder(new LineBorder(Color.YELLOW, 2));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        panelJuego = new javax.swing.JPanel();
        statusBar = new javax.swing.JLabel();
        labelJugadorActual = new javax.swing.JLabel();
        labelTurnos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        panelBotones.setBackground(new java.awt.Color(34, 34, 34));
        panelBotones.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(panelBotones);
        panelBotones.setBounds(570, 40, 120, 380);

        panelJuego.setBackground(new java.awt.Color(34, 34, 34));
        panelJuego.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        getContentPane().add(panelJuego);
        panelJuego.setBounds(20, 40, 530, 380);

        statusBar.setBackground(new java.awt.Color(22, 22, 22));
        statusBar.setForeground(new java.awt.Color(255, 255, 255));
        statusBar.setText("statusBar");
        getContentPane().add(statusBar);
        statusBar.setBounds(40, 430, 390, 20);

        labelJugadorActual.setBackground(new java.awt.Color(22, 22, 22));
        labelJugadorActual.setForeground(new java.awt.Color(255, 255, 255));
        labelJugadorActual.setText("jugadorActual");
        getContentPane().add(labelJugadorActual);
        labelJugadorActual.setBounds(50, 10, 150, 20);

        labelTurnos.setForeground(java.awt.Color.white);
        labelTurnos.setText("Turnos restantes:");
        getContentPane().add(labelTurnos);
        labelTurnos.setBounds(440, 430, 150, 20);

        setSize(new java.awt.Dimension(723, 494));
        setLocationRelativeTo(null);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrameTablero().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelJugadorActual;
    private javax.swing.JLabel labelTurnos;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JLabel statusBar;
    // End of variables declaration//GEN-END:variables

    private static class ListenerCelda implements ActionListener {

        private final int fila;
        private final int columna;
        private final Partida partida;

        public ListenerCelda(int i, int j, Partida p) {
            this.fila = i;
            this.columna = j;
            this.partida = p;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!this.partida.isRepeticion()) {
                if (this.partida.getComando().getTipo() == -1) {
                    if (this.partida.prepararComando(fila, columna)) {
                        Sonido.reproducir("Command");
                    } else {
                        Sonido.reproducir("Balloon");
                    }
                } else {
                    this.partida.getComando().changeFila(this.fila);
                    this.partida.getComando().changeColumna(this.columna);
                    if (this.partida.getComando().validarComando(this.partida) == 0) {
                        Sonido.reproducir("Command");
                        this.partida.ejecutarComando(this.partida.getComando());
                    } else {
                        Sonido.reproducir("Balloon");
                        this.partida.prepararComando(fila, columna);
                    }
                }
            }
        }
    }

    private static class ListenerBoton implements ActionListener {

        private final Sistema sistema;
        private final Partida partida;
        private final int tipo;

        public ListenerBoton(Sistema sistema, Partida partida, int tipo) {
            this.sistema = sistema;
            this.partida = partida;
            this.tipo = tipo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (this.tipo) {
                case 0:
                    this.partida.getComando().setTipo(2);
                    if (this.partida.getComando().validarComando(this.partida) == 0) {
                        Sonido.reproducir("Command");
                        this.partida.ejecutarComando(this.partida.getComando());
                    }
                    break;
                case 1:
                    this.partida.getComando().setTipo(1);
                    this.partida.ejecutarComando(this.partida.getComando());
                    break;
                case 2:
                    Sonido.reproducir("Command");
                    this.partida.ejecutarComando(this.partida.pop());
                    break;
                case 3:
                    FramePartida frame = new FramePartida(this.sistema, this.partida);
                    frame.setVisible(true);
            }
        }
    }

}
