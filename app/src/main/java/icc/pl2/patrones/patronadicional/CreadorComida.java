package icc.pl2.patrones.patronadicional;

import icc.pl2.patrones.modelo.Posicion;

public abstract class CreadorComida {
    
    public abstract Comida crear(Posicion posicion);
    public abstract String nombre();

    @Override
    public String toString() {
        return nombre();
    }
}