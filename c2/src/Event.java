public class Event {
    private int size;
    private int start;
    private int end;
    private String name = "";


    public Event(String name, int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setStart(int start){
        this.start = start;
    }

    public void setEnd(int end){
        this.end = end;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public void printEvent(){
        System.out.println(this);
    }

    public String toString() {
        return "Event: name=" + this.name + ", size=" + this.size + ", start="
                + this.start + ", end=" + this.end;
    }
}
