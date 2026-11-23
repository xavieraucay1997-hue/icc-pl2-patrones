package icc.pl2.patrones.decorator;

import java.util.ArrayList;
import java.util.List;

public class ReglasBase implements ReglasJuego {
    private final int intervaloMs;

    public ReglasBase(int intervaloMs) { this.intervaloMs = intervaloMs; }

    @Override
    public int puntos(int base) {
         return base; 
        }
    @Override
    public int intervaloMs() {
         return intervaloMs; 
        }
    @Override
    public void tick(long ms) {

    }
    @Override
    public ReglasJuego limpiar() {
         return this; 
        }
    @Override
    public void renovar(Class<? extends ReglasJuego> tipo) {
        
    }
    @Override
    public boolean tiene(Class<? extends ReglasJuego> tipo) {
         return false; 
        }
    @Override
    public List<String> efectos() {
         return new ArrayList<>(); 
        }
}
