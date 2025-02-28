package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;
import java.util.List;

public interface Character extends ImageTile, Serializable {
    public Position getPosition();
    public int getHp();
    public int getAttack();
    public void setAttack(int newAttack);
    public void looseHp(int Attack);


    public void setPosition(Position position);


}
