package game.support;

import game.controllers.RoomController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameModel {

    private String farmerName;
    private String difficulty;
    private String startingWeapon;
    private int totalGold;
    private DL4tree<RoomController> maze;
    private int health;
    private int minAttack;
    private int maxAttack;
    private int attackPotionActive;
    private int armorActive;
    private ArrayList<String> inventoryString;
    private int attackDealt;
    private int snakesKilled;

    public GameModel() {

    }

    public GameModel(String farmerName, String difficulty,
                     String startingWeapon, int totalGold, int health) {
        this.farmerName = farmerName;
        this.difficulty = difficulty;
        this.startingWeapon = startingWeapon;
        this.totalGold = totalGold;
        this.maze = null;
        this.health = health;
        this.inventoryString = new ArrayList<>(Arrays.asList(startingWeapon));
        this.attackDealt = 0;
        this.snakesKilled = 0;
        initAttackValues(startingWeapon);
    }

    public void addKill() {
        this.snakesKilled += 1;
    }

    public int getKills() {
        return this.snakesKilled;
    }

    public void addAttackDealt(int damage) {
        this.attackDealt += damage;
    }

    public int getAttackDealt() {
        return this.attackDealt;
    }

    public ArrayList<String> getInventoryString() {
        return this.inventoryString;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setStartingWeapon(String startingWeapon) {
        this.startingWeapon = startingWeapon;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStartingWeapon() {
        return startingWeapon;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public DL4tree<RoomController> getMaze() {
        return this.maze;
    }

    public int getAttackPotionActive() {
        return this.attackPotionActive;
    }

    public void setAttackPotionActive(int clicks) {
        this.attackPotionActive += clicks;
    }

    public void decrementAttackPotion() {
        this.attackPotionActive--;
    }

    public int getArmorActive() {
        return this.armorActive;
    }

    public void setArmorActive(int clicks) {
        this.armorActive += clicks;
    }

    public void decrementArmor() {
        this.armorActive--;
    }

    public int dealDamage(int value) {
        this.health -= value;
        return this.health;
    }

    public DL4tree<RoomController> createMaze(RoomController initial) {
        LinkedNode<RoomController> initialRoom = new LinkedNode<RoomController>(initial);
        this.maze = new DL4tree<RoomController>(initialRoom, initialRoom);
        return this.maze;
    }

    private void initAttackValues(String weapon) {
        switch (weapon) {
        case "Long Sword":
            this.minAttack = 5;
            this.maxAttack = 11;
            break;
        case "Mace":
            this.minAttack = 7;
            this.maxAttack = 9;
            break;
        case "Dagger":
            this.minAttack = 2;
            this.maxAttack = 4;
            break;
        default:
            break;
        }
    }

    public void changeWeapon(String newWeapon) {
        switch (newWeapon) {
        case "Long Sword":
            this.minAttack = 5;
            this.maxAttack = 11;
            break;
        case "Mace":
            this.minAttack = 7;
            this.maxAttack = 9;
            break;
        case "Dagger":
            this.minAttack = 2;
            this.maxAttack = 4;
            break;
        case "Shovel":
            this.minAttack = 1;
            this.maxAttack = 3;
            break;
        case "Steel Dagger":
            this.minAttack = 4;
            this.maxAttack = 6;
            break;
        case "Diamond Sword":
            this.minAttack = 11;
            this.maxAttack = 14;
            break;
        case "Bow":
            this.minAttack = 1;
            this.maxAttack = 7;
            break;
        case "Axe":
            this.minAttack = 6;
            this.maxAttack = 9;
            break;
        default:
            System.out.println("ERROR: Weapon not found.");
            break;
        }
    }

    public void removeItem(String item) {
        Boolean removed = inventoryString.remove(item);
        if (removed) {
            System.out.println("Item successfully removed.");
        } else {
            System.out.println("ERROR: Item not removed.");
        }
    }

    public int getAttackValue() {
        Random rand = new Random();
        int attack = rand.nextInt((maxAttack - minAttack) + 1) + minAttack;
        return attack;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nDifficulty: %s\nStarting Weapon: %s\nGold: %d",
                farmerName, difficulty, startingWeapon, totalGold);
    }

}
