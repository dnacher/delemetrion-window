package entes;

import graficos.Pantalla;
import mapa.Mapa;

public abstract class Ente {
    protected int x;
    protected int y;
    private boolean eliminado = false;
    protected Mapa mapa;

    public void actualizar() {

    }

    public void mostrar(final Pantalla pantalla) {
    }

    public void eliminar() {
        this.eliminado = true;
    }

    public int getX() {
        return this.x;
    }

    public boolean isEliminado() {
        return this.eliminado;
    }

    public void setEliminado(final boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setX(final int x) {
        this.x += x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int y) {
        this.y += y;
    }
}