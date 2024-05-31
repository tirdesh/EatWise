package model.util.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0)
            throw new IllegalArgumentException("Initial capacity must be positive");
        array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        ensureCapacity();

        // Shift elements to the right to make space for the new element
        System.arraycopy(array, index, array, index + 1, size - index);

        // Insert the new element at the specified index
        array[index] = element;
        size++;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        return array[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element))
                return i;
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return array[0];
    }

    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return array[size - 1];
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public void addLast(T element) {
        add(size, element);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public T set(int index, T element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        T replaced = array[index];
        array[index] = element;
        return replaced;
    }
    
    public Iterator<T> iterator() {
    	return null;
    }
}
