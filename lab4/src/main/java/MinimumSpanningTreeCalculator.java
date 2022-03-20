import java.util.*;

public class MinimumSpanningTreeCalculator {
    private Map<Intersection, Integer> intersectionMap = new HashMap<>();
    private List<Street> streets = new ArrayList<>();
    private Set<Street> MinimumSpanningTreeStreets = new HashSet<>();

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = new ArrayList<>(streets);
    }


    public Map<Intersection, Integer> getIntersectionMap() {
        return intersectionMap;
    }

    public void setIntersectionMap(Set<Intersection> intersections) {
        int counter = 0;
        for(Intersection intersection : intersections){
            intersectionMap.put(intersection, ++counter);
        }
    }

    public void sortStreets(){
        Comparator<Street> streetComparator = Comparator.comparing(Street::getLength);
        streets.sort(streetComparator);
    }

    public void computeMinimumSpanningTree(){
        this.sortStreets();
        int numberOfStreetsInTree = 0;

        for(Street street : streets){
            if(!intersectionMap.get(street.getIntersectionPair().getFirst()).
                    equals(intersectionMap.get(street.getIntersectionPair().getSecond()))){

                uniteIntersections(street.getIntersectionPair().getFirst(),
                        street.getIntersectionPair().getSecond());

                ++numberOfStreetsInTree;
                addStreetToSet(street);
                if(numberOfStreetsInTree == intersectionMap.size()-1)
                    break;
            }
        }
    }

    public void addStreetToSet(Street street){
        MinimumSpanningTreeStreets.add(street);
    }

    public int getMinimumSpanningTreeValue(){
        computeMinimumSpanningTree();

        int totalCost = 0;
        for(Street street : MinimumSpanningTreeStreets){
            totalCost += street.getLength();
        }
        return totalCost;
    }

    public void assignValue(Intersection intersection, Integer value){
        intersectionMap.put(intersection, value);
    }

    public void uniteIntersections(Intersection intersection1, Intersection intersection2){
        Integer value1 = intersectionMap.get(intersection1);
        Integer value2 = intersectionMap.get(intersection2);

        intersectionMap.keySet().stream().
                filter(intersection -> intersectionMap.get(intersection).equals(value2)).
                forEach(intersection -> assignValue(intersection, value1));
    }

    public Set<Street> getMinimumSpanningTreeStreets() {
        return MinimumSpanningTreeStreets;
    }

    public void setMinimumSpanningTreeStreets(Set<Street> minimumSpanningTreeStreets) {
        this.MinimumSpanningTreeStreets = minimumSpanningTreeStreets;
    }
}
