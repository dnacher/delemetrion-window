package entes.criaturas;

import entes.Ente;
import graficos.Pantalla;
import graficos.Sprite;

public abstract class Criatura extends Ente {
    protected Sprite sprite;
    protected char direccion = 'e';
    protected boolean enMovimiento = false;

    @Override
    public void actualizar() {
    }

    @Override
    public void mostrar(final Pantalla pantalla) {
    }

    public void mover(final int desplazamientoX, final int desplazamientoY) {
        if (desplazamientoX > 0) {
            this.direccion = 'e';
        }
        if (desplazamientoX < 0) {
            this.direccion = 'w';
        }
        if (desplazamientoY > 0) {
            this.direccion = 's';
        }
        if (desplazamientoY < 0) {
            this.direccion = 'n';
        }

        if (!isEliminado()) {
            if (!enColision(desplazamientoX, 0)) {
                setX(desplazamientoX);
            }
            if (!enColision(0, desplazamientoY)) {
                setY(desplazamientoY);
            }
        }

    }

    private boolean enColision(final int desplazamientoX, final int desplazamientoY) {
        boolean colision = false;
        int posicionX = this.x + desplazamientoX;
        int posicionY = this.y + desplazamientoY;
        int margenIzquierdo = -4;
        int margenDerecho = 28;
        int margenSuperior = -2;
        int margenInferior = 30;
        int bordeIzquierdo = (posicionX + margenDerecho) / this.sprite.getLado();
        int bordeDerecho = (posicionX + margenIzquierdo) / this.sprite.getLado();
        int bordeSuperior = (posicionY + margenInferior) / this.sprite.getLado();
        int bordeInferior = (posicionY + margenSuperior) / this.sprite.getLado();
        if (this.mapa.getCuadroCatalogo(bordeIzquierdo + bordeSuperior * this.mapa.getAncho()).esSolido()) {
            colision = true;
        }
        if (this.mapa.getCuadroCatalogo(bordeDerecho + bordeSuperior * this.mapa.getAncho()).esSolido()) {
            colision = true;
        }
        if (this.mapa.getCuadroCatalogo(bordeIzquierdo + bordeInferior * this.mapa.getAncho()).esSolido()) {
            colision = true;
        }
        if (this.mapa.getCuadroCatalogo(bordeDerecho + bordeInferior * this.mapa.getAncho()).esSolido()) {
            colision = true;
        }
        return colision;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

}
