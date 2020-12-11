package utils;

public class Pair<F, S>{

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
        return ((Pair<?, ?>) obj).getFirst().equals(this.getFirst()) && ((Pair<?, ?>) obj).getSecond().equals(this.getSecond());
    }

    public String toString() {
        return "(" + this.first.toString() + ", " + this.second.toString() + ")";
    }
}
