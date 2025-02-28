package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import static pt.upskill.projeto1.objects.Items.Treasure.DropTreasure;

public class King extends Enemy{
    private Position position;
    private int hp=500;
    private int attack=0;
    private int killScore = 1000;

    public King (Position position){
        this.position=position;
    }
    @Override
    public String getName() {
        return "King";
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
        //each time get attacked is called teh attack increments +5
        this.attack+=5;
        System.out.println("king attack " + this.attack);
        return this.attack;
    }

    @Override
    public void setAttack(int newAttack) {
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

            DropTreasure(this.position);

            gui.removeImage(this);
            state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(this);
            state.getRoomList().get(state.getCurrentRoomIndex()).removeEnemy(this);
            gui.setStatus(hero.getStatusScoreString() + "  -  "+this.getName()+" died!" + "  + "+this.getKillscore()+" points");


            // make everyone else run away
            if (state.getRoomList().get(state.getCurrentRoomIndex()).getEnemies().size()>0){
                gui.showMessage("GUARD", "Oh No! The King is dead! RUN AWAY");
                for (int i =state.getRoomList().get(state.getCurrentRoomIndex()).getEnemies().size()-1;i>=0;i--) {
                    Enemy inimigo =state.getRoomList().get(state.getCurrentRoomIndex()).getEnemies().get(i);
                    inimigo.looseHp(inimigo.getHp());
                }

            }
        }
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public Vector2D padraoMov() {
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

            // movimento também na diagonal como a rainha em apenas 1 casa

            /*if (movX.getX()!=0 && movY.getY()!=0){
                double rand_2 = Math.random();
                if (rand_2 <0.5){
                    return movX;
                } else {
                    return movY;
                }
            }*/
            return movX.plus(movY);
        }
    }

    @Override
    public int getKillscore() {
        return this.killScore;
    }


    @Override
    public void KillScore(){
        Hero hero = Hero.getInstance();
        hero.addScore(this.getKillscore());
    }

    @Override
    public void addHP(int hp) {
        this.hp+=hp;
    }


}
