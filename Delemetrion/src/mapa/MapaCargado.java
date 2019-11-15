package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

    private int[] pixeles;

    public MapaCargado(final String ruta) {
        super(ruta);
    }

    
    protected void cargarMapa(final String ruta) {
        try {
            BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));
            this.Ancho = imagen.getWidth();
            this.Alto = imagen.getHeight();
            this.cuadrosCatalogo = new Cuadro[this.Ancho * this.Alto];
            this.pixeles = new int[this.Ancho * this.Alto];
            imagen.getRGB(0, 0, this.Ancho, this.Alto, this.pixeles, 0, this.Ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void generarMapa() {
        for (int i = 0; i < this.pixeles.length; i++) {
            switch (this.pixeles[i]) {
                case 0xfffff200:
                    this.cuadrosCatalogo[i] = Cuadro.ARENA;
                    continue;
                case 0xff99d9ea:
                    this.cuadrosCatalogo[i] = Cuadro.AGUA;
                    continue;
                case 0xff3f48cc:
                    this.cuadrosCatalogo[i] = Cuadro.AGUAPROFUNDA;
                    continue;
                case 0xff22b14c:
                    this.cuadrosCatalogo[i] = Cuadro.PASTO;
                    continue;
                case 0xffb97a57:
                    this.cuadrosCatalogo[i] = Cuadro.TIERRA;
                    continue;
                case 0xffff7f27:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO1;
                    continue;
                case 0xffa349a4:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO2;
                    continue;
                case 0xffed1c24:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO3;
                    continue;
                case 0xffffffff:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO4;
                    continue;
                case 0xffffaec9:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO5;
                    continue;
                case 0xff7f7f7f:
                    this.cuadrosCatalogo[i] = Cuadro.CAMINO6;
                    continue;
                default:
                    this.cuadrosCatalogo[i] = Cuadro.VACIO;
                    continue;
            }
        }
    }
}