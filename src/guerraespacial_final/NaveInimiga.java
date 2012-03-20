package guerraespacial_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Sprite;

public class NaveInimiga extends ObjetoComMovimento {

    Sprite sprite;
    int velocidade;
    int vida;

    public NaveInimiga(int x, int y, int velocidade) {

        this.vida = 40;
        this.velocidade = velocidade;
        this.x = x;
        this.y = y;
        try {            
            this.sprite = new Sprite("resources/nave_branca_mini.png", 1, 32, 32);
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }
    }

    public void step(long timeElapsed){

    }

    public void draw(Graphics g) {
        if(this.estaMorto()){
            return; //Não desenha nada;
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

    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, 32, 32);
    }

    public void persegueObjetoMaisProximo(GameObject objeto1, GameObject objeto2) {
        double distanciaObjeto1 = this.calculaDistanciaAte(objeto1);
        double distanciaObjeto2 = this.calculaDistanciaAte(objeto2);
        if(distanciaObjeto1 < distanciaObjeto2){
            this.persegue(objeto1);
        } else {
            this.persegue(objeto2);
        }
    }
    
    /**
     * http://www.mundoeducacao.com.br/matematica/distancia-entre-dois-pontos.htm
     * a distância entre dois pontos é a hipotenusa do triangulo formado entre os pontos.
     * a fórmula é:
     * distancia² = (x2-x1)² + (y2-y1)²
     */
    public double calculaDistanciaAte(GameObject objeto){
        int x1 = this.getX();
        int y1 = this.getY();        
        int x2 = objeto.getX();
        int y2 = objeto.getY();
        
        int x = x2 - x1;
        int y = y2 - y1;
        
        //Pow é a função para elevar um número a uma potencia.
        double distanciaAoQuadrado = Math.pow(x, 2) + Math.pow(y, 2);
        //Agora, faz a raiz da distância ao quadrado para ter a distância.
        //Math.sqrt é a fórmula que faz a raiz de um número
        double distancia = Math.sqrt(distanciaAoQuadrado);

        return distancia;
    }

    /**
     * Perseguir significa que o X e o Y desta nave deve sempre se aproximar
     * do x e do y do objeto perseguido.
     *
     * Para deixar o mais realista possível, faremos a perseguição também na diagonal.
     * Lembre que se o
     * x do perseguidor for Maior -> Perseguidor está à direita
     * x do perseguidor for Menor -> Perseguidor está à esquerda
     * y do perseguidor for Maior -> Perseguidor está abaixo
     * y do perseguidor for Menor -> Perseguidor está acima
     */
    public void persegue(GameObject objeto){
        int xPerseguido = objeto.getX();
        int yPerseguido = objeto.getY();

        if(this.x < xPerseguido && this.y < yPerseguido){
            //NaveMini Está à esquerda e acima
            //Nave Perseguida está abaixo e à direita.
            this.moveDireitaBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(4);

        } else if(this.x < xPerseguido && this.y > yPerseguido){
            //NaveMini Está à esquerda e abaixo
            //Nave Perseguida está acima e à direita.
            this.moveDireitaCima(this.velocidade);
            this.sprite.setCurrAnimFrame(2);

        } else if(this.x > xPerseguido && this.y < yPerseguido){
            //NaveMini Está à direita e acima
            //Nave Perseguida está abaixo e à esquerda.
            this.moveEsquerdaBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(6);

        } else if(this.x > xPerseguido && this.y > yPerseguido){
            //NaveMini Está à direita e abaixo
            //Nave Perseguida está acima e à esquerda.
            this.moveEsquerdaCima(this.velocidade);
            this.sprite.setCurrAnimFrame(8);

        } if(this.x < xPerseguido && this.y == yPerseguido){            
            //Nave Perseguida está a direita.
            this.moveDireita(this.velocidade);
            this.sprite.setCurrAnimFrame(3);

        } else if(this.x > xPerseguido && this.y == yPerseguido){
            //Nave Perseguida está a esquerda.
            this.moveEsquerda(this.velocidade);
            this.sprite.setCurrAnimFrame(7);

        } else if(this.x == xPerseguido && this.y < yPerseguido){
            //Nave Perseguida está abaixo
            this.moveBaixo(this.velocidade);
            this.sprite.setCurrAnimFrame(5);

        } else if(this.x == xPerseguido && this.y > yPerseguido){
            //Nave Perseguida está acima
            this.moveCima(this.velocidade);
            this.sprite.setCurrAnimFrame(1);
        }

    }


}
