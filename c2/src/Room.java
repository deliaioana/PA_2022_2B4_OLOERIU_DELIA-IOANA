public class Room {
    private int capacity;
    private String name;
    private Types type;

    enum Types {lab, lecture_hall, computer_lab}

    public Room(String name, String type, int capacity) {
        this.capacity = capacity;
        this.type = Types.valueOf(type);
        this.name = name;
    }

    public Types getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printRoom() {
        System.out.println(this);
    }

    public String toString() {
        return "Room: name=" + this.name + ", type=" + this.type + ", capacity=" + this.capacity;
    }
}
