package Lab08Test.il.ac.telhai.ds.trees;


public class AVLTree<T extends Comparable<T>> {

    int height;
    T value;
    AVLTree<T> right;
    AVLTree<T> left;

    public AVLTree(T value) {
        this.value = value;
        height = 0;
        right = null;
        left = null;
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if (value.compareTo(this.value) < 0) {
            if(left != null){
                left = left.add(value);
                updateHeight();
                if(getBalanceFactor() == 2){
                    if(left.getBalanceFactor() >= 0) return rotateLeft(this);
                    return rotateLeftRight(this);
                }
                return this;
            }
            left = new AVLTree<>(value);
            updateHeight();
            return this;
        }


        if(right != null){
            right = right.add(value);
            updateHeight();
            if(getBalanceFactor() == -2){
                if(right.getBalanceFactor() <= 0) return rotateRight(this);
                return rotateRightLeft(this);
            }
            return this;
        }
        right = new AVLTree<>(value);
        updateHeight();
        return this;

    }

    private void updateHeight() {
        int lh = left != null ? left.height : -1;
        int rh = right != null ? right.height : -1;
        height = Math.max(lh, rh) +1;
    }


    private int getBalanceFactor() {
        int leftHeight = left != null ? left.height : -1;
        int rightHeight = right != null ? right.height : -1;
        return leftHeight - rightHeight;
    }

    private AVLTree<T> rotateLeft(AVLTree<T> root){
        AVLTree<T> newR = root.left;
        AVLTree<T> tmp = root.left.right;
        root.left.right = root;
        root.left = tmp;
        newR.right.updateHeight();
        newR.left.updateHeight();
        newR.updateHeight();
        return newR;
    }

    private AVLTree<T> rotateRight(AVLTree<T> root) {
        AVLTree<T> newR = root.right;
        AVLTree<T> tmp = root.right.left;
        root.right.left = root;
        root.right = tmp;
        newR.left.updateHeight();
        newR.right.updateHeight();
        newR.updateHeight();
        return newR;
    }

    private AVLTree<T> rotateLeftRight(AVLTree<T> root){
        AVLTree<T> left = root.left;
        AVLTree<T> leftRight = root.left.right;
        left.right = leftRight.left;
        leftRight.left = left;
        root.left = leftRight;
        return rotateLeft(root);
    }

    private AVLTree<T> rotateRightLeft(AVLTree<T> root){
        AVLTree<T> right = root.right;
        AVLTree<T> rightLeft = root.right.left;
        right.left = rightLeft.right;
        rightLeft.right = right;
        root.right = rightLeft;
        return rotateRight(root);
    }


    //return the value in this node
    public T getValue() {
        return value;
    }

    //return the left subTree of this node
    public AVLTree<T> getLeft() {
        return left;
    }

    //return the right subTree of this node
    public AVLTree<T> getRight() {
        return right;
    }
}
