package main;

import entes.criaturas.Jugador;
import graficos.Pantalla;
import graficos.Sprite;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import mapa.Mapa;
import mapa.MapaCargado;
import control.Teclado;

public class Main extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static final String NOMBRE = "Delemetrion";
    private static int aps = 0;
    private static int FPS = 0;
    private static volatile boolean enFuncionamiento = false;

    private static JFrame Ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;
    private static Mapa mapa;
    private static Jugador jugador;
    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

    // constructor
    public Main() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);
        // mapa = new MapaGenerado(128, 128);
        teclado = new Teclado();
        addKeyListener(teclado);
        mapa = new MapaCargado("/Mapas/Mapa.png");
        jugador = new Jugador(mapa, teclado, Sprite.personajeParadoEste, 225, 225);
        Ventana = new JFrame(NOMBRE);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ventana.setResizable(false);
        Ventana.setLayout(new BorderLayout());
        Ventana.add(this, BorderLayout.CENTER);
        Ventana.pack();
        Ventana.setLocationRelativeTo(null);
        Ventana.setVisible(true);
    }

    public static void main(final String[] args) {
        Main main = new Main();
        main.iniciar();

    }

    public synchronized void iniciar() {
        enFuncionamiento = true;
        thread = new Thread(this, "Graficos");
        thread.start();
    }

    public synchronized void detener() {
        enFuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void actualizar() {
        aps++;
        teclado.actualizar();
        jugador.actualizar();

    }

    public void mostrar() {

        BufferStrategy estrategia = getBufferStrategy();
        if (estrategia == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();
        mapa.mostrar(jugador.getX() - pantalla.getAncho() / 2 + jugador.getSprite().getLado() / 2, jugador.getY() - pantalla.getAlto() / 2
                + jugador.getSprite().getLado() / 2, pantalla);
        jugador.mostrar(pantalla);
        // hace lo mismo que el for y es menos costoso
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        /*
         * for (int i=0; i< pixeles.length; i++){ pixeles[i]= pantalla.pixeles[i]; }
         */
        // dibuja el buffer
        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.drawString("X: " + jugador.getX(), 10, 20);
        g.drawString("Y: " + jugador.getY(), 10, 30);
        g.dispose();
        estrategia.show();
        FPS++;
    }

    @Override
    public void run() {
        final int NS_X_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 80;
        final double NS_X_ACTUALIZACION = NS_X_SEGUNDO / APS_OBJETIVO;
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;

        // pantalla activa
        requestFocus();

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NS_X_ACTUALIZACION;
            while (delta >= 1) {
                actualizar();
                delta--;
            }

            mostrar();
            if (System.nanoTime() - referenciaContador > NS_X_SEGUNDO) {
                Ventana.setTitle(NOMBRE + " || APS: " + aps + " || fps: " + FPS);
                aps = 0;
                FPS = 0;
                referenciaContador = System.nanoTime();

            }

        }
    }
}
