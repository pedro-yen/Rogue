package pt.upskill.projeto1.objects.Tiles.Door;

import pt.upskill.projeto1.rogue.utils.Position;

public class DoorOpen_Closed extends Door {
    private int doorNumb;

    private Position position;
    private int positionInMap;
    private String nextRoomID; // posição na lista roomList no engine da sala à qual esta porta conecta
    private int conectingDoorOfNextRoom;
    private int key; // se key =0 está fechada
    private int score=300;

    /*private boolean State=false; // State TRUE - abera; FALSE - fechada*/

    public DoorOpen_Closed() {

    }

    @Override
    public String getName() {
        if (this.key ==0) {
            return "DoorOpen";
        }else {
            return "DoorClosed";
        }


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
        this.conectingDoorOfNextRoom=doorNumber;
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

    /*public boolean getState() {
        return this.State;
    }

    public void setState(boolean state) {
        this.State = state;
    }*/
}
