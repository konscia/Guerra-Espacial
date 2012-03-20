
package guerraespacial_3;

import javaPlay.Sprite;

public class ExplosaoForte extends Explosao {

    public ExplosaoForte(int x, int y){        
        super(x, y);
        
        try {            
            this.sprite = new Sprite("resources/explosao_forte.png", 16, 64, 62);
        } catch (Exception ex) {
            System.out.println("Falha ao carregar imagem: "+ex.getMessage());
        }        
    }

}
