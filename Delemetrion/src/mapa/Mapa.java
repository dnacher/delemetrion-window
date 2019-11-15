package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
    protected int Ancho;

    protected int Alto;
    protected int[] cuadros;
    protected Cuadro[] cuadrosCatalogo;

    public Mapa(final int Ancho, final int Alto) {
        this.Ancho = Ancho;
        this.Alto = Alto;

        this.cuadros = new int[Ancho * Alto];
        generarMapa();
    }

    public Mapa(final String ruta) {
        cargarMapa(ruta);
        generarMapa();
    }

    protected void generarMapa() {

    }

    protected void cargarMapa(final String ruta) {

    }

    private void actualizar() {

    }

    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {
        pantalla.setDiferencia(compensacionX, compensacionY);

        int oeste = compensacionX >> 5; // /32
        int este = compensacionX + pantalla.getAncho() + Cuadro.Lado >> 5;
        int norte = compensacionY >> 5;
        int sur = compensacionY + pantalla.getAlto() + Cuadro.Lado >> 5;
        for (int y = norte; y < sur; y++) {
            for (int x = 0; x < este; x++) {
                // getCuadro(x, y).mostrar(x, y, pantalla);
                if (x < 0 || y < 0 || x >= this.Ancho || y >= this.Alto) {
                    Cuadro.VACIO.mostrar(x, y, pantalla);
                } else {
                    this.cuadrosCatalogo[x + y * this.Ancho].mostrar(x, y, pantalla);
                }
            }
        }
    }

    public Cuadro getCuadroCatalogo(final int posicion) {
        try {
            return this.cuadrosCatalogo[posicion];
        } catch (Exception name) {
            return this.cuadrosCatalogo[0];
        }

    }

    public int getAncho() {
        return this.Ancho;
    }

    public Cuadro getCuadro(final int x, final int y) {
        if (x < 0 || y < 0 || x >= this.Ancho || y >= this.Alto) {
            return Cuadro.VACIO;
        }
        switch (this.cuadros[x + y * this.Ancho]) {
            case 0:
                return Cuadro.ARENA;
            case 1:
                return Cuadro.AGUA;
            case 2:
                return Cuadro.AGUAPROFUNDA;
            case 3:
                return Cuadro.PASTO;
            case 4:
                return Cuadro.TIERRA;
            case 5:
                return Cuadro.CAMINO1;
            case 6:
                return Cuadro.CAMINO2;
            case 7:
                return Cuadro.CAMINO3;
            case 8:
                return Cuadro.CAMINO4;
            case 9:
                return Cuadro.CAMINO5;
            case 10:
                return Cuadro.CAMINO6;
            default:
                return Cuadro.VACIO;
        }

    }
}
