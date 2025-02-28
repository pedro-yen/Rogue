package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Tengu extends Enemy{

    private Position position;
    private int padrao =0;
    private int hp=100;
    private int attack=15;
    private int killScore=100;
    private boolean diagonalAttack=false;

    public Tengu(Position position){
        this.position=position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Tengu";
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
        /*if (diagonalAttack){
            this.attack=5;
        } else {
            this.attack=10;
        }*/
        return this.attack;
    }

    @Override
    public void setAttack(int newAttack) {
        // função nunca é utilizada
        this.attack=newAttack;
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

    public Vector2D padraoMov (){
        Hero hero = Hero.getInstance();
        int engageDist=3;
        int distX = hero.getPosition().getX() - getPosition().getX();
        int distY = hero.getPosition().getY() - getPosition().getY();
        Vector2D movX;
        Vector2D movY;


        //Aproxima

        double rand =  Math.random();
        if (distX>engageDist ||distY>engageDist) {
            if (rand < 0.25) {
                return new Vector2D(1, 0);

            } else if (rand < 0.5) {
                return new Vector2D(-1, 0);
            } else if (rand < 0.75) {
                return new Vector2D(0, 1);
            } else {
                return new Vector2D(0, -1);
            }
        } else {
            if (distX<0){
                movX= new  Vector2D(-1,0);
            } else if (distX>0){
                movX = new Vector2D(1,0);
            } else {
                movX= new Vector2D(0,0);
            }
            if (distY<0){
                movY =new  Vector2D(0,-1);
            } else if (distY>0){
                movY= new Vector2D(0,1);
            } else {
                movY =new Vector2D(0,0);

            }

            if (movX.getX()!=0 && movY.getY()!=0){
                double rand_2 = Math.random();
                if (rand_2 <0.5){
                    return movX;
                } else {
                    return movY;
                }
            }
            return movX.plus(movY);
        }

        /*switch (padrao){
            case (0):
                padrao++;
                return new  Vector2D(1,0);
            case (1):
                padrao++;
                return new  Vector2D(0,1);
            case (2):
                padrao++;
                return new  Vector2D(-1,0);
            case (3):
                padrao=0;
                return new  Vector2D(0,-1);
        }

        return new Vector2D(0,0);*/
    }



    @Override
    public void KillScore(){
        Hero hero = Hero.getInstance();
        hero.addScore(getKillscore());
    }

    @Override
    public void addHP(int hp) {
        this.hp+=hp;
    }
}
