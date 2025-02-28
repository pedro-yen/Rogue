package pt.upskill.projeto1.objects.Tiles.Door;

import pt.upskill.projeto1.objects.Tiles.Tile;

public abstract class Door implements Tile {
    private int doorNumb;
    private String nextRoomID; // posição na lista roomList no engine da sala à qual esta porta conecta
    private int conectingDoorOfNextRoom;
    private int key;
    private int score;
    private boolean firstTimeHero;



    public abstract String getNextRoomID();
    public abstract void setNextRoomID(String  nextRoomID);

    public abstract int  getConectingDoorOfNextRoom();
    // numero da porta da sala de destino, este numero deverá estar no mapa
    public abstract void setConectingDoorOfNextRoom(int doorNumber);

    public abstract int  getKey();
    public abstract void setkey(int key);

    private int positionInMap;

    public abstract int getPositionInMap();

    public abstract void setPositionInMap(int positionInMap);

    public abstract int getScore();

    public abstract void setScore(int score);

}
