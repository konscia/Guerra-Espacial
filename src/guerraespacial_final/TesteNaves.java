package guerraespacial_final;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;

public class TesteNaves implements GameStateController {

    NaveJogador1 jogador1;
    NaveJogador2 jogador2;
    ArrayList<NaveInimiga> naves;
    ArrayList<Tiro> tirosJogador;
    ArrayList<Explosao> explosoes;

    public void load() {
        this.jogador1 = new NaveJogador1();
        this.jogador2 = new NaveJogador2();
        this.naves = new ArrayList<NaveInimiga>();

        //Adiciona para a lista uma nave em cada canto.
        this.naves.add(new NaveInimiga(0, 0, 4));
        this.naves.add(new NaveInimiga(1800, 0, 6));
        this.naves.add(new NaveInimiga(800, 600, 3));
        this.naves.add(new NaveInimiga(0, 1600, 7));
        this.naves.add(new NaveInimiga(0, 300, 4));
        this.naves.add(new NaveInimiga(400, 0, 6));
        this.naves.add(new NaveInimiga(400, 1600, 3));
        this.naves.add(new NaveInimiga(3200, 300, 8));
        
        this.tirosJogador = new ArrayList<Tiro>();
        this.explosoes = new ArrayList<Explosao>();

    }

    public void step(long timeElapsed) {
        this.jogador1.step(timeElapsed);
        this.jogador2.step(timeElapsed);

        for (NaveInimiga nave : this.naves) {
            if(this.jogador1.estaMorto() && this.jogador2.estaMorto()){
                JOptionPane.showMessageDialog(null, "Você perdeu.");
                System.exit(1);
            } else if( this.jogador1.estaMorto() ) {
                nave.persegue(this.jogador2);
            } else if( this.jogador2.estaMorto() ){
                nave.persegue(this.jogador1);
            } else {
                nave.persegueObjetoMaisProximo(this.jogador1, this.jogador2);
            }
        }

        for (Tiro tiro : this.tirosJogador) {
            tiro.step(timeElapsed);
        }

        for (Explosao explosao : this.explosoes) {
            explosao.step(timeElapsed);
        }
        
        this.lancaTirosJogador();
        this.verificaColisaoNavesInimigas();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        this.jogador1.draw(g);
        this.jogador2.draw(g);

        //Desenha cada nave
        for (NaveInimiga nave : this.naves) {
            nave.draw(g);
        }
         
        for (Tiro tiro : this.tirosJogador) {
            tiro.draw(g);
        }

        for (Explosao explosao : this.explosoes) {
            explosao.draw(g);
        }
    }

    private void lancaTirosJogador() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.ESPACO) ){
            if(this.jogador1.podeAtirar()){
                this.tirosJogador.add( this.jogador1.getTiro() );
            }            
        }

        if(teclado.keyDown( Keys.E ) ){
            if(this.jogador2.podeAtirar()){
                this.tirosJogador.add( this.jogador2.getTiro() );
            }
        }
    }

    private void verificaColisaoNavesInimigas() {
        
        for(NaveInimiga nave : this.naves){
            if(nave.estaMorto()){
                continue; //pula para próxima
            }
            //Colisão com tiros
            this.verificaColisaoComTiros(nave);
            //Colisão com jogadores
            this.verificaColisaoComJogadores(nave);
        }
    }

    private void verificaColisaoComTiros(NaveInimiga nave){
        for(Tiro tiro : this.tirosJogador){                        
            if(tiro.temColisao(nave.getRetangulo())){                
                nave.perdeVida(10);
                this.explosoes.add( new ExplosaoFraca(nave.getX(), nave.getY()) );
            }
        }
    }

    private void verificaColisaoComJogadores(NaveInimiga nave){
        if(this.jogador1.temColisao(nave) && !this.jogador1.estaMorto()){
            this.jogador1.perdeVida(50);
            //Kamikazeeeeeeeeeeeee
            nave.perdeVida(40);
            this.explosoes.add( new ExplosaoForte(nave.getX(), nave.getY()) );
        }

         if(this.jogador2.temColisao(nave) && !this.jogador2.estaMorto() ){
            this.jogador2.perdeVida(50);
            //Kamikazeeeeeeeeeeeee
            nave.perdeVida(40);
            this.explosoes.add( new ExplosaoForte(nave.getX(), nave.getY()) );
        }
    }

    public void unload() {}
    public void start() {}
    public void stop() {}
}
