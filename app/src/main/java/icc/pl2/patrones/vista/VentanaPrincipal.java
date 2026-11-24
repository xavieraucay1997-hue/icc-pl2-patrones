package icc.pl2.patrones.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import icc.pl2.patrones.decorator.ReglasBase;
import icc.pl2.patrones.juego.Partida;
import icc.pl2.patrones.modelo.Direccion;
import icc.pl2.patrones.modelo.Tablero;

public class VentanaPrincipal extends JFrame {
    private static final int COLUMNAS_INICIALES = 20;
    private static final int FILAS_INICIALES = 20;

    private final Partida partida;
    private final PanelTablero panelTablero;
    private final Timer reloj;

    private final JLabel lblEstado = new JLabel();
    private final JLabel lblPuntaje = new JLabel();
    private final JLabel lblMensaje = new JLabel();

    public VentanaPrincipal() {
        super("SnakeLab · Minijuego");
        partida = new Partida(
                new Tablero(COLUMNAS_INICIALES, FILAS_INICIALES),
                new ReglasBase(Partida.INTERVALO_MS));
        panelTablero = new PanelTablero(partida);
        reloj = new Timer(partida.intervaloMs(), e -> alTick());

        construirInterfaz();
        configurarTeclas();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        reloj.start();
        refrescar();
        panelTablero.requestFocusInWindow();
    }

    private void construirInterfaz() {
        JPanel lateral = new JPanel(new GridBagLayout());
        lateral.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        lateral.setPreferredSize(new Dimension(310, 0));

        lblMensaje.setPreferredSize(new Dimension(260, 60));
        lblMensaje.setVerticalAlignment(SwingConstants.TOP);

        JPanel[] grupos = { panelPartida(), panelPuntuacion(), panelMensaje() };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 8, 0);
        for (int i = 0; i < grupos.length; i++) {
            gbc.gridy = i;
            lateral.add(grupos[i], gbc);
        }
        gbc.gridy = grupos.length;
        gbc.weighty = 1;
        lateral.add(Box.createVerticalGlue(), gbc);

        JLabel pie = new JLabel(
                "Movimiento: ↑ ↓ ← →   |   Tablero: "
                        + partida.tablero().columnas()
                        + " × "
                        + partida.tablero().filas(),
                JLabel.CENTER);
        pie.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.add(panelTablero, BorderLayout.CENTER);
        izquierda.add(pie, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(izquierda, BorderLayout.CENTER);
        add(lateral, BorderLayout.EAST);
    }

    private JPanel panelPartida() {
        JPanel p = grupo("Partida");
        JPanel botones = new JPanel(new GridLayout(2, 2, 4, 4));
        botones.add(boton("Iniciar", () -> partida.iniciar()));
        botones.add(boton("Pausar", () -> partida.pausar()));
        botones.add(boton("Reanudar", () -> partida.reanudar()));
        botones.add(boton("Reiniciar", () -> partida.reiniciar()));
        p.add(botones);
        p.add(Box.createVerticalStrut(4));
        p.add(lblEstado);
        alinear(p);
        return p;
    }

    private JPanel panelPuntuacion() {
        JPanel p = grupo("Puntuación");
        p.add(lblPuntaje);
        alinear(p);
        return p;
    }

    private JPanel panelMensaje() {
        JPanel p = grupo("Mensaje");
        p.add(lblMensaje);
        alinear(p);
        return p;
    }

    private void alinear(JPanel grupo) {
        for (Component hijo : grupo.getComponents()) {
            ((JComponent) hijo).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    private JPanel grupo(String titulo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createTitledBorder(titulo));
        return p;
    }

    private JButton boton(String texto, Runnable accion) {
        JButton b = new JButton(texto);
        b.setFocusable(false);
        b.addActionListener(e -> {
            accion.run();
            refrescar();
            panelTablero.requestFocusInWindow();
        });
        return b;
    }

    private void configurarTeclas() {
        mapear(KeyEvent.VK_UP, Direccion.ARRIBA);
        mapear(KeyEvent.VK_DOWN, Direccion.ABAJO);
        mapear(KeyEvent.VK_LEFT, Direccion.IZQUIERDA);
        mapear(KeyEvent.VK_RIGHT, Direccion.DERECHA);
    }

    private void mapear(int tecla, Direccion direccion) {
        JComponent raiz = getRootPane();
        raiz.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla, 0), direccion.name());
        raiz.getActionMap().put(direccion.name(), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.cambiarDireccion(direccion);
            }
        });
    }

    private void alTick() {
        partida.mover();
        reloj.setDelay(partida.intervaloMs());
        refrescar();
    }

    private void refrescar() {
        lblEstado.setText("Estado actual: " + partida.estado().nombre());
        lblPuntaje.setText(partida.puntaje() + " puntos");
        lblMensaje.setText("<html><body style='width:240px'>" + partida.mensaje() + "</body></html>");
        panelTablero.repaint();
    }
}