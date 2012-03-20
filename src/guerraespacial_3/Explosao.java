
package guerraespacial_3;

import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Sprite;

public class Explosao extends GameObject {

    protected Sprite sprite;
    private int frame;
    private int timeElapsedInMiliseconds;

    public Explosao(int x, int y){
        this.x = x;
        this.y = y;
        this.frame = 0;        
    }

    public void step(long timeElapsed) {
        if(this.frame >= 16){
            return; //Parou animação
        }

        this.timeElapsedInMiliseconds += timeElapsed;
        //Exemplo de animação baseada em tempo.
        //Muda de frame a cada 100 milisegundos.
        //A animação dura em torno de 1 segundos e 6
        if(this.timeElapsedInMiliseconds > 100){
            this.frame = (this.frame + 1);
            this.sprite.setCurrAnimFrame(this.frame);
            this.timeElapsedInMiliseconds -= 100;
        }
    }

    @Override
    public void draw(Graphics g) {
        this.sprite.draw(g, this.x, this.y);
    }



}
