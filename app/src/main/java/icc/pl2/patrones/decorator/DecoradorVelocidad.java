package icc.pl2.patrones.decorator;

public class DecoradorVelocidad extends DecoradorReglas {
    public static final long DURACION_MS = 8_000;

    public DecoradorVelocidad(ReglasJuego envuelto) { super(envuelto, DURACION_MS); }

    @Override
    public int intervaloMs() {
        return Math.max(40, envuelto.intervaloMs() / 2); 
    }
    @Override
    protected String nombre() {
        return "Velocidad"; 
    }
}
