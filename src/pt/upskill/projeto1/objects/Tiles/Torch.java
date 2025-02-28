package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.rogue.utils.Position;

public class Torch implements Tile{
    private Position position;

    public Torch(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Torch";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    public void removeThis(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(this);
        gui.removeImage(this);
    }
}
