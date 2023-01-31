package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
    public static final int DEF_MAX_HASH_SIZE = 10;

    private DLinkedList<V>[] table;
    private int size;
    private int sizeOfElements;


    /**
     * constructor
     * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public HashTable() {
        table = new DLinkedList[DEF_MAX_HASH_SIZE];
        for (int i = 0; i < DEF_MAX_HASH_SIZE; i++) {
            table[i] = new DLinkedList<V>();
        }
        size = DEF_MAX_HASH_SIZE;
        sizeOfElements = 0;
    }

    /**
     * constructs a hash-table of size 'hashSize'.
     *
     * @param hashSize, the size of the constructed hash-table.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public HashTable(int hashSize) {
        table = new DLinkedList[hashSize];
        for (int i = 0; i < hashSize; i++) {
            table[i] = new DLinkedList<V>();
        }
        size = hashSize;
        sizeOfElements = 0;
    }

    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {
        int index = hash(val.hashCode());
        if (index < 0) return false;
        return contains(table[index], val);

    }

    public boolean contains(DLinkedList<V> ll, V value) {
        ll.goToBeginning();
        if(ll.isEmpty()) return false;
        if(ll.getCursor().equals(value)) return true;
        while (ll.hasNext()) {
            ll.getNext();
            if (ll.getCursor().equals(value)) return true;
        }
        return false;
    }

    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already existed in the the hash-table, it will not be
     * added again. Return true if the val was added successfully. Otherwise
     * return false.
     */
    public boolean add(V val) {
        if (contains(val)) return false;
        int index = hash(val.hashCode());
        if (index < 0) return false;
        table[index].insert(val);
        sizeOfElements++;
        return true;
    }

    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     * false.
     */
    public boolean remove(V val) {
        if (!contains(val)) return false;
        int index = hash(val.hashCode());
        if (index < 0 ) return false;
        if (table[index].remove(val) != null) {
            sizeOfElements--;
            return true;
        }
        return false;
    }

    /**
     * clear all the data in the hash-table
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            table[i].clear();
        }
        sizeOfElements = 0;
    }

    /**
     * @return true if the hash-table is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return sizeOfElements == 0;
    }

    private int hash(int i) {
        i = Math.abs(i);
        if (size != 0) return i % size;
        return -1;
    }
}