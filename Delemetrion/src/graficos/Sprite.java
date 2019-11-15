package graficos;

public final class Sprite {
    private final int lado;

    private int x;
    private int y;

    public int[] pixeles;

    public int getLado() {
        return this.lado;
    }

    private HojaSprites hoja;

    // coleccion de Personaje
    public static final Sprite personajeParadoEste = new Sprite(32, 0, 6, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoEste1 = new Sprite(32, 3, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoEste2 = new Sprite(32, 3, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoEste3 = new Sprite(32, 3, 9, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaEste1 = new Sprite(32, 0, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaEste2 = new Sprite(32, 0, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaEste3 = new Sprite(32, 0, 9, 0, HojaSprites.jugador);

    public static final Sprite personajeParadoEspalda = new Sprite(32, 6, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoEspalda2 = new Sprite(32, 6, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoEspalda3 = new Sprite(32, 6, 9, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaEspalda1 = new Sprite(32, 1, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaEspalda2 = new Sprite(32, 1, 9, 0, HojaSprites.jugador);

    public static final Sprite personajeCaminaOeste1 = new Sprite(32, 2, 6, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaOeste2 = new Sprite(32, 2, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaOeste3 = new Sprite(32, 2, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoOeste = new Sprite(32, 2, 9, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoOeste1 = new Sprite(32, 4, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoOeste2 = new Sprite(32, 4, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeParadoOeste3 = new Sprite(32, 4, 9, 0, HojaSprites.jugador);

    public static final Sprite personajeCaminaSur1 = new Sprite(32, 5, 6, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaSur2 = new Sprite(32, 5, 7, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaSur3 = new Sprite(32, 5, 8, 0, HojaSprites.jugador);
    public static final Sprite personajeCaminaSur4 = new Sprite(32, 5, 9, 0, HojaSprites.jugador);
    // fin de personaje

    // coleccion de sprites

    // coleccion mapa
    public static final Sprite arena = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
    public static final Sprite tierra = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
    public static final Sprite pasto = new Sprite(32, 2, 0, 0, HojaSprites.desierto);
    public static final Sprite aguaProfunda = new Sprite(32, 3, 0, 0, HojaSprites.desierto);
    public static final Sprite agua = new Sprite(32, 4, 0, 0, HojaSprites.desierto);
    public static final Sprite Camino1 = new Sprite(32, 1, 1, 0, HojaSprites.desierto);
    public static final Sprite Camino2 = new Sprite(32, 3, 1, 0, HojaSprites.desierto);
    public static final Sprite Camino3 = new Sprite(32, 0, 2, 0, HojaSprites.desierto);
    public static final Sprite Camino4 = new Sprite(32, 2, 2, 0, HojaSprites.desierto);
    public static final Sprite Camino5 = new Sprite(32, 4, 2, 0, HojaSprites.desierto);
    public static final Sprite Camino6 = new Sprite(32, 1, 3, 0, HojaSprites.desierto);
    // cuadro vacio =negro
    public static final Sprite vacio = new Sprite(32, 0);

    // fin mapa
    // fin de coleccion

    public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
        this.lado = lado;
        this.pixeles = new int[this.lado * this.lado];
        this.x = columna * lado;
        this.y = fila * lado;
        this.hoja = hoja;

        cargarSprite(version);

    }

    // constructor para cuadros de colores solidos
    public Sprite(final int lado, final int color) {
        this.lado = lado;
        this.pixeles = new int[lado * lado];
        for (int i = 0; i < this.pixeles.length; i++) {
            this.pixeles[i] = color;
        }
    }

    public void cargarSprite(final int version) {
        if (version == 0) {
            cargaNormal();
        } else {
            cargaManipulada(version);
        }
    }

    public void cargaNormal() {
        for (int y = 0; y < this.lado; y++) {
            for (int x = 0; x < this.lado; x++) {
                this.pixeles[x + y * this.lado] = this.hoja.pixeles[x + this.x + (y + this.y) * this.hoja.getAncho()];
            }
        }
    }

    public void cargaManipulada(final int version) {
        int[] pixelesTemporales = iniciarPixelesTemporales();
        switch (version) {
            case 1:
                invertX(pixelesTemporales);
                break;
            case 2:
                invertY(pixelesTemporales);
                break;
            case 3:
                invertXY(pixelesTemporales);
                break;
            case 4:
                FI(pixelesTemporales);
                break;
            case 5:
                F(pixelesTemporales);
                break;
            case 6:
                FI_Yinvert(pixelesTemporales);
                break;
            case 7:
                F_Yinvert(pixelesTemporales);
                break;
            default:
                cargaNormal();
        }
    }

    public int[] iniciarPixelesTemporales() {
        int[] pixelesTemporales = new int[this.lado * this.lado];
        for (int y = 0; y < this.lado; y++) {
            for (int x = 0; x < this.lado; x++) {
                pixelesTemporales[x + y * this.lado] = this.hoja.pixeles[x + this.x + (y + this.y) * this.hoja.getAncho()];
            }
        }
        return pixelesTemporales;
    }

    public void invertX(final int[] pixelesTemporales) {
        int i = 0;
        for (int y = 0; y < this.lado; y++) {
            for (int x = this.lado - 1; x > -1; x--) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

    public void invertY(final int[] pixelesTemporales) {
        int i = 0;
        for (int y = this.lado - 1; y > -1; y--) {
            for (int x = 0; x < this.lado; x++) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

    public void invertXY(final int[] pixelesTemporales) {
        for (int i = 0; i < this.pixeles.length; i++) {
            this.pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
        }
    }

    private void FI(final int[] pixelesTemporales) {
        int i = 0;
        for (int x = this.lado - 1; x > -1; x--) {
            for (int y = 0; y < this.lado; y++) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

    private void F(final int[] pixelesTemporales) {
        int i = 0;
        for (int x = 0; x < this.lado; x++) {
            for (int y = this.lado - 1; y > -1; y--) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

    private void FI_Yinvert(final int[] pixelesTemporales) {
        int i = 0;
        for (int x = 0; x < this.lado; x++) {
            for (int y = 0; y < this.lado; y++) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

    private void F_Yinvert(final int[] pixelesTemporales) {
        int i = 0;
        for (int x = this.lado - 1; x > -1; x--) {
            for (int y = this.lado - 1; y > -1; y--) {
                this.pixeles[i] = pixelesTemporales[x + y * this.lado];
                i++;
            }
        }
    }

}
