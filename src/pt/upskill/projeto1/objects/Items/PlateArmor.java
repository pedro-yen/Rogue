package pt.upskill.projeto1.objects.Items;

import javafx.geometry.Pos;
import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public class PlateArmor implements Items{
    private Position position;
    private double mitigation=0.3;
    private boolean pickUp=false;

    private int pickUpScore=100;


    public PlateArmor(Position position){
        this.position=position;
    }

    @Override
    public String getName() {
        return "Plate_armor";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    public double getMitigation() {
        return this.mitigation;
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


    public static void DropArmor(Position position){
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        PlateArmor armor = new PlateArmor(position);

        // add to room / list of items of the room and image tiles on gui

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(armor);
        state.getRoomList().get(state.getCurrentRoomIndex()).getPickUpList().add(armor);
        gui.addImage(armor);
    }

}
