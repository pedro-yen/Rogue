package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.rogue.utils.Position;

public class Red implements StatusBarInterface{
    private Position position;

    public Red(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Red";
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
