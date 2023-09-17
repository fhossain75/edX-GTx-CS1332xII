import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST2<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This should be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
// Iterative method
//        BSTNode<T> curr = root;
//        while (curr != null) {
//            // Current value equals input data
//            if ((curr.getData().compareTo(data)) == 0) {
//                return true;
//            }
//
//            // Current node is smaller than input data
//            else if ((curr.getData().compareTo(data)) < 0) {
//                curr = curr.getRight();
//            }
//
//            // Current node is smaller than input data
//            else if ((curr.getData().compareTo(data)) > 0) {
//                curr = curr.getLeft();
//            }
//        }
//        // Input data not found in tree
//        return false;

        return rContains(root, data);
    }

    private boolean rContains(BSTNode<T> curr, T data) {

        // Recursive method
        if (curr == null) {
            return false;
        }

        // Current value equals input data
        else if ((curr.getData().compareTo(data)) == 0) {
            return true;
        }

        // Current node is smaller than input data
        else if ((curr.getData().compareTo(data)) < 0) {
            return rContains(curr.getRight(), data);
        }

        // Current node is smaller than input data
        else {
            return rContains(curr.getLeft(), data);
        }
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
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {

        BSTNode<T> removedNode = new BSTNode<>(null);
        root = rRemove(root, data, removedNode);

        return removedNode.getData();
    }

    // Rewriting binary tree to remove input node
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> removedNode) {

        // Data not found
        if (curr == null) {
            throw new NoSuchElementException("Error: Data does not exist in tree.");
        }

        // Current node is smaller than input data
        else if ((curr.getData().compareTo(data)) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, removedNode));
        }

        // Current node is larger than input data
        else if ((curr.getData().compareTo(data)) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, removedNode));
        }

        // Data found
        else {
            // Decrement size
            size--;

            // Store data of node to be removed
            removedNode.setData(curr.getData());

            // Case 1 - Node containing data is a leaf node
            if (curr.getLeft() == null && curr.getRight() == null) {
                // Removal: Set current to null
                return null;
            }

            // Case 2.1 - Node containing data has one child (left)
            else if (curr.getLeft() != null && curr.getRight() == null) {
                // Removal: Replace current with left node
                return curr.getLeft();
            }
            // Case 2.2 - Node containing data has one child (right)
            else if (curr.getLeft() == null && curr.getRight() != null) {
                // Removal: Replace current with right node
                return curr.getRight();
            }

            // Case 3 - Node containing data has two children
            else {
                BSTNode<T> dummy = new BSTNode<>(null);
                // Set nodes right of current and remove successor
                curr.setRight(removeSuccessor(curr.getRight(), dummy));
                // Replace (not remove): Replace current node with successor's data
                curr.setData(dummy.getData());
            }
        }

        return curr;
    }

    // Get the smallest node of succeeding node - Step right, the far left as possible
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {

        // Base case: Successor found
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            // Remove successor by setting to null or right subtree
            return curr.getRight();
        }
        else {
            // Assign next node
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