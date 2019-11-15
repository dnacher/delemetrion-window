package graficos;

import mapa.cuadro.Cuadro;
import entes.criaturas.Jugador;

public final class Pantalla {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;
    private int diferenciaX;
    private int diferenciaY;

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.pixeles = new int[alto * ancho];

    }

    public void limpiar() {
        for (int i = 0; i < this.pixeles.length; i++) {
            this.pixeles[i] = 0;
        }
    }

    public void mostrarCuadro(int compensacionX, int compensacionY, final Cuadro cuadro) {
        compensacionX -= this.diferenciaX;
        compensacionY -= this.diferenciaY;

        for (int y = 0; y < cuadro.sprite.getLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < cuadro.sprite.getLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -cuadro.sprite.getLado() || posicionX >= this.ancho || posicionY < 0 || posicionY >= this.alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                this.pixeles[posicionX + posicionY * this.ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, final Jugador jugador) {
        compensacionX -= this.diferenciaX;
        compensacionY -= this.diferenciaY;

        for (int y = 0; y < jugador.getSprite().getLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.getSprite().getLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.getSprite().getLado() || posicionX >= this.ancho || posicionY < 0 || posicionY >= this.alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                int colorPixelJugador = jugador.getSprite().pixeles[x + y * jugador.getSprite().getLado()];
                if (colorPixelJugador != 0xffb5e61d) {
                    this.pixeles[posicionX + posicionY * this.ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void setDiferencia(final int diferenciaX, final int diferenciaY) {
        this.diferenciaX = diferenciaX;
        this.diferenciaY = diferenciaY;
    }

}
