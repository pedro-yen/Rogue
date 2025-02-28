package pt.upskill.projeto1.objects.Items.Weapon;

import pt.upskill.projeto1.objects.Items.Items;

public abstract class Weapon implements Items {
    private int attackModifier;

    public int getAttackModifier() {
        return attackModifier;
    }
    public abstract void AddPickUpScore();
}
