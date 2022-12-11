package Lab06Test.il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T> {
    private BinaryTreeI<T> left;
    private BinaryTreeI<T> right;
    private T key;

    public BinaryTree(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public BinaryTree(T key, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        this.left = left;
        this.right = right;
        this.key = key;
    }

    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return key;
    }

    @Override
    public void setValue(T value) {
        this.key = value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int height() {
        int r = 0, l = 0, max = 0;
        if (isLeaf()) return 0;
        if (left != null) l = left.height();
        if (right != null) r = right.height();
        max = Math.max(r, l);
        return max + 1;
    }

    @Override
    public int size() {
        if (isLeaf()) return 1;
        else {
            if (left == null) return right.size() + 1;
            else if (right == null) return left.size() + 1;
            else return left.size() + 1 + right.size();
        }
    }

    @Override
    public void clear() {
        right = null;
        left = null;

    }

    @Override
    public String preOrder() {
        return preOrder(" ", " ");
    }

    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder sb = new StringBuilder("");
        sb.append(separationBeforeVal);
        sb.append(key);
        sb.append(separationAfterVal);

        if (left != null) sb.append(left.preOrder(separationBeforeVal, separationAfterVal));
        if (right != null) sb.append(right.preOrder(separationBeforeVal, separationAfterVal));

        return sb.toString();
    }

    @Override
    public String inOrder() {
        return inOrder(" ", " ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder sb = new StringBuilder("");
        if (left != null) sb.append(left.inOrder(separationBeforeVal, separationAfterVal));

        sb.append(separationBeforeVal);
        sb.append(key);
        sb.append(separationAfterVal);

        if (right != null) sb.append(right.inOrder(separationBeforeVal, separationAfterVal));
        return sb.toString();
    }

    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }

    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder sb = new StringBuilder("");

        if (left != null) sb.append(left.postOrder(separationBeforeVal, separationAfterVal));

        if (right != null) sb.append(right.postOrder(separationBeforeVal, separationAfterVal));

        sb.append(separationBeforeVal);
        sb.append(key);
        sb.append(separationAfterVal);
        return sb.toString();
    }
}
