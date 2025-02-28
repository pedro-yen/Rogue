package pt.upskill.projeto1.objects.Characters;

import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.io.Serializable;

public abstract class Enemy implements Character{
    private int killScore;
    public abstract int getKillscore();
    public abstract Vector2D padraoMov ();

    public abstract void KillScore();
    public abstract void addHP(int hp);
}
