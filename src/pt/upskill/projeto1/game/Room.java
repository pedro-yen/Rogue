package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.*;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Items.Treasure;
import pt.upskill.projeto1.objects.Items.Weapon.Hammer;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.objects.Items.Key;
import pt.upskill.projeto1.objects.Items.Weapon.Sword;
import pt.upskill.projeto1.objects.Tiles.*;
import pt.upskill.projeto1.objects.Tiles.Door.Door;
import pt.upskill.projeto1.objects.Tiles.Door.DoorOpen_Closed;
import pt.upskill.projeto1.objects.Tiles.Door.DoorWay;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Red;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pt.upskill.projeto1.rogue.utils.Verify.isInt;
import static pt.upskill.projeto1.rogue.utils.Verify.stringToInt;

public class Room implements Serializable {
    private String roomID;
    private List<ImageTile> tiles = new ArrayList<>();
    private List<Enemy> Enemies = new ArrayList<>();
    private  List<Door> doorList = new ArrayList<>();
    private List<Items> pickUpList = new ArrayList<>();
    private boolean FirstTimeHero =true;
    private int roomScore = 200;



    //inicializar o room
    public Room(String room) {
        this.roomID = room;

        roomInit();
    }
    public void removeEnemy(Enemy enemy) {
        Enemies.remove(enemy);
        tiles.remove(enemy);
    }

    public List<Enemy> getEnemies() {
        return Enemies;
    }

    public List<ImageTile> getTiles() {
        return tiles;
    }

    public ImageTile getObject(int i) {
        return tiles.get(i);
    }

    public List<Door> getDoorList() {
        return doorList;
    }

    public String getRoomID() {
        return roomID;
    }

    public List<Items> getPickUpList() {
        return pickUpList;
    }

    public boolean isFirstTimeHero() {
        return FirstTimeHero;
    }

    public void setFirstTimeHero(boolean firstTimeHero) {
        FirstTimeHero = firstTimeHero;
    }

    public int getRoomScore() {
        return roomScore;
    }

