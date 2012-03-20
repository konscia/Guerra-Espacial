package guerraespacial_3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;

public class TesteNaves implements GameStateController {

    NaveJogador1 naveJogador1;
    ArrayList<Tiro> tirosJogador;
    NaveInimiga naveInimiga;
    ArrayList<Explosao> explosoes;
    
    public void load() {
        this.naveJogador1 = new NaveJogador1();
        this.tirosJogador = new ArrayList<Tiro>();
        this.naveInimiga = new NaveInimiga(800, 600, 7);
        this.explosoes = new ArrayList<Explosao>();
    }

    public void step(long timeElapsed) {
        this.naveJogador1.step(timeElapsed);
        this.naveInimiga.persegue(this.naveJogador1);

        for (Tiro tiro : this.tirosJogador) {
            tiro.step(timeElapsed);
        }

        for (Explosao explosao : this.explosoes) {
            explosao.step(timeElapsed);
        }

        this.lancaTirosJogador();
        this.verificaColisaoNavesInimigas();
    }

    private void lancaTirosJogador() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.ESPACO) ){
            if(this.naveJogador1.podeAtirar()){
                this.tirosJogador.add( this.naveJogador1.getTiro() );
            }
        }
    }

    private void verificaColisaoNavesInimigas() {
        if(this.naveInimiga.estaMorto()){
            return;
        }

        for(Tiro tiro : this.tirosJogador){
            if(tiro.temColisao(this.naveInimiga.getRetangulo())){
                this.naveInimiga.perdeVida(10);
                int xExplosao = this.naveInimiga.getX();
                int yExplosao = this.naveInimiga.getY();
                this.explosoes.add( new ExplosaoFraca(xExplosao, yExplosao) );
            }
        }
        
    }



    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        this.naveJogador1.draw(g);
        this.naveInimiga.draw(g);

        for (Tiro tiro : this.tirosJogador) {
            tiro.draw(g);
        }

        for (Explosao explosao : this.explosoes) {
            explosao.draw(g);
        }
    }    

    public void unload() {}
    public void start() {}
    public void stop() {}
}
