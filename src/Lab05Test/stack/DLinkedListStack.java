package Lab05Test.stack;

public class DLinkedListStack<T> implements Stack<T> {

    private DLinkedList<T> ll;
    public int size;

    public DLinkedListStack() {
        this.ll = new DLinkedList<>();
        this.size = 0;
    }

    @Override
    public void push(T t) {
        ll.goToEnd();
        ll.insert(t);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;
        ll.goToEnd();
        size--;
        return ll.remove();
    }

    @Override
    public T top() {
        if (isEmpty()) return null;
        ll.goToEnd();
        return ll.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }


    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        ll.goToEnd();
        StringBuilder string = new StringBuilder();
        string.append("[");
        T element = ll.getCursor();
        while (element != null) {
            string.append(ll.getCursor().toString());
            if (ll.getPrev() != null) {
                string.append(", ");
                ll.getNext();
            }
            element = ll.getPrev();
        }
        string.append("]");
        return string.toString();
    }
}
