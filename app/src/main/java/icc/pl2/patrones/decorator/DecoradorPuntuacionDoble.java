package icc.pl2.patrones.decorator;

public class DecoradorPuntuacionDoble extends DecoradorReglas {

    public static final long DURACION_MS = 10_000;

    public DecoradorPuntuacionDoble(ReglasJuego envuelto) { super(envuelto, DURACION_MS);
        
    }

    @Override
    public int puntos(int base) {
        return envuelto.puntos(base) * 2;
        }
    @Override
    protected String nombre() {
        return "Puntos x2";
    }
}
