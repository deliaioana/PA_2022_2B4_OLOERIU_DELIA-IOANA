import org.apache.commons.lang3.tuple.Pair;

public class Street{
    private String name;
    private Integer length;
    private Pair<Intersection, Intersection> intersectionPair;

    public void setName(String name){
        this.name=name;
    }

    public Street(String name, Integer length){
        this.setName(name);
        this.setLength(length);
        this.initializeIntersectionPair();
    }

    public Street() {
        this.initializeIntersectionPair();
    }

    public void initializeIntersectionPair(){
        intersectionPair = new Pair<Intersection, Intersection>() {
            @Override
            public Intersection getLeft() {
                return null;
            }

            @Override
            public Intersection getRight() {
                return null;
            }

            @Override
            public Intersection setValue(Intersection value) {
                return null;
            }
        };
    }

    public String getName(){
        return this.name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Pair<Intersection, Intersection> getIntersectionPair() {
        return intersectionPair;
    }

    public void setIntersectionPair(Pair<Intersection, Intersection> intersectionPair) {
        this.intersectionPair = intersectionPair;
    }

    @Override
    public String toString(){
        return "\nStreet: " + name +
                ", length: " + length;
    }
}
