package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Characters.Hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pt.upskill.projeto1.game.KeyEvent.MainMenu;
import static pt.upskill.projeto1.rogue.utils.Verify.stringToInt;

public class GameOver {


    public static void saveScoreShowLeaderBoard(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        Hero hero = Hero.getInstance();


        gui.clearImages();

        String heroName = HeroNamePrompt();



        int Score = hero.getScore();

        List<Integer> ScoreList = new ArrayList<>();
        List<String> NameList = new ArrayList<>();
        //i iterador do score array
        int i=0;


        File ficheiroLeaderboard = new File("LeaderBoard/LeaderBoard.txt");



        //ler leaderboard
        try{
            Scanner fileScanner = new Scanner(ficheiroLeaderboard);
            /*while (fileScanner.hasNext("#")){
                String fileString = fileScanner.nextLine();
                System.out.println(fileString+0);
            }*/
            // WHY ARENT YOU WORKING?????


            String fileString = fileScanner.nextLine(); //??????????????????????????
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

        //Verificar se o score do jogador é bom o suficinete para entrar no leaderboard, e colocalo se for
        for (int j = 0; j<ScoreList.size();j++){
            if (Score>ScoreList.get(j)){
                //o score do jogador é maior que o que está no leaderboard


                //alterar ScoreList e nameList
                for (int k=ScoreList.size()-1;k>j ;k--){
                    ScoreList.set(k,ScoreList.get(k-1));
                    NameList.set(k,NameList.get(k-1));
                }

                ScoreList.set(j,Score);
                NameList.set(j,heroName);
                break;
            }

        }


        //guardar leaderboard
        try {
            PrintWriter fileWriter = new PrintWriter(ficheiroLeaderboard);
            fileWriter.println("# Name;Score");
            for (int m=0; m<ScoreList.size();m++){
                fileWriter.println(NameList.get(m)+";"+ScoreList.get(m));
            }

            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //mostrar

        String showMessageString ="Nickname: "+NameList.get(0)+" || Score: "+ScoreList.get(0);
        for (int j = 1; j < ScoreList.size(); j++) {
            showMessageString+=System.lineSeparator()+"Nickname: "+NameList.get(j)+" || Score: "+ScoreList.get(j);
        }


        gui.showMessage("Leader Board", showMessageString);

        MainMenu();
    }



    public static String HeroNamePrompt(){
        // envia a mensagem de game over e enquanto

        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        String heroName=null;

            try {
                heroName = gui.showInputDialog("SAVE YOUR NAME","Input your name in the dialog box bellow"+System.lineSeparator()+" (the name cannot contain \";\"):");
                boolean invalidName=true;
                while (invalidName){

                    // ver dollar??? tbm
                    String[] vetor = heroName.split(";");
                    if (vetor.length<2 && !heroName.isEmpty()){
                        invalidName=false;
                    } else {
                        heroName = gui.showInputDialog("GAME OVER", "Name has an error, please input the hero name without \";\" symbol:");
                    }

                }
            }
            catch(NullPointerException e){
                System.out.println(e.getMessage());
            }



        return heroName;
    }








    /*public static void main(String[] args) {
        String a = "Teste1";
        *//*String[] vetor = a.split(";");
        System.out.println(vetor.length);
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);

        }*//*

        List<Integer> ScoreList = new ArrayList<>();
        *//*List<String> NameArray = new ArrayList<>();
        ScoreList.add(stringToInt(a));
        ScoreList.add(stringToInt("97898798"));
        for (Integer i:ScoreList) {
            System.out.println(i);
        }*//*
    }*/
}
