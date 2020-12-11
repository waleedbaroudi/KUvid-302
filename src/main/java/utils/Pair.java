package utils;

public class Pair<F extends Number, S extends Number> implements Comparable<Pair<F, S>>{

    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second= second;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Pair<F, S>) obj).getFirst().doubleValue() == this.getFirst().doubleValue() && ((Pair<F, S>) obj).getSecond().doubleValue() == this.getSecond().doubleValue();
    }

    @Override
    public int compareTo(Pair<F, S> o) {
        return (int) (this.getFirst().doubleValue() - o.getFirst().doubleValue() + (this.getSecond().doubleValue() - o.getSecond().doubleValue()));
    }

    @Override
    public int hashCode() {
        return (int) Math.sqrt(Math.pow(this.getFirst().doubleValue(), 2) + Math.pow(this.getSecond().doubleValue(), 2));
    }

    public String toString() {
        return "(" + this.first.toString() + ", " + this.second.toString() + ")";

    }
}
