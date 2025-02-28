package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.objects.Characters.Hero;

public class CheatCodes {

    public static void totallyNotACheat(){
        Hero hero= Hero.getInstance();
        hero.setHp(80);
        hero.setNumbFireball(3);
    }
}
