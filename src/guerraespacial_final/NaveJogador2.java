package guerraespacial_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Sprite;

public class NaveJogador2 extends ObjetoComMovimento {

    Sprite sprite;
    int vida;

    //Só pode lançar um tiro após o outro com um intervalo de 10 frames.
    int controleTiros = 0;
    int framesContoleTiros = 5;

    public NaveJogador2() {

        try {            
            this.sprite = new Sprite("resources/nave_verde.png", 4, 64, 64);
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }

        this.vida = 100;
        this.x = 270;
        this.y = 272;
    }

    public void step(long timeElapsed){
        Keyboard teclado = GameEngine.getInstance().getKeyboard();
        if(this.estaMorto()){
            if( teclado.keyDown( Keys.R ) ){
                this.vida += 100;
            }
            return; //Não Calcula
        }
        
        this.controleTiros++;

        

        if( teclado.keyDown( Keys.A ) && teclado.keyDown( Keys.W) ){
            this.sprite.setCurrAnimFrame(8);
            this.moveEsquerdaCima(12);

        } else if ( teclado.keyDown( Keys.A ) && teclado.keyDown( Keys.S) ){
            this.sprite.setCurrAnimFrame(6);
            this.moveEsquerdaBaixo(12);

        } else if ( teclado.keyDown( Keys.D ) && teclado.keyDown( Keys.W) ){
            this.sprite.setCurrAnimFrame(2);
            this.moveDireitaCima(12);

        } else if ( teclado.keyDown( Keys.D ) && teclado.keyDown( Keys.S) ){
            this.sprite.setCurrAnimFrame(4);
            this.moveDireitaBaixo(12);

        } else if( teclado.keyDown( Keys.D ) ){
            this.sprite.setCurrAnimFrame(3);
            this.moveDireita(12);

        } else if( teclado.keyDown( Keys.A ) ){
            this.sprite.setCurrAnimFrame(7);
            this.moveEsquerda(12);

        } else if( teclado.keyDown( Keys.W ) ){
            this.sprite.setCurrAnimFrame(1);
            this.moveCima(12);

        } else if( teclado.keyDown( Keys.S ) ){
            this.sprite.setCurrAnimFrame(5);
            this.moveBaixo(12);

        }
    }

    public void draw(Graphics g) {
        if(this.estaMorto()){
            g.setColor(Color.red);
            g.drawString("Player 2 morreu", this.x+5, this.y-15);
            g.drawString("Pressione R para continuar", this.x+5, this.y);
            return;
        }
        g.setColor(Color.white);
        g.drawString(this.vida+"", this.x+5, this.y-15);

        this.sprite.draw(g, this.x, this.y);        
    }

    public void perdeVida(int numPontos){
        this.vida -= numPontos;
    }

    public boolean estaMorto(){
        return (this.vida <= 0);
    }

     public boolean podeAtirar(){
        return (this.controleTiros > this.framesContoleTiros);
    }

    public Tiro getTiro(){
        //O tiro pode sair de qualquer um dos oito lados.
        //E o x e y inicial do tiro podem sempre ser diferentes

        int xTiro = this.x;
        int yTiro = this.y;
        int tamanhoNave = 64;
        int metadeNave = tamanhoNave / 2;

        switch(this.direcao){
            case DIREITA:
                xTiro += tamanhoNave;
                yTiro += metadeNave;
                break;
            case ESQUERDA:
                yTiro += metadeNave;
                break;
            case CIMA:
                xTiro += metadeNave;
                break;
            case BAIXO:
                xTiro += metadeNave;
                yTiro += tamanhoNave;
                break;
            case DIREITA_CIMA:
                xTiro += tamanhoNave;
                break;
            case DIREITA_BAIXO:
                xTiro += tamanhoNave;
                yTiro += tamanhoNave;
                break;
            case ESQUERDA_CIMA:
                break;
            case ESQUERDA_BAIXO:
                yTiro += tamanhoNave;
                break;
        }

        this.controleTiros = 0;
        return new Tiro(xTiro, yTiro, this.direcao);
    }

    public Rectangle getRetangulo(){
        return new Rectangle(this.x+4, this.y+4, 56, 56);
    }

     public boolean temColisao(NaveInimiga nave){
        return this.getRetangulo().intersects( nave.getRetangulo() );
    }
}
