package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.rogue.utils.Position;

public class Floor implements Tile {

    private Position position;
    private final double tileName = Math.random();

    public Floor(Position position) {
        this.position=position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        String name;
        if (this.tileName<0.2){
            name = "Grass";
        } else{
            name = "Floor";
        }
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
