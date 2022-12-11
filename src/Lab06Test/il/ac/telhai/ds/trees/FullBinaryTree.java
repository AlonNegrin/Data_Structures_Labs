package Lab06Test.il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> {
    public FullBinaryTree(T key) {
        super(key);
    }

    public FullBinaryTree(T key, FullBinaryTree<T> left, FullBinaryTree<T> right) {
        super(key, left, right);
        if ((left == null || right == null) && left != right) {
            throw new IllegalArgumentException("invalid input for full binary tree");
        }
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if (getRight() == null && left == null) {
            return;
        }
        if (!(left instanceof FullBinaryTree<T>)) {
            throw new IllegalArgumentException("can only add FullBinaryTree nodes.");
        }

        if (getRight() == null) {
            throw new RuntimeException("you cant add a node if right is null");
        }
        super.setLeft(left);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (getLeft() == null && right == null) {
            return;
        }
        if (!(right instanceof FullBinaryTree<T>)) {
            throw new IllegalArgumentException("can only add FullBinaryTree nodes.");
        }

        if (getLeft() == null) {
            throw new RuntimeException("you cant add a node if left is null");
        }
        super.setRight(right);
    }
}
