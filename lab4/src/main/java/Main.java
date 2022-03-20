import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args){

        Intersection[] intersectionArray = IntStream.range(1,10).
                mapToObj(index -> new Intersection("Int " + index)).toArray(Intersection[]::new);

        Integer[] streetLengthsArray = {3,3,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3};

        Street[] streetArray = IntStream.range(1, 17).
                mapToObj(index -> new Street("Str " + index, streetLengthsArray[index - 1])).toArray(Street[]::new);

        intersectionArray[0].addConnection(streetArray[0], intersectionArray[8]);
        intersectionArray[0].addConnection(streetArray[1], intersectionArray[5]);
        intersectionArray[0].addConnection(streetArray[2], intersectionArray[4]);
        intersectionArray[1].addConnection(streetArray[3], intersectionArray[7]);
        intersectionArray[1].addConnection(streetArray[4], intersectionArray[8]);
        intersectionArray[1].addConnection(streetArray[5], intersectionArray[2]);
        intersectionArray[2].addConnection(streetArray[6], intersectionArray[8]);
        intersectionArray[2].addConnection(streetArray[7], intersectionArray[3]);
        intersectionArray[3].addConnection(streetArray[8], intersectionArray[6]);
        intersectionArray[3].addConnection(streetArray[9], intersectionArray[4]);
        intersectionArray[3].addConnection(streetArray[10], intersectionArray[8]);
        intersectionArray[4].addConnection(streetArray[11], intersectionArray[5]);
        intersectionArray[4].addConnection(streetArray[12], intersectionArray[6]);
        intersectionArray[5].addConnection(streetArray[13], intersectionArray[6]);
        intersectionArray[6].addConnection(streetArray[14], intersectionArray[7]);
        intersectionArray[7].addConnection(streetArray[15], intersectionArray[8]);

        LinkedList<Street> streetLinkedList = new LinkedList<>(Arrays.stream(streetArray).toList());
        Set<Intersection> intersectionSet = new HashSet<>(Arrays.stream(intersectionArray).toList());

        System.out.println("------------------------- streets & intersections -------------------------");
        System.out.println("STREETS: \n" + streetLinkedList + '\n');
        System.out.println("INTERSECTIONS: \n" + intersectionSet + '\n');

        /*
          Comparator with lambda expression */
        System.out.println("------------------------- sorted streets -------------------------");
        Comparator<Street> streetComparator = Comparator.comparing(Street::getLength);
        streetLinkedList.sort(streetComparator);

        System.out.println(streetLinkedList);

        /*
          Check if set has duplicates */
        System.out.println("\n------------------------- check duplicates -------------------------");

        boolean hasDuplicates = false;
        for(Intersection intersection1 : intersectionSet){
            int numberOfOccurances = 0;
            for(Intersection intersection2 : intersectionSet)
                if (intersection1 == intersection2)
                    ++numberOfOccurances;
            if(numberOfOccurances != 1)
                hasDuplicates=true;
        }

        if(!hasDuplicates)
            System.out.println("No duplicates found");
        else
            System.out.println("Duplicates found");

        /*
          Compulsory */
        System.out.println("\n------------------------- compulsory -------------------------");
        City city = new City("Jump");
        city.setIntersections(intersectionSet);
        System.out.println(city);

        city.printStreetsLongerThanLength(2);

        System.out.println("\n------------------------- random names -------------------------");

        city.giveRandomNames();
        System.out.println(city);

        System.out.println("\n------------------------- minimum spanning tree -------------------------");

        City city2 = new City("Animal city");

        Intersection[] intersectionArray2 = IntStream.range(1,10).
                mapToObj(index -> new Intersection()).toArray(Intersection[]::new);

        Integer[] streetLengthsArray2 = {4, 8, 8, 11, 7, 4, 2, 9, 14, 10, 2, 1, 6, 7};

        Street[] streetArray2 = IntStream.range(1, 15).
                mapToObj(index -> new Street(streetLengthsArray2[index-1])).toArray(Street[]::new);

        intersectionArray2[0].addConnection(streetArray2[0], intersectionArray2[1]);
        intersectionArray2[0].addConnection(streetArray2[1], intersectionArray2[7]);
        intersectionArray2[1].addConnection(streetArray2[2], intersectionArray2[2]);
        intersectionArray2[1].addConnection(streetArray2[3], intersectionArray2[7]);
        intersectionArray2[2].addConnection(streetArray2[4], intersectionArray2[3]);
        intersectionArray2[2].addConnection(streetArray2[5], intersectionArray2[5]);
        intersectionArray2[2].addConnection(streetArray2[6], intersectionArray2[8]);
        intersectionArray2[3].addConnection(streetArray2[7], intersectionArray2[4]);
        intersectionArray2[3].addConnection(streetArray2[8], intersectionArray2[5]);
        intersectionArray2[4].addConnection(streetArray2[9], intersectionArray2[5]);
        intersectionArray2[5].addConnection(streetArray2[10], intersectionArray2[6]);
        intersectionArray2[6].addConnection(streetArray2[11], intersectionArray2[7]);
        intersectionArray2[6].addConnection(streetArray2[12], intersectionArray2[8]);
        intersectionArray2[7].addConnection(streetArray2[13], intersectionArray2[8]);

        Set<Intersection> intersectionSet2 = new HashSet<>(Arrays.stream(intersectionArray2).toList());
        city2.setIntersections(intersectionSet2);

        city2.giveRandomNames();

        System.out.println("Minimun spanning tree value for '" + city2.getName() + "' is: " +
                city2.MinimumSpanningTreeValue());
        System.out.println(city2.MinimumSpanningTree());
    }
}
