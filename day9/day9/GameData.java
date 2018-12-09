package day9;

public class GameData {
    private int numberMarbles;
    private int numberPlayers;

    public GameData(int numberMarbles, int numberPlayers) {
        this.numberMarbles = numberMarbles;
        this.numberPlayers = numberPlayers;
    }

    public int getNumberMarbles() {
        return numberMarbles;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }
}
