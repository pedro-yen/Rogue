package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.rogue.utils.Position;

public class Green implements StatusBarInterface{
    private Position position;

    public Green(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Green";
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

