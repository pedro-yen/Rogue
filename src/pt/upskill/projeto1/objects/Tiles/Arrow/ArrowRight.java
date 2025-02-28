package pt.upskill.projeto1.objects.Tiles.Arrow;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class ArrowRight extends Arrow{
    private Position position;

    public ArrowRight() {
        Hero hero = Hero.getInstance();
        this.position= hero.getPosition().plus(Direction.RIGHT.asVector());
    }

    @Override
    public String getName() {
        return "arrow_Right";
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
