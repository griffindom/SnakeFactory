package sample;

public class GameModel {

    private String farmerName;
    private String difficulty;
    private String startingWeapon;
    private int totalGold;
    private DL4tree<RoomController> maze;

    public GameModel() {

    }

    public GameModel(String farmerName, String difficulty, String startingWeapon, int totalGold) {
        this.farmerName = farmerName;
        this.difficulty = difficulty;
        this.startingWeapon = startingWeapon;
        this.totalGold = totalGold;
        this.maze = null;
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

    public DL4tree<RoomController> getMaze() {
        return this.maze;
    }

    public DL4tree<RoomController> createMaze(RoomController initial) {
        LinkedNode<RoomController> initialRoom = new LinkedNode<RoomController>(initial);
        this.maze = new DL4tree<RoomController>(initialRoom, initialRoom);
        return this.maze;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nDifficulty: %s\nStarting Weapon: %s\nGold: %d",
                farmerName, difficulty, startingWeapon, totalGold);
    }

}
