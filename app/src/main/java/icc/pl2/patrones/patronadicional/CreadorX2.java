package icc.pl2.patrones.patronadicional;

import icc.pl2.patrones.modelo.Posicion;

public class CreadorX2 extends CreadorComida {
    @Override
    public Comida crear(Posicion posicion) {
        return new ComidaX2(posicion); 
    }
    @Override
    public String nombre() {
        return "Manzana dorada (puntos x2)"; 
    }
}
