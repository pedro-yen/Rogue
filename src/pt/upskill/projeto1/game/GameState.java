package pt.upskill.projeto1.game;

import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public enum  GameState {

    INSTANCE;

    //atributos
    private List<Room> roomList=new ArrayList<>();
    private String currentRoom;  // NOME DA SALA

    private int currentRoomIndex; // INDICE DA SALA NO ROOMLIST
    private int previousRoomIndex; // SALA ANTERIOR (CONFIGURAÇÃO)

    private Position HeroPosition;



    //funções
    public static GameState getINSTANCE() {
        return INSTANCE;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(String currentRoom) {
        // esta função serve para iniciar o jogo de forma a poder escolher a sala

        this.currentRoom = currentRoom;
        setIndexfromRoomID(this.currentRoom);

    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList=roomList;
    }

    public void setIndexfromRoomID(String roomID){
        // receives a string and based on the name returns an index
        int index =0;

        for (Room i:this.roomList) {
            if (i.getRoomID().equals(roomID)){
                this.currentRoomIndex=index;
                break;
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
    }

    public Object readResolve(){
        return getINSTANCE();
    }

    public Position getHeroPosition() {
        return HeroPosition;
    }

    public void setHeroPosition(Position heroPosition) {
        HeroPosition = heroPosition;
    }

    /*public static GameState setINSTANCE(GameState gameState) {
        ;
    }*/











}
