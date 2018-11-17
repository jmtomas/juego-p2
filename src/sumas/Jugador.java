/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Comparable<Jugador>, Serializable {

    private String nombre;
    private String alias;
    private int edad;
    private int puntaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Jugador() {
    }

    public Jugador(String nombre, String alias, int edad) {
        this.nombre = nombre;
        this.alias = alias;
        this.edad = edad;
        this.puntaje = 0;
    }

    public void ganarPartida(boolean esRepeticion) {
        if (!esRepeticion) {
            this.puntaje++;
        }
    }

    @Override
    public int compareTo(Jugador j) {
        return j.getPuntaje() - this.getPuntaje();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jugador) {
            return this.getAlias().equals(((Jugador) obj).getAlias());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.alias);
        hash = 41 * hash + this.edad;
        hash = 41 * hash + this.puntaje;
        return hash;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", alias=" + alias + ", edad=" + edad + ", puntaje=" + puntaje + '}';
    }
    
    public String toJson() {
        return "{nombre: " + nombre + ", alias: " + alias + ", edad: " + edad + ", puntaje: " + puntaje + '}';
    }
    
}
