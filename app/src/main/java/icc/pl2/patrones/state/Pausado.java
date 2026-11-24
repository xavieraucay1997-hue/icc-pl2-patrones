package icc.pl2.patrones.state;

import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;

public class Pausado implements EstadoPartida {
    @Override
    public String nombre() {
        return "PAUSADO";
    }

    @Override
    public void iniciar(Partida partida) {
        partida.rechazar("Operación inválida: usa Reanudar para continuar.");
    }

    @Override
    public void pausar(Partida partida) {
        partida.rechazar("Operación inválida: la partida ya está pausada.");
    }

    @Override
    public void reanudar(Partida partida) {
        partida.setMensaje("Partida reanudada.");
        partida.setEstado(new Jugando());
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
}