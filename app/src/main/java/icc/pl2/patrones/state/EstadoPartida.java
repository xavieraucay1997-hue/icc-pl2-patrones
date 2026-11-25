package icc.pl2.patrones.state;

import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.patronadicional.CreadorComida;

public interface EstadoPartida {
    String nombre();

    void iniciar(Partida partida);

    void pausar(Partida partida);

    void reanudar(Partida partida);

    void reiniciar(Partida partida);

    void mover(Partida partida);

    void cambiarDireccion(Partida partida, Direccion direccion);

    default void ubicarComida(Partida partida, int x, int y, CreadorComida creador) {
        partida.colocarComida(x, y, creador);
    }

    default void generarAleatoria(Partida partida, CreadorComida creador) {
        partida.generarAleatoriaValida(creador);
    }
}