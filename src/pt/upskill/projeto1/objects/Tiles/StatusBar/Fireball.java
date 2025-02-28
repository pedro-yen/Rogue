package pt.upskill.projeto1.objects.Tiles.StatusBar;

import pt.upskill.projeto1.game.Engine;
import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.Enemy;
import pt.upskill.projeto1.objects.Tiles.Door.Door;
import pt.upskill.projeto1.objects.Tiles.Wall;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.List;

public class Fireball implements FireTile {
    private Position position;
    private int damage=100;

    public Fireball(Position position) {
        this.position=position;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean validateImpact() {
        GameState state = GameState.getINSTANCE();

        //lista de image tiles
        List<ImageTile> list = state.getRoomList().get(state.getCurrentRoomIndex()).getTiles();

        for (ImageTile i: list){
            if (i.getPosition().equals(this.position)){
                if (i instanceof Enemy){
                    //se encontrar inimigo este perde tudo o seu HP
                    ((Enemy) i).looseHp(this.damage);
                    return false;

                } else if (i instanceof Wall){
                    return false;

                } else if (i instanceof Door){
                    return false;

                }
            }
        }
        return true;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }
}
