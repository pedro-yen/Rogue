package pt.upskill.projeto1.objects.Items.Weapon;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.PlateArmor;
import pt.upskill.projeto1.rogue.utils.Position;

public class Sword extends Weapon{
    private Position position;
    private int attackModifier=+5;
    private boolean pickUp=false;
    private int pickUpScore = 200;

    public Sword(Position position){
        this.position=position;
    }

    @Override
    public String getName() {
        return "Sword";
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
        String meterPontos="";


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

    public static void DropSword(Position position){
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        Sword sword = new Sword(position);

        // add to room / list of items of the room and image tiles on gui

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(sword);
        state.getRoomList().get(state.getCurrentRoomIndex()).getPickUpList().add(sword);
        gui.addImage(sword);
    }


}
