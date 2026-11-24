package icc.pl2.patrones.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Posicion;
import icc.pl2.patrones.patronadicional.Comida;

public class PanelTablero extends JPanel {
    private static final int CELDA = 30;
    private static final Color FONDO_A = new Color(26, 43, 38);
    private static final Color FONDO_B = new Color(31, 50, 44);
    private static final Color COLOR_SERPIENTE = new Color(60, 180, 75);
    private static final Color COLOR_COMIDA = new Color(220, 50, 47);

    private final Partida partida;

    public PanelTablero(Partida partida) {
        this.partida = partida;
        setPreferredSize(new Dimension(
                partida.tablero().columnas() * CELDA,
                partida.tablero().filas() * CELDA));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int x = 0; x < partida.tablero().columnas(); x++) {
            for (int y = 0; y < partida.tablero().filas(); y++) {
                g2.setColor(((x + y) % 2 == 0) ? FONDO_A : FONDO_B);
                g2.fillRect(x * CELDA, y * CELDA, CELDA, CELDA);
            }
        }

        Comida comida = partida.comida();
        if (comida != null) {
            Color colorComida = comida.color() != null ? comida.color() : COLOR_COMIDA;
            g2.setColor(colorComida);
            g2.fillOval(
                    comida.posicion().x() * CELDA + 3,
                    comida.posicion().y() * CELDA + 3,
                    CELDA - 6,
                    CELDA - 6);
        }

        Color baseSerpiente = partida.serpiente().color() != null
                ? partida.serpiente().color()
                : COLOR_SERPIENTE;

        int indice = 0;
        for (Posicion p : partida.serpiente().segmentos()) {
            g2.setColor(indice == 0 ? baseSerpiente.brighter() : baseSerpiente);
            g2.fillRoundRect(
                    p.x() * CELDA + 2,
                    p.y() * CELDA + 2,
                    CELDA - 4,
                    CELDA - 4,
                    8,
                    8);
            indice++;
        }
    }
}