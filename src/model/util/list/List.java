package model.util.list;


public interface List<T> extends Iterable<T> {

    void add(int index, T element);

    void addFirst(T element);

    void addLast(T element);

    T first();

    T get(int index);

    boolean isEmpty();

    T last();

    T remove(int index);

    T removeFirst();

    T removeLast();

    T set(int index, T element);

    int size();
}
