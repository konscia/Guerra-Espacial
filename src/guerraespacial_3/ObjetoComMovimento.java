package guerraespacial_3;

import java.awt.Rectangle;
import javaPlay.GameObject;

public abstract class ObjetoComMovimento extends GameObject {

    protected Direcao direcao = Direcao.CIMA;

    public void moveDireita(int valor) {
        direcao = Direcao.DIREITA;
        this.x += valor;
    }   

    public void moveEsquerda(int valor) {
        direcao = Direcao.ESQUERDA;
        this.x -= valor;
    }

    public void moveCima(int valor) {
        direcao = Direcao.CIMA;
        this.y -= valor;
    }

    public void moveBaixo(int valor) {
        direcao = Direcao.BAIXO;
        this.y += valor;
    }

    private int calculaDistanciaDiagonal(int valor){
        /**
        valor² = x² + y²;
        x = y, então
        valor² = 2x²;
        x² = raiz (valor² / 2)
         */
        return (int)Math.floor( Math.sqrt( Math.pow(valor, 2) / 2));
    }
    public void moveDireitaCima(int valor) {
        this.direcao = Direcao.DIREITA_CIMA;
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x += distancia;
        this.y -= distancia;
    }

    public void moveDireitaBaixo(int valor) {
        this.direcao = Direcao.DIREITA_BAIXO;
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x += distancia;
        this.y += distancia;
    }

    public void moveEsquerdaCima(int valor) {
        this.direcao = Direcao.ESQUERDA_CIMA;
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x -= distancia;
        this.y -= distancia;
    }

    public void moveEsquerdaBaixo(int valor) {
        this.direcao = Direcao.ESQUERDA_BAIXO;
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x -= distancia;
        this.y += distancia;
    }
}
