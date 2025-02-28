package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.objects.Tiles.Tile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Black implements StatusBarInterface {
    private Position position;

    public Black(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Black";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }
}
