package Lab10Test.il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {

    private T[] data;
    private int length;
    private int size;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(int length) {
        length++;
        data = (T[]) new Comparable[length];
        data[0] = null;
        this.length = length;
        size = 0;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(T[] arr) {
        length = arr.length + 1;
        data = (T[]) new Comparable[length];
        data[0] = null;
        for (int i = 0; i < length; i++) {
            data[i + 1] = arr[i];
        }
        size = length - 1;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos + 1);
    }

    private boolean isLeaf(int pos) {
        return (pos > (size / 2));
    }

    private void swap(int fpos, int spos)
    {
        T tmp;
        tmp = data[fpos];
        data[fpos] = data[spos];
        data[spos] = tmp;
    }

    public boolean isFull() {
        return data[length] != null;
    }

    public boolean isEmpty() {
        return data[1] != null;
    }

    private void siftUp(int index) {
        int parent = index / 2;
        int leftChild = parent * 2;
        int rightChild = parent * 2 + 1;
        int p2l = data[parent].compareTo(data[leftChild]);
        int p2r = data[parent].compareTo(data[rightChild]);

        if (p2l > 0 || p2r > 0) {
            T temp = data[parent];
            int l2r = data[leftChild].compareTo(data[rightChild]);
            if (l2r > 0) {
                data[parent] = data[rightChild];
                data[rightChild] = temp;
            } else {
                data[parent] = data[leftChild];
                data[leftChild] = temp;
            }
            siftUp(parent);
        }
    }

    private void siftDown(int index) {
        int parent = index;
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        int p2l = data[parent].compareTo(data[leftChild]);
        int p2r = data[parent].compareTo(data[rightChild]);
    }

    public void insert(T element) {
        if (!isFull()) {
            data[size + 1] = element;
            siftUp(size + 1);
            size++;
        }
    }

    public T getMin() {
        return data[1];
    }

    public T deleteMin() {
        return data[1];
    }

    /**
     * return a string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        return "hi";
    }
}
