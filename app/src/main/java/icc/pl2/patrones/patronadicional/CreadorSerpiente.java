package icc.pl2.patrones.patronadicional;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.modelo.Serpiente;
import icc.pl2.patrones.modelo.Tablero;

public abstract class CreadorSerpiente {
    protected abstract Color color();
    protected abstract int frutasParaCrecer();
    protected abstract int segmentosPorCrecimiento();
    public abstract String nombre();

    public Serpiente crear(Tablero tablero) {
        return new Serpiente(cuerpoInicial(tablero), color(), frutasParaCrecer(), segmentosPorCrecimiento());
    }

    private List<Posicion> cuerpoInicial(Tablero t) {
        int cx = t.columnas() / 2;
        int cy = t.filas() / 2;
        return Arrays.asList(new Posicion(cx, cy), new Posicion(cx - 1, cy), new Posicion(cx - 2, cy));
    }
}
