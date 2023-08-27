import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {

        // Error Handling: Invalid data
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Error: Provided data is null.");
        }

        root = rAdd(root, data);
    }

    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {

        // Base case: Found position where to add data
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        }

        // Current value is larger than data value, traverse to left node
        else if ((curr.getData().compareTo(data)) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        }

        // Current value is smaller than data value, traverse to right node
        else if ((curr.getData().compareTo(data)) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }

        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {

        // Error Handling: Invalid data
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Error: Provided data is null.");
        }

        // Error Handling: Invalid data
        if (size == 0) {
            throw new java.lang.IllegalArgumentException("Error: Empty tree.");
        }

        BSTNode<T> returnNode = new BSTNode<T>(null);

        root = rRemove(root, data, returnNode);

        return returnNode.getData();
    }

    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> returnData) {

        // Error Handling: Data doesn't exist in tree
        if (curr == null) {
            throw new NoSuchElementException("Error: Data does not exist in tree.");
        }

        // Current value is larger than data value, traverse to left node
        else if ((curr.getData().compareTo(data)) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, returnData));
        }

        // Current value is smaller than data value, Add left
        else if ((curr.getData().compareTo(data)) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, returnData));
        }

        // Base case: Found position of data value
        else if ((curr.getData().compareTo(data)) == 0) {
            size--;
            returnData.setData(curr.getData());

            // Case 1: Leaf Node
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            }

            // Case 2: One Child Node
            else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            }

            else if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            }

            // Case 3: Two Child Node - successor
            else {
                BSTNode<T> dummyNode = new BSTNode<>(null);
                // Set right nodes
                curr.setRight(removeSuccessor(curr.getRight(), dummyNode));
                // Replace the current node's value with the successor value
                curr.setData(dummyNode.getData());
            }
        }

        return curr;
    }
    
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {

            // Base Case - Find last left node
            if (curr.getLeft() == null) {
                dummy.setData(curr.getData());
                // Removes the current node from the tree,
                // making the right child None or another subtree
                return curr.getRight();
            }

            else {
                // Add nodes
                curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            }

            return curr;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}