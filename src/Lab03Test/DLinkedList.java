package Lab03Test;

public class DLinkedList<T> implements List<T> {

    private final DNode head;
    private final DNode tail;
    private DNode cursor;

    public DLinkedList() {
        head = new DNode(null);
        tail = new DNode(null);
        head.setNext(tail);
        tail.setPrev(head);
        head.setPrev(null);
        tail.setNext(null);
        cursor = head;
    }

    @Override
    public void insert(T newElement) {
        if (newElement == null) throw new RuntimeException("element is null");
        DNode node = new DNode((newElement));
        if(cursor.next == null){
            return;
        }
        cursor.next.setPrev(node);
        cursor.setNext(node);
        node.setPrev(cursor);
        node.setNext(cursor.next);
        cursor = node;
    }

    @Override
    public T remove() {
        if (head.next == tail) {
            return null;
        }
        cursor.prev.setNext(cursor.next);
        cursor.next.setPrev(cursor.prev);
        DNode node = cursor;
        cursor = cursor.next;
        if (cursor == tail) {
            cursor = head.next;
            return node.getElement();
        }
        return node.getElement();
    }

    @Override
    public T remove(T element) {
        cursor = head;
        while (cursor.getElement() != element) {
            if(cursor.next == null){return null;}
            cursor = cursor.next;
        }
        return remove();
    }

    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        cursor = head;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty() || newElement == null) throw new RuntimeException("list is empty or element is null");
        remove();
        cursor = cursor.prev;
        insert(newElement);
    }

    @Override
    public boolean isEmpty() {
        return (head.next == tail && tail.prev == head);
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()) return false;
        cursor = head.next;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if (isEmpty()) return false;
        cursor = tail.prev;
        return true;
    }

    @Override
    public T getNext() {
        if (cursor.next == tail) {
            return null;
        }
        cursor = cursor.next;
        return cursor.getElement();
    }

    @Override
    public T getPrev() {
        if (cursor.prev == head) {
            return null;
        }
        cursor = cursor.prev;
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        if (isEmpty()) return null;
        return cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        return !isEmpty() && cursor.next != tail;
    }

    @Override
    public boolean hasPrev() {
        return !isEmpty() && cursor.prev != head;
    }

    @Override
    public String toString() {
        return "";
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;

        }
    }
}

