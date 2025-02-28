package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.rogue.utils.Position;

public class Gold implements Tile{

    private Position position;

    public Gold(Position position) {
        this.position=position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Gold";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
