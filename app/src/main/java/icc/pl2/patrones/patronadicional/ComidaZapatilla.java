package icc.pl2.patrones.patronadicional;

import java.awt.Color;

import icc.pl2.patrones.decorator.DecoradorVelocidad;
import icc.pl2.patrones.decorator.ReglasJuego;
import icc.pl2.patrones.modelo.Posicion;

public class ComidaZapatilla extends Comida {
    public ComidaZapatilla(Posicion posicion) {
        super(posicion);
    }

    @Override
    public int puntosBase() {
        return 0; 
    }
    @Override
    public Color color() {
        return new Color(0x29B6F6); 
    }
    @Override
    public String nombre() {
        return "Zapatilla"; 
    }

    @Override
    public ReglasJuego aplicarEfecto(ReglasJuego reglas) {
        if (reglas.tiene(DecoradorVelocidad.class)) {
            reglas.renovar(DecoradorVelocidad.class);
            return reglas;
        }
        return new DecoradorVelocidad(reglas);
    }
}
