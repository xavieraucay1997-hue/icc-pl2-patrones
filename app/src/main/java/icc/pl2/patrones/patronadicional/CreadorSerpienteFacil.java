package icc.pl2.patrones.patronadicional;
import java.awt.Color;

public class CreadorSerpienteFacil extends CreadorSerpiente {
    @Override
    protected Color color() {
        return new Color(0x3FA65B); 
    }
    @Override
    protected int frutasParaCrecer() {
        return 2; 
    }
    @Override
    protected int segmentosPorCrecimiento() {
        return 1; 
    }
    @Override
    public String nombre() {
        return "Fácil"; 
    }
}
