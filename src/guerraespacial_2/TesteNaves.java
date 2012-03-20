package guerraespacial_2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;

public class TesteNaves implements GameStateController {

    NaveJogador1 naveJogador1;
    ArrayList<Tiro> tirosJogador;
    
    public void load() {
        this.naveJogador1 = new NaveJogador1();
        this.tirosJogador = new ArrayList<Tiro>();
    }

    public void step(long timeElapsed) {
        this.naveJogador1.step(timeElapsed);

        for (Tiro tiro : this.tirosJogador) {
            tiro.step(timeElapsed);
        }

        this.lancaTirosJogador();
    }

    private void lancaTirosJogador() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.ESPACO) ){
            if(this.naveJogador1.podeAtirar()){
                this.tirosJogador.add( this.naveJogador1.getTiro() );
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        this.naveJogador1.draw(g);

        for (Tiro tiro : this.tirosJogador) {
            tiro.draw(g);
        }
    }    

    public void unload() {}
    public void start() {}
    public void stop() {}
}
