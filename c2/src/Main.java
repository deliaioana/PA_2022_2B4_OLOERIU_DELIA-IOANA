public class Main {
    public static void main(String[] args) {

        //enum Types {lab, lecture_hall, computer_lab}
        //private Types type;


        Room r1 = new Room("401", "lab", 30);
        r1.printRoom();
        Room r2 = new Room("403", "lab", 30);
        r2.printRoom();
        Room r3 = new Room("405", "lab", 30);
        r3.printRoom();
        Room r4 = new Room("309", "lecture_hall", 100);
        r4.printRoom();

        Event e1 = new Event("C1", 100, 8, 10);
        e1.printEvent();
        Event e2 = new Event("C2", 100, 10, 12);
        e2.printEvent();
        Event e3 = new Event("L1", 30, 8, 10);
        e3.printEvent();
        Event e4 = new Event("L2", 30, 8, 10);
        e4.printEvent();
        Event e5 = new Event("L3", 30, 10, 12);
        e5.printEvent();
    }

}


