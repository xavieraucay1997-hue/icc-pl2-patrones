package icc.pl2.patrones.patronadicional;

import icc.pl2.patrones.modelo.Posicion;

public class CreadorZapatilla extends CreadorComida {
    @Override
    public Comida crear(Posicion posicion) {
         return new ComidaZapatilla(posicion); 
        }
    @Override
    public String nombre() {
         return "Zapatilla (velocidad)"; 
        }
}
