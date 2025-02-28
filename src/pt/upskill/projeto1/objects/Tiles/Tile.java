package pt.upskill.projeto1.objects.Tiles;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;

public interface Tile extends ImageTile, Serializable {
    void setPosition(Position position);
}
