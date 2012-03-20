package guerraespacial_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Sprite;

public class NaveJogador1 extends ObjetoComMovimento {

    Sprite sprite;
    int vida;

    public NaveJogador1() {

        try {            
            this.sprite = new Sprite("resources/nave.png", 4, 64, 64);                        
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }

        this.x = 370;
        this.y = 272;
        this.vida = 100;
    }

    public void step(long timeElapsed){
        Keyboard teclado = GameEngine.getInstance().getKeyboard();                   

        if( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.CIMA) ){
            this.sprite.setCurrAnimFrame(8);
            this.moveEsquerdaCima(12);

        } else if ( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.BAIXO) ){
            this.sprite.setCurrAnimFrame(6);
            this.moveEsquerdaBaixo(12);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.CIMA) ){
            this.sprite.setCurrAnimFrame(2);
            this.moveDireitaCima(12);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.BAIXO) ){
            this.sprite.setCurrAnimFrame(4);
            this.moveDireitaBaixo(12);

        } else if( teclado.keyDown( Keys.DIREITA ) ){
            this.sprite.setCurrAnimFrame(3);
            this.moveDireita(12);

        } else if( teclado.keyDown( Keys.ESQUERDA ) ){
            this.sprite.setCurrAnimFrame(7);
            this.moveEsquerda(12);

        } else if( teclado.keyDown( Keys.CIMA ) ){
            this.sprite.setCurrAnimFrame(1);
            this.moveCima(12);

        } else if( teclado.keyDown( Keys.BAIXO ) ){
            this.sprite.setCurrAnimFrame(5);
            this.moveBaixo(12);

        }
    }

    public void draw(Graphics g) {        
        g.setColor(Color.white);
        g.drawString(this.vida+"", this.x+5, this.y-15);

        this.sprite.draw(g, this.x, this.y);        
    }
       
    public Rectangle getRetangulo(){
        return new Rectangle(this.x+4, this.y+4, 56, 56);
    }
}
