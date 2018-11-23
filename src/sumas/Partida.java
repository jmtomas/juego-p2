/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

public class Partida extends Observable implements Serializable {

    private ArrayList<Comando> comandos;
    private Comando comando;
    private int turnos;
    private int restantes;
    private Jugador rojo;
    private Jugador azul;
    private String actual;
    private Tablero tablero;
    private boolean finPartida;
    private int terminacion;
    private boolean repeticion;
    Calendar cal;

    public ArrayList<Comando> getComandos() {
        return comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    public Comando getComando() {
        return comando;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getRestantes() {
        return restantes;
    }

    public void setRestantes(int restantes) {
        this.restantes = restantes;
    }

    public Jugador getRojo() {
        return rojo;
    }

    public void setRojo(Jugador rojo) {
        this.rojo = rojo;
    }

    public Jugador getAzul() {
        return azul;
    }

    public void setAzul(Jugador azul) {
        this.azul = azul;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isFinPartida() {
        return finPartida;
    }

    public void setFinPartida(boolean fin) {
        this.finPartida = fin;
    }

    public int getTerminacion() {
        return terminacion;
    }

    public void setTerminacion(int terminacion) {
        this.terminacion = terminacion;
    }

    public boolean isRepeticion() {
        return repeticion;
    }

    public void setRepeticion(boolean repeticion) {
        this.repeticion = repeticion;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    private void push(Comando comando) {
        this.comandos.add(comando);
    }

    public final Comando pop() {
        return this.comandos.remove(0);
    }

    public void alternarJugadorActual() {
        this.tablero.resetPosibles();
        switch (this.actual) {
            case "rojo":
                this.actual = "azul";
                break;
            case "azul":
                this.actual = "rojo";
        }
        this.tablero.setUltima(new Pieza(0, this.actual));
    }

    public boolean esFinTurno() {
        if (this.tablero.getPosibles().isEmpty()) {
            return !this.isFinPartida();
        }
        return false;
    }

    public Partida() {
        this.actual = "rojo";
        this.finPartida = false;
        this.repeticion = false;
        this.comandos = new ArrayList<>();
        this.comando = new Comando();
        this.tablero = new Tablero();
        this.cal = Calendar.getInstance();
    }

    public Partida(Partida partida, boolean init) {
        this();
        this.rojo = partida.rojo;
        this.azul = partida.azul;
        this.terminacion = partida.terminacion;
        this.turnos = partida.turnos;
        if (init) {
            this.restantes = partida.restantes;
            for (Comando c : partida.comandos) {
                this.comandos.add(new Comando(c));
            }
            Comando cmd = this.pop();
            while (cmd.getTipo() != -1) {
                cmd = this.pop();
            }
            this.actual = partida.actual;
            this.tablero = new Tablero(partida.tablero);
        } else {
            partida.comandos.remove(partida.comandos.indexOf(new Comando()));
            partida.comandos.add(0, new Comando());
            this.restantes = partida.turnos;
            for (Comando c : partida.comandos) {
                this.comandos.add(new Comando(c));
            }
            this.repeticion = true;
        }
    }

    public void chequearTerminacion() {
        switch (this.terminacion) {
            case 1:
                if (this.tablero.calcularPuntaje(actual) == 36) {
                    this.terminarPartida(this.actual);
                }
                break;
            case 2:
                if (this.tablero.calcularPuntaje(actual) > 0) {
                    this.terminarPartida(this.actual);
                }
                break;
            case 3:
                if (this.restantes < 1) {
                    int puntosRojo = this.tablero.calcularPuntaje("azul");
                    int puntosAzul = this.tablero.calcularPuntaje("rojo");
                    if (puntosAzul > puntosRojo) {
                        this.actual = "azul";
                    } else if (puntosAzul < puntosRojo) {
                        this.actual = "rojo";
                    } else {
                        this.actual = "gris";
                    }
                    this.terminarPartida(this.actual);
                }
        }
    }

    public boolean prepararComando(int fila, int columna) {
        Pieza target = this.tablero.piezaCoord(fila, columna);
        boolean pudo = true;
        if (target.valida(this)) {
            this.comando.setTarget(target);
            this.comando.setFila(fila);
            this.comando.setColumna(columna);
            this.comando.setTipo(0);
        }
        else {
            pudo = false;
            this.comando.setTipo(-1);
        }
        this.setChanged();
        this.notifyObservers();
        return pudo;
    }

    public void ejecutarComando(Comando cmd) {
        this.push(new Comando(cmd));
        switch (cmd.getTipo()) {
            case 0:
                this.tablero.moverPieza(cmd);
                if (this.esFinTurno()) {
                    this.restantes--;
                }
                this.chequearTerminacion();
                if (this.esFinTurno()) {
                    this.alternarJugadorActual();
                }
                if (this.finPartida) {
                    this.tablero.resetPosibles();
                }
                break;
            case 1:
                this.alternarJugadorActual();
                this.terminarPartida(this.actual);
                break;
            case 2:
                this.alternarJugadorActual();
        }
        cmd.setTipo(-1);
        this.setChanged();
        this.notifyObservers();
    }

    public void terminarPartida(String color) {
        switch (color) {
            case "rojo":
                this.rojo.ganarPartida(repeticion);
                break;
            case "azul":
                this.azul.ganarPartida(repeticion);
        }
        this.finPartida = true;
    }

    @Override
    public String toString() {
        return "Partida{" + "comandos=" + comandos + ", turnos=" + turnos + ", restantes=" + restantes + ", rojo=" + rojo + ", azul=" + azul + ", actual=" + actual + ", tablero=" + tablero + ", finPartida=" + finPartida + ", terminacion=" + terminacion + ", repeticion=" + repeticion + '}';
    }

}
