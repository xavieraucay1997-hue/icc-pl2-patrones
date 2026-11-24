package icc.pl2.patrones.juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import icc.pl2.patrones.decorator.ReglasJuego;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.modelo.Serpiente;
import icc.pl2.patrones.modelo.Tablero;
import icc.pl2.patrones.patronadicional.Comida;
import icc.pl2.patrones.patronadicional.ComidaFruta;
import icc.pl2.patrones.state.EstadoPartida;
import icc.pl2.patrones.state.Preparado;
import icc.pl2.patrones.state.Terminado;

public class Partida {
    public static final int PUNTOS_POR_COMIDA = 10;
    public static final int INTERVALO_MS = 150;
    private static final int LONGITUD_INICIAL = 3;
    private static final int FRUTAS_PARA_CRECER = 1;
    private static final int SEGMENTOS_POR_CRECIMIENTO = 1;

    private final Tablero tablero;
    private final ReglasJuego reglasBase;
    private final Random azar = new Random();

    private Serpiente serpiente;
    private Comida comida;
    private EstadoPartida estado;
    private ReglasJuego reglas;
    private int puntaje;
    private String mensaje;

    public Partida(Tablero tablero, ReglasJuego reglasBase) {
        this.tablero = tablero;
        this.reglasBase = reglasBase;
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
        int inicioX = tablero.columnas() / 2;
        int inicioY = tablero.filas() / 2;
        List<Posicion> inicial = new ArrayList<>();
        for (int i = 0; i < LONGITUD_INICIAL; i++) {
            inicial.add(new Posicion(inicioX - i, inicioY));
        }
        serpiente = new Serpiente(inicial, Color.GREEN, FRUTAS_PARA_CRECER, SEGMENTOS_POR_CRECIMIENTO);
        reglas = reglasBase;
        puntaje = 0;
        mensaje = "Partida lista. Presiona Iniciar.";
        generarComida();
    }

    public boolean hayColision(Posicion destino) {
        return !tablero.dentro(destino) || serpiente.chocaCon(destino);
    }

    public void avanzarSerpiente() {
        Posicion destino = serpiente.siguienteCabeza();
        boolean come = comida != null && comida.posicion().equals(destino);

        if (come) {
            serpiente.alComer();
            int puntos = comida.puntosBase();
            puntaje += puntos;
            reglas = comida.aplicarEfecto(reglas);
            mensaje = "Comiste " + comida.nombre() + " (+" + puntos + ").";
        }

        serpiente.avanzar(destino);

        if (come && !generarComida()) {
            terminar("Tablero completo: ¡ganaste!");
        }
    }

    public void terminar(String motivo) {
        mensaje = motivo;
        setEstado(new Terminado());
    }

    private boolean generarComida() {
        List<Posicion> libres = new ArrayList<>();
        for (int x = 0; x < tablero.columnas(); x++) {
            for (int y = 0; y < tablero.filas(); y++) {
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
        Posicion posAleatoria = libres.get(azar.nextInt(libres.size()));
        comida = new ComidaFruta(posAleatoria, "Fruta", PUNTOS_POR_COMIDA, Color.RED);
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

    public ReglasJuego reglas() {
        return reglas;
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