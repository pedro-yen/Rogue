package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save implements Serializable {
    //atributos
    private List<Room> roomList=new ArrayList<>();
    private String currentRoom;

    private int currentRoomIndex;
    private int previousRoomIndex;

    //atributos heroi
    private Position HeroPosition;
    private int hp;
    private int attack;

    private int numbFireball;
    private Items[] items;
    private int openSlotInventory;

    private int score;
    private Direction direction;


    private boolean heroThrowFireball =false;










    //funções

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    public void setCurrentRoomIndex(int currentRoomIndex) {
        this.currentRoomIndex = currentRoomIndex;
    }

    public int getPreviousRoomIndex() {
        return previousRoomIndex;
    }

    public void setPreviousRoomIndex(int previousRoomIndex) {
        this.previousRoomIndex = previousRoomIndex;
    }

    public Position getHeroPosition() {
        return HeroPosition;
    }

    public void setHeroPosition(Position heroPosition) {
        HeroPosition = heroPosition;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getNumbFireball() {
        return numbFireball;
    }

    public void setNumbFireball(int numbFiFireball) {
        this.numbFireball = numbFiFireball;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    public int getOpenSlotInventory() {
        return openSlotInventory;
    }

    public void setOpenSlotInventory(int openSlotInventory) {
        this.openSlotInventory = openSlotInventory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isHeroThrowFireball() {
        return heroThrowFireball;
    }

    public void setHeroThrowFireball(boolean heroThrowFireball) {
        this.heroThrowFireball = heroThrowFireball;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    /*public String getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
        setIndexfromRoomID(this.currentRoom);

    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setIndexfromRoomID(String roomID){
        int index =0;

        for (Room i:this.roomList) {
            if (i.getRoomID().equals(roomID)){
                this.currentRoomIndex=index;
            }
            index++;
        }
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    public int getPreviousRoomIndex() {
        return previousRoomIndex;
    }

    public void setPreviousRoomIndex(int previousRoomIndex) {
        this.previousRoomIndex = previousRoomIndex;
    }*/
}
