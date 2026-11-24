package icc.pl2.patrones.patronadicional;

import icc.pl2.patrones.modelo.Posicion;

import java.awt.Color;

public class ComidaFruta extends Comida {
    private final String nombre;
    private final int puntos;
    private final Color color;

    public ComidaFruta(Posicion posicion, String nombre, int puntos, Color color) {
        super(posicion);
        this.nombre = nombre;
        this.puntos = puntos;
        this.color = color;
    }

    @Override
    public int puntosBase() {
        return puntos;
    }
    @Override
    public Color color() {
        return color; 
    }
    @Override
    public String nombre() {
        return nombre;
    }
}
