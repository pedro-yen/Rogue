package pt.upskill.projeto1.objects.Tiles.Arrow;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class ArrowLeft extends Arrow{
    private Position position;

    public ArrowLeft() {
        Hero hero = Hero.getInstance();
        this.position= hero.getPosition().plus(Direction.LEFT.asVector());
    }

    @Override
    public String getName() {
        return "arrow_Left";
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
    public void removeThis(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(this);
        gui.removeImage(this);
    }
}
