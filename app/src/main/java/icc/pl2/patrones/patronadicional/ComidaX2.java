package icc.pl2.patrones.patronadicional;

import java.awt.Color;

import icc.pl2.patrones.decorator.DecoradorPuntuacionDoble;
import icc.pl2.patrones.decorator.ReglasJuego;
import icc.pl2.patrones.modelo.Posicion;

public class ComidaX2 extends Comida {
    public ComidaX2(Posicion posicion) { super(posicion); }

    @Override
    public int puntosBase() {
        return 0; 
        
    }
    @Override
    public Color color() {
        return new Color(0xE0A800); 
        
    }
    @Override
    public String nombre() {
        return "Manzana dorada"; 
        
    }

    @Override
    public ReglasJuego aplicarEfecto(ReglasJuego reglas) {
        if (reglas.tiene(DecoradorPuntuacionDoble.class)) {
            reglas.renovar(DecoradorPuntuacionDoble.class);
            return reglas;
        }
        return new DecoradorPuntuacionDoble(reglas);
    }
}
