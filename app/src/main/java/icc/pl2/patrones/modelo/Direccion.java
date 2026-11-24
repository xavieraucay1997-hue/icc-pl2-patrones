package icc.pl2.patrones.modelo;

public enum Direccion {
    ARRIBA(0, -1), ABAJO(0, 1), IZQUIERDA(-1, 0), DERECHA(1, 0);

    private final int dx;
    private final int dy;

    Direccion(int dx, int dy) { this.dx = dx; this.dy = dy; }

    public int dx() {
        return dx;
    }
    public int dy() {
        return dy;
    }

    public Direccion opuesta() {
        return switch (this) {
            case ARRIBA -> ABAJO;
            case ABAJO -> ARRIBA;
            case IZQUIERDA -> DERECHA;
            case DERECHA -> IZQUIERDA;
        };
    }
}