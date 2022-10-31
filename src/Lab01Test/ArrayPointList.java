package Lab01Test;

import java.awt.*;

public class ArrayPointList implements PointList {

    static final int MAX_SIZE = 100;

    private Point[] array;
    private int cursor;
    private int size;

    public ArrayPointList(int size) {
        this.size = 0;
        this.array = new Point[size];
        this.cursor = 0;
    }

    public ArrayPointList() {
        this.size = 0;
        this.array = new Point[MAX_SIZE];
        this.cursor = 0;
    }


    @Override
    public void append(Point newPoint) {
        this.array[size] = newPoint;
        this.size++;
        this.cursor = size - 1;
    }

    @Override
    public void clear() {
        size = 0;
        cursor = 0;
        array[0] = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()) return false;
        this.cursor = 0;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if (isEmpty()) return false;
        this.cursor = size - 1;
        return true;
    }

    @Override
    public boolean goToNext() {
        if (array[cursor] == null || cursor == size - 1) return false;
        this.cursor++;
        return true;
    }

    @Override
    public boolean goToPrior() {
        if (this.cursor == 0) return false;
        this.cursor--;
        return true;
    }

    @Override
    public Point getCursor() {
        if (isEmpty()) return null;
        return new Point(this.array[cursor]);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty List";
        String string1 = "";
        for (int i = 0; i < size; i++) {
            string1 += array[i].toString() + "\n";
        }
        return string1;

    }
}
