package icc.pl2.patrones.state;

import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.patronadicional.CreadorComida;

public class Terminado implements EstadoPartida {
    @Override
    public String nombre() {
        return "TERMINADO";
    }

    @Override
    public void iniciar(Partida partida) {
        partida.rechazar("Operación inválida: la partida terminó, usa Reiniciar.");
    }

    @Override
    public void pausar(Partida partida) {
        partida.rechazar("Operación inválida: la partida terminó, usa Reiniciar.");
    }

    @Override
    public void reanudar(Partida partida) {
        partida.rechazar("Operación inválida: la partida terminó, usa Reiniciar.");
    }

    @Override
    public void reiniciar(Partida partida) {
        partida.restaurar();
        partida.setEstado(new Preparado());
    }

    @Override
    public void mover(Partida partida) {
    }

    @Override
    public void cambiarDireccion(Partida partida, Direccion direccion) {
    }

    @Override
    public void ubicarComida(Partida partida, int x, int y, CreadorComida creador) {
    }
}