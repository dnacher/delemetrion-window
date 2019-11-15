package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    // coleccion de hojas de sprites
    public static HojaSprites desierto = new HojaSprites("/Texturas/desierto.png", 320, 320);
    public static HojaSprites jugador = new HojaSprites("/Characters/characters.png", 320, 320);

    // fin de la coleccion

    public HojaSprites(final String ruta, final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.pixeles = new int[ancho * alto];
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, this.pixeles, 0, ancho);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

}
