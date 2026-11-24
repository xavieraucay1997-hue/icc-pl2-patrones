package icc.pl2.patrones.patronadicional;

import java.awt.Color;

public class CreadorSerpienteDificil extends CreadorSerpiente {
    @Override
    protected Color color() {
        return new Color(0x8E44AD); 
    }
    @Override
    protected int frutasParaCrecer() {
        return 1; 
    }
    @Override
    protected int segmentosPorCrecimiento() {
        return 2; 
    }
    @Override
    public String nombre() {
        return "Difícil"; 
    }
}
