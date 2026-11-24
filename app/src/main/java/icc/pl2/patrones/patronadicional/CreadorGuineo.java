package icc.pl2.patrones.patronadicional;

import java.awt.Color;

import icc.pl2.patrones.modelo.Posicion;

public class CreadorGuineo extends CreadorComida {
    @Override
    public Comida crear(Posicion posicion) {
        return new ComidaFruta(posicion, "Guineo", 200, new Color(0xF5D547));
    }
    @Override
    public String nombre() { return "Guineo (200 puntos)"; 
        
    }
}
