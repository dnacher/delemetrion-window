package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

    private final static int numeroTeclas = 120;
    private final boolean[] teclas = new boolean[numeroTeclas];

    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean correr;

    public void actualizar() {
        this.arriba = this.teclas[KeyEvent.VK_W];
        this.abajo = this.teclas[KeyEvent.VK_S];
        this.izquierda = this.teclas[KeyEvent.VK_A];
        this.derecha = this.teclas[KeyEvent.VK_D];
        this.correr = this.teclas[KeyEvent.VK_SHIFT];
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.teclas[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {

    }
}
