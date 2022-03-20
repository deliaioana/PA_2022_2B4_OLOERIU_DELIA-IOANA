import com.github.javafaker.Faker;

import java.util.*;

public class City {
    private String name;
    private Set<Intersection> intersections;
    private Set<Street> streets = new HashSet<>();

    public City(String name){
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(Set<Intersection> intersections) {
        this.intersections = intersections;
    }

    @Override
    public String toString() {
        return "CITY{" +
                "NAME='" + name + "'\n" +
                "INTERSECTIONS= " + intersections +
                "\nSTREETS= " + this.updateAndGetStreets() +
                '}';
    }

    public Intersection getIntersection(Intersection intersection){
        return this.getIntersections().stream().filter(intersection::equals).findAny().orElse(null);
    }

    public Set<Street> getStreets(){
        return this.streets;
    }

    public void addStreet(Street street){
        this.streets.add(street);
    }

    public Set<Street> updateAndGetStreets() {
        for(Intersection intersection : this.intersections){
            Map<Intersection, Street> connections = intersection.getConnections();
            for (Map.Entry<Intersection,Street> entry : connections.entrySet()){
                this.addStreet(entry.getValue());
            }
        }
        return this.getStreets();
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }

    public void printStreetsLongerThanLength(int length){
        System.out.println("\nStreets longer than " + length + " in '" + this.name + "' city:");

        this.updateAndGetStreets().stream()
                .filter(street -> street.getLength() > length &&
                        street.getIntersectionPair().getFirst().getConnections().size() +
                        street.getIntersectionPair().getSecond().getConnections().size() >= 3)
                .forEach(System.out::print);
    }

    public void assignRandomNameToIntersection(Intersection intersection){
        Faker faker = new Faker();
        intersection.setName(faker.harryPotter().spell());
    }

    public void assignRandomNameToStreet(Street street){
        Faker faker = new Faker();
        street.setName(faker.harryPotter().character());
    }

    public void giveRandomNames(){
        this.getIntersections().stream().forEach(this::assignRandomNameToIntersection);
        this.updateAndGetStreets();
        this.getStreets().stream().forEach(this::assignRandomNameToStreet);
        this.updateAndGetStreets();
    }

    public int MinimumSpanningTreeValue(){
        MinimumSpanningTreeCalculator minimumSpanningTreeCalculator = new MinimumSpanningTreeCalculator();
        minimumSpanningTreeCalculator.setStreets(this.streets);
        minimumSpanningTreeCalculator.setIntersectionMap(this.intersections);
        return minimumSpanningTreeCalculator.getMinimumSpanningTreeValue();
    }

    public Set<Street> MinimumSpanningTree(){
        MinimumSpanningTreeCalculator minimumSpanningTreeCalculator = new MinimumSpanningTreeCalculator();
        minimumSpanningTreeCalculator.setStreets(this.streets);
        minimumSpanningTreeCalculator.setIntersectionMap(this.intersections);
        minimumSpanningTreeCalculator.computeMinimumSpanningTree();
        return minimumSpanningTreeCalculator.getMinimumSpanningTreeStreets();
    }
}
