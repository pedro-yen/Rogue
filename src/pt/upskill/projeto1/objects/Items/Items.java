package pt.upskill.projeto1.objects.Items;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;

public interface Items extends ImageTile, Serializable {



void setPosition(Position position);




void RemoveItem();
void AddPickUpScore();
int getPickUpScore();
//void removeFromHero();

}
