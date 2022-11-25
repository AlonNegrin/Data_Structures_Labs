package Lab04Test;

public class SparseMatrix<T> implements Matrix<T> {

    private DLinkedList<SparseMatrixEntry> ll;
    private T defaultValue;
    private boolean transpose;
    private int size;

    public SparseMatrix(T defaultValue) {
        this(MAX_SIZE, defaultValue);
    }

    public SparseMatrix(int size, T defaultValue) {
        this.ll = new DLinkedList<>();
        this.defaultValue = defaultValue;
        this.transpose = false;
        this.size = size;
    }


    @Override
    public T get(int i, int j) {
        if (notLegal(i, j)) throw new IllegalArgumentException();
        int temp;
        if (transpose) {
            temp = i;
            i = j;
            j = temp;
        }
        ll.goToBeginning();
        SparseMatrixEntry entry = ll.getCursor();
        while (entry != null) {
            if (entry.getRow() == i && entry.getCol() == j) {
                return entry.getValue();
            }
            entry = ll.getNext();
        }
        return defaultValue;
    }


    @Override
    public void set(int row, int col, T element) {
        if (notLegal(row, col)) throw new IllegalArgumentException();
        int temp;
        if (transpose) {
            temp = row;
            row = col;
            col = temp;
        }
        ll.goToBeginning();
        SparseMatrixEntry entry = ll.getCursor();
        while (entry != null) {
            if (entry.getRow() == row && entry.getCol() == col) {
                if (element == defaultValue) {
                    ll.remove(entry);
                    return;
                }
                entry.setValue(element);
                return;
            }
            entry = ll.getNext();
        }
        entry = new SparseMatrixEntry(row, col, element);
        ll.insert(entry);


    }

    @Override
    public void transpose() {
        transpose = !transpose;
    }

    private boolean notLegal(int i, int j) {
        return (i < 1 || j < 1 || i > size || j > size);
    }


    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int row) {
            if (row > 0) this.row = row;
        }

        public int getCol() {
            return this.col;
        }

        public void setCol(int col) {
            if (col > 0) this.col = col;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T newVal) {
            this.value = newVal;
        }


    }
}
