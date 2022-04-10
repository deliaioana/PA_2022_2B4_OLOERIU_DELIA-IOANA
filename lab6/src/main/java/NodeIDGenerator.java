public class NodeIDGenerator {
    private int lastID = 1;
    public int getNewID(){
        ++lastID;
        return lastID-1;
    }
}
