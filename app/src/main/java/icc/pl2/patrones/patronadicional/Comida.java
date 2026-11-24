package icc.pl2.patrones.patronadicional;

import icc.pl2.patrones.decorator.ReglasJuego;
import icc.pl2.patrones.modelo.Posicion;

import java.awt.Color;

public abstract class Comida {
    private final Posicion posicion;

    protected Comida(Posicion posicion) { this.posicion = posicion; }

    public Posicion posicion() { return posicion; }
    public abstract int puntosBase();
    public abstract Color color();
    public abstract String nombre();

    /** Por defecto la comida no cambia las reglas. Las especiales las envuelven con un decorador. */
    public ReglasJuego aplicarEfecto(ReglasJuego reglas) { return reglas; }
}
