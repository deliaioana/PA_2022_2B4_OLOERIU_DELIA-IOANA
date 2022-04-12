package usefulComponents;

public class PlayerIDGenerator {
    private int id = 0;

    public int getNewID(){
        ++id;
        return id;
    }
}
