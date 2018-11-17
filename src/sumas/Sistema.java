/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Sistema extends Observable implements Observer {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Partida> partidas;

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public Sistema() {
        try {
            FileInputStream fi = new FileInputStream("sistema.bin");
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream oi = new ObjectInputStream(bi);
            this.jugadores = (ArrayList<Jugador>) (oi.readObject());
            this.partidas = (ArrayList<Partida>) (oi.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            this.jugadores = new ArrayList<>();
            this.partidas = new ArrayList<>();
        }
    }

    public void guardar() throws FileNotFoundException, IOException {
        FileOutputStream fo = new FileOutputStream("sistema.bin");
        BufferedOutputStream bo = new BufferedOutputStream(fo);
        try (ObjectOutputStream oo = new ObjectOutputStream(bo)) {
            oo.writeObject(this.jugadores);
            oo.writeObject(this.partidas);
            oo.flush();
        }
    }

    public boolean aliasUnico(String alias) {
        boolean unico = true;
        for (int i = 0; i < this.jugadores.size() && unico; i++) {
            Jugador j = this.jugadores.get(i);
            if (j.getAlias().equals(alias)) {
                unico = false;
            }
        }
        return unico;
    }

    public void registroJugador(String nombre, String alias, int edad) {
        Jugador j = new Jugador(nombre, alias, edad);
        this.jugadores.add(j);
        this.setChanged();
        this.notifyObservers();
    }

    public Jugador ultimoJugador() {
        return this.jugadores.get(this.jugadores.size() - 1);
    }

    private Jugador buscarJugador(String alias) {
        Jugador jugador = new Jugador("", alias, 0);
        for (Jugador j : this.jugadores) {
            if (j.equals(jugador)) {
                jugador = j;
            }
        }
        return jugador;
    }

    public Partida nuevaPartida(int condicion,
            String aliasRojo, String aliasAzul) {
        Jugador rojo = this.buscarJugador(aliasRojo);
        Jugador azul = this.buscarJugador(aliasAzul);
        Partida p = new Partida();
        p.setRojo(rojo);
        p.setAzul(azul);
        p.setTerminacion(condicion);
        p.addObserver(this);
        return p;
    }

    public Partida nuevaPartida(Partida partida,
            String aliasRojo, String aliasAzul) {
        Jugador rojo = this.buscarJugador(aliasRojo);
        Jugador azul = this.buscarJugador(aliasAzul);
        Partida p = new Partida(partida, true);
        p.setAzul(azul);
        p.setRojo(rojo);
        p.addObserver(this);
        return p;
    }

    public Partida nuevaReplica(int indice) {
        Partida p = new Partida(this.partidas.get(indice), false);
        return p;
    }

    public String jugadoresJson() {
        StringBuilder s = new StringBuilder();
        s.append("[").append(this.jugadores.get(0).toJson());
        this.jugadores.stream().forEach((j) -> {
            s.append(", ").append(j.toJson());
        });
        s.append("]");
        return s.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        Partida p = (Partida) o;
        if (p.isFinPartida()) {
            this.partidas.add(p);
            this.setChanged();
            this.notifyObservers();
            p.deleteObserver(this);
        }
    }

}
