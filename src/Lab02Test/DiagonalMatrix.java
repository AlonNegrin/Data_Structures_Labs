package Lab02Test;


public class DiagonalMatrix implements Matrix {

    private double[] array;
    private boolean transpose;

    //    Precondition: size>0
//  Post condition: Initializes a size*size  diagonal matrix using an array of length 2*size-1.
    DiagonalMatrix(int size) {
        if (size <= 0) {
            throw new RuntimeException("size need to be > 0");
        }
        array = new double[2 * size - 1];
        transpose = false;
    }

    //   Initializes an MAX_SIZE * MAX_SIZE diagonal matrix
    DiagonalMatrix() {
        array = new double[MAX_SIZE * 2 - 1];
        transpose = false;
    }

    @Override
    public double get(int i, int j) {
        if (i < 1 || j > array.length / 2 + 1) throw new RuntimeException("invalid vertices");
        if (transpose) {
            int temp = i;
            i = j;
            j = temp;
        }
        if (i - j >= 0) {
            return array[i - j];
        } else {
            return array[(array.length + (i - j))];
        }
    }

    @Override
    public void set(int i, int j, double x) {
        if (i < 1 || j > array.length * 2 + 1) throw new RuntimeException("invalid vertices");
        if (transpose) {
            int temp = i;
            i = j;
            j = temp;
        }
        if (i - j >= 0) {
            array[i - j] = x;
        } else {
            array[(array.length + (i - j))] = x;
        }

    }

    @Override
    public void transpose() {
        transpose = !transpose;
    }

    @Override
    public Matrix getTranspose() {
        DiagonalMatrix mat = new DiagonalMatrix(array.length);
        mat.transpose = transpose;
        mat.array = array;
        mat.transpose();
        return mat;
    }

    @Override
    public String toString() {
        int len = array.length / 2 + 1;
        StringBuilder string = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                string.append(get(i, j));
                if (j != len) {
                    string.append("\t");
                }
            }
            string.append("\n");

        }
        return string.toString();
    }
}
