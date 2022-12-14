package Lab05Test.stack;

public interface Stack<T> {
    void push(T t);
    T pop();
    T top();
    boolean isEmpty();
}
