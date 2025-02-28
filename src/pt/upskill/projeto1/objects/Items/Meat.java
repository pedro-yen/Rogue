package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public class Meat implements Items{

    private int hpBoost = 40;
    private boolean pickUp=false;

    private int pickUpScore=50;


    public Meat(Position position){
        this.position=position;
    }
    private Position position;
    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    public int getHpBoost() {
        return hpBoost;
    }

    @Override
    public void RemoveItem() {

    }

    @Override
    public void AddPickUpScore() {
        // garantir que o score só é aplicado na primeira vez em que é apanhado
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();

        String meterPontos="";
        if (this.pickUp==false){
            hero.addScore(getPickUpScore());
            this.pickUp=true;
            meterPontos = "  + "+ getPickUpScore()+ " points";

        }
        gui.setStatus(hero.getStatusScoreString() + "  -  You picked up "+this.getName()+meterPontos);

    }

    @Override
    public int getPickUpScore() {
        return this.pickUpScore;
    }
}
