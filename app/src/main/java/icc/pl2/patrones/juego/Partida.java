package icc.pl2.patrones.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import icc.pl2.patrones.decorator.ReglasJuego;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.modelo.Serpiente;
import icc.pl2.patrones.modelo.Tablero;
import icc.pl2.patrones.patronadicional.Comida;
import icc.pl2.patrones.patronadicional.CreadorComida;
import icc.pl2.patrones.patronadicional.CreadorGuineo;
import icc.pl2.patrones.patronadicional.CreadorManzana;
import icc.pl2.patrones.patronadicional.CreadorSerpiente;
import icc.pl2.patrones.patronadicional.CreadorSerpienteIntermedia;
import icc.pl2.patrones.patronadicional.CreadorX2;
import icc.pl2.patrones.patronadicional.CreadorZapatilla;
import icc.pl2.patrones.state.EstadoPartida;
import icc.pl2.patrones.state.Preparado;
import icc.pl2.patrones.state.Terminado;

public class Partida {
    public static final int INTERVALO_MS = 150;

    private final Tablero tablero;
    private final ReglasJuego reglasBase;
    private final Random azar = new Random();
    private final CreadorComida creadorPorDefecto = new CreadorManzana();

    private CreadorSerpiente creadorSerpiente = new CreadorSerpienteIntermedia();
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

    public void iniciar() { estado.iniciar(this); }
    public void pausar() { estado.pausar(this); }
    public void reanudar() { estado.reanudar(this); }
    public void reiniciar() { estado.reiniciar(this); }
    public void mover() { estado.mover(this); }
    public void cambiarDireccion(Direccion d) { estado.cambiarDireccion(this, d); }
    public void ubicarComida(int x, int y, CreadorComida c) { estado.ubicarComida(this, x, y, c); }
    public void generarAleatoria(CreadorComida c) { estado.generarAleatoria(this, c); }

    public void restaurar() {
        serpiente = creadorSerpiente.crear(tablero);
        reglas = reglasBase;
        puntaje = 0;
        mensaje = "Partida lista. Presiona Iniciar.";
        comida = null;
        colocarAleatoria(creadorPorDefecto);
    }

    public boolean hayColision(Posicion destino) {
        return !tablero.dentro(destino) || serpiente.chocaCon(destino);
    }

    public void avanzarSerpiente() {
        reglas.tick(reglas.intervaloMs());
        reglas = reglas.limpiar();

        Posicion destino = serpiente.siguienteCabeza();
        boolean come = comida != null && comida.posicion().equals(destino);

        if (come) {
            Comida consumida = comida;
            serpiente.alComer();
            int puntos = reglas.puntos(consumida.puntosBase());
            puntaje += puntos;
            reglas = consumida.aplicarEfecto(reglas);
            mensaje = "Comiste " + consumida.nombre() + " (+" + puntos + ").";
            comida = null;
        }

        serpiente.avanzar(destino);

        if (come && !colocarAleatoria(creadorPorDefecto)) {
            terminar("Tablero completo: ¡ganaste!");
        }
    }

    public void terminar(String motivo) {
        mensaje = motivo;
        setEstado(new Terminado());
    }

    public boolean colocarComida(int x, int y, CreadorComida creador) {
        Posicion p = new Posicion(x, y);
        if (!tablero.dentro(p)) {
            return rechazar("Operación inválida: " + p + " está fuera del tablero.");
        }
        if (serpiente.ocupa(p)) {
            return rechazar("Operación inválida: " + p + " está ocupada por la serpiente.");
        }
        comida = creador.crear(p);
        mensaje = comida.nombre() + " ubicada en " + p + ".";
        return true;
    }

    public boolean colocarAleatoria(CreadorComida creador) {
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
            return false;
        }
        Posicion p = libres.get(azar.nextInt(libres.size()));
        comida = creador.crear(p);
        mensaje = comida.nombre() + " generada en " + p + ".";
        return true;
    }

    public void generarAleatoriaValida(CreadorComida creador) {
        if (!colocarAleatoria(creador)) {
            terminar("Tablero completo: no hay casillas libres.");
        }
    }

    public void setEstado(EstadoPartida nuevo) { this.estado = nuevo; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setCreadorSerpiente(CreadorSerpiente c) { this.creadorSerpiente = c; }

    public boolean rechazar(String motivo) {
        this.mensaje = motivo;
        return false;
    }

    public EstadoPartida estado() { return estado; }
    public Tablero tablero() { return tablero; }
    public Serpiente serpiente() { return serpiente; }
    public Comida comida() { return comida; }
    public ReglasJuego reglas() { return reglas; }
    public int puntaje() { return puntaje; }
    public String mensaje() { return mensaje; }
    public int intervaloMs() { return reglas.intervaloMs(); }

    public static CreadorComida[] creadoresDisponibles() {
        return new CreadorComida[] {
                new CreadorManzana(),
                new CreadorX2(),
                new CreadorZapatilla(),
                new CreadorGuineo()
        };
    }
}