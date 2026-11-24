package icc.pl2.patrones.modelo;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Serpiente {
    private final Deque<Posicion> cuerpo = new ArrayDeque<>();
    private final int segmentosPorCrecimiento;
    private final int frutasParaCrecer;
    private Direccion direccion = Direccion.DERECHA;
    private Direccion solicitada = Direccion.DERECHA;
    private int frutasAcumuladas = 0;
    private int pendientes = 0;
    private Color color;

    public Serpiente(List<Posicion> inicial, Color color, int frutasParaCrecer, int segmentosPorCrecimiento) {
        cuerpo.addAll(inicial);
        this.color = color;
        this.segmentosPorCrecimiento = segmentosPorCrecimiento;
        this.frutasParaCrecer = frutasParaCrecer;
    }

    public Posicion cabeza() {
        return cuerpo.peekFirst(); 
    }
    public Iterable<Posicion> segmentos() {
        return cuerpo; 
    }
    public int longitud() {
        return cuerpo.size(); 
    }
    public boolean ocupa(Posicion p) {
        return cuerpo.contains(p); 
    }
    public Color color() {
        return color; 
    }
    public void setColor(Color color) {
        this.color = color; 
    }


    public void cambiarDireccion(Direccion nueva) {
        if (nueva != direccion.opuesta()) solicitada = nueva;
    }

    public Posicion siguienteCabeza() {
        Posicion c = cabeza();
        return new Posicion(c.x() + solicitada.dx(), c.y() + solicitada.dy());
    }

    
    public boolean chocaCon(Posicion p) {
        boolean colaSeMueve = pendientes == 0;
        int i = 0;
        int n = cuerpo.size();
        for (Posicion s : cuerpo) {
            i++;
            if (colaSeMueve && i == n) break;
            if (s.equals(p)) return true;
        }
        return false;
    }

    public void alComer() {
        frutasAcumuladas++;
        if (frutasAcumuladas >= frutasParaCrecer) {
            frutasAcumuladas = 0;
            pendientes += segmentosPorCrecimiento;
        }
    }

    public void avanzar(Posicion nuevaCabeza) {
        direccion = solicitada;
        cuerpo.addFirst(nuevaCabeza);
        if (pendientes > 0) pendientes--; else cuerpo.removeLast();
    }
}
