package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.Enemy;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Items.Items;
import pt.upskill.projeto1.objects.Items.Meat;
import pt.upskill.projeto1.objects.Tiles.Arrow.*;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static pt.upskill.projeto1.game.GameOver.saveScoreShowLeaderBoard;
import static pt.upskill.projeto1.rogue.utils.CheatCodes.totallyNotACheat;
import static pt.upskill.projeto1.rogue.utils.Verify.*;

public class KeyEvent {

    public static void pressEsc(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        String EscString = "0. Resume Game" + lineSeparator()+
                "1. Save Game"    + lineSeparator()+
                "2. Load Game"  + lineSeparator()+
                "3. Tutorial"  + lineSeparator()+
                "4. Main Menu"  + lineSeparator()+
                "5. Cheat Code"  + lineSeparator()+
                "6. Quit Game";

        boolean notValidAnswer = true;

        try{
            while (notValidAnswer){
                String EscAnswer= gui.showInputDialog("Menu", EscString);

                int input = stringToInt(EscAnswer);
                switch (input){
                    case(0):
                        notValidAnswer = false;
                        break;
                    case(1):
                        SaveGame();
                        notValidAnswer = false;
                        break;
                    case(2):
                        LoadGame();
                        notValidAnswer = false;
                        break;
                    case(3):
                        ShowInstructions();
                        notValidAnswer = false;
                        break;
                    case(4):
                        MainMenu();
                        notValidAnswer = false;
                        break;
                    case(5):
                        totallyNotACheat();
                        notValidAnswer = false;
                        break;
                    case(6):
                        System.exit(0);
                        notValidAnswer = false;
                        break;
                }

            }
        }catch (NullPointerException e){
            //do nothing go back to asking
        }

    }

    public static void StartNewGame(){
        GameState state = GameState.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();
        Hero hero = Hero.getInstance();


        // clear everything rooms e state
        state.getRoomList().clear();
        ReadRooms("rooms");
        //state.setCurrentRoom("rooms/roomtest_2.txt");
        state.setCurrentRoom("rooms/room0.txt");
        state.setPreviousRoomIndex(0);
        hero.resetHero();

        gui.newImages(state.getRoomList().get(state.getCurrentRoomIndex()).getTiles());

        statusBar.UpdateBar();


        gui.setStatus( hero.getStatusScoreString() + "  -  The game has started!");
    }




    public static void SaveGame() {
        GameState state = GameState.getINSTANCE();
        Hero hero = Hero.getInstance();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();


        Save save = new Save();

        //setters para o save
        save.setCurrentRoom(state.getCurrentRoom());
        save.setCurrentRoomIndex(state.getCurrentRoomIndex());
        save.setPreviousRoomIndex(save.getPreviousRoomIndex());
        save.setRoomList(state.getRoomList());

        //setters para atributo heroi
        save.setHeroPosition(hero.getPosition());
        save.setHp(hero.getHp());
        save.setNumbFireball(hero.getNumbFireball());
        save.setItems(hero.getItems());
        save.setOpenSlotInventory(hero.getOpenSlotInventory());
        save.setScore(hero.getScore());
        save.setHeroThrowFireball(hero.isHeroThrowFireball());
        save.setDirection(hero.getDirection());



        try{
            FileOutputStream out = new FileOutputStream("SaveFolder/Save.dat");
            ObjectOutputStream outObj = new ObjectOutputStream(out);
            outObj.writeObject(save);
            outObj.close();
            out.close();

        } catch (IOException e){
            System.out.println(e.getMessage());

        }

        gui.setStatus(hero.getStatusScoreString()+"  -  Game Saved!");

    }

