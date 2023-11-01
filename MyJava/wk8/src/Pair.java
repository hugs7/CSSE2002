public class Pair<T, K> {
    private T first;
    private K second;

    public Pair(T one, K two) {
        this.first = one;
        this.second = two;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public String toString() {
        return String.format("(%s,%s)",first.toString(), second.toString());
    }

    public static void main(String[] args) {
        Pair pair = new Pair(1, new Pair<>(1, "hello"));
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }
}