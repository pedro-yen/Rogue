package pt.upskill.projeto1.objects.Items.Weapon;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.rogue.utils.Position;

public class Hammer extends Weapon {
    private Position position;
    private int attackModifier=+10;
    private boolean pickUp=false;
    private int pickUpScore=100;

    public Hammer(Position position){
        this.position=position;
    }

    @Override
    public String getName() {
        return "Hammer";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public void RemoveItem() {

    }

    public int getPickUpScore() {
        return pickUpScore;
    }

    @Override
    public void AddPickUpScore() {
        Hero hero = Hero.getInstance();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        // garantir que o score só é aplicado na primeira vez em que é apanhado
        String meterPontos = "";
        if (this.pickUp==false){
            hero.addScore(getPickUpScore());
            this.pickUp=true;
            meterPontos="  + "+getPickUpScore()+" points";
        }
        gui.setStatus(hero.getStatusScoreString() + "  -  You picked up "+this.getName()+meterPontos);

    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }
}
