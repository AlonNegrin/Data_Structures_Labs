package Lab05Test.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Objects;

public class EvaluatePostfix {

    private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
    private static Stack<Double> myStack = new DLinkedListStack<Double>();

    public static void invalid() {
        System.err.println(tokenizer);
        System.err.println(myStack);
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');

        int token = tokenizer.nextToken();
        Double a;
        Double b;
        while (token != StreamTokenizer.TT_EOF && !Objects.equals(tokenizer.sval, "quit")) {

            switch (token) {
                case StreamTokenizer.TT_NUMBER -> myStack.push(tokenizer.nval);
                case '+' -> {
                    b = myStack.pop();
                    a = myStack.pop();
                    if (a == null || b == null) {
                        invalid();
                    } else myStack.push(a + b);
                }
                case '-' -> {
                    b = myStack.pop();
                    a = myStack.pop();
                    if (a == null || b == null) {
                        invalid();
                    } else myStack.push(a - b);
                }
                case '*' -> {
                    b = myStack.pop();
                    a = myStack.pop();
                    if (a == null || b == null) {
                        invalid();
                    } else myStack.push(a * b);
                }
                case '/' -> {
                    b = myStack.pop();
                    a = myStack.pop();
                    if (a == null || b == null) {
                        invalid();
                    } else myStack.push(a / b);
                }
                default -> invalid();
            }
            token = tokenizer.nextToken();
        }
        if (myStack.isEmpty()) invalid();
        Double result = myStack.pop();
        if(myStack.isEmpty()) System.out.println(result);
        else invalid();
        //TODO add your code here.

    }

}
