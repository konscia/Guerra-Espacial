package guerraespacial_1;

import java.awt.Rectangle;
import javaPlay.GameObject;

public abstract class ObjetoComMovimento extends GameObject {

    public void moveDireita(int valor) {        
        this.x += valor;
    }   

    public void moveEsquerda(int valor) {        
        this.x -= valor;
    }

    public void moveCima(int valor) {
        this.y -= valor;
    }

    public void moveBaixo(int valor) {        
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
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x += distancia;
        this.y -= distancia;
    }

    public void moveDireitaBaixo(int valor) {
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x += distancia;
        this.y += distancia;
    }

    public void moveEsquerdaCima(int valor) {        
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x -= distancia;
        this.y -= distancia;
    }

    public void moveEsquerdaBaixo(int valor) {        
        int distancia = this.calculaDistanciaDiagonal(valor);
        this.x -= distancia;
        this.y += distancia;
    }
}
