package mapa;

import java.util.Random;

public class MapaGenerado extends Mapa {

    private static final Random aleatorio = new Random();

    public MapaGenerado(final int Ancho, final int Alto) {
        super(Ancho, Alto);
    }

    
    protected void generarMapa() {
        for (int y = 0; y < this.Alto; y++) {
            for (int x = 0; x < this.Ancho; x++) {
                this.cuadros[x + y * this.Ancho] = this.aleatorio.nextInt(5);
            }
        }
    }
}
