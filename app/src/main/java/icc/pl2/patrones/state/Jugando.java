package icc.pl2.patrones.state;


import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Posicion;

public class Jugando implements EstadoPartida {
    @Override
    public String nombre() {
        return "JUGANDO";
    }

    @Override
    public void iniciar(Partida partida) {
        partida.rechazar("Operación inválida: la partida ya está en curso.");
    }

    @Override
    public void pausar(Partida partida) {
        partida.setMensaje("Partida en pausa.");
        partida.setEstado(new Pausado());
    }

    @Override
    public void reanudar(Partida partida) {
        partida.rechazar("Operación inválida: la partida no está pausada.");
    }

    @Override
    public void reiniciar(Partida partida) {
        partida.restaurar();
        partida.setEstado(new Preparado());
    }

    @Override
    public void mover(Partida partida) {
        Posicion destino = partida.serpiente().siguienteCabeza();
        if (partida.hayColision(destino)) {
            partida.terminar("¡Colisión! Fin de la partida.");
            return;
        }
        partida.avanzarSerpiente();
    }

    @Override
    public void cambiarDireccion(Partida partida, Direccion direccion) {
        partida.serpiente().cambiarDireccion(direccion);
    }
}