    public void roomInit() {
        StatusBar statusBar = StatusBar.getINSTANCE();
        Hero hero = Hero.getInstance();



        //paint the floor
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.tiles.add(new Floor(new Position(x,y)));
            }
        }
        int doorIndex = 0;
        int pickUpNumb = 0;


        //LER FICHEIRO ROOM
        try {
            Scanner fileScanner = new Scanner(new File(roomID));
            while (fileScanner.hasNext()) {
                // LER INSTRUÇÕES/CARACTERÍSTICAS DA SALA
                while (fileScanner.hasNext("#")) {
                    String nextLine = fileScanner.nextLine();
                    String[] arrayString = nextLine.split(" ");

                    //verificação de linha # vazia
                    if (arrayString.length > 1) {
                        if (isInt(arrayString[1])) {
                            //leitura da linha com as indicações das portas
                            switch (arrayString[2]) {
                                case ("D"):
                                    this.doorList.add(new DoorOpen_Closed());
                                    break;
                                case ("E"):
                                    this.doorList.add(new DoorWay());

                                    break;
                            }
                            doorList.get(doorIndex).setPositionInMap(Integer.parseInt(arrayString[1]));
                            //indicar em que posição do mapa esta porta está, esta localização vai ser tambem a sua identificação

                            doorList.get(doorIndex).setNextRoomID((arrayString[3]));
                            // indice da sala de destino na lista de salas

                            doorList.get(doorIndex).setConectingDoorOfNextRoom(Integer.parseInt(arrayString[4]));
                            //System.out.println("connectiong next door "+arrayString[4]);

                            //System.out.println("Porta "+doorList.get(doorIndex).getPositionInMap()+" | Proxima sala "+ doorList.get(doorIndex).getNextRoomID()+" | porta na proxima sala "+doorList.get(doorIndex).getConectingDoorOfNextRoom());
                            if (arrayString.length == 6) {

                                doorList.get(doorIndex).setkey(stringToInt(arrayString[5]));
                            } else {
                                doorList.get(doorIndex).setkey(0);
                            }


                            doorIndex++;
                        } else {
                            // defeniçao da key
                            pickUpList.add(new Key());
                            ((Key) pickUpList.get(pickUpNumb)).setKeyId(stringToInt(arrayString[2]));
                        }
                    }
                }

                //LER MAPA

                for (int y = 0; y < 10; y++) {
                    String linha = fileScanner.nextLine();
                    String[] vetorTiles = linha.split("");
                    for (int x = 0; x < 10; x++) {

                        if (isInt(vetorTiles[x])) {
                            // VERIFICA SE O QUE ESTÁ NA STRING É INTEIRO E SÓ DEPOIS FAZ A COMPARAÇÃO COM O NUMERO DA PORTA
                            for (int k = 0; k < doorList.size(); k++) {
                                if (Integer.parseInt(vetorTiles[x]) == doorList.get(k).getPositionInMap()) {
                                    doorList.get(k).setPosition(new Position(x,y));
                                    this.tiles.add(doorList.get(k));
                                }
                            }

                        }

                        switch (vetorTiles[x]) {
                            case ("G"):
                                this.Enemies.add(new BadGuy(new Position(x,y)));
                                break;
                            case ("B"):
                                this.Enemies.add(new Bat(new Position(x,y)));
                                break;
                            case ("H"):
                                hero.setPosition(new Position(x,y));
                                this.tiles.add(hero);
                                break;
                            case ("S"):
                                this.Enemies.add(new Skeleton(new Position(x,y)));
                                break;
                            case ("T"):
                                this.Enemies.add(new Thief(new Position(x,y)));
                                break;
                            case ("W"):
                                this.tiles.add(new Wall(new Position(x,y)));
                                break;

                            case("k"):
                                // ATENÇÃO APENAS FUNCIONA PARA 1 KEY POR SALA
                                for (int i = 0; i<pickUpList.size();i++){
                                    if (pickUpList.get(i) instanceof Key){
                                        ((Key) pickUpList.get(i)).setPosition(new Position(x,y));
                                        break;
                                    }
                                }
                                break;
                            case("h"):
                                this.pickUpList.add(new Hammer(new Position(x,y)));
                                break;
                            case("s"):
                                this.pickUpList.add(new Sword(new Position(x,y)));
                                break;
                            case("m"):
                                this.pickUpList.add(new Meat(new Position(x,y)));
                                break;
                            case("g"):
                                this.tiles.add(new Gold(new Position(x,y)));
                                break;
                            case("r"):
                                this.tiles.add(new Red(new Position(x,y)));
                                break;
                            case("t"):
                                this.tiles.add(new Torch(new Position(x,y)));
                                break;
                            case ("a"):
                                this.tiles.add(new Trap(new Position(x,y)));
                                break;
                            case ("K"):
                                this.Enemies.add(new King(new Position(x,y)));
                                break;
                            case ("U"):
                                this.pickUpList.add(new Treasure(new Position(x,y)));
                                break;
                            case ("u"):
                                this.Enemies.add(new Tengu(new Position(x,y)));
                                break;
                        }
                    }
                }
            }

            //adicionar inimigos no final --> ter a certeza que estes ficam em cima de todos os tiles

            for (ImageTile i:this.pickUpList) {
                this.tiles.add(i);
            }
            for (ImageTile i:this.Enemies) {
                this.tiles.add(i);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // mudar para sout???
        }

    }



    public static void checkDoor(Door door, Position position){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();
        Hero hero = Hero.getInstance();
        GameState state = GameState.getINSTANCE();

        if (door.getKey()==0){
            if (state.getRoomList().get(state.getCurrentRoomIndex()).isFirstTimeHero()){
                hero.addScore(state.getRoomList().get(state.getCurrentRoomIndex()).getRoomScore());
                state.getRoomList().get(state.getCurrentRoomIndex()).setFirstTimeHero(false);
                gui.setStatus(hero.getStatusScoreString() + "  -  You stepped into another room" +  "  + "+door.getScore()+" points");
            }
            gui.setStatus(hero.getStatusScoreString() + "  -  You stepped into another room");
            changeRoom(position);

        } else {
            for (Items i: hero.getItems()) {
                if (i instanceof Key){
                    if (((Key) i).getKeyId() == door.getKey()){
                        door.setkey(0);
                        hero.removeItemOnArray(i); // remover do inventário do heroi
                        statusBar.getBar().remove(i); //remover da lista de tiles do status bar
                        gui.removeStatusImage(i); // remover imagem do status bar?
                        statusBar.UpdateBar();
                        gui.setStatus(hero.getStatusScoreString() + "  -  Door is Unlocked");
                        break;
                    }
                }

                gui.setStatus(hero.getStatusScoreString() + "  -  Door is Locked, you need Key"+door.getKey());
            }
        }

    }



    public static void changeRoom(Position position){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();
        Hero hero = Hero.getInstance();

        //fazer o set up para mudar de sala ver qual é a sala que estamos agora
        state.setPreviousRoomIndex(state.getCurrentRoomIndex());
        //qual a porta com base na sala anterior
        for (ImageTile i:state.getRoomList().get(state.getPreviousRoomIndex()).getTiles()) {
            if (i instanceof Door && i.getPosition().equals(position)){
                Door door = (Door) i;
                state.setCurrentRoom(door.getNextRoomID());

                gui.clearImages();
                state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(hero);
                //remover o heroi desta sala

                hero.setPosition(getNextRoomDoorPosition(door.getConectingDoorOfNextRoom(), door));
                state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(hero);
                //adicionar o heroi na nova sala com a nova posição


                gui.newImages(state.getRoomList().get(state.getCurrentRoomIndex()).getTiles());
                break;
            }
        }

        if (state.getCurrentRoom().equals("rooms/room3.txt")){
            gui.showMessage("King", "WHO DARES TO DISTURB ME?");
        }










    }

    public static Position getNextRoomDoorPosition(int previousDoor, Door door){
        // preciso do state para ir buscar a lista de salas
        //atributo next room
        //atributo next door
        //ir buscar a posição dessa sala e devolver
        
        GameState state = GameState.getINSTANCE();

       //DOOR GETS EFFED WHEN ROOM GETS CHANGED SINCE WE ARE USING IMAGINE TILE LIST TO GET TO IT ASSUMES DOOR ON NEW ROOM

        for (Room i:state.getRoomList()) {
            if (door.getNextRoomID().equals(i.getRoomID())) {
                //procura na lista de salas a sala de destino que esta porta liga

                for (Door j : i.getDoorList()) {
                    if (door.getConectingDoorOfNextRoom() == j.getPositionInMap()) {
                        //na sala de destino vai buscar a posição da porta para que possa meter lá o heroi, muda o estado dessa porta para aberta
                        j.setkey(0);
                        return j.getPosition();
                    }
                }
            }
        }
        return new Position(0,0);
    }

    public static void removeRoom(){

    }








}
