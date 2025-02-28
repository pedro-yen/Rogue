package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.Enemy;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Fireball;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;
import pt.upskill.projeto1.rogue.utils.Verify;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static pt.upskill.projeto1.game.KeyEvent.*;

import static pt.upskill.projeto1.rogue.utils.Verify.*;

public class Engine implements Serializable {

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }


    public void init(){
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();

        gui.setEngine(this);
        gui.go();
        MainMenu();

        while (true){
            gui.update();
        }
    }



    public void notify(int keyPressed){
        Hero hero = Hero.getInstance();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();


        if (keyPressed == KeyEvent.VK_DOWN){
            if (hero.isHeroThrowFireball()){
                hero.setDirection(Direction.DOWN);
                hero.ThrowFireball();
                hero.setHeroThrowFireball(false);
                removeArrows();
            } else {
                hero.addScore(-1);
                gui.setStatus(hero.getStatusScoreString());

                if (legalMovePlayer(hero.getPosition().plus(Direction.DOWN.asVector()))){
                    hero.setPosition(hero.getPosition().plus(Direction.DOWN.asVector()));
                }

                hero.setDirection(Direction.DOWN);

                enemyMove();
            }




        }
        if (keyPressed == KeyEvent.VK_UP){
            if (hero.isHeroThrowFireball()){
                hero.setDirection(Direction.UP);
                hero.ThrowFireball();
                hero.setHeroThrowFireball(false);
                removeArrows();
            }else {
                hero.addScore(-1);
                gui.setStatus(hero.getStatusScoreString());

                if (legalMovePlayer(hero.getPosition().plus(Direction.UP.asVector()))){
                    hero.setPosition(hero.getPosition().plus(Direction.UP.asVector()));
                }

                hero.setDirection(Direction.UP);

                enemyMove();
            }


        }
        if (keyPressed == KeyEvent.VK_LEFT){
            if (hero.isHeroThrowFireball()){
                hero.setDirection(Direction.LEFT);
                hero.ThrowFireball();
                hero.setHeroThrowFireball(false);
                removeArrows();

            } else {
                hero.addScore(-1);
                gui.setStatus(hero.getStatusScoreString());

                if (legalMovePlayer(hero.getPosition().plus(Direction.LEFT.asVector()))){
                    hero.setPosition(hero.getPosition().plus(Direction.LEFT.asVector()));
                }

                hero.setDirection(Direction.LEFT);

                enemyMove();
            }




        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            if (hero.isHeroThrowFireball()) {
                hero.setDirection(Direction.RIGHT);
                hero.ThrowFireball();
                hero.setHeroThrowFireball(false);
                removeArrows();
            } else {
                hero.addScore(-1);
                gui.setStatus(hero.getStatusScoreString());
                if (legalMovePlayer(hero.getPosition().plus(Direction.RIGHT.asVector()))){
                    hero.setPosition(hero.getPosition().plus(Direction.RIGHT.asVector()));
                }
                hero.setDirection(Direction.RIGHT);
                enemyMove();
            }
        }



        if (keyPressed == KeyEvent.VK_NUMPAD0){
            removeImageInventoryBar(hero.getItems()[0]);
            /*// remover de bar update bar update inventory
            //drop inventory item on position 0
            //place in teh ground
            //adicionar ao image tiles posição do heroi*/
        }
        if (keyPressed == KeyEvent.VK_NUMPAD1){
            removeImageInventoryBar(hero.getItems()[1]);
        }
        if (keyPressed == KeyEvent.VK_NUMPAD2){
            removeImageInventoryBar(hero.getItems()[2]);
        }
        if (keyPressed == KeyEvent.VK_SPACE){
            if (hero.getNumbFireball()>0){
                hero.setHeroThrowFireball(true);
                showArrows();
            } else {
                gui.setStatus(hero.getStatusScoreString() + "  -  No fireballs Available");
            }


        }


        if (keyPressed == KeyEvent.VK_ESCAPE){
            pressEsc();

        }

    }












}
