import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {

        // Error Handling: Invalid input
        if (key == null || value == null) {
            throw new IllegalArgumentException("Error: Provided input(s) is null.");
        }

        // Check if resizing is necessary
        double newLoadFactor = (double) (size + 1) / table.length;

        if (newLoadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }

        // Calculate key compressed hashcode
        int index = Math.abs(key.hashCode() % table.length);

        // Case 1: Index is null
        if (table[index] == null) {
            table[index] = new ExternalChainingMapEntry<>(key, value);
            return null;
        }

        // Case 2: Collision

        // Init variables
        ExternalChainingMapEntry<K, V> head = table[index];
        ExternalChainingMapEntry<K, V> curr = head;
        boolean isDuplicate = false;
        V oldValue = null;

        // Check if key is duplicate
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                isDuplicate = true;
                oldValue = curr.getValue();
                break;
            }
            curr = curr.getNext();
        }

        // Case 2.1: Duplicate key
        if (isDuplicate) {
            // Replace key's current value with input value
            curr.setValue(value);
        }

        // Case 2.2: Non-duplicate key
        else {
            // Assign new head
            table[index] = new ExternalChainingMapEntry<>(key, value, head);
        }

        // Increment size
        size ++;

        return oldValue;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {

        // Error Handling: Invalid input
        if (key == null) {
            throw new IllegalArgumentException("Error: Provided input(s) is null.");
        }

        // Calculate key compressed hashcode
        int index = Math.abs(key.hashCode() % table.length);

        // Init variables
        ExternalChainingMapEntry<K, V> prev = null;
        ExternalChainingMapEntry<K, V> curr = table[index];

        // Iterate through linked list to find key
        while (curr != null) {
            // Key Found
            if (curr.getKey().equals(key)) {

                // Store old value
                V value = curr.getValue();

                // Edge case: key is head
                if (prev == null) {
                    table[index] = curr.getNext(); // Remove head
                }

                else {
                    prev.setNext(curr.getNext()); // Remove key
                }

                size --;
                return value;
            }
            // Take step forward
            prev = curr;
            curr = curr.getNext();
        }

        // Error Handling: Key does not exist in map
        throw new NoSuchElementException("Error: Provided key does not exist in map.");
    }


    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {

        // Init new resized table
        ExternalChainingMapEntry<K, V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        // Populate new table with values from old table
        for (ExternalChainingMapEntry<K, V> entry: table) {

            if (entry != null) {

                // Get entry key
                K key = entry.getKey();

                // Calculate key compressed hashcode (index)
                int index = Math.abs(key.hashCode() % length);

                // Add key, value to new table
                newTable[index] = new ExternalChainingMapEntry<>(key, entry.getValue());
            }
        }

        // Assign table variable to new table
        table = newTable;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

