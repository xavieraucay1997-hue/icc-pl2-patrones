package icc.pl2.patrones.modelo;

import java.util.Objects;

public final class Posicion {
    private final int x;
    private final int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y; 

    }

    public int x(){
        return x;
    }

    public int y() {
        return y;
    }
     
    @Override 

    public boolean equals(Object o ){
        if(this == o){
            return true;

        } if(!(o instanceof Posicion)) {
            return false;
            
        }
        Posicion p = (Posicion) o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
}