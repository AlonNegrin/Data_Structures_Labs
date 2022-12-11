package Lab06Test.il.ac.telhai.ds.trees;

import java.io.IOException;
import java.io.StreamTokenizer;

public class ExpressionTree extends FullBinaryTree<String> {
    public ExpressionTree(String key) {
        super(key);
    }

    public ExpressionTree(String key, FullBinaryTree<String> left, FullBinaryTree<String> right) {
        super(key, left, right);
    }

    /*
     * Read the stream tokenizer until EOF and construct
     *  the expression tree corresponding to it.
     * The input contains a prefix expression.
     */
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        int token = tokenizer.nextToken();
        if (token == StreamTokenizer.TT_EOF) return null;
        if (token == StreamTokenizer.TT_NUMBER) {
            return new ExpressionTree((int)(tokenizer.nval) + "");
        }
        return new ExpressionTree((char)token + "", createTree(tokenizer), createTree(tokenizer));
    }

    /*
     * Returns the infix expression corresponding to the current tree (*)
     */
    public String infix() {
        return inOrder(" ", " ");
    }

    /*
     * Returns the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return preOrder();
    }

    /*
     * Evaluates the expression corresponding to the current tree
     * and returns its value
     */
    public double evaluate() {
        return evaluate(this);
    }

    private double evaluate(BinaryTreeI<String> tree) {
        return switch (tree.getValue()) {
            case "+" -> evaluate(tree.getLeft()) + evaluate(tree.getRight());
            case "-" -> evaluate(tree.getLeft()) - evaluate(tree.getRight());
            case "*" -> evaluate(tree.getLeft()) * evaluate(tree.getRight());
            case "/" -> evaluate(tree.getLeft()) / evaluate(tree.getRight());
            default -> Double.parseDouble(tree.getValue());
        };
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder sb = new StringBuilder();
        if (isLeaf()) return getValue();
        sb.append("(");
        sb.append(getLeft().inOrder(separationBeforeVal, separationAfterVal));
        sb.append(separationBeforeVal);
        sb.append(getValue());
        sb.append(separationAfterVal);
        sb.append(getRight().inOrder(separationBeforeVal, separationAfterVal));
        sb.append(")");

        return sb.toString();
    }
}


