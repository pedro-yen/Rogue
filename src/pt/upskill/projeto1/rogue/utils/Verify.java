package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.game.GameState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.*;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.objects.Tiles.Door.Door;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Red;
import pt.upskill.projeto1.objects.Tiles.Torch;
import pt.upskill.projeto1.objects.Tiles.Trap;
import pt.upskill.projeto1.objects.Items.Treasure;
import pt.upskill.projeto1.objects.Tiles.Wall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static pt.upskill.projeto1.game.KeyEvent.*;
import static pt.upskill.projeto1.game.Room.checkDoor;

public class Verify {
    /*private static final Verify INSTANCE = new Verify();

    public static Verify getINSTANCE() {
        return INSTANCE;
    }*/


    public static List<String> verifyGetAllRooms(String dirName){
        List<String> fileRoomList = new ArrayList<>();
        //try {
        File[] files = new File(dirName).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileRoomList.add(file.getName());
            }
        }

        //} catch (Exception e){
        //   System.out.println("dirName incorrect");
        //}
        return fileRoomList;
    }










    public static boolean isInt(String test){
        try {
            Integer.parseInt(test);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean legalMoveEnemy(Position posInimigo, Position newposition, List<ImageTile> imageTileList) {
        //Engine engine = Engine.getINSTANCE();

        // vERIFICA SE O LOCAL PARA ONDE SE PRETENDE IR ESTÁ OCUPADO COM: WALL, SKELETON E HERO

        //Verificar se fica out of bounds

        if (newposition.getX() < 0 || newposition.getX() > 9 || newposition.getY() < 0 || newposition.getY() > 9) {
            return false;
        }

        for (ImageTile i : imageTileList) {
            if (i.getPosition().equals(newposition)) {
                //verificar localização
                if (i instanceof Enemy) {
                    return false;
                } else if (i instanceof Wall) {
                    return false;
                } else if (i instanceof Hero) {
                    //quando é o heroi ESTE inimigo ataca-o
                    Hero hero = Hero.getInstance();
                    //ir buscar o meu objeto inimigo
                    for (ImageTile j: imageTileList) {
                        if (j.getPosition().equals(posInimigo) && j instanceof Enemy) {
                            //recebe a posição original do inimigo para o ir buscar e ter o atributo attack
                            hero.looseHp(((Enemy) j).getAttack());
                        }
                    }
                    return false;
                } else if (i instanceof Door) {
                    return false;
                } else if (i instanceof Trap) {
                    return false;
                }
            }

        }
        return true;
    }





    /// VERIFY LEGAL MOVE PARA O PLAYER pode entrar em portas e escadas

    public static boolean legalMovePlayer(Position position) {// colocar im
        Hero hero = Hero.getInstance();
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        //Verificar se fica out of bounds
        if (position.getX() < 0 || position.getX() > 9 || position.getY() < 0 || position.getY() > 9) {
            return false;
        }


        ImageTile object = null; // inicialização do object fora do ciclo for
        ImageTile object2 = null; // inicialização do object fora do ciclo for


        //verificar todos os tiles do tabuleiro e comparar a sua posição com a posição que o heroi pretende ir
        for (int i = 0; i < state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().size(); i++) {
            object = state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(i);

            if (object.getPosition().equals(position)) {
                if (object instanceof Enemy) {
                    ((Enemy) object).looseHp(hero.getAttack());
                    hero.isAttacking();
                    return false;
                } else if (object instanceof Wall) {
                    return false;
                } else if (object instanceof Items) {
                    for (int j = i + 1; j < state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().size(); j++) {
                        object2 = state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j);

                        // verificar que não existe inimigos em cima do item, os image tiles anteriores já foram verficados
                        if (object2 instanceof Enemy && object2.getPosition().equals(position)) {
                            ((Enemy) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j)).looseHp(hero.getAttack());
                            hero.isAttacking();
                            return false;
                        }
                    }
                    if (object instanceof Treasure){
                        ((Treasure) object).AddPickUpScore();
                        gameWon();
                        return false;
                    }
                    if (hero.getOpenSlotInventory() < 3) {
                        hero.setItemOnArray((Items) object);
                    }

                    return true;


                } else if (object instanceof Door) {
                    // verificar se a porta está aberta ou fechada

                    checkDoor((Door) object, position); //SOMETHING BROKE IN CHECK

                    return false;
                } else if (object instanceof Red) {
                    for (int j = i + 1; j < state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().size(); j++) {
                        object2 = state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j);

                        // verificar que não existe inimigos em cima do Save Plate, os image tiles anteriores já foram verficados
                        if (object2 instanceof Enemy && object2.getPosition().equals(position)) {
                            ((Enemy) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j)).looseHp(hero.getAttack());

                            hero.isAttacking();
                            return false;
                        }
                        SaveGame();
                        return false;

                    }
                } else if (object instanceof Torch) {
                    //if it finds a torch replenish fireballs
                    hero.setNumbFireball(3);
                    ((Torch) object).removeThis();
                    return true;
                } else if(object instanceof Trap){
                    for (int j = i + 1; j < state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().size(); j++) {
                        object2 = state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j);
                        // verificar que não existe inimigos em cima da Trap, os image tiles anteriores já foram verficados

                        if (object2 instanceof Enemy && object2.getPosition().equals(position)) {
                            ((Enemy) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(j)).looseHp(hero.getAttack());
                            //Attack(position, imageTileList);
                            return false;
                        }
                    }
                    hero.looseHp(((Trap) object).getDamage());
                    return true; // redundante
                }
            }
        }
        return true;
    }









    public static int stringToInt(String a){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        //takes a string and gets the numbers
        String b=a.replaceAll("[^0-9]","");
        int numero=-1;
        try{
            numero =  (Integer.parseInt(b));
        } catch (NumberFormatException e){
            gui.showMessage("Error", "Input not valid");
            pressEsc();
        }
        return  numero;
    }














    //ISTO NÃO FAZ SENTIDO PARA BAIXO

   /* public void AttackTheHero(Position position, List<ImageTile> imageTileList, Hero hero){
        for (ImageTile i:imageTileList) {
            if (i instanceof Enemy && i.getPosition().equals(position)) hero.looseHp(((Enemy) i).getAttack(), imageTileList);
        }
    }

    public void AttackTheEnemy(Position position, List<ImageTile> imageTileList, Hero hero) {
        for (ImageTile i:imageTileList) {
            if (i instanceof Hero && i.getPosition().equals(position)) {
                hero.looseHp(((Enemy) i).getAttack(), imageTileList);
        }
        }
    }*/




}



