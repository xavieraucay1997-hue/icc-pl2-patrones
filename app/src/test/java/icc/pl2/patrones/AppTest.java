package icc.pl2.patrones;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import icc.pl2.patrones.decorator.ReglasBase;
import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.modelo.Serpiente;
import icc.pl2.patrones.modelo.Tablero;

class AppTest {

    @Test
    void tableroValidaPosicionesDentroYFura() {
        Tablero tablero = new Tablero(20, 15);

        assertEquals(20, tablero.columnas());
        assertEquals(15, tablero.filas());
        assertTrue(tablero.dentro(new Posicion(0, 0)));
        assertTrue(tablero.dentro(new Posicion(19, 14)));
        assertFalse(tablero.dentro(new Posicion(-1, 0)));
        assertFalse(tablero.dentro(new Posicion(20, 14)));
    }

    @Test
    void serpienteEvitaGiroOpuestoYAvanza() {
        Serpiente serpiente = new Serpiente(
                List.of(new Posicion(5, 5), new Posicion(4, 5), new Posicion(3, 5)),
                Color.GREEN,
                1,
                1
        );

        serpiente.cambiarDireccion(Direccion.IZQUIERDA);
        assertEquals(new Posicion(6, 5), serpiente.siguienteCabeza());

        serpiente.cambiarDireccion(Direccion.ARRIBA);
        Posicion siguiente = serpiente.siguienteCabeza();
        serpiente.avanzar(siguiente);

        assertEquals(new Posicion(5, 4), serpiente.cabeza());
        assertEquals(3, serpiente.longitud());
    }

    @Test
    void partidaIniciaEnPreparadoYDetectaColisionConBorde() {
        Partida partida = new Partida(new Tablero(10, 10), new ReglasBase(Partida.INTERVALO_MS));

        assertEquals("Preparado", partida.estado().nombre());
        assertEquals(0, partida.puntaje());
        assertTrue(partida.hayColision(new Posicion(-1, 0)));
        assertFalse(partida.hayColision(new Posicion(5, 5)));
    }
}