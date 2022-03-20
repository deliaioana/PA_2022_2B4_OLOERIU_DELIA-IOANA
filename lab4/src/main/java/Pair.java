public class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.setFirst(first);
        this.setSecond(second);
    }

    public Pair() {

    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}