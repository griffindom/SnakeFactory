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
    private ArrayList<String> inventoryString;

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
        this.inventoryString = new ArrayList<>(Arrays.asList(startingWeapon, "Potion"));
        initAttackValues(startingWeapon);
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

    public int getAttackValue() {
        Random rand = new Random();
        int attack = rand.nextInt((maxAttack - minAttack) + 1) + minAttack;
        System.out.println("Dealt " + attack + " damage to snake.");
        return attack;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nDifficulty: %s\nStarting Weapon: %s\nGold: %d",
                farmerName, difficulty, startingWeapon, totalGold);
    }

}
