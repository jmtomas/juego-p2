/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.Serializable;
import java.util.Objects;

public class Pieza implements Serializable {

    private int fila, columna, valor;
    private String color;

    public Pieza(Pieza pieza) {
        this.fila = pieza.fila;
        this.columna = pieza.columna;
        this.valor = pieza.valor;
        this.color = pieza.color;
    }

    public Pieza(int valor, String color) {
        this.fila = 0;
        this.columna = 0;
        this.valor = valor;
        this.color = color;
    }

    public Pieza(int fila, int columna, int valor, String color) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.color = color;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void mover(Comando cmd) {
        switch (this.color) {
            case "azul":
                this.fila++;
                break;
            case "rojo":
                this.fila--;
        }
        this.columna += cmd.getColumna();
    }
    
    public boolean alFinal() {
        boolean status = false;
        if (this.color.equals("rojo") && this.fila == 1
                || this.color.equals("azul") && this.fila == 8) {
            status = true;
        }
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pieza) {
            return this.color.equals(((Pieza) obj).color)
                    && this.valor == ((Pieza) obj).valor;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.fila;
        hash = 79 * hash + this.columna;
        hash = 79 * hash + this.valor;
        hash = 79 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public String toString() {
        return "Pieza{" + "fila=" + fila + ", columna=" + columna + ", valor=" + valor + ", color=" + color + '}';
    }

}
