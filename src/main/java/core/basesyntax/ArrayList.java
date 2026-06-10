package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public T[] growIfArrayFull() {
        int oldCapacity = elementData.length;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity == oldCapacity) {
            newCapacity++;
        }

        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void add(T value) {
        elementData = growIfArrayFull();
        elementData[size++] = value;

    }

    @Override
    public void add(T value, int index) {

        if (index == size) {

        } else {
            checkIndex(index);
        }
        elementData = growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        T element = elementData[index];
        return element;

    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // останній = null, size--

        return element;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(elementData[i]);
            }
        }
        throw new NoSuchElementException("No such element found");

    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
