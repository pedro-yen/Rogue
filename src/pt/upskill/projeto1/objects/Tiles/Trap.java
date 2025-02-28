package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.rogue.utils.Position;

public class Trap implements Tile{
    private Position position;
    private int Damage=5;
    private boolean notActivated = true;
    public Trap(Position position) {
        this.position=position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        String name;
        if (notActivated){
            name="Floor";
        } else {
            name="Trap";
        }
        return name;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setNotActivated(boolean notActivated) {
        this.notActivated = notActivated;
    }

    public int getDamage() {
        setNotActivated(false);
        return this.Damage;
    }
}