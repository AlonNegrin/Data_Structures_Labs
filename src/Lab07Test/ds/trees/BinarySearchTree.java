package Lab07Test.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> {

    BstNode root;
    int size;

    // Binary Search Tree Node
    class BstNode {
        T val;
        BstNode left, right;

        public BstNode(T val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public enum Direction {
        LEFT, RIGHT
    }

    // Returns the val given a path from the root.
    // Used for testing. DO NOT DELETE.
    public T getValInPath(Direction... direction) {
        BstNode node = root;
        for (Direction d : direction) {
            if (d.equals(Direction.LEFT) && node.left != null)
                node = node.left;
            else if (d.equals(Direction.RIGHT) && node.right != null)
                node = node.right;
            else
                return null;
        }
        return node.val;
    }

    /**
     * Constructs an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * returns the number of elements in the tree
     *
     * @param val
     */
    public int size() {
        return size;
    }


    /**
     * Adds the object value to the tree as a leaf according to the parameter.
     *
     * @param val
     * @return True, if the element was added. Otherwise false.
     */
    public boolean add(T val) {
        if (contains(val)) return false;
        root = add(root, val);
        size++;
        return true;
    }

    private BstNode add(BstNode node, T val) {
        if (node == null) {
            return new BstNode(val);
        }
        if (node.val.compareTo(val) < 0) node.right = add(node.right, val);
        if (node.val.compareTo(val) > 0) node.left = add(node.left, val);

        return node;
    }

    /**
     * Removes the object in the tree which is equal to the parameter.
     * Nothing is done if not found
     *
     * @param val: the object to be looked for in the tree
     * @return True, if the element was removed. Otherwise false.
     */
    public boolean remove(T val) {
        int nowSize = size();
        if (!contains(val)) return false;
        root = remove(root, val);
        return nowSize != size();
    }

    private BstNode remove(BstNode node, T val) {
        if (node == null) return null;

        if (node.val.compareTo(val) == 0) {
            if (node.left != null && node.right != null) {
                node.val = findSucssesor(node.right);
                node.right =remove(node.right, node.val);
                return node;
            }
            size--;
            if (node.right != null)
                return node.right;

            if (node.left != null)
                return node.left;
            return null;
        }
        if (node.val.compareTo(val) > 0) {
            if (node.left != null){
                node.left = remove(node.left,val);
                return node;
            }
        }
        node.right = remove(node.right, val);
        return node;
    }

        /**
         * Looks for an object which is equal to the parameter.
         *
         * @param val: the object to be looked for in the tree
         * @return true if the tree contains this object. Otherwise, return false.
         */
        public boolean contains (T val){
            return contains(root, val);
        }

        private boolean contains (BstNode node, T val){
            if (node != null) {
                if (val.compareTo(node.val) == 0) return true;
                if (val.compareTo(node.val) > 0) return contains(node.right, val);
                if (val.compareTo(node.val) < 0) return contains(node.left, val);
            }
            return false;
        }

        /**
         * Looks for the minimal object in the tree, which is greater than or equal to
         * the parameter.
         *
         * @param val: the object to be looked for in the tree
         * @return a reference to the found object.
         */
        public T findGe (T val){
            if (contains(val)) return val;
            if (root == null) return null;
            T result;

            if(root.val.compareTo(val) > 0){
                result = findGe(val,root,Direction.LEFT);
            }
            else{
                result = findGe(val,root,Direction.RIGHT);
            }
            if(result.compareTo(val) < 0) return null;
            return result;
        }

        private T findGe(T val, BstNode node,Direction direction){
            if(node == null) return null;
            T result;
            if (node.val.compareTo(val) > 0){
                if(direction!= Direction.LEFT) return null;
                result = findGe(val,node.left,direction);
                if(result != null)
                    return result;

                return node.val;
            }
            if(direction != Direction.RIGHT) return null;
            result = findGe(val,node.right,direction);
            if(result != null) return result;
            return node.val;
        }

        private T findSucssesor (BstNode root)
        {
            if (root.left == null) return root.val;
            return findSucssesor(root.left);

        }
    }

