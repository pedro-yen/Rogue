package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.game.StatusBar;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public class Key implements Items {
    private Position position;

    private int keyId;
    private boolean pickUp=false;
    private int pickUpScore=200;
    public Key (){

    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public void RemoveItem() {
        //remover do hero
        // status bar e
        Hero hero = Hero.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();
        hero.removeItemOnArray(this);
    }

    public int getPickUpScore() {
        return pickUpScore;
    }



    @Override
    public void AddPickUpScore() {
        // garantir que o score só é aplicado na primeira vez em que é apanhado
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();
        String meterPontos = "";
        if (this.pickUp==false){
            hero.addScore(getPickUpScore());
            meterPontos="  + "+getPickUpScore()+" points";
            this.pickUp=true;
        }
        gui.setStatus(hero.getStatusScoreString() + "  -  You picked up Key"+this.getKeyId()+meterPontos);
    }

}
