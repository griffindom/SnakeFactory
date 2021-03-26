package sample;

public class GreenSnake extends Mob {
    public GreenSnake() {
        attackAbility = new GreenAttack();
        health = 10; //or any value
    }
}
