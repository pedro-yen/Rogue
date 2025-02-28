package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.rogue.utils.Position;

public class RedGreen implements StatusBarInterface{
    private Position position;

    public RedGreen(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "RedGreen";
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
