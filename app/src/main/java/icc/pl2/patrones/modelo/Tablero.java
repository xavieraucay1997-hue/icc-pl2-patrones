package icc.pl2.patrones.modelo;


public class Tablero {
    private final int columnas;
    private final int filas;

    public Tablero(int columnas, int filas) { this.columnas = columnas; this.filas = filas; }

    public int columnas() {
        return columnas;
    }
    public int filas() {
        return filas;
    }

    public boolean dentro(Posicion p) {
        return p.x() >= 0 && p.x() < columnas && p.y() >= 0 && p.y() < filas;
    }
}
