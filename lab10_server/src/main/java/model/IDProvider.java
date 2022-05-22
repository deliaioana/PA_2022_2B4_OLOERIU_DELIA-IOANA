package model;

public class IDProvider {
    static private Integer currentMaxID = 0;

    static public Integer getNewID () {
        ++currentMaxID;
        return currentMaxID;
    }
}
