package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.List;

public class Thief extends Enemy {
    private Position position;
    //private int padrao = 0;
    private int hp = 30;
    private int attack = 5;
    private Items stolenItem = null;
    private boolean hasItem=false;
    private int killScore=400;

    public Thief(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getAttack() {
        Hero hero = Hero.getInstance();
        if (hasItem) {
            return (int) attack * 3;
        } else {
            //if it doesnt have a item it will steal and only do minimum damage
            /** ver que item ele vai roubar
             *
             */
            for (int i = 0; i<hero.getItems().length;i++) {
                if (hero.getItems()[i]!=null){
                    setStolenItem(hero.getItems()[i]);
                    hero.removeItemOnArray(hero.getItems()[i]);
                    hasItem=true;
                    break;
                }

            }



        }
        

        return attack;
    }

    @Override
    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }

    @Override
    public void looseHp(int Attack) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();
        GameState state = GameState.getINSTANCE();


        hp-=Attack;
        gui.setStatus(hero.getStatusScoreString() + "  -  "+hero.getName()+" attacked " +this.getName()+"! "+
                this.getName()+" has "+this.getHp()+"  HP left");
        if (hp<=0){

            KillScore();
            if (getStolenItem()!=null){
                this.stolenItem.setPosition(this.position);
                state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(this.stolenItem);
                gui.addImage(getStolenItem());
            }


            gui.removeImage(this);
            state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(this);
            state.getRoomList().get(state.getCurrentRoomIndex()).removeEnemy(this);
            gui.setStatus(hero.getStatusScoreString() + "  -  "+this.getName()+" died!" + "  + "+this.getKillscore()+" points");
        }

    }

    @Override
    public int getKillscore() {
        return this.killScore;
    }

    public Vector2D padraoMov() {
        Hero hero = Hero.getInstance();
        int engageDist=3;
        int distX = hero.getPosition().getX() - getPosition().getX();
        int distY = hero.getPosition().getY() - getPosition().getY();


        //Aproxima


        Vector2D movX;
        Vector2D movY;
        double rand =  Math.random();
        if (distX>engageDist ||distY>engageDist){
            if (rand<0.25){
                return new Vector2D(1,1);
            } else if (rand <0.5) {
                return new Vector2D(-1,1);
            } else if (rand<0.75){
                return new Vector2D(-1,-1);
            } else {
                return new Vector2D(1,-1);
            }
        } else {
            if (distX<0){
                movX= new Vector2D(-1,0);
            } else if (distX>0){
                movX= new Vector2D(1,0);
            } else {
               if (rand<0.5){
                   movX= new Vector2D(-1,0);
               } else {
                   movX= new Vector2D(1,0);
               }
            }
            if (distY<0){
                movY= new Vector2D(0,-1);
            } else if (distY>0){
                movY= new Vector2D(0,1);
            } else {
                if (rand<0.5){
                    movY= new Vector2D(0,-1);
                } else {
                    movY= new Vector2D(0,1);
                }

            }
            return movX.plus(movY);
        }

    }



    @Override
    public void KillScore(){
        Hero hero = Hero.getInstance();
        hero.addScore(this.getKillscore());
    }


    public Items getStolenItem() {
        return stolenItem;
    }

    public void setStolenItem(Items stolenItem) {
        this.stolenItem = stolenItem;
    }

    @Override
    public void addHP(int hp) {
        this.hp+=hp;
    }
}
