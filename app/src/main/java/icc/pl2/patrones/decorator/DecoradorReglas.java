package icc.pl2.patrones.decorator;

import java.util.ArrayList;
import java.util.List;

/** Decorador abstracto: envuelve otras reglas, delega y agrega una duración. */
public abstract class DecoradorReglas implements ReglasJuego {
    protected ReglasJuego envuelto;
    private final long duracionMs;
    private long restanteMs;

    protected DecoradorReglas(ReglasJuego envuelto, long duracionMs) {
        this.envuelto = envuelto;
        this.duracionMs = duracionMs;
        this.restanteMs = duracionMs;
    }

    @Override 
    public int puntos(int base) { 
        return envuelto.puntos(base); 
    }
    @Override
    public int intervaloMs() { 
        return envuelto.intervaloMs();
     }

    @Override public void tick(long ms) {
        envuelto.tick(ms);
        restanteMs -= ms;
    }

    @Override public ReglasJuego limpiar() {
        envuelto = envuelto.limpiar();
        return restanteMs > 0 ? this : envuelto;
    }

    @Override public void renovar(Class<? extends ReglasJuego> tipo) {
        if (getClass().equals(tipo)) restanteMs = duracionMs;
        envuelto.renovar(tipo);
    }

    @Override public boolean tiene(Class<? extends ReglasJuego> tipo) {
        return getClass().equals(tipo) || envuelto.tiene(tipo);
    }

    @Override public List<String> efectos() {
        List<String> lista = new ArrayList<>(envuelto.efectos());
        lista.add(nombre() + " (" + ((restanteMs + 999) / 1000) + " s)");
        return lista;
    }

    protected abstract String nombre();
}
