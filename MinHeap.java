import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {

        // Error handling: Invalid input
        if (data == null) {
            throw new IllegalArgumentException("Error: Invalid input.");
        }

        // Increment size
        size ++;

        // Check if resizing is needed
        if (backingArray.length < size) {
            // Init new array
            T[] newBackingArray = (T[]) new Comparable[backingArray.length * 2 + 1];

            // Copy elements to new backing array
            for (int i = 0; i < backingArray.length; i++) {
                newBackingArray[i] = backingArray[i];
            }

            // Update backing array
            backingArray = newBackingArray;
        }

        // Add element to back of array
        backingArray[size] = data;

        // Unheap
        int currIndex = size;
        int parentIndex = (int) Math.floor( (double) (size - 1) / 2);

        while (parentIndex > 1) {

            T curr = backingArray[currIndex];
            T parent = backingArray[parentIndex];

            // Check if node is greater than parent node
            if (curr.compareTo(parent) > 0) {

                // Swap nodes
                backingArray[currIndex] = parent;
                backingArray[parentIndex] = curr;

                // Calculate index of next parent
                parentIndex = (int) Math.floor( (double) (currIndex - 1) / 2);
            }
            else {
                break;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return null;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}