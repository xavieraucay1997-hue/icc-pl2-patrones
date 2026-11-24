package icc.pl2.patrones.patronadicional;

import java.awt.Color;

public class CreadorSerpienteIntermedia extends CreadorSerpiente {
    @Override
    protected Color color() {
        return new Color(0x2F6FED); 
    }
    @Override
    protected int frutasParaCrecer() {
        return 1; 
    }
    @Override
    protected int segmentosPorCrecimiento() {
        return 1; 
    }
    @Override
    public String nombre() {
        return "Medio"; 
    }
}
