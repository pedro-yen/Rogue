package pt.upskill.projeto1.objects.Tiles.Door;

import pt.upskill.projeto1.objects.Tiles.Tile;
import pt.upskill.projeto1.rogue.utils.Position;

import javax.xml.transform.dom.DOMResult;

public class DoorWay extends Door {
    private int doorNumb;
    private Position position;
    private int positionInMap;

    private String nextRoomID; // posição na lista roomList no engine da sala à qual esta porta conecta
    private int conectingDoorOfNextRoom;
    private int key=0; //isto n#ao ]e necess]ario
    private int score=100;

    public DoorWay() {

    }

    @Override
    public String getName() {
        return "DoorWay";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public String getNextRoomID() {
        return this.nextRoomID;
    }

    @Override
    public void setNextRoomID(String nextRoomID) {
        this.nextRoomID="rooms/"+nextRoomID;
    }

    @Override
    public int getConectingDoorOfNextRoom() {
        return conectingDoorOfNextRoom;
    }

    @Override
    public void setConectingDoorOfNextRoom(int doorNumber) {
        this.key=conectingDoorOfNextRoom;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public void setkey(int key) {
        this.key=key;
    }

    @Override
    public int getPositionInMap() {
        return this.positionInMap;
    }

    @Override
    public void setPositionInMap(int positionInMap) {
        this.positionInMap=positionInMap;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score=score;
    }



}
