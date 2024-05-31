
package model.util.list;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractSequentialList<T> implements List<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public LinkedList() {
        head = new ListNode<>(null);
        tail = new ListNode<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        ListNode<T> node = getNode(index);
        return new LinkedListIterator(node, index);
    }

    @Override
    public int size() {
        return size;
    }

    private ListNode<T> getNode(int index) {
        ListNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current;
    }

    private static class ListNode<E> {
        private E data;
        private ListNode<E> next;
        private ListNode<E> prev;

        public ListNode(E data) {
            this.data = data;
        }
    }

    private class LinkedListIterator implements ListIterator<T> {
        private ListNode<T> next;
        private ListNode<T> lastReturned;
        private int nextIndex;

        public LinkedListIterator(ListNode<T> node, int index) {
            next = node;
            nextIndex = index;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return next != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No such element.");
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return next.prev != head;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("No such element.(previous)");
            }
            next = next.prev;
            lastReturned = next;
            nextIndex--;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.prev.next = lastReturned.next;
            lastReturned.next.prev = lastReturned.prev;
            lastReturned.data = null;
            lastReturned = null;
            size--;
        }

        @Override
        public void set(T e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.data = e;
        }

        @Override
        public void add(T e) {
            if (e == null) {
                throw new NullPointerException();
            }
            ListNode<T> newNode = new ListNode<>(e);
            newNode.prev = next.prev;
            newNode.next = next;
            next.prev.next = newNode;
            next.prev = newNode;
            nextIndex++;
            size++;
            lastReturned = null;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        ListIterator<T> iterator = listIterator(index);
        iterator.add(element);
    }

    @Override
    public void addFirst(T element) {
        add(0, element);
    }

    @Override
    public void addLast(T element) {
        add(size, element);
    }

    @Override
    public T first() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return head.next.data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        ListNode<T> node = getNode(index);
        return node.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T last() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return tail.prev.data;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        ListIterator<T> iterator = listIterator(index);
        T removed = iterator.next();
        iterator.remove();
        return removed;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return remove(size - 1);
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        ListNode<T> node = getNode(index);
        T oldValue = node.data;
        node.data = element;
        return oldValue;
    }
}
