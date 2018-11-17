/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.Serializable;
import java.util.Objects;

public class Comando implements Serializable {

    /**
     * Tipos de comandos: -1 inválido 0 movimiento 1 terminar 2 saltar turno
     */
    private int tipo, fila, columna;
    private Pieza target;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void changeFila(int fila) {
        this.fila = fila - this.fila;
    }

    public void changeColumna(int columna) {
        this.columna = columna - this.columna;
    }

    public Pieza getTarget() {
        return target;
    }

    public void setTarget(Pieza target) {
        this.target = target;
    }

    public Comando() {
        this.tipo = -1;
        this.fila = 0;
        this.columna = 0;
        this.target = new Pieza(0, "gris");
    }

    public Comando(Comando cmd) {
        this.tipo = cmd.tipo;
        this.fila = cmd.fila;
        this.columna = cmd.columna;
        this.target = new Pieza(cmd.target);
    }

    public int validarComando(Partida partida) {
        Tablero t = partida.getTablero();
        Pieza pieza = t.buscarPieza(this.target);
        int codigo = 0;
        switch (this.tipo) {
            case -1:
                codigo = 1;
                break;
            case 0:
                if (pieza.equals(t.getUltima())) {
                    codigo = 2;
                } else if (!t.getPosibles().contains(pieza)
                        && !t.getPosibles().isEmpty()) {
                    codigo = 3;
                } else if (!t.despejado(pieza, this.columna)) {
                    codigo = 4;
                } else if (this.columna < -1 || this.columna > 1
                        || (pieza.getColor().equals("rojo") && this.fila != -1)
                        || (pieza.getColor().equals("azul") && this.fila != 1)) {
                    codigo = 5;
                }
                break;
            case 2:
                if (t.getPosibles().isEmpty()) {
                    codigo = 6;
                }
        }
        return codigo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.tipo;
        hash = 83 * hash + this.fila;
        hash = 83 * hash + this.columna;
        hash = 83 * hash + Objects.hashCode(this.target);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comando other = (Comando) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.fila != other.fila) {
            return false;
        }
        if (this.columna != other.columna) {
            return false;
        }
        return Objects.equals(this.target, other.target);
    }

    @Override
    public String toString() {
        return "Comando{" + "tipo=" + tipo + ", fila=" + fila + ", columna=" + columna + ", target=" + target + '}';
    }

}
