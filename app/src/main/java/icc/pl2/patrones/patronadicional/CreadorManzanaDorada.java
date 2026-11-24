package icc.pl2.patrones.patronadicional;

import java.awt.Color;

import icc.pl2.patrones.modelo.Posicion;

public class CreadorManzanaDorada extends CreadorComida {
    @Override
    public Comida crear(Posicion posicion) {
        return new ComidaFruta(posicion, "Manzana dorada", 1000, new Color(0xD4A017));
    }
    @Override
    public String nombre() {
        return "Manzana dorada (1000 puntos)";
    }
}
