package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;

public interface StatusBarInterface extends ImageTile, Serializable {
    void setPosition(Position position);
}
