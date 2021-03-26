package sample;

public class DaggerHero extends Mob {
    public DaggerHero() {
        attackAbility = new DaggerAttack();
        health = 100;
    }
}
