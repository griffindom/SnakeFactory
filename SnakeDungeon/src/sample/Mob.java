package sample;
// abstract class for the different monsters. I don't know how to make them move so i didnt include that

public abstract class Mob {
    int health;
    AttackAbility attackAbility;
    public Mob() {}

    public void performAttack() {
        attackAbility.attack();
    }
}
