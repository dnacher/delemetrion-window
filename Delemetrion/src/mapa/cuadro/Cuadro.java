package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
    public int x;
    public int y;
    public Sprite sprite;
    private boolean solido;
    public static final int Lado = 32;
    // coleccion de cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.vacio, true);
    public static final Cuadro ARENA = new Cuadro(Sprite.arena, true);
    public static final Cuadro TIERRA = new Cuadro(Sprite.tierra);
    public static final Cuadro PASTO = new Cuadro(Sprite.pasto);
    public static final Cuadro AGUAPROFUNDA = new Cuadro(Sprite.aguaProfunda, true);
    public static final Cuadro AGUA = new Cuadro(Sprite.agua, true);
    public static final Cuadro CAMINO1 = new Cuadro(Sprite.Camino1);
    public static final Cuadro CAMINO2 = new Cuadro(Sprite.Camino2);
    public static final Cuadro CAMINO3 = new Cuadro(Sprite.Camino3);
    public static final Cuadro CAMINO4 = new Cuadro(Sprite.Camino4);
    public static final Cuadro CAMINO5 = new Cuadro(Sprite.Camino5);
    public static final Cuadro CAMINO6 = new Cuadro(Sprite.Camino6);

    // fin de coleccion

    public Cuadro(final Sprite sprite) {
        this.sprite = sprite;
        this.solido = false;
    }

    public Cuadro(final Sprite sprite, final boolean solido) {
        this.sprite = sprite;
        this.solido = solido;
    }

    public void mostrar(final int x, final int y, final Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5, y << 5, this);
    }

    /*
     * public boolean solido() { return false; }
     */

    public boolean esSolido() {
        return this.solido;
    }
}