    public static void LoadGame(){
        GameState state = GameState.getINSTANCE();
        Hero hero = Hero.getInstance();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();


        //ler ficheiro Save.dat
        try {
            FileInputStream fileIn = new FileInputStream("SaveFolder/Save.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Save save = (Save) in.readObject();
            in.close();
            fileIn.close();

            state.setRoomList(save.getRoomList());
            state.setCurrentRoom(save.getCurrentRoom());
            state.setPreviousRoomIndex(save.getPreviousRoomIndex());

            //setters para atributo heroi
            hero.setPosition(save.getHeroPosition());
            hero.setHp(save.getHp());
            hero.setNumbFireball(save.getNumbFireball());
            hero.setItems(save.getItems());
            hero.setOpenSlotInventory(save.getOpenSlotInventory());
            hero.setScore(save.getScore());
            hero.setHeroThrowFireball(save.isHeroThrowFireball());
            hero.setDirection(save.getDirection());



            // reiniciar gui

            StatusBar statusBar = StatusBar.getINSTANCE();

            //Nova sala
            gui.clearImages();
            gui.clearStatus();

            statusBar.UpdateBar();
            int index =0;
            for (ImageTile i:state.getRoomList().get(state.getCurrentRoomIndex()).getTiles()) {
                if (i instanceof Hero) {
                    break;
                }
                index++;
            }
            state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().remove(index);


            gui.newImages(state.getRoomList().get(state.getCurrentRoomIndex()).getTiles());

            state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(hero);
            gui.addImage(hero);

        } catch (IOException e) {
            System.out.println("Erro a ler o ficheiro com o save do mapa!");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível converter o objeto salvo no mapa!");
        }

        /*for (ImageTile i:loadedEngine.) {

        }*/

        gui.setStatus(hero.getStatusScoreString()+"  -  Game Loaded!");
    }


    public static void ReadRooms(String nameFolder){
        GameState state = GameState.getINSTANCE();


        List<String> arrayFileName = verifyGetAllRooms(nameFolder);
        for (String i : arrayFileName){
            state.getRoomList().add(new Room(nameFolder+"/"+i));
        }
    }






    public static void enemyMove() {
        GameState state = GameState.getINSTANCE();

        //for (int i = 0; i < this.roomList.get(this.currentRoom).getTiles().size(); i++) {
        for (ImageTile i :state.getRoomList().get( state.getCurrentRoomIndex()).getTiles()){
            if (i instanceof Enemy){ // vê todos os inimigos na sala para movelos
                Vector2D novoMov = (((Enemy) i).padraoMov()); // vai buscar movimentos de cada inimigo consoante o seu atributo padrao mov
                if (legalMoveEnemy(i.getPosition(),  i.getPosition().plus(novoMov),
                        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles())){
                    //Verificar se o movimento é legal e se for para cima do heroi este inimigo ataca-o
                    ((Enemy) i).setPosition(i.getPosition().plus(novoMov));

                }
            }
        }
    }



    public static void removeImageInventoryBar(Items item){
        Hero hero = Hero.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();

        try{
            if (item instanceof Meat){
                hero.addHp((Meat) item);
                removeImageFromBar(item);


                gui.setStatus(hero.getStatusScoreString() + "  -  Item Used");

            } else {
                item.setPosition(hero.getPosition());
                state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(item);
                gui.addImage(item);

                removeImageFromBar(item);
                gui.setStatus(hero.getStatusScoreString() + "  -  Item Dropped");
            }


            // remover da barra de estado e colocar


        } catch (NullPointerException e){

        }
    }


    public static void removeImageFromBar(Items item){
        Hero hero = Hero.getInstance();
        StatusBar statusBar = StatusBar.getINSTANCE();
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        statusBar.getBar().remove(item);
        hero.removeItemOnArray(item);
        gui.removeStatusImage(item);

        statusBar.UpdateBar();
    }


    public static void showArrows(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();

        Arrow arrowUp = new ArrowUp();
        Arrow arrowDown = new ArrowDown();
        Arrow arrowLeft = new ArrowLeft();
        Arrow arrowRight = new ArrowRight();

        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(arrowUp);
        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(arrowDown);
        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(arrowLeft);
        state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().add(arrowRight);

        gui.addImage(arrowUp);
        gui.addImage(arrowDown);
        gui.addImage(arrowRight);
        gui.addImage(arrowLeft);

    }
    public static void removeArrows(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        GameState state = GameState.getINSTANCE();


        int arrowIndexInTiles=0;
        int[] arrows = new int[4];
        int vetorarrowIndex=0;

        for (ImageTile i:state.getRoomList().get(state.getCurrentRoomIndex()).getTiles()) {
            if (i instanceof Arrow){
                arrows[vetorarrowIndex]=arrowIndexInTiles;
                vetorarrowIndex++;
            }
            arrowIndexInTiles++;
        }


        ((Arrow) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(arrows[3])).removeThis();
        ((Arrow) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(arrows[2])).removeThis();
        ((Arrow) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(arrows[1])).removeThis();
        ((Arrow) state.getRoomList().get(state.getCurrentRoomIndex()).getTiles().get(arrows[0])).removeThis();


    }


    public static void MainMenu(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();



        String MainMenuString;
        boolean NOtValidAnswer=true;
        while (NOtValidAnswer){
            try{
                MainMenuString = "0. Start new Game" + lineSeparator()+
                        "1. Load Game"  + lineSeparator()+
                        "2. See Leader Board"   + lineSeparator()+
                        "3. Tutorial"   + lineSeparator()+
                        "4. Quit Game";
                String mainMenuAnwser =gui.showInputDialog("MAIN MENU", MainMenuString);

                int option = stringToInt(mainMenuAnwser);
                switch (option){
                    case(0):
                        StartNewGame();
                        NOtValidAnswer=false;
                        break;
                    case(1):
                        LoadGame();
                        NOtValidAnswer=false;
                        break;
                    case(2):
                        ReadLeaderBoard();
                        NOtValidAnswer=false;
                        break;
                    case(3):
                        ShowInstructions();
                        NOtValidAnswer=false;
                        break;
                    case(4):
                        System.exit(0);
                        NOtValidAnswer=false;
                        break;

                }
            } catch (NullPointerException e){
                //DO SOMEHTING WHILE TRUE SOMEHTING
                // ERRO NÃO FOI POSSIVEL LER O COMANDO VOLTA A SELECIONAR O QUE PRETENDE



                // or do nothing and go back to teh top
            }

        }

    }

    public static void ReadLeaderBoard(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();
        gui.clearImages();

        List<Integer> ScoreList = new ArrayList<>();
        List<String> NameList = new ArrayList<>();
        //i iterador do score array
        int i=0;

        //ler leaderboard
        File ficheiroLeaderboard = new File("LeaderBoard/LeaderBoard.txt");
        try{
            Scanner fileScanner = new Scanner(ficheiroLeaderboard);

            System.out.println("file Scanner");
            /*while (fileScanner.hasNext("#")){
                String fileString = fileScanner.nextLine();
                System.out.println(fileString+0);
            }*/
            // WHY ARENT YOU WORKING?????


            String fileString = fileScanner.nextLine(); //?????????????????????????? porque é que # nãon está a funcionar?
            while (fileScanner.hasNext()){
                fileString = fileScanner.nextLine();
                String[] vetor = fileString.split(";");
                NameList.add(vetor[0]);
                ScoreList.add(stringToInt(vetor[1]));
                i++;
            }

            fileScanner.close();

        } catch (FileNotFoundException e){
            System.out.println("Ficheiro não encontrado");
        }

        //mostrar

        String showMessageString ="Nickname: "+NameList.get(0)+" || Score: "+ScoreList.get(0);
        for (int j = 1; j < ScoreList.size(); j++) {
            showMessageString+=System.lineSeparator()+"Nickname: "+NameList.get(j)+" || Score: "+ScoreList.get(j);
        }


        gui.showMessage("Leader Board", showMessageString);
        MainMenu();
    }



    public static void ShowInstructions(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        String InsctructionString ="Hero moves with arrows" + System.lineSeparator()+
                "Attack Enemies walk into them or use Fireball" + lineSeparator()+
                "To use Fireball SPACEBAR + Arrow" +lineSeparator()+
                "Drop items Numpad 0, 1 and 2" + lineSeparator()+
                "To use Consumable drop that item"+lineSeparator()+lineSeparator()+
                "To finish the game you need to find the King and get his treasure"+lineSeparator()+
                "Go on your adventure young Hero!";

        gui.showMessage("INSTRUCTIONS", InsctructionString);

        MainMenu();
    }


    public static void gameWon(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        gui.showMessage("CONGRATS!", "You have defeated the King and his bodyguards!"+System.lineSeparator()+
                "As look at the treasure you've acquired you start to ponder about the countless lives that you've slaughtered"
                +System.lineSeparator()+"Are you sure you're the good guy?");
        saveScoreShowLeaderBoard();
    }
}
