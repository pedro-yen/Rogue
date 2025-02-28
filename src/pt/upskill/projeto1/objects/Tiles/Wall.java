package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.rogue.utils.Position;

public class Wall implements Tile {
    private Position position;

    public Wall(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Wall";
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
