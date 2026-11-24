package icc.pl2.patrones.state;

import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;

public interface EstadoPartida {
    String nombre();

    void iniciar(Partida partida);

    void pausar(Partida partida);

    void reanudar(Partida partida);

    void reiniciar(Partida partida);

    void mover(Partida partida);

    void cambiarDireccion(Partida partida, Direccion direccion);
}