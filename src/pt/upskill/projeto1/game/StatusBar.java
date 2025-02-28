package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Characters.Hero;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Black;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Fireball;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Green;
import pt.upskill.projeto1.objects.Tiles.StatusBar.Red;
import pt.upskill.projeto1.objects.Tiles.StatusBar.RedGreen;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatusBar implements Serializable {
    private static final StatusBar INSTANCE = new StatusBar();
    private List<ImageTile> bar; //= new ArrayList<>();


    public static StatusBar getINSTANCE() {
        return INSTANCE;
    }



    public void UpdateBar(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        gui.clearStatus();
        bar = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            bar.add(new Black(new Position(i,0)));
        }


        UpdateFireball();
        UpdateHeroHp();
        UpdateInventory();

        gui.newStatusImages(bar);
    }
    public void UpdateFireball(){
        Hero hero = Hero.getInstance();
        for (int i = 0; i < hero.getNumbFireball(); i++) {
            bar.add(new Fireball(new Position(i,0)));
        }
    }
    public void UpdateHeroHp(){
        Hero hero = Hero.getInstance();
        int g = 0; //quantas barras de cada cor
        int r_g = 0;
        int r = 0;
        //int i=3; // posição inicial do vetor hp
        if (hero.getHp() == 80) {
            g = 4;
            r_g = 0;
            r = 0;
        } else if (70 <= hero.getHp() && hero.getHp() < 80) {
            g = 3;
            r_g = 1;
            r = 0;
        } else if (60 <= hero.getHp() && hero.getHp() < 70) {
            g = 3;
            r_g = 0;
            r = 1;
        } else if (50 <= hero.getHp() && hero.getHp() < 60) {
            g = 2;
            r_g = 1;
            r = 1;
        } else if (40 <= hero.getHp() && hero.getHp() < 50) {
            g = 2;
            r_g = 0;
            r = 2;
         } else if (30 <= hero.getHp() && hero.getHp() < 40) {
            g = 1;
            r_g = 1;
            r = 2;
        } else if (20 <= hero.getHp() && hero.getHp() < 30) {
            g = 1;
            r_g = 0;
            r = 3;
        } else if (10 <= hero.getHp() && hero.getHp() < 20) {
            g = 0;
            r_g = 1;
            r = 3;
        } else if (0 < hero.getHp() && hero.getHp() < 10) {
            g = 0;
            r_g = 0;
            r = 4;
        }
        for (int j = 3; j < (g + 3); j++) {
            bar.add(new Green(new Position(j,0)));
        }
        for (int j = (g + 3); j < (3 + g + r_g); j++) {
            bar.add(new RedGreen(new Position(j,0)));
        }
        for (int j = (3 + g + r_g); j < 7; j++) {
            bar.add(new Red(new Position(j,0)));
        }
    }



    public void UpdateInventory() {
        /*for (ImageTile i:bar) {
            if (i instanceof Items){
                bar.remove(i);
            }
        }*/
        //remover todos os itens para ter uma lista limpa

        Hero hero = Hero.getInstance();
        // falta adicionar dizer se é para adicionar ou remover
        for (int i = 0; i < hero.getItems().length; i++) {
            if (hero.getItems()[i] != null) {
                hero.getItems()[i].setPosition(new Position(i + 7, 0));
                bar.add(hero.getItems()[i]);
            }
        }

    }

    public List<ImageTile> getBar(){
        return this.bar;
    }

    public void clearImages(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        for (int i=0;i< bar.size();i++){
            if (bar.get(i)  instanceof Black){
                // do nothing
            } else {

                gui.removeStatusImage(bar.get(i));
                bar.remove(i);

            }
        }
    }



}
