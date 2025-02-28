package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.game.*;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.PlateArmor;
import pt.upskill.projeto1.objects.Items.Weapon.Sword;
import pt.upskill.projeto1.objects.Items.Weapon.Weapon;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Fireball;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import static pt.upskill.projeto1.game.GameOver.saveScoreShowLeaderBoard;

public class Hero implements Character {

    private Position position;
    private static final Hero INSTANCE = new Hero();
    private int hp=80;
    private int attack=20;

    private int numbFireball=3;
    private Items[] items = new Items[3];
    private int openSlotInventory = 0;

    private int score=0;
    private Direction direction = Direction.UP;

    private String statusScoreString = "Score: "+getScore(); // this message will show the score of the hero on teh status bar

    private boolean heroThrowFireball =false;

    private Hero(){}

    public static Hero getInstance() {
        return INSTANCE;
    }



    public void setPosition(Position position) {
        this.position = position;

    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getHp() {
        return hp;
    }
    public void isAttacking(){
        StatusBar statusBar = StatusBar.getINSTANCE();
        for (Items i:this.items) {
            if (i instanceof Sword){
                this.hp+=getAttack();
                checkHP();
                statusBar.UpdateBar();
            }
        }
    }
    @Override
    public int getAttack() {
        boolean isSword=false; // fazer lifesteal
        int FinalAttack = this.attack;
        for (Items i:items) {
            if (i instanceof Weapon) {
            FinalAttack+= ((Weapon) i).getAttackModifier();
            }
        }
        //verifica 1º se tem espada se tiver faz lifesteal e depois volta a colocar o sinalizador a falso
        return FinalAttack;
    }

    @Override
    public void setAttack(int newAttack) {
        this.attack=newAttack;
    }

    @Override
    public void looseHp(int Attack) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();

        int finalAttack = Attack;

        // fator de mitigação da armadura
        for (Items i: items) {
            if (i instanceof PlateArmor){
                finalAttack = (int) ((1-((PlateArmor) i).getMitigation()) *Attack);
            }
        }

        hp-=finalAttack;
        gui.setStatus(getStatusScoreString() + "  -  "+this.getName()+" was attacked and has "+ this.getHp()+" HP left");
        statusBar.UpdateBar();
        if (hp<=0){
            //gameover
            gui.setStatus(getStatusScoreString() + "  -  "+this.getName()+" died!");
            gui.showMessage("Game Over",  "The hero is dead!" + System.lineSeparator()+" Input his name so that we can remember him!\"");
            saveScoreShowLeaderBoard();
        }
    }

    public void checkHP(){
        if (this.hp>80){
            hp=80;
        }
    }

    public void addHp(Meat meat){
        // se tivesses mais consumiveis iria ficar a interface e não a meat
        this.hp+=meat.getHpBoost();
        checkHP();
    }
    public void setHp(int hp){
        this.hp=hp;
    }

    public int getNumbFireball() {
        return numbFireball;
    }

    public void setNumbFireball(int numbFireball) {
        StatusBar statusBar = StatusBar.getINSTANCE();

        this.numbFireball = numbFireball;
        statusBar.UpdateBar();
    }

    public Items[] getItems() {
        return items;
    }

    public void setOpenSlotInventory(int openSlotInventory){
        this.openSlotInventory=openSlotInventory;
    }

    public void setItemOnArray(Items item){
        StatusBar statusBar = StatusBar.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();

        //remover do tabuleiro de jogo e dos atributos da sala??
        gui.removeImage(item);
        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(item);
        state.getRoomList().get(state.getCurrentRoomIndex()).getPickUpList().remove(item);

            this.items[openSlotInventory]=(item);
            this.openSlotInventory++;
            item.AddPickUpScore();
            statusBar.UpdateBar();



    }

    public void removeItemOnArray(Items item){
        //try{
            for (int i = 0; i< items.length; i++) {
                if (items[i].equals(item)){
                    items[i]=null;
                    this.openSlotInventory--;
                    break;
                }
            }
            // mexer tudo +para a esquerda
            for (int i = 0; i < items.length - 1; i++) {
                try{
                    if (items[i] == null) {

                        // encontra o proximo elemento não nulo
                        int nextNonNull = i + 1;
                        while (nextNonNull < items.length && items[nextNonNull] == null) {
                            nextNonNull++;
                        }

                        // Se encontrar um elemento não nulo mover esse elemento para a esquerda
                        if (nextNonNull < items.length) {
                            items[i] = items[nextNonNull];
                            items[nextNonNull] = null;
                        }
                    }
                } catch (Exception e){
                    continue; // isto é assim?
                }

            }
    }

    public void setItems(Items[] items){
        this.items=items;
    }

    public int getOpenSlotInventory() {
        return openSlotInventory;
    }




    public int getScore() {
        return score;
    }

    public void addScore(int score){
        this.score+=score;
        if (this.score<0){
            this.score=0;
        }
        this.statusScoreString = "Score: "+getScore();
    }

    public void setScore(int score) {
        this.score = score;
        this.statusScoreString = "Score: "+getScore();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


    public void ThrowFireball(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();

        if (this.numbFireball>0){
            this.numbFireball--;
            statusBar.UpdateBar();

            Fireball fireball = new Fireball(this.getPosition());
            FireBallThread fireBallThread = new FireBallThread(this.getDirection(),fireball);
            gui.addImage(fireball);
            //fireBallThread.start();
            fireBallThread.run();
        } else {
            gui.setStatus(getStatusScoreString() + "  -  No fireballs Available");
        }

    }

    public String getStatusScoreString() {
        return statusScoreString;
    }

    public boolean isHeroThrowFireball() {
        return heroThrowFireball;
    }

    public void setHeroThrowFireball(boolean heroThrowFireball) {
        this.heroThrowFireball = heroThrowFireball;
    }

    public void resetHero(){
        //Position position;   posição não é necessário
        setHp(80);
        setAttack(20);

        numbFireball=3;
        for (int i= 0;i< items.length;i++) {
            items[i]=null;
        }

        setOpenSlotInventory(0);
        setScore(0);
        this.score=0;
        setDirection(Direction.UP);

        setHeroThrowFireball(false);

    }
}

