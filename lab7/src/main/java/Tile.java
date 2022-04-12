public class Tile {
    private Character letter;
    private int points;

    Tile (Character letter, int points){
        this.setLetter(letter);
        this.setPoints(points);
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
}
