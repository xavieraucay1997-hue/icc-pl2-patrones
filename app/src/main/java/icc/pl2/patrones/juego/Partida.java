package icc.pl2.patrones.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import icc.pl2.patrones.patronadicional.Comida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.modelo.Serpiente;
import icc.pl2.patrones.modelo.Tablero;
import icc.pl2.patrones.state.Pausado;
import icc.pl2.patrones.state.Preparado;
import icc.pl2.patrones.state.Terminado ;

public class Partida {
    public static final int PUNTOS_POR_COMIDA = 10;
    public static final int INTERVALO_MS = 150;
    private static final int LONGITUD_INICIAL = 3;

    private final Tablero tablero;
    private final Random azar = new Random();

    private Serpiente serpiente;
    private Comida comida;
    private EstadoPartida estado;
    private int puntaje;
    private String mensaje;

    public Partida(Tablero tablero) {
        this.tablero = tablero;
        restaurar();
        this.estado = new Preparado();
    }

    public void iniciar() {
        estado.iniciar(this);
    }

    public void pausar() {
        estado.pausar(this);
    }

    public void reanudar() {
        estado.reanudar(this);
    }

    public void reiniciar() {
        estado.reiniciar(this);
    }

    public void mover() {
        estado.mover(this);
    }

    public void cambiarDireccion(Direccion direccion) {
        estado.cambiarDireccion(this, direccion);
    }

    public void restaurar() {
        Posicion cabeza = new Posicion(tablero.ancho() / 2, tablero.alto() / 2);
        serpiente = new Serpiente(cabeza, Direccion.DERECHA, LONGITUD_INICIAL);
        puntaje = 0;
        mensaje = "Partida lista. Presiona Iniciar.";
        generarComida();
    }

    public boolean hayColision(Posicion destino) {
        return !tablero.contiene(destino) || serpiente.colisionaConCuerpo(destino);
    }

    public void avanzarSerpiente() {
        Posicion destino = serpiente.siguienteCabeza();
        boolean come = comida != null && comida.posicion().equals(destino);

        serpiente.avanzar(come);

        if (come) {
            puntaje += comida.puntos();
            mensaje = "Comiste (+" + comida.puntos() + ").";
            if (!generarComida()) {
                terminar("Tablero completo: ¡ganaste!");
            }
        }
    }

    public void terminar(String motivo) {
        mensaje = motivo;
        setEstado(new Terminado());
    }

    private boolean generarComida() {
        List<Posicion> libres = new ArrayList<>();
        for (int x = 0; x < tablero.ancho(); x++) {
            for (int y = 0; y < tablero.alto(); y++) {
                Posicion p = new Posicion(x, y);
                if (!serpiente.ocupa(p)) {
                    libres.add(p);
                }
            }
        }
        if (libres.isEmpty()) {
            comida = null;
            return false;
        }
        comida = new Comida(libres.get(azar.nextInt(libres.size())), PUNTOS_POR_COMIDA);
        return true;
    }

    public void setEstado(EstadoPartida nuevo) {
        this.estado = nuevo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean rechazar(String motivo) {
        this.mensaje = motivo;
        return false;
    }

    public EstadoPartida estado() {
        return estado;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Serpiente serpiente() {
        return serpiente;
    }

    public Comida comida() {
        return comida;
    }

    public int puntaje() {
        return puntaje;
    }

    public String mensaje() {
        return mensaje;
    }

    public int intervaloMs() {
        return INTERVALO_MS;
    }
}