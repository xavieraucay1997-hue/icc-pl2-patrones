package icc.pl2.patrones.decorator;

import java.util.List;


public interface ReglasJuego {
    int puntos(int base);
    int intervaloMs();
   
    void tick(long ms);

    ReglasJuego limpiar();

    void renovar(Class<? extends ReglasJuego> tipo);
    boolean tiene(Class<? extends ReglasJuego> tipo);
    List<String> efectos();
}
