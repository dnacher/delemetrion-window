package entes.criaturas;

import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;
import control.Teclado;

public class Jugador extends Criatura {
    private Teclado teclado;
    private int animacion;
    private int velocidadMovimiento = 1;

    public Jugador(final Mapa mapa, final Teclado teclado, final Sprite sprite) {
        this.teclado = teclado;
        this.sprite = sprite;
        this.mapa = mapa;
    }

    public Jugador(final Mapa mapa, final Teclado teclado, final Sprite sprite, final int posicionX, final int posicionY) {
        this.teclado = teclado;
        this.x = posicionX;
        this.y = posicionY;
        this.sprite = sprite;
        this.mapa = mapa;
    }

    @Override
    public void actualizar() {
        int desplazamientoX = 0;
        int desplazamientoY = 0;
        if (this.animacion < 32767) {
            this.animacion++;
        } else {
            this.animacion = 0;
        }

        if (this.teclado.correr) {
            this.velocidadMovimiento = 2;
        } else {
            this.velocidadMovimiento = 1;
        }
        if (this.teclado.arriba) {
            desplazamientoY -= this.velocidadMovimiento;
        }
        if (this.teclado.abajo) {
            desplazamientoY += this.velocidadMovimiento;
        }
        if (this.teclado.izquierda) {
            desplazamientoX -= this.velocidadMovimiento;
        }
        if (this.teclado.derecha) {
            desplazamientoX += this.velocidadMovimiento;
        }

        if (desplazamientoX != 0 || desplazamientoY != 0) {
            mover(desplazamientoX, desplazamientoY);
            this.enMovimiento = true;
        } else {
            this.enMovimiento = false;
        }
        if (this.direccion == 's') {
            if (this.enMovimiento) {
                animacionCaminaSur();
            } else {
                animacionParadoEste();
            }
        }
        if (this.direccion == 'n') {
            if (this.enMovimiento) {
                animacionCaminaEspalda();
            } else {
                animacionParadoEspalda();
            }
        }
        if (this.direccion == 'e') {
            if (this.enMovimiento) {
                animacionCaminaEste();
            } else {
                animacionParadoEste();
            }
        }
        if (this.direccion == 'w') {
            if (this.enMovimiento) {
                animacionCaminaOeste();
            } else {
                animacionParadoOeste();
            }
        }
    }

    public void animacionParadoEste() {
        int resto = this.animacion % 60;
        if (resto > 10 && resto <= 30) {
            this.sprite = Sprite.personajeParadoEste;
        } else if (resto > 30) {
            this.sprite = Sprite.personajeParadoEste1;
        } else {
            this.sprite = Sprite.personajeParadoEste2;
        }
    }

    public void animacionParadoOeste() {
        int resto = this.animacion % 60;
        if (resto > 10 && resto <= 30) {
            this.sprite = Sprite.personajeParadoOeste1;
        } else if (resto > 30) {
            this.sprite = Sprite.personajeParadoOeste2;
        } else {
            this.sprite = Sprite.personajeParadoOeste3;
        }
    }

    public void animacionParadoEspalda() {
        int resto = this.animacion % 60;
        if (resto > 10 && resto <= 30) {
            this.sprite = Sprite.personajeParadoEspalda;
        } else if (resto > 30) {
            this.sprite = Sprite.personajeParadoEspalda2;
        } else {
            this.sprite = Sprite.personajeParadoEspalda3;
        }
    }

    public void animacionCaminaEspalda() {
        this.sprite = Sprite.personajeParadoEspalda;
        if (this.animacion % 60 > 30) {
            this.sprite = Sprite.personajeCaminaEspalda1;
        } else {
            this.sprite = Sprite.personajeCaminaEspalda2;
        }
    }

    public void animacionCaminaSur() {
        this.sprite = Sprite.personajeParadoEste;
        if (this.animacion % 60 > 10 && this.animacion % 60 <= 30) {
            this.sprite = Sprite.personajeCaminaSur1;
        } else if (this.animacion % 60 > 30) {
            this.sprite = Sprite.personajeCaminaSur3;
        } else {
            this.sprite = Sprite.personajeCaminaSur4;
        }
    }

    public void animacionCaminaEste() {
        int resto = this.animacion % 60;
        this.sprite = Sprite.personajeParadoEste;
        if (resto > 10 && resto <= 30) {
            this.sprite = Sprite.personajeCaminaEste2;
        } else if (resto > 30) {
            this.sprite = Sprite.personajeCaminaEste3;
        } else {
            this.sprite = Sprite.personajeCaminaEste1;
        }
    }

    public void animacionCaminaOeste() {
        int resto = this.animacion % 60;
        this.sprite = Sprite.personajeParadoOeste;
        if (resto > 10 && resto <= 30) {
            this.sprite = Sprite.personajeCaminaOeste1;
        } else if (resto > 30) {
            this.sprite = Sprite.personajeCaminaOeste2;
        } else {
            this.sprite = Sprite.personajeCaminaOeste3;
        }
    }

    @Override
    public void mostrar(final Pantalla pantalla) {
        pantalla.mostrarJugador(this.x, this.y, this);
    }
}
