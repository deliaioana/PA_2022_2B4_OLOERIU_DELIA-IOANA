package model;

public class Tile {
    private Character letter;
    private int points;

    Tile (Character letter, int points){
        this.setLetter(letter);
        this.setPoints(points);
    }

    public Tile() {

    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", points=" + points +
                '}';
    }

    public void copyTile(Tile tile) {
        this.setPoints(tile.points);
        this.setLetter(tile.letter);
    }
}
