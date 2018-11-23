/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.Serializable;
import java.util.ArrayList;

public class Tablero implements Serializable {

    private ArrayList<Pieza> piezas;
    private Pieza ultima;
    private ArrayList<Pieza> posibles;

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }

    public Pieza getUltima() {
        return ultima;
    }

    public void setUltima(Pieza ultima) {
        this.ultima = ultima;
    }

    public ArrayList<Pieza> getPosibles() {
        return posibles;
    }

    public void setPosibles(ArrayList<Pieza> posibles) {
        this.posibles = posibles;
    }

    public Tablero() {
        this.ultima = new Pieza(0, "rojo");
        this.posibles = new ArrayList<>();
        this.piezas = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            this.piezas.add(new Pieza(1, i + 1, i, "azul"));
            this.piezas.add(new Pieza(8, i, 8 - i + 1, "rojo"));
        }
    }

    public Tablero(Tablero tablero) {
        this.ultima = new Pieza(0, "rojo");
        this.posibles = new ArrayList<>();
        tablero.getPosibles().stream().forEach((p) -> {
            this.posibles.add(new Pieza(p));
        });
        this.piezas = new ArrayList<>();
        tablero.getPiezas().stream().forEach((p) -> {
            this.piezas.add(new Pieza(p));
        });
    }

    public Pieza piezaCoord(int fila, int columna) {
        for (Pieza p : this.piezas) {
            if (p.getFila() == fila && p.getColumna() == columna) {
                return p;
            }
        }
        return new Pieza(0, "gris");
    }

    public boolean despejado(Pieza pieza, int columna) {
        boolean esta = true;
        int f = pieza.getFila();
        switch (pieza.getColor()) {
            case "azul":
                f++;
                break;
            case "rojo":
                f--;
        }
        int c = pieza.getColumna() + columna;
        for (Pieza p : this.piezas) {
            if (p.equals(this.piezaCoord(f, c))
                    || c < 1 || c > 9
                    || f < 1 || f > 8) {
                esta = false;
            }
        }
        return esta;
    }

    public int calcularPuntaje(String color) {
        int puntaje = 0;
        int fila = 0;
        switch (color) {
            case "azul":
                fila = 8;
                break;
            case "rojo":
                fila = 1;
        }
        for (Pieza p : this.piezas) {
            if (p.getFila() == fila && p.getColor().equals(color)) {
                puntaje += p.getValor();
            }
        }
        return puntaje;
    }

    public void sumarPosibles(Pieza pieza) {
        this.posibles = new ArrayList<>();
        int[] sumas = new int[4];
        for (Pieza p : this.piezas) {
            if (p.getFila() == pieza.getFila()) {
                sumas[0] += p.getValor();
            }
            if (p.getColumna() == pieza.getColumna()) {
                sumas[1] += p.getValor();
            }
            if (p.getFila() - p.getColumna() == pieza.getFila() - pieza.getColumna()) {
                sumas[2] += p.getValor();
            }
            if (p.getFila() + p.getColumna() == pieza.getFila() + pieza.getColumna()) {
                sumas[3] += p.getValor();
            }
        }
        for (int i : sumas) {
            if (i != this.ultima.getValor()
                    && i >= 1 && i <= 8) {
                Pieza p = this.buscarPieza(new Pieza(i, pieza.getColor()));
                if ((this.despejado(p, -1)
                        || this.despejado(p, 0)
                        || this.despejado(p, 1))
                        && !this.posibles.contains(p)
                        && !p.alFinal()) {
                    this.posibles.add(p);
                }
            }
        }
    }

    public void resetPosibles() {
        this.posibles = new ArrayList<>();
    }

    public Pieza buscarPieza(Pieza pieza) {
        if (this.piezas.contains(pieza)) {
            return this.piezas.get(this.piezas.indexOf(pieza));
        }
        return new Pieza(0, "gris");
    }

    public void moverPieza(Comando cmd) {
        Pieza p = this.buscarPieza(cmd.getTarget());
        p.mover(cmd);
        this.setUltima(p);
        this.sumarPosibles(p);
    }

    @Override
    public String toString() {
        return "Tablero{" + "piezas=" + piezas + ", ultima=" + ultima + ", posibles=" + posibles + '}';
    }

}
