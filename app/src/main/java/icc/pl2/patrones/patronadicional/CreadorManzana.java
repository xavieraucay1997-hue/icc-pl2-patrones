package icc.pl2.patrones.patronadicional;

import java.awt.Color;

import icc.pl2.patrones.modelo.Posicion;

public class CreadorManzana extends CreadorComida {
    @Override
    public Comida crear(Posicion posicion) {
        return new ComidaFruta(posicion, "Manzana", 100, new Color(0xD64535));
    }
    
    @Override
    public String nombre() {
         return "Manzana (100 puntos)";
        }
}
