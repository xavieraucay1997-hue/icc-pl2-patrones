package icc.pl2.patrones.state;


import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;

public class Preparado implements EstadoPartida {
    @Override
    public String nombre() {
        return "PREPARADO";
    }

    @Override
    public void iniciar(Partida partida) {
        partida.setMensaje("¡Partida iniciada!");
        partida.setEstado(new Jugando());
    }

    @Override
    public void pausar(Partida partida) {
        partida.rechazar("Operación inválida: primero inicia la partida.");
    }

    @Override
    public void reanudar(Partida partida) {
        partida.rechazar("Operación inválida: no hay una partida pausada.");
    }

    @Override
    public void reiniciar(Partida partida) {
        partida.restaurar();
    }

    @Override
    public void mover(Partida partida) {
    }

    @Override
    public void cambiarDireccion(Partida partida, Direccion direccion) {
        partida.serpiente().cambiarDireccion(direccion);
    }
}