package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.Enemy;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.rogue.utils.Position;

public class Treasure implements Items {
    private Position position;

    private int pickUpScore=10000; //????


    public Treasure(Position position){
        this.position=position;
    }

    @Override
    public String getName() {
        return "Treasure";
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

    }

    @Override
    public void AddPickUpScore() {
        // garantir que o score só é aplicado na primeira vez em que é apanhado
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();



        hero.addScore(this.pickUpScore);

        String meterPontos="";
            meterPontos = "  + "+ getPickUpScore()+ " points";
        gui.setStatus(hero.getStatusScoreString() + "  -  You picked up "+this.getName()+meterPontos);

        // TODO CONGRATS???

    }

    @Override
    public int getPickUpScore() {
        return this.pickUpScore;
    }


    public static void DropTreasure(Position position){
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        Treasure treasure = new Treasure(position);

        // add to room / list of items of the room and image tiles on gui

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(treasure);
        state.getRoomList().get(state.getCurrentRoomIndex()).getPickUpList().add(treasure);
        gui.addImage(treasure);
    }
}